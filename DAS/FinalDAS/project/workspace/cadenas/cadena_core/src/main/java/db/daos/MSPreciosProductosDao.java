package db.daos;

import bean.Producto;
import bean.Sucursal;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

public class MSPreciosProductosDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        Producto prod = new Producto();
        prod.setCodigoDeBarras( result.getString("codigoDeBarras") );
        prod.setPrecio( result.getFloat("precio") );
        prod.setIdSucursal(result.getShort("idSucursal"));
        return prod;
    }

    @Override
    public void insert(Bean bean) throws SQLException {
    }

    @Override
    public void insertBatch(List<Bean> beans) throws SQLException {
        Producto producto;

        List<Producto> productos = new LinkedList<>();

        for(Bean bean:beans){
            producto = (Producto) bean;
            productos.add(producto);
        }

        this.connect();
        this.setProcedure( "dbo.SP_INSERTPRECIOPRODUCTO(?,?,?)" );
        for (Producto prod :productos){
            this.setParameter(1,prod.getIdSucursal());
            this.setParameter(2,prod.getPrecio());
            this.setParameter(3,prod.getCodigoDeBarras());
            this.addBatch();
        }

        this.executeBatch();
        this.disconnect();
    }


    @Override
    public void update(Bean bean) throws SQLException {

    }

    @Override
    public void delete(Bean bean) throws SQLException {

    }

    @Override
    public List<Bean> select(Bean bean) throws SQLException {

        final Sucursal suc =  (Sucursal) bean;
        List<Bean> productos;
        this.connect();
        this.setProcedure("dbo.SP_GETPRECIOSPRODUCTOS(?)");

        if(suc.getIdSucursal() == null) {
            this.setNull(1, Types.SMALLINT);
        }else{
            this.setParameter( 1,suc.getIdSucursal());
        }

        productos = this.executeQuery();
        this.disconnect();
        return productos;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
