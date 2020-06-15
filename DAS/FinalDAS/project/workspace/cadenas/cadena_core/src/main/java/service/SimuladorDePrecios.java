package service;


import bean.Producto;

import java.math.BigDecimal;
import java.math.RoundingMode;
public class SimuladorDePrecios {

    private Producto[] productos;

    public SimuladorDePrecios(Producto[] productos) throws IllegalArgumentException{

        if (productos==null || productos.length == 0){
            throw  new IllegalArgumentException("La lista de productos es null o vacia");
        }
        this.productos = productos;

    }

    public void simular() {

        double percentage;
        double max;
        double min;
        double random;

        for(Producto p : this.productos){
            percentage = (10 * p.getPrecio()) / 100;
            max = p.getPrecio() + percentage;
            min = p.getPrecio() - percentage;
            random = min + Math.random() * (max - min);
            p.setPrecio(round(random,2));
        }

        return;
    }

    public Producto[] getSimulacion() {
        return this.productos;
    }

    private float round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.floatValue();
    }
}
