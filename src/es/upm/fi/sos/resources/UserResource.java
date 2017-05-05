
package es.upm.fi.sos.resources;

import javax.ws.rs.Path;//para @Path
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;//para @Produces
import javax.ws.rs.core.Context;//para Context object
import javax.ws.rs.core.MediaType;//para MediaType
import javax.ws.rs.core.Request;//para Request object
import javax.ws.rs.core.Response;//para Response object
import javax.ws.rs.core.UriInfo;//para UriInfo object

import es.upm.fi.sos.logic.*;
import es.upm.fi.sos.model.*;
import javax.ws.rs.GET;//para @GET

@Path("users")
public class UserResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	
	
	/*************************** getUser **************************
	 URI: 
	 http://localhost:8080/UPMSOCIAL/v0.1/users/{user_id} 		//notación en la memoria
	 http://localhost:8080/UPMSOCIAL/v0.1/users/101			  	//notación en el restful client	
	 */
	@GET
	@Path("/{param_user_id}")//: \\d+}")//solo dígitos
	@Produces({MediaType.APPLICATION_XML})	
	public Response getUser(@PathParam("param_user_id") String userId){
		int idUserInt = Integer.parseInt(userId);
		System.out.println("AQUI LLEGO*****************************************");
		Response res=null;
		UPMSocialManager myManager = new UPMSocialManagerService();
		if (myManager.userExists(idUserInt)){
			User usuario = myManager.getUser(idUserInt);
			res = Response.ok(usuario).build();
			System.out.println("NO SE SI LLEGO *****************************************");
		}
		else{
			res = Response.status(Response.Status.NOT_FOUND).build();
		}
		return res;
		
		
	}
	
}
