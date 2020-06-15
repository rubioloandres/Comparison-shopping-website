package bean;

import com.google.gson.annotations.SerializedName;
import db.Bean;

public class CriterioLocalizacionSucursal implements Bean {
    @SerializedName("codigoEntidadFederal")
    private String codigoEntidadFederal;

    @SerializedName("localidad")
    private String localidad;

    public String getCodigoEntidadFederal() {
        return codigoEntidadFederal;
    }

    public void setCodigoEntidadFederal(String codigoEntidadFederal) {
        this.codigoEntidadFederal = codigoEntidadFederal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
