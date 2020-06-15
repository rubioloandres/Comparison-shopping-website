package db.daos;

import db.beans.Localidad;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MSLocalidadesDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        Localidad loc = new Localidad();
        loc.setCodigoEntidadFederal(result.getString("codigoEntidadFederal"));
        loc.setNombreLocalidad(result.getString("nombreLocalidad"));
        return loc;
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
        this.setProcedure("dbo.spLocalidades");
        List<Bean> localidades = this.executeQuery();
        this.disconnect();
        return localidades;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
