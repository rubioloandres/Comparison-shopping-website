package service.CanastaBasica;
import Exceptions.APIException;
import db.beans.CategoriaProducto;
import db.beans.CriterioBusquedaProducto;
import db.beans.Producto;
import db.Bean;
import db.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.GSON;
import utilities.ListUtils;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CanastaBasica {

    private static final Logger logger =
            LoggerFactory.getLogger(CanastaBasica.class);

    //static config;

    public  static List<CategoriaProducto> obtenerCategorias() throws APIException
    {
        try {
            List<Bean> beans = DaoFactory.getDao("CategoriasProducto").select(null);
            if(beans != null){
                return Arrays.asList(GSON.transform(beans, CategoriaProducto[].class));
            }else{
                throw new APIException("GSON failed -> obtenerCategorias");
            }
        }catch (SQLException ex) {
            throw new APIException("SQLException,metodo -> obtenerCategorias:{}" + ex.getMessage());
        }

    }

    private static List<Producto> obtenerProductos () throws APIException
    {

        try {
            List<Bean> beans = DaoFactory.getDao("Productos").select(null);
            if(beans != null){
                return Arrays.asList(GSON.transform(beans, Producto[].class));
            }else{
                throw new APIException("GSON failed -> obtenerProductos");
            }
        }catch (SQLException ex) {
            throw new APIException("SQLException,metodo -> obtenerProductos:{}" + ex.getMessage());
        }
    }

    public  static List<Producto> buscarProductos (final CriterioBusquedaProducto criterio) throws APIException
    {
        if(criterio == null) {
            throw new APIException("Se debe proveer un criterio para la busqueda de productos" );
        }

        List<Producto> productos = obtenerProductos();

        if(criterio.getIdCategoria() == null & criterio.getMarcas() == null & criterio.getPalabraclave() == null)
            return productos;

        return productos.stream()
                        .filter(p -> criterio.filtraPorCategoria(p))
                        .filter(p -> criterio.filtraPorMarcas(p))
                        .filter(p -> criterio.filtraPorPalabraClave(p))
                        .collect(Collectors.toList());
    }

    public static List<Producto> buscarProductosPorCodigos(final String codigos) throws APIException
    {
        if(codigos == null) {
            throw new IllegalArgumentException("Se debe proveer una lista de codigos para la busqueda de productos");
        }

        List<Producto> productos = obtenerProductos();
        List<String> lcodigos = new ArrayList<>();
        for (String c : ListUtils.asList(codigos)) {
            String trim = c.trim();
            lcodigos.add(trim);
        }

        Predicate<Producto>  porCodigoDeBarras  = p -> {
            return lcodigos.contains(p.getCodigoDeBarras());
        };

        List<Producto> list = new ArrayList<>();
        for (Producto producto : productos) {
            if (porCodigoDeBarras.test(producto)) {
                list.add(producto);
            }
        }

        return list;
    }
}
