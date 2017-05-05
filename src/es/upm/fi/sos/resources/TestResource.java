
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

@Path("test")
public class TestResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	
	
	/*************************** TestUser **************************
	 URI: 
	 http://localhost:8080/UPMSOCIAL/v0.1/test/{test_id} 		//notación en la memoria
	 http://localhost:8080/UPMSOCIAL/v0.1/test/101			  	//notación en el restful client	
	 */
	@GET
	@Path("/{param_test_id}")//: \\d+}")//solo dígitos
	@Produces({MediaType.TEXT_HTML})//{MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})	
	public String getUser(@PathParam("param_test_id") String testId){				
		return "<body><h1>hola esta es la pagina de Test</h1><h2>"+testId+"</h2></body>";
	}
	
}

