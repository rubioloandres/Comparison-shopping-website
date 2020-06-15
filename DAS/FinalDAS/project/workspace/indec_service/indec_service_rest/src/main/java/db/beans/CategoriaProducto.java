package db.beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CategoriaProducto implements Bean {

    @SerializedName("idCategoria")
    private Integer idCategoria;
    @SerializedName("nombreCategoria")
    private String nombreCategoria;
    @SerializedName("urlImagenCategoria")
    private String urlImagenCategoria;


    public Integer getIdCategoria() {      return idCategoria;   }

    public void setIdCategoria(Integer idCategoria) {      this.idCategoria = idCategoria;   }

    public String getNombreCategoria() {       return nombreCategoria;   }

    public void setNombreCategoria(String nombreCategoria) {      this.nombreCategoria = nombreCategoria;   }

    public String getUrlImagenCategoria() {       return urlImagenCategoria;   }

    public void setUrlImagenCategoria(String urlImagenCategoria) {     this.urlImagenCategoria = urlImagenCategoria;   }
}
