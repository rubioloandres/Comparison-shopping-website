package db.daos;

import bean.CriterioLocalizacionSucursal;
import bean.Sucursal;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
public class MSSucursalesDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
        Sucursal sucursal = new Sucursal();
        sucursal.setIdSucursal(result.getInt("idSucursal"));
        sucursal.setNombreSucursal(result.getString("nombreSucursal"));
        sucursal.setCuit(result.getString("cuit"));
        sucursal.setEmail(result.getString("email"));
        sucursal.setTelefono(result.getString("telefono"));
        sucursal.setDireccion(result.getString("direccion"));
        sucursal.setLongitud(result.getString("latitud"));
        sucursal.setLatitud(result.getString("longitud"));
        sucursal.setProvincia(result.getString("provincia"));
        sucursal.setCodigoEntidadFederal(result.getString("codigoEntidadFederal"));
        sucursal.setLocalidad(result.getString("localidad"));
        return sucursal;
    }

    @Override
    public void insert(Bean bean) throws SQLException {

    }

    @Override
    public void insertBatch(List<Bean> beans) throws SQLException {

    }

    @Override
    public void update(Bean bean) throws SQLException {

    }

    @Override
    public void delete(Bean bean) throws SQLException {

    }

    @Override
    public List<Bean> select(Bean bean) throws SQLException {
        final CriterioLocalizacionSucursal criterio = (CriterioLocalizacionSucursal) bean;
        List<Bean>  sucs;
        this.connect();
        this.setProcedure("dbo.SP_GETSUCURSALES(?,?)");
        if(criterio.getCodigoEntidadFederal() == null) {
            this.setNull(1, Types.VARCHAR);
        }
        else {
            this.setParameter(1, criterio.getCodigoEntidadFederal());
        }
        if(criterio.getLocalidad() == null) {
            this.setNull(2, Types.VARCHAR);
        }
        else {
            this.setParameter(2, criterio.getLocalidad());
        }
        sucs = this.executeQuery();
        this.disconnect();
        return sucs;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
