package utilities;

import sdk.contract.CadenaServiceContract;
import db.beans.Configuracion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Cadenas.Cadenas;

import java.util.LinkedList;
import java.util.List;

import static service.Cadenas.Cadenas.obtenerConfiguraciones;

public class ServiceHealth {

    private static final Logger logger = LoggerFactory.getLogger(ServiceHealth.class);

    public static String health
            (final Configuracion configuracion) {

        boolean isAvailable =  false;


        String status  =
                   "{"
                    + "nombreCadena: " + configuracion.getNombreCadena()
                    + ", "
                    + "idCadena: " + configuracion.getIdCadena()
                    + ", "
                    + "url: " + "'" + configuracion.getUrl() + "'"
                    + ", "
                    + "tecnologia: " +  configuracion.getNombreTecnologia()
                    + ", "
                    + "disponibilidad: %s" 
                    +
                   "}";

        try {
            CadenaServiceContract client = Cadenas.buildClient(configuracion);

            String okResponse;

            okResponse = client.health();

            logger.debug(okResponse);

            isAvailable = okResponse.trim().toLowerCase().equals("ok");

            logger.debug("-------------------------");

            if(isAvailable) {
                return String.format( status, "ok" );
            }

            return String.format( status, "nok" );


        } catch (Exception ex) {
             return String.format( status, "nok" );

        }
    }

    public static List<String> traversalHealth ()
    {
        List<Configuracion> configuraciones = obtenerConfiguraciones();

        List<String> result = new LinkedList<>();

        for(Configuracion config: configuraciones){
            result.add(health(config));
        }
        return result;

        /*return configuraciones
                .parallelStream()
                .map((config) -> health(config))
                .collect(toList());*/

    }

}
