package db.daos;


import db.beans.Ingrediente;
import db.beans.Menu;
import db.beans.Plato;
import db.Bean;
import db.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class MSMenuSemanalDao extends DaoImpl {

    @Override
    public Bean make(ResultSet result) throws SQLException {
        return null;
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

        List<Bean> menuSemanal = new LinkedList<Bean>(); //prestar atencion a esto
        List <Plato> platos;
        List <Ingrediente> ingredientes;
        Menu menu;
        Plato plato;
        Ingrediente ingrediente;

        this.connect();
        this.setProcedure("dbo.spMenu",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

        ResultSet result  = this.getStatement().executeQuery();
        result.next();

        while(result.getRow()>0){
            menu = new Menu();
            menu.setNombreMenu(result.getString("nombreMenu"));
            menu.setDia(result.getString("dia"));
            menu.setIdMenu(result.getInt("idMenu"));
            platos = new LinkedList<Plato>();
            while (result.getRow()>0 && menu.getIdMenu() == result.getInt("idMenu")){
                plato = new Plato();
                plato.setNombrePlato(result.getString("nombrePlato"));
                plato.setImagenPlato(result.getString("imagenPlato"));
                plato.setPreparacion(result.getString("preparacion"));
                plato.setIdPlato(result.getShort("idPlato"));
                plato.setIdMenu(result.getInt("idMenu"));
                ingredientes = new LinkedList<Ingrediente>();
                while (result.getRow()>0 && plato.getIdPlato() == result.getInt("idPlato")) {
                    ingrediente = new Ingrediente();
                    ingrediente.setNombreIngrediente(result.getString("nombreIngrediente"));
                    ingrediente.setDescripcion(result.getString("descripcion"));
                    ingrediente.setIdIngrediente(result.getInt("idIngrediente"));
                    ingredientes.add(ingrediente);
                    result.next();
                }
                plato.setIngredientes(ingredientes);
                platos.add(plato);
            }
            menu.setPlatos(platos);
            menuSemanal.add(menu);
        }
        this.disconnect();
        return menuSemanal;
    }

    @Override
    public boolean valid(Bean bean) throws SQLException {
        return false;
    }
}
