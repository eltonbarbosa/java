package br.com.inatel.jersey;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

@Path("/contatos")
public class ContatoService {
	
	@Context
	private UriInfo uriInfo;
	 
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getContatos() {
		
		try {
			ContatoDAO contatoDAO = new ContatoDAO();
			List contatos = contatoDAO.list();
			GenericEntity entities = new GenericEntity<List<Contato>>(contatos) {};
			return Response.status(Status.OK).entity(entities).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createContato(Contato contato) {
		ContatoDAO contatoDAO;
		try {
			contatoDAO = new ContatoDAO();
			Contato newContato = contatoDAO.insertContato(contato);
			GenericEntity entity = new GenericEntity<Contato>(newContato) {};
			return Response
					.status(Status.CREATED)
					.header("Location", String.format("%s/%s",uriInfo.getAbsolutePath().toString(),newContato.getId()))
					.entity(entity)
					.build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
