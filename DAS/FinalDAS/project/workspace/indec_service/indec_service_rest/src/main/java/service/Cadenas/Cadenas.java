package service.Cadenas;

import Exceptions.APIException;
import sdk.clients.factory.CadenaClientFactory;

import db.beans.Cadena;
import db.beans.Configuracion;
import db.Bean;
import db.DaoFactory;

import sdk.ds.Sucursal;
import sdk.contract.CadenaServiceContract;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utilities.GSON;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Cadenas {
    private static final Logger logger = LoggerFactory.getLogger(Cadenas.class);

    public static List<Cadena> obtenerCadenas()
    {
        try {
            List<Bean> beans = DaoFactory.getDao("Cadenas").select(null);
            if(beans != null){
                return Arrays.asList(GSON.transform(beans, Cadena[].class));
            }else{
                throw new APIException("GSON failed -> obtenerCadenas");
            }
        }catch (SQLException ex) {
            throw new APIException("SQLException,metodo -> obtenerCadenas " + ex.getMessage());
        }
    }

    public static List<Configuracion> obtenerConfiguraciones()
    {
        try {
            List<Bean> beans = DaoFactory.getDao("Configuraciones").select(null);
            if(beans != null){
                return Arrays.asList(GSON.transform(beans, Configuracion[].class));

            }else{
                throw new APIException("GSON failed -> obtenerConfiguraciones");
            }
        }catch (SQLException ex) {
            throw new APIException("SQLException,metodo -> obtenerConfiguraciones " + ex.getMessage());
        }
    }

    public static List<Cadena> sucursales
            (final String codigoentidadfederal
                    ,final String localidad
                    ,final List<Configuracion> configuraciones)
    {
        return configuraciones
                .parallelStream()
                .map((config) -> sucursales(codigoentidadfederal,localidad,config))
                .collect(toList());

    }

    public static List<Cadena> preciosSucursales
            (final String codigoentidadfederal
                    ,final String localidad
                    ,final String codigos
                    ,final List<Configuracion> configuraciones)
    {
        return configuraciones
                .parallelStream()
                .map((config) -> preciosSucursales(codigoentidadfederal,localidad,codigos,config))
                .collect(toList());
    }

    public static Cadena sucursales
            (final String codigoentidadfederal
                    ,final String localidad
                    ,final Configuracion configuracion)
    {

        try{
            CadenaServiceContract client = buildClient(configuracion);

            String jsonResponse;

            List<Sucursal> sucursales = client.sucursales(codigoentidadfederal,localidad);

            final boolean haySucursales = sucursales != null && !(sucursales.isEmpty());

            if (haySucursales) {
                return buildCadenaDisponible(configuracion, sucursales);
            } else {
                return buildCadenaNoDisponible(configuracion);
            }

        }catch (Exception ex){
            logger.error( ex.getMessage() );
            return buildCadenaNoDisponible(configuracion);
        }

    }

    public static Cadena preciosSucursales
            (final String codigoentidadfederal
                    ,final String localidad
                    ,final String codigos,final Configuracion configuracion)
    {

        try{
            CadenaServiceContract client = buildClient(configuracion);

            List<Sucursal> sucursales = client.preciosSucursales(codigoentidadfederal,localidad,codigos);

            final boolean haySucursales = sucursales != null && !(sucursales.isEmpty());

            if (haySucursales) {
                return buildCadenaDisponible(configuracion, sucursales);
            } else {
                return buildCadenaNoDisponible(configuracion);
            }
        }catch (Exception ex){
            return buildCadenaNoDisponible(configuracion);
        }
    }

    public static CadenaServiceContract buildClient(Configuracion configuracion)
    {

        if(esConfiguracionDeCadenaValida(configuracion)){

            final String url = configuracion.getUrl();
            final String tecnologia = configuracion.getNombreTecnologia();

            CadenaServiceContract client;
            client = CadenaClientFactory.clientFor(url,tecnologia);
            return client;

        }else{
            throw new IllegalArgumentException("La configuracion del cliente no es valida: " + configuracion.toString());
        }
    }

    private static boolean esConfiguracionDeCadenaValida (Configuracion configuracion)
    {
        if (configuracion == null) {
            return false;
        }

        final boolean isValidUrl = configuracion.getUrl() != null && !(configuracion.getUrl().trim().equals(""));
        final boolean isValidTecnologia = configuracion.getNombreTecnologia() != null && !(configuracion.getNombreTecnologia().trim().equals(""));
        final boolean isValidIdCadena = configuracion.getIdCadena() != null && configuracion.getIdCadena() > 0;
        final boolean isValidIdConfig = configuracion.getIdConfig() != null && configuracion.getIdConfig() > 0;
        final boolean isVaidNombreCadena = configuracion.getNombreCadena() != null && !(configuracion.getNombreCadena().trim().equals(""));

        if(isValidUrl && isValidTecnologia && isValidIdCadena && isValidIdConfig && isVaidNombreCadena){
            return true;
        } else{
            return false;
        }
    }

    public static Cadena buildCadenaNoDisponible(Configuracion configuracion)
    {
        Cadena cadena = new Cadena();
        cadena.setDisponible(false);
        cadena.setIdCadena(configuracion.getIdCadena());
        cadena.setNombreCadena(configuracion.getNombreCadena());
        LinkedList<Sucursal> sucs = new LinkedList<Sucursal>();
        cadena.setSucursales(sucs);
        return cadena;
    }

    public static Cadena buildCadenaDisponible(Configuracion configuracion, List<Sucursal> sucursales)
    {
        Cadena cadena = new Cadena();
        Integer idCadena = configuracion.getIdCadena();
        String nombreCadena = configuracion.getNombreCadena();
        for (Sucursal s : sucursales){
            s.setIdCadena(idCadena);
        }
        cadena.setDisponible(true);
        cadena.setIdCadena(idCadena);
        cadena.setNombreCadena(nombreCadena);
        cadena.setSucursales(sucursales);
        return cadena;
    }
}



