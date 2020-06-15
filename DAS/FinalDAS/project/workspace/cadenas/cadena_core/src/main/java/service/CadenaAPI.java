package service;
import bean.CriterioBusquedaProductos;
import bean.CriterioLocalizacionSucursal;
import bean.Producto;
import bean.Sucursal;
import db.Bean;
import db.DaoFactory;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import utilities.GSON;

public class CadenaAPI {

    private CadenaAPI(){ }


    public static String sucursales (final String codigoentidadfederal
            ,final String localidad) throws Exception
    {

        if(codigoentidadfederal == null || codigoentidadfederal.trim().equals(""))
            throw new Exception("El_parametro_codigos_es_null_o_vacio");

        if(localidad == null || localidad.trim().equals(""))
            throw new Exception("El_parametro_codigos_es_null_o_vacio");
        try {

            CriterioLocalizacionSucursal criterio = new CriterioLocalizacionSucursal();
            criterio.setCodigoEntidadFederal(codigoentidadfederal);
            criterio.setLocalidad(localidad);

            List<Bean> sucs;

            sucs = DaoFactory.getDao("Sucursales")
                             .select(criterio);

            return GSON.toJson(sucs);

        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static String preciosSucursales(final String codigoentidadfederal
            ,final String localidad
            ,final String codigos) throws Exception
    {

        if(codigoentidadfederal == null || codigoentidadfederal.trim().equals(""))
            throw new Exception("El_parametro_codigos_es_null_o_vacio");

        if(localidad == null || localidad.trim().equals(""))
            throw new Exception("El_parametro_codigos_es_null_o_vacio");

        if(codigos == null)
            throw new Exception("El_parametro_codigos_es_null");

        try {

            CriterioBusquedaProductos criterio = new CriterioBusquedaProductos();
            criterio.setCodigoEntidadFederal(codigoentidadfederal);
            criterio.setLocalidad(localidad);
            criterio.setCodigos(codigos);

            List<Bean> ps = DaoFactory.getDao("PreciosSucursales")
                                      .select(criterio);

            return GSON.toJson(ps);

        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }


    public static void simularPrecios (final String codigoentidadfederal
            ,final String localidad) throws Exception
    {

        if(codigoentidadfederal == null || codigoentidadfederal.trim().equals(""))
            throw new Exception("El_parametro_codigos_es_null_o_vacio");

        if(localidad == null || localidad.trim().equals(""))
            throw new Exception("El_parametro_codigos_es_null_o_vacio");
        try {

            List<Bean> beans;

            CriterioLocalizacionSucursal criterio = new CriterioLocalizacionSucursal();
            criterio.setCodigoEntidadFederal(codigoentidadfederal);
            criterio.setLocalidad(localidad);

            beans = DaoFactory.getDao("Sucursales")
                    .select(criterio);

            List<Sucursal> sucursales;
            sucursales = new LinkedList<>();
            Sucursal sucursal;

            for(Bean b : beans){
                sucursal = (Sucursal) b;
                sucursales.add(sucursal);
            }


            Producto[] productos;
            Producto[] preciosSimulados;
            SimuladorDePrecios simulador;

            for(Sucursal s : sucursales ){

                beans = DaoFactory.getDao("PreciosProductos")
                                  .select(s);

                productos = GSON.transform(beans, Producto[].class);

                simulador = new SimuladorDePrecios(productos);

                simulador.simular();

                preciosSimulados = simulador.getSimulacion();

                beans = new LinkedList<>();

                for(Producto p : preciosSimulados){
                    beans.add(p);
                }

                DaoFactory.getDao("PreciosProductos")
                          .insertBatch(beans);
            }

        } catch (SQLException ex) {
            throw new Exception(ex.getMessage());
        }
    }

}
