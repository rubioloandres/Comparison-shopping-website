package service.Comparador;

import db.beans.Cadena;
import db.beans.Producto;

import sdk.ds.ProductoSucursal;
import sdk.ds.Sucursal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;

public class Comparador {

    private final List<Cadena> cadenas;
    private final List<Producto> productosDelCarrito;
    private final Map<String,Double> codigoPrecio;
    private Integer cant_max;

    public Comparador(final List<Cadena> cadenas, final List<Producto> productosDelCarrito) {

        if ((cadenas != null) && (!cadenas.isEmpty())){
            this.cadenas = cadenas;
        }else{
            throw new IllegalArgumentException("Comparador -> La lista de cadenas es null o esta vacia");
        }

        if ((productosDelCarrito != null) && (!productosDelCarrito.isEmpty())){
            this.productosDelCarrito = productosDelCarrito;
        }else{
            throw new IllegalArgumentException("Comparador -> La lista de productos es null o esta vacia");
        }

        this.codigoPrecio = new HashMap<>();
        cant_max = 0;

    }

    public void comparar(){
        buscarPreciosMasBajos();
        marcarProductosMasBaratos();
        calcularTotalesPorSucursal();
        calcularCantidadDeProductosMasBaratosPorSucursal();
        marcarMejoresSucursales();
        completarProductosFaltantes();
    }


    public List<Cadena> obtenerComparacion(){
        return this.cadenas;
    }

    private void marcarProductosMasBaratos() {
        double precio;
        String codigo;
        for (Cadena c : cadenas) {
            for (Sucursal sc : c.getSucursales()) {
                for (ProductoSucursal p : sc.getProductos()) {
                    codigo = p.getCodigoDeBarras();
                    precio = codigoPrecio.get(codigo);
                    if (p.getPrecio() == precio) {
                        p.setMejorPrecio(true);
                    } else {
                        p.setMejorPrecio(false);
                    }
                }
            }
        }
    }

    private void buscarPreciosMasBajos() {
        double  precioActual,precio;
        String codigo;
        for (Cadena c : cadenas) {
            for (Sucursal sc : c.getSucursales()) {
                for (ProductoSucursal p : sc.getProductos()) {
                    codigo = p.getCodigoDeBarras();
                    precio = p.getPrecio();
                    if (codigoPrecio.containsKey( codigo )) {
                        precioActual = codigoPrecio.get( codigo );
                        if (precio < precioActual) {
                            codigoPrecio.replace( codigo, precio );
                        }
                    } else {
                        codigoPrecio.put( codigo, precio );
                    }
                }
            }
        }
    }

    private void calcularTotalesPorSucursal() {
        BigDecimal  bd;
        double precioTotal;
        for (Cadena c : this.cadenas) {
            for (Sucursal s : c.getSucursales()) {
                precioTotal = 0;
                for (ProductoSucursal p : s.getProductos()) {
                    precioTotal = precioTotal + p.getPrecio();
                }
                bd = new BigDecimal(precioTotal).setScale(2, RoundingMode.HALF_UP);
                s.setTotal(bd.doubleValue());
            }
        }
    }

    private void calcularCantidadDeProductosMasBaratosPorSucursal(){

        for (Cadena c : cadenas) {
            for (Sucursal s : c.getSucursales()) {
                Integer cantidad = 0;
                for(ProductoSucursal p : s.getProductos()){
                    if(p.isMejorPrecio())
                        cantidad = cantidad + 1;
                }
                s.setCantidadDeProductosConPrecioMasBajo(cantidad);
                if(cantidad > cant_max){
                    cant_max = cantidad;
                }
            }
        }

    }

    private void completarProductosFaltantes(){
        ProductoSucursal np;
        List<ProductoSucursal> productosSucursal;
        List<ProductoSucursal> productosAusentes;
        boolean elProductoNoEstaEnElCarrito;
        for (Cadena c : this.cadenas) {
            for (Sucursal s : c.getSucursales()) {
                productosAusentes = new LinkedList<>();
                for(Producto pc : productosDelCarrito){
                    productosSucursal = s.getProductos();
                    elProductoNoEstaEnElCarrito = !existe(pc,productosSucursal);
                    if(elProductoNoEstaEnElCarrito){
                        np = new ProductoSucursal();
                        np.setMejorPrecio(false);
                        np.setPrecio(0.0);
                        np.setCodigoDeBarras(pc.getCodigoDeBarras());
                        np.setNombre(pc.getNombreProducto());
                        np.setMarca(pc.getNombreMarca());
                        productosAusentes.add(np);
                    }
                }
                s.getProductos().addAll(productosAusentes);
            }
        }
    }

    private void marcarMejoresSucursales() {

        for (Cadena c : this.cadenas) {
            for (Sucursal s : c.getSucursales()) {
                if(cant_max == s.getCantidadDeProductosConPrecioMasBajo())
                    s.setMejorOpcion(true);
                else
                    s.setMejorOpcion(false);

            }
        }
    }

    private boolean existe (final Producto producto,List<ProductoSucursal>productos){
        for (ProductoSucursal p : productos) {
            if(p.getCodigoDeBarras().equals(producto.getCodigoDeBarras())) {
                return true;
            }

        }
        return false;
    }
}