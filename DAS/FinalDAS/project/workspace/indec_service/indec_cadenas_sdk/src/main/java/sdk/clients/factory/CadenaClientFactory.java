package sdk.clients.factory;

import sdk.clients.CadenaRestClient;
import sdk.clients.CadenaSoapClient;
import sdk.clients.exceptions.ClientException;
import sdk.contract.CadenaServiceContract;

public class CadenaClientFactory {

    public static CadenaServiceContract  clientFor (final String url,final String tecnologia)
            throws ClientException
    {
        CadenaServiceContract sc;
        if (tecnologia.trim().toUpperCase().equals("REST")) {
            sc = new CadenaRestClient(url);
        }else if (tecnologia.trim().toUpperCase().equals("SOAP")) {
            sc = new CadenaSoapClient(url);
        } else  throw new ClientException ("No se pudo crear el cliente, verifique los parametros..");

        if(sc==null){
            throw new  ClientException("El cliente creado por CadenaClientFactory es null");
        }else{
            return sc;
        }
    }
}