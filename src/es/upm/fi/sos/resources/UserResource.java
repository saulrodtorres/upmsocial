
package es.upm.fi.sos.resources;

import javax.ws.rs.Path;//para @Path
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;//para @Produces
import javax.ws.rs.core.Context;//para Context object
import javax.ws.rs.core.MediaType;//para MediaType
import javax.ws.rs.core.Request;//para Request object
import javax.ws.rs.core.Response;//para Response object
import javax.ws.rs.core.UriInfo;//para UriInfo object
import javax.xml.bind.JAXBElement;

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
	 http://localhost:8080/UPMSOCIAL/v0.1/users/{user_id} 		//
	 http://localhost:8080/UPMSOCIAL/v0.1/users/1			  	//usuario -> saul
	 */
	
	@GET
	@Path("/{param_user_id}")//: \\d+}")//solo dígitos
	@Produces({MediaType.APPLICATION_XML})	
	
	public Response getUser(@PathParam("param_user_id") String userId){
		int idUserInt = Integer.parseInt(userId);
		System.out.println("----0>>>>>>>>>>ENTRO EN getUser*****************************************");
		Response res=null;
		UPMSocialManager myManager = new UPMSocialManagerService();
		if (myManager.userExists(idUserInt)){
			System.out.println("----3b>>>>>>>>>>entra en el if*********");

			User usuario = myManager.getUser(idUserInt);
			res = Response.ok(usuario).build();
			System.out.println("----4a>>>>>>>>>>El usuario existe dentro de getUser()*********");
		}
		else{
			System.out.println("----4b>>>>>>>>>>El usuario no existe dentro de getUser(), revisa la db*********");
			res = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		return res;
		
		
	}
	
}
