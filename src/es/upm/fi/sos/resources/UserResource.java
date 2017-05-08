
package es.upm.fi.sos.resources;

import javax.ws.rs.Path;//para @Path
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;//para @Produces
import javax.ws.rs.QueryParam;//para los parametros pasados después de ? (tipo query)
import javax.ws.rs.core.Context;//para Context object
import javax.ws.rs.core.MediaType;//para MediaType
import javax.ws.rs.core.Request;//para Request object
import javax.ws.rs.core.Response;//para Response object
import javax.ws.rs.core.UriInfo;//para UriInfo object
import javax.xml.bind.JAXBElement;//para serializar

import es.upm.fi.sos.logic.*;
import es.upm.fi.sos.model.*;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;//para @GET
import javax.ws.rs.POST;

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
	
	public String getUser(@PathParam("param_user_id") String userId){
		int idUserInt = (int) Integer.parseInt(userId);
		String xml = null;
		//Response res = Response.ok(entity, type)
		UPMSocialManager myManager = new UPMSocialManagerService();
		System.out.printf("----0>>>>>>>>>> entra en getUser(%s)",userId);
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
	/*************************** getUserList **************************
	 URI: 
	 http://localhost:8080/UPMSOCIAL/v0.1/users/ 		
	 http://localhost:8080/UPMSOCIAL/v0.1/users/			  	
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML})
	public Response getUserList(){
		Response res;
		UPMSocialManager myManager = new UPMSocialManagerService();
		List<User> lista = myManager.getAllUsers();
		res = Response.ok(lista).build();
		return res;
	}
	/*************************** getUserListFiltered **************************
	 URI: 
	 http://localhost:8080/UPMSOCIAL/v0.1/users?name={nombre}&school={escuela}	
	 http://localhost:8080/UPMSOCIAL/v0.1/users?name=saul&school=FI			  	
	 */
	@GET
	@Produces({MediaType.APPLICATION_XML})
	public Response getUserListFiltered(@QueryParam("name") String paramName){
		Response res;
		UPMSocialManager myManager = new UPMSocialManagerService();
		List<User> lista = myManager.getUsersFiltered(paramName);
		res = Response.ok(lista).build();
		return res;
	}
	/*************************** deleteUser **************************
	 URI: 
	 http://localhost:8080/UPMSOCIAL/v0.1/users
	 http://localhost:8080/UPMSOCIAL/v0.1/users	  	
	 */
	@DELETE
	@Path("{param_user_id}")
	public Response deleteUser(@PathParam("param_user_id") String id) {
		int idUserInt = (int) Integer.parseInt(id);
		Response res;		UPMSocialManager myManager = new UPMSocialManagerService();
		if (myManager.userExists(idUserInt)){
			
			User usuario = myManager.getUser(idUserInt);
			myManager.removeUser(usuario);
			res = Response.ok().build();
		} else {
			res = Response.noContent().build();
		}
		return res;
	}
	/*************************** postUser **************************
	 URI: 
	 http://localhost:8080/UPMSOCIAL/v0.1/users
	 http://localhost:8080/UPMSOCIAL/v0.1/users	  	
	 */
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response putTodo(JAXBElement<User> usuarioJAX) {
		User usuario = usuarioJAX.getValue();
		Response res;
		UPMSocialManager myManager = new UPMSocialManagerService();
		String aviso  = "<!doctype html>"+
				    	"<html lang=\"en\"<head><title>AVISO</title></head><body><h1>EL USUARIO YA EXISTE</h1></body></html>";
		if (myManager.userExists(usuario.getId())){
			myManager.createUser(usuario);	
			res = Response.created(uriInfo.getAbsolutePath()).build();
		} else {
			
			return Response.status(403).type("text/HTML")
	                .entity(aviso).build();
		}
		return res;
	}
	
}
