package br.com.inatel.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.inatel.entities.Estoque;
import br.com.inatel.services.EstoqueService;

@Path("/estoques")
public class ApiServiceEstoque {
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getEstoques() {
		EstoqueService estoqueService = EstoqueService.getInstance();
		List<Estoque>estoques = estoqueService.getEstoques();
		return
				Response
					.status(200)
					.entity(estoques)
					.build();
	}

}
