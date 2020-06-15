package db.daos;

import db.beans.Plato;
import db.Bean;
import db.DaoImpl;
import db.beans.Producto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSProductosPorPlatoDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        Producto producto = new Producto();
        producto.setCodigoDeBarras(result.getString("codigoDeBarras"));
        producto.setIdIngrediente(result.getShort("idIngrediente"));
        return producto;
    }

    @Override
    public void insert(Bean bean) throws SQLException {

    }

    @Override
    public void update(Bean bean) throws SQLException {

    }

    @Override
    public void delete(Bean bean) throws SQLException {

    }

    @Override
    public List<Bean> select(Bean bean) throws SQLException {
        Plato plato = (Plato) bean;
        this.connect();
        this.setProcedure("dbo.spProductosPorPlato(?)");
        this.setParameter(1,plato.getIdPlato());
        List<Bean> productos = this.executeQuery();
        this.disconnect();
        return productos;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
