package sdk.clients;

import sdk.clients.exceptions.ClientException;
import sdk.clients.genericClients.RestClient;
import sdk.contract.CadenaServiceContract;
import sdk.ds.Sucursal;
import sdk.utils.GSON;

import java.util.Arrays;
import java.util.List;

import static sdk.clients.constants.Constants.*;

public class CadenaRestClient extends RestClient implements CadenaServiceContract {

    public CadenaRestClient(final String url){
        super(url);
    }

    public String health() throws ClientException {
        final String url = getQuery(HEALTH);
        //TODO log
        return call(GET, String.format(url));
    }


    public List<Sucursal> sucursales(final String codigoentidadfederal, final String localidad)
            throws ClientException
    {

        //Contruimos el formato de la query string.
        final String query = getQuery(SUCURSALES,CODIGO_ENTIDAD_FEDERAL,LOCALIDAD);

        //Injectamos los parametros a la query string.
        final String url = String.format(query,codigoentidadfederal,localidad);

        //Hacemos la llamada http
        final String sucursalesJson = call(GET, url);


        Sucursal[] sucs = GSON.toObject(sucursalesJson, Sucursal[].class);

        List<Sucursal> sucursales = Arrays.asList(sucs);

        return sucursales;

    }

    public List<Sucursal> preciosSucursales(String codigoentidadfederal, String localidad, String codigos)
            throws ClientException
    {

        //Validamos parametros.
        if(codigos == null) throw new ClientException("El parametro codigos es null.");


        //Contruimos el formato de la query string
        final String query = getQuery(PRECIOSSUCURSALES,CODIGO_ENTIDAD_FEDERAL, LOCALIDAD, CODIGOS);

        //Injectamos los parametros a la query string.
        final String url = String.format(query,codigoentidadfederal, localidad, codigos);

        //Hacemos la llamada http
        final String preciosJson = call(POST, url);


        Sucursal[] sucs = GSON.toObject(preciosJson, Sucursal[].class);

        List<Sucursal> sucursales = Arrays.asList(sucs);

        return sucursales;
    }
}
