package db.beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class Ingrediente implements Bean {

    @SerializedName("nombreIngrediente")
    private String nombreIngrediente;
    @SerializedName("idIngrediente")
    private Integer idIngrediente;
    @SerializedName("descripcion")
    private String descripcion;


    public Integer getIdIngrediente() { return idIngrediente;}

    public void setIdIngrediente(Integer idIngrediente) { this.idIngrediente = idIngrediente;}

    public String getNombreIngrediente() {       return nombreIngrediente;   }

    public void setNombreIngrediente(String nombreIngrediente) {       this.nombreIngrediente = nombreIngrediente;   }

    public String getDescripcion() {       return descripcion;   }

    public void setDescripcion(String descripcion) {       this.descripcion = descripcion;   }
}
