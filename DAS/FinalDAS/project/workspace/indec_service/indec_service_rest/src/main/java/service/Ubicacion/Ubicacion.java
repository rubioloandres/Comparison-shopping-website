package service.Ubicacion;

import Exceptions.APIException;
import db.beans.Cadena;
import db.beans.Localidad;
import db.beans.Provincia;
import db.Bean;
import db.DaoFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Cadenas.Cadenas;
import utilities.GSON;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

public class Ubicacion {

    private static final Logger logger =
            LoggerFactory.getLogger( Cadenas.class);

    public static List<Localidad> obtenerLocalidades() throws APIException
    {
        try {
            List<Bean> beans = DaoFactory.getDao("Localidades").select(null);
            if(beans != null){
                return Arrays.asList(GSON.transform(beans, Localidad[].class));
            }else{
                throw new APIException("GSON failed -> obtenerLocalidades");
            }
        }catch (SQLException ex) {
            throw new APIException("SQLException,metodo -> obtenerLocalidades:{}" + ex.getMessage());
        }
    }

    public static List<Provincia> obtenerProvincias() throws APIException
    {
        try {
            List<Bean> beans = DaoFactory.getDao("Provincias").select(null);
            if(beans != null){
                return Arrays.asList(GSON.transform(beans, Provincia[].class));
            }else{
                throw new APIException("GSON failed -> obtenerProvincias");
            }
        }catch (SQLException ex) {
            throw new APIException("SQLException,metodo -> obtenerProvincias:{}" + ex.getMessage());
        }
    }

}
