package br.com.inatel.jersey;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.inatel.security.Secured;

@Path("/hello")
public class HelloWordJersey {
	
	@Secured
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response helloJersey() {
		List<HelloEntity> items = HelloWordDAO.getInstance().getItems();
		GenericEntity entity = new GenericEntity<List<HelloEntity>>(items) {};
		return Response
		.ok()
		.entity(entity)
		.build();
	}
	
	@GET
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getItem(@PathParam("id") Long id) {
		HelloEntity item = HelloWordDAO.getInstance().getItemById(id);
		if (item != null) {
			GenericEntity entity = new GenericEntity<HelloEntity>(item) {};
			return Response
					.status(Status.OK)
					.entity(entity)
					.build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response createItem(HelloEntity item){
		HelloWordDAO.getInstance().createItem(item);
		GenericEntity entity = new GenericEntity<HelloEntity>(item) {};
		return Response
				.status(Status.CREATED)
				.entity(entity)
				.build();
	}
	
	@PUT
	@Path("{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response updateItem(@PathParam("id") Long id, HelloEntity itemToUpdate) {
		HelloEntity item = HelloWordDAO.getInstance().getItemById(id);
		if (item != null) {
			item.setName(itemToUpdate.getName());
			itemToUpdate = HelloWordDAO.getInstance().updateItem(item);
			GenericEntity entity = new GenericEntity<HelloEntity>(itemToUpdate) {};
			return Response
					.status(Status.OK)
					.entity(entity)
					.build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteItem(@PathParam("id") Long id) {
		boolean delete = HelloWordDAO.getInstance().delete(id);
		if (delete) {
			return Response
					.status(Status.OK)
					.build();
		} else {
			return Response
					.status(Status.NOT_FOUND)
					.build();
		}
	}
	
}
