package br.com.inatel.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.inatel.entities.Produto;
import br.com.inatel.services.ProdutoService;

@Path("/produtos")
public class ApiServiceProdutos {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Response getProdutos() {
		ProdutoService produtdoService = ProdutoService.getInstance();
		List<Produto>produtos = produtdoService.getProdutos();
		return
				Response
					.status(200)
					.entity(produtos)
					.build();
	}
	
	@GET
	@Path("/{idProduto}")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getProdutoById(@PathParam("idProduto") Long idProduto) {
		ProdutoService produtdoService = ProdutoService.getInstance();
		Produto produto = produtdoService.getProdutoById(idProduto);
		return
				Response
					.status(200)
					.entity(produto)
					.build();
	}
	
	@PUT
	@Path("/{idProduto}")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response updateProduto(@PathParam("idProduto") Long idProduto, Produto produtoAtualizar) {
		ProdutoService produtdoService = ProdutoService.getInstance();
		Produto p = produtdoService.getProdutoById(idProduto);
		if (p == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		p.setDescricao(produtoAtualizar.getDescricao());
		produtdoService.update(p);
		
		return Response
		.status(Status.OK)
		.entity(p)
		.build();
		
	}
}
