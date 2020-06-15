package db.beans;

import java.util.List;

public class CriterioBusquedaProducto {

    private Short idCategoria;
    private List<String> marcas;
    private String palabraclave;

    public String getPalabraclave() { return palabraclave; }

    public void setPalabraclave(String palabraclave) { this.palabraclave = palabraclave; }


    public Short getIdCategoria() { return idCategoria; }

    public void setIdCategoria(Short idCategoria) { this.idCategoria = idCategoria; }

    public boolean filtraPorCategoria (Producto p) {
        if (this.getIdCategoria() == null) {
            return true;
        } else {
            return p.getIdCategoria().equals(this.getIdCategoria());
        }
    }

    public boolean filtraPorMarcas (Producto p) {
        if (this.getMarcas() == null) {
            return true;
        } else {
            for (String marca:this.getMarcas()){
                if(p.getNombreMarca().trim().toLowerCase().equals(marca.trim().toLowerCase())){
                  return true;
                }
            }
            return false;
        }
    }

    public boolean filtraPorPalabraClave (Producto p) {

        if (this.getPalabraclave() == null) {
            return true;
        } else {
            return
                    p.getNombreProducto().trim().toLowerCase().contains(this.getPalabraclave().toLowerCase().trim())
                            || p.getNombreMarca().trim().toLowerCase().contains(this.getPalabraclave().toLowerCase().trim())
                            || p.getNombreCategoria().trim().toLowerCase().contains(this.getPalabraclave().toLowerCase().trim());
        }

    }

    public List<String> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<String> marcas) {
        this.marcas = marcas;
    }
}
