package db.daos;

import bean.CriterioBusquedaProductos;
import bean.Producto;
import bean.Sucursal;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.LinkedList;
import java.util.List;

public class MSPreciosSucursalesDao extends DaoImpl {
    @Override
    public Bean make(ResultSet result) throws SQLException {
      return null;
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
        final CriterioBusquedaProductos cs =  (CriterioBusquedaProductos) bean;
        List<Bean>  sucursales = new LinkedList<Bean>(); //prestar atencion a esto
        List <Producto> productos;
        Producto producto;
        this.connect();
        this.setProcedure("dbo.SP_GETPRECIOSSUCURSALES(?,?,?)",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        if(cs.getCodigoEntidadFederal() == null) {
            this.setNull(1, Types.VARCHAR);
        }
        else {
            this.setParameter(1, cs.getCodigoEntidadFederal());
        }
        if(cs.getLocalidad() == null) {
            this.setNull(2, Types.VARCHAR);
        }
        else {
            this.setParameter(2, cs.getLocalidad());
        }
        if(cs.getCodigos() == null) {
            this.setNull(3, Types.VARCHAR);
        }
        else {
            this.setParameter(3, cs.getCodigos());
        }

        ResultSet result  = this.getStatement().executeQuery();
        result.next();

        while(result.getRow()>0){
            Sucursal sucursal = new Sucursal();
            sucursal.setIdSucursal(result.getInt("idSucursal"));
            sucursal.setNombreSucursal(result.getString("nombreSucursal"));
            sucursal.setDireccion(result.getString("direccion"));
            sucursal.setLongitud(result.getString("latitud"));
            sucursal.setLatitud(result.getString("longitud"));
            sucursal.setEmail(result.getString("email"));
            sucursal.setTelefono(result.getString("telefono"));
            sucursal.setCuit(result.getString("cuit"));
            sucursal.setLocalidad(result.getString("localidad"));
            sucursal.setProvincia(result.getString("provincia"));
            sucursal.setCodigoEntidadFederal(result.getString("codigoEntidadFederal"));

            productos = new LinkedList<Producto>();
            while (result.getRow()>0 && sucursal.getIdSucursal() == result.getInt("idSucursal")){
                producto = new Producto();
                producto.setCodigoDeBarras(result.getString("codigoDeBarras"));
                producto.setNombre(result.getString("nombreProducto"));
                producto.setMarca(result.getString("marca"));
                producto.setPrecio(result.getFloat("precio"));
                producto.setValidoDesde(result.getString("validoDesde"));
                productos.add(producto);
                result.next();
            }
            sucursal.setProductos(productos);
            sucursales.add(sucursal);
        }
        this.disconnect();
        return sucursales;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
