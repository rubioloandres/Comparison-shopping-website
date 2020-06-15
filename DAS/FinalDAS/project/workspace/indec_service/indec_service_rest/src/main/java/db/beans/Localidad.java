package db.beans;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class Localidad implements Bean {
    @SerializedName("codigoEntidadFederal")
    private String codigoEntidadFederal;
    @SerializedName("nombreLocalidad")
    private String nombreLocalidad;

    public String getCodigoEntidadFederal() {
        return codigoEntidadFederal;
    }

    public void setCodigoEntidadFederal(String codigoEntidadFederal) {        this.codigoEntidadFederal = codigoEntidadFederal;    }

    public String getNombreLocalidad() {
        return nombreLocalidad;
    }

    public void setNombreLocalidad(String nombreLocalidad) {
        this.nombreLocalidad = nombreLocalidad;
    }
}
