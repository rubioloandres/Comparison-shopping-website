package ws;

import service.CadenaAPI;
import bean.Sucursal;
import utilities.GSON;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/cadenaRestTwo")
@Produces(MediaType.APPLICATION_JSON)
public class CadenaRestTwo  {

    //TODO: USAR LOGGER
    @GET
    @Path("/health")
    public String health()
    {
        return "OK";
    }

    @GET
    @Path("/sucursales")
    public Response sucursales (@QueryParam("codigoentidadfederal") final String codigoentidadfederal
            ,@QueryParam("localidad") final String localidad)
    {
        try{

            return Response.status(Response.Status.OK).entity(
                    CadenaAPI.sucursales(codigoentidadfederal, localidad)
            ).build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/preciosSucursales")
    public Response preciosSucursales (@QueryParam("codigoentidadfederal") final String codigoEntidadFederal
            ,@QueryParam("localidad") final String localidad
            ,@QueryParam("codigos") final String codigos)
    {

        try{

            return Response.status(Response.Status.OK).entity(
                    CadenaAPI.preciosSucursales(codigoEntidadFederal, localidad, codigos)
            ).build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{ error:" + e.getMessage() + "}")
                    .build();
        }
    }

    @PUT
    @Path("/simularPrecios")
    public Response simularPrecios (@QueryParam("codigoentidadfederal") final String codigoEntidadFederal
            ,@QueryParam("localidad") final String localidad)
    {
        try{
            CadenaAPI.simularPrecios(codigoEntidadFederal,localidad);
            return Response.status(Response.Status.OK).build();
        }
        catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}

