package db.daos;
import db.beans.CategoriaProducto;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSCategoriasProductoDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        CategoriaProducto categoria = new CategoriaProducto();
        categoria.setIdCategoria(result.getInt("idCategoria"));
        categoria.setNombreCategoria(result.getString("nombreCategoria"));
        categoria.setUrlImagenCategoria(result.getString("urlImagenCategoria"));
        return categoria;
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
            this.connect();
            this.setProcedure("dbo.spCategoriasProducto");
            List<Bean> categorias = this.executeQuery();
            this.disconnect();
            return categorias;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
