package db.beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

import java.util.List;

public class Menu implements Bean {

    @SerializedName("idMenu")
    private Integer idMenu;
    @SerializedName("nombreMenu")
    private String nombreMenu;
    @SerializedName("dia")
    private String dia;
    @SerializedName("platos")
    private List<Plato> platos;

    public Integer getIdMenu() {       return idMenu;   }

    public void setIdMenu(Integer idMenu) {       this.idMenu = idMenu;   }

    public String getNombreMenu() {      return nombreMenu;   }

    public void setNombreMenu(String nombreMenu) {       this.nombreMenu = nombreMenu;   }

    public String getDia() {       return dia;   }

    public void setDia(String dia) {       this.dia = dia;   }

    public List<Plato> getPlatos() {       return platos;   }

    public void setPlatos(List<Plato> platos) {       this.platos = platos;   }
}
