package db.beans;

import sdk.ds.Sucursal;
import com.google.gson.annotations.SerializedName;
import db.Bean;



import java.util.List;

public class Cadena implements Bean {

    @SerializedName("idCadena")
    private Integer idCadena;

    @SerializedName("nombreCadena")
    private String nombreCadena;

    @SerializedName("sucursales")
    private List<Sucursal> sucursales;

    @SerializedName("imagenCadena")
    private String imagenCadena;

    @SerializedName("disponible")
    private Boolean disponible;


    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Integer getIdCadena() {      return idCadena;   }

    public void setIdCadena(Integer idCadena) {       this.idCadena = idCadena;   }

    public String getNombreCadena() {       return nombreCadena;   }

    public void setNombreCadena(String nombreCadena) {       this.nombreCadena = nombreCadena;   }

    public List<Sucursal> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    public String getImagenCadena() {      return imagenCadena;   }

    public void setImagenCadena(String imagenCadena) {      this.imagenCadena = imagenCadena;   }
}
