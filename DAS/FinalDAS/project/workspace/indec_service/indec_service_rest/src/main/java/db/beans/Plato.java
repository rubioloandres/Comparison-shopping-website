package db.beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

import java.util.List;

public class Plato implements Bean {

    @SerializedName("idPlato")
    private short idPlato;
    @SerializedName("nombrePlato")
    private String nombrePlato;
    @SerializedName("preparacion")
    private String preparacion;
    @SerializedName("imagenPlato")
    private String imagenPlato;
    @SerializedName("idMenu")
    private Integer idMenu;
    @SerializedName("ingredientes")
    private List<Ingrediente> ingredientes;

    public short getIdPlato() {      return idPlato;   }

    public void setIdPlato(short idPlato) {       this.idPlato = idPlato;   }

    public String getNombrePlato() {       return nombrePlato;   }

    public void setNombrePlato(String nombrePlato) {       this.nombrePlato = nombrePlato;   }

    public String getPreparacion() {       return preparacion;   }

    public void setPreparacion(String preparacion) {       this.preparacion = preparacion;   }

    public String getImagenPlato() {       return imagenPlato;   }

    public void setImagenPlato(String imagenPlato) {      this.imagenPlato = imagenPlato;   }

    public List<Ingrediente> getIngredientes() {      return ingredientes;   }

    public void setIngredientes(List<Ingrediente> ingredientes) {       this.ingredientes = ingredientes;   }

    public Integer getIdMenu() {      return idMenu;  }

    public void setIdMenu(Integer idMenu) {      this.idMenu = idMenu;   }
}
