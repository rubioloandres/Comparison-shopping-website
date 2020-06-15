package ws;


import service.CadenaAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(targetNamespace = "http://ws.das.edu.ubp.ar/"
           ,portName = "CadenaCXFOnePort"
           ,serviceName = "CadenaCXFOneService")

public class CadenaCXFOne {

    private static final Logger logger = LoggerFactory.getLogger(CadenaCXFOne.class);

    @WebMethod(operationName = "health", action = "urn:Health")
    public String health() {
        return "OK";
    }


    @WebMethod(operationName = "sucursales", action = "urn:Sucursales")
    public String sucursales
            (@WebParam(name = "codigoentidadfederal") final String codigoentidadfederal
            ,@WebParam(name = "localidad") final String localidad) throws Exception {


        return CadenaAPI.sucursales(codigoentidadfederal, localidad);
    }

    @WebMethod(operationName = "preciosSucursales", action = "urn:PreciosSucursales")
    public String preciosSucursales
            (@WebParam(name = "codigoentidadfederal") final String codigoentidadfederal
            ,@WebParam(name = "localidad") final String localidad
            ,@WebParam(name = "codigos") final String codigos) throws Exception{

        return CadenaAPI.preciosSucursales(codigoentidadfederal,localidad,codigos);
    }

    @WebMethod(operationName = "simularPrecios", action = "urn:SimularPrecios")
    public void simularPrecios
            (@WebParam(name = "codigoentidadfederal") final String codigoentidadfederal
            ,@WebParam(name = "localidad") final String localidad
            ,@WebParam(name = "codigos") final String codigos) throws Exception{

        CadenaAPI.simularPrecios(codigoentidadfederal,localidad);
        return;
    }

}
