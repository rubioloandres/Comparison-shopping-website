import Exceptions.APIException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import db.Bean;
import db.Dao;
import db.DaoFactory;
import db.beans.*;
import org.junit.Test;
import service.MenuSaludable.MenuSaludable;

import java.sql.SQLException;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

public class MenuSaludableSpecs {

    private Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
            .create();

    @Test
    public void obtenerProductosPorPlato_Success() {
        try {
            List<Producto> ip = MenuSaludable.obtenerProductosPorPlato( (short) 1 );
            System.out.println(gson.toJson(ip));
            assertTrue(true);
        }
        catch(APIException ex) {
            System.out.println("Error: "+ex.getMessage());
            fail();
        }
    }

    @Test
    public void MSProductosPorPlatoDao_Success() {
        try {
            Plato plato = new Plato();
            plato.setIdPlato((short)1);
            Dao dao = DaoFactory.getDao("ProductosPorPlato", "");
            List<Bean> ip = dao.select(plato);
            System.out.println(gson.toJson(ip));
            assertTrue(true);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            fail();
        }
    }
/*
    @Test
    public void MSProductosDao_success() {
        try {
            ProductoBean producto = new ProductoBean();
            producto.setIdCategoria(1L);
            Dao dao = DaoFactory.getDao("Productos", "");
            List<Bean> productos = dao.select(producto);
            System.out.println(gson.toJson(productos));
            assertTrue(true);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            fail();
        }
    }

    @Test
    public void MSProvinciasDao_success() {
        try {
            Provincia provincia = new Provincia();
            Dao dao = DaoFactory.getDao("Provincias", "");
            List<Bean> provincias = dao.select(provincia);
            System.out.println(gson.toJson(provincias));
            assertTrue(true);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            fail();
        }
    }

    @Test
    public void MSLocalidadesDao_success() {
        try {
            Localidad localidad = new Localidad();
            localidad.setIdProv(2L);
            Dao dao = DaoFactory.getDao("Localidades", "");
            List<Bean> localidades = dao.select(localidad);
            System.out.println(gson.toJson(localidades));
            assertTrue(true);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            fail();
        }
    }

    @Test
    public void MSLocalidadesDao_success_null_case() {
        try {
            Localidad localidad = new Localidad();
            Dao dao = DaoFactory.getDao("Localidades", "");
            List<Bean> localidades = dao.select(localidad);
            System.out.println(gson.toJson(localidades));
            assertTrue(true);
        }
        catch(SQLException ex) {
            System.out.println("Error: "+ex.getMessage());
            fail();
        }
    }
*/
}
