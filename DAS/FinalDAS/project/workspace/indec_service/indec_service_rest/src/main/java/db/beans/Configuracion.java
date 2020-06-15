package db.beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class Configuracion implements Bean {

    @SerializedName("idConfig")
    private Integer idConfig;
    @SerializedName("idCadena")
    private Integer idCadena;
    @SerializedName("nombreCadena")
    private String nombreCadena;
    @SerializedName("nombreTecnologia")
    private String nombreTecnologia;
    @SerializedName("url")
    private String url ;

    public Integer getIdConfig() {       return idConfig;   }

    public void setIdConfig(Integer idConfig) {       this.idConfig = idConfig;   }

    public String getNombreTecnologia() {       return nombreTecnologia;   }

    public void setNombreTecnologia(String nombreTecnologia) {       this.nombreTecnologia = nombreTecnologia;   }

    public Integer getIdCadena() {  return idCadena;  }

    public void setIdCadena(Integer idCadena) {    this.idCadena = idCadena;   }

    public String getNombreCadena() {        return nombreCadena;   }

    public void setNombreCadena(String nombreCadena) {       this.nombreCadena = nombreCadena;   }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Configuracion{" +
                "idConfig=" + idConfig +
                ", idCadena=" + idCadena +
                ", nombreCadena='" + nombreCadena + '\'' +
                ", nombreTecnologia='" + nombreTecnologia + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
