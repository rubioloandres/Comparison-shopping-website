package sdk.ds;

import com.google.gson.annotations.SerializedName;
import java.util.List;


public class Sucursal {

    @SerializedName("idSucursal")
    private Integer idSucursal;

    @SerializedName("nombreSucursal")
    private String nombreSucursal;

    @SerializedName("direccion")
    private String direccion;

    @SerializedName("latitud")
    private String latitud;

    @SerializedName("longitud")
    private String longitud;

    @SerializedName("email")
    private String email;

    @SerializedName("telefono")
    private String telefono;

    @SerializedName("cuit")
    private String cuit;

    @SerializedName("localidad")
    private String localidad;

    @SerializedName("provincia")
    private String provincia;

    @SerializedName("codigoEntidadFederal")
    private String codigoEntidadFederal;

    @SerializedName("idCadena")
    private Integer idCadena;

    @SerializedName("productos")
    private List<ProductoSucursal> productos;

    @SerializedName("cantidadDeProductosConPrecioMasBajo")
    private int cantidadDeProductosConPrecioMasBajo;

    @SerializedName("mejorOpcion")
    private boolean mejorOpcion;

    @SerializedName("total")
    private double total;


    public int getCantidadDeProductosConPrecioMasBajo() {
        return cantidadDeProductosConPrecioMasBajo;
    }

    public void setCantidadDeProductosConPrecioMasBajo(int cantidadDeProductosConPrecioMasBajo) {
        this.cantidadDeProductosConPrecioMasBajo = cantidadDeProductosConPrecioMasBajo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isMejorOpcion() {
        return mejorOpcion;
    }

    public void setMejorOpcion(boolean mejorOpcion) {
        this.mejorOpcion = mejorOpcion;
    }
    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCodigoEntidadFederal() {
        return codigoEntidadFederal;
    }

    public void setCodigoEntidadFederal(String codigoEntidadFederal) {
        this.codigoEntidadFederal = codigoEntidadFederal;
    }
    public Integer getIdCadena() {
        return idCadena;
    }

    public void setIdCadena(Integer idCadena) {
        this.idCadena = idCadena;
    }

    public List<ProductoSucursal> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoSucursal> productos) {
        this.productos = productos;
    }
}
