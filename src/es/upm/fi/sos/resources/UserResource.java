
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
	@Path("/{param_user_id}")//: \\d+}")//solo d�gitos
	@Produces({MediaType.APPLICATION_XML})	
	
	public String getUser(@PathParam("param_user_id") String userId){
		int idUserInt = Integer.parseInt(userId);
		String xml = null;
		//Response res = Response.ok(entity, type)
		UPMSocialManager myManager = new UPMSocialManagerService();
		
		if (myManager.userExists(idUserInt)){
			
			User usuario = myManager.getUser(idUserInt);
			xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standolane=\"yes\">"+
					"<user>"+
					"<username>"+usuario.getName()+"</username>"+
					"<school>"+usuario.getSchool()+"</school>"+
					"<photo>"+usuario.getPhoto()+"</photo>"+
					"</user>";
		}
		else{
					System.out.println("||||||||||>>>>>>>Tenemos un problema");	
		}
		return xml;
	}
	
}
