package service.MenuSaludable;

import Exceptions.APIException;
import db.beans.*;
import db.Bean;
import db.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Cadenas.Cadenas;
import sdk.ds.ProductoSucursal;
import sdk.ds.Sucursal;
import utilities.GSON;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class MenuSaludable {

    private static final Logger logger =
            LoggerFactory.getLogger(MenuSaludable.class);

    public static List<Menu> obtenerMenuSemanal()throws APIException
    {

        try {
            List<Bean> beans = DaoFactory.getDao("MenuSemanal").select(null);
            if(beans != null){
                return Arrays.asList(GSON.transform(beans, Menu[].class));
            }else{
                throw new APIException("GSON failed -> obtenerMenuSemanal");
            }
        }catch (SQLException ex) {
            throw new APIException("SQLException,metodo -> obtenerMenuSemanal:{}" + ex.getMessage());
        }

    }


    public static List<Producto> obtenerProductosPorPlato(short idPlato) throws APIException
    {

        Plato plato = new Plato();
        plato.setIdPlato(idPlato);


        try {
            List<Bean> beans = DaoFactory.getDao("ProductosPorPlato").select(plato);
            if(beans != null){
                return Arrays.asList(GSON.transform(beans, Producto[].class));
            }else{
                throw new APIException("GSON failed -> obtenerProductosPorPlato");
            }
        }catch (SQLException ex) {
            throw new APIException("SQLException,metodo -> obtenerProductosPorPlato:{}" + ex.getMessage());
        }

    }

    public static List<Cadena> armarPlato
            (final  String codigoentidadfederal
            ,final  String localidad
            ,final  short idPlato) throws APIException
    {

            BigDecimal bd;
            final List<Producto> productosIngrediente = obtenerProductosPorPlato(idPlato);

            final List<Configuracion> configuraciones = Cadenas.obtenerConfiguraciones();


            String codigos =
                    productosIngrediente.stream().map(p -> p.getCodigoDeBarras()).collect(Collectors.joining(","));

            List<Cadena> cadenas = Cadenas.preciosSucursales(codigoentidadfederal,localidad,codigos,configuraciones);

            List<Cadena> cadenasDisponibles = new LinkedList<>();
            List<Cadena> cadenasNoDisponibles = new LinkedList<>();

            //Separamos las cadenasDisponibles de las cadenasNoDisponibles
            for (Cadena cad : cadenas) {
                if (cad.getDisponible())
                    cadenasDisponibles.add( cad );
                if (!cad.getDisponible())
                    cadenasNoDisponibles.add( cad );
            }

            if(!cadenasDisponibles.isEmpty()) {

//----------------------------------------------------------------------------------------------------------------------
                for (Cadena cadena : cadenasDisponibles) {
                    for (Sucursal sucursal : cadena.getSucursales()) {
                        for (ProductoSucursal producto : sucursal.getProductos()) {
                            int idIngrediente = 0;
                            for (Producto pi : productosIngrediente) {
                                if (pi.getCodigoDeBarras().equals( producto.getCodigoDeBarras() ))
                                    idIngrediente = pi.getIdIngrediente();
                            }
                            producto.setIdIngrediente( idIngrediente );
                        }
                    }
                }
//----------------------------------------------------------------------------------------------------------------------
                double precioTotal = 0;
                List<Integer> cantidadesDeProductosPorSucursal = new LinkedList<>();
                for (Cadena cadena : cadenasDisponibles) {
                    for (Sucursal sucursal : cadena.getSucursales()) {
                        precioTotal = 0;
                        Map<Integer, List<ProductoSucursal>> productosPorIngrediente =
                                sucursal.getProductos().stream()
                                        .collect( groupingBy( ProductoSucursal::getIdIngrediente ) );

                        final Map<Integer, ProductoSucursal> productoMasBaratoPorIngrediente = new HashMap<>();

                        productosPorIngrediente.forEach( (ingrediente, productos) -> {
                                    final ProductoSucursal pp =
                                            productos.stream().min( comparing( p -> p.getPrecio() ) ).get();
                                    productoMasBaratoPorIngrediente.put( ingrediente, pp );
                                }
                        );

                        for (Map.Entry<Integer, ProductoSucursal> entry : productoMasBaratoPorIngrediente.entrySet()) {
                            precioTotal = precioTotal + entry.getValue().getPrecio();
                        }

                        ArrayList<ProductoSucursal> prods = new ArrayList<>( productoMasBaratoPorIngrediente.values() );
                        sucursal.setProductos( prods );
                        sucursal.setCantidadDeProductosConPrecioMasBajo((int) sucursal.getProductos().stream().count() );
                        cantidadesDeProductosPorSucursal.add(sucursal.getCantidadDeProductosConPrecioMasBajo());
                        sucursal.setTotal( precioTotal );
                    }
                }
//----------------------------------------------------------------------------------------------------------------------
                final long cantidad_max = cantidadesDeProductosPorSucursal.stream()
                        .max( naturalOrder() )
                        .get();

                double menorPrecioTotal = Double.MAX_VALUE;
                for (Cadena c : cadenasDisponibles) {
                    for (Sucursal s : c.getSucursales()) {
                        if (cantidad_max == s.getCantidadDeProductosConPrecioMasBajo()) {
                            s.setMejorOpcion( true );
                            if (s.getTotal() < menorPrecioTotal) {
                                menorPrecioTotal = s.getTotal();
                            }
                        } else s.setMejorOpcion( false );

                    }
                }

//----------------------------------------------------------------------------------------------------------------------
                for (Cadena c : cadenasDisponibles) {
                    for (Sucursal s : c.getSucursales()) {
                        if (s.isMejorOpcion() && s.getTotal() == menorPrecioTotal)
                            s.setMejorOpcion( true );
                        else s.setMejorOpcion( false );

                    }
                }
//----------------------------------------------------------------------------------------------------------------------
                for (Cadena c : cadenasDisponibles) {
                    for (Sucursal s : c.getSucursales()) {
                        bd = new BigDecimal(s.getTotal()).setScale(2, RoundingMode.HALF_UP);
                        s.setTotal(bd.doubleValue());
                    }
                }


//----------------------------------------------------------------------------------------------------------------------
                return  Stream.concat(cadenasDisponibles.stream(), cadenasNoDisponibles.stream())
                        .collect(toList());
//----------------------------------------------------------------------------------------------------------------------


            }else{

                return cadenasNoDisponibles;
            }
    }
}
