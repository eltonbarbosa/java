package br.com.inatel.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;

import br.com.inatel.entities.EstoqueProduto;
import br.com.inatel.entities.Produto;
import br.com.inatel.entities.StatusProduto;
import br.com.inatel.services.EstoqueProdutoService;

@Path("/estoques")
public class ApiServiceEstoqueProduto{
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/produtos")
	public Response getEstoques() {
	EstoqueProdutoService estoqueProdutoService = EstoqueProdutoService.getInstance();
	List<EstoqueProduto>estoquesProdutos = estoqueProdutoService.getEstoquesProdutos();
	return
			Response
				.status(200)
				.entity(estoquesProdutos)
				.build();
	}
	
	@GET
	@Path("/{nomeEstoque}/produtos")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getProdutosPorEstoque(@PathParam("nomeEstoque") String nomeEstoque) {
		EstoqueProdutoService estoqueProdutoService = EstoqueProdutoService.getInstance();
		List<Produto> produtos = estoqueProdutoService.getProdutosPorEstoqueNome(nomeEstoque);
		return
				Response
					.status(200)
					.entity(produtos)
					.build();
	}
	
	@GET
	@Path("/{nomeEstoque}/{idProduto}/quantidade")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getQuantidadeDoProdutoPorEstoque(
			@PathParam("nomeEstoque") String nomeEstoque,
			@PathParam("idProduto") Long idProduto) {
		EstoqueProdutoService estoqueProdutoService = EstoqueProdutoService.getInstance();
		List<EstoqueProduto> produtosEstoque = estoqueProdutoService.getQuantidadeDoProdutoPorEstoqueNome(nomeEstoque, idProduto);
		return
				Response
					.status(200)
					.entity(produtosEstoque)
					.build();
	}
	
	@DELETE
	@Path("/{nomeEstoque}/{idProduto}/remove")
	@Produces({MediaType.APPLICATION_JSON})
	public Response removerProdutoDoEstoque(
			@PathParam("nomeEstoque") String nomeEstoque,
			@PathParam("idProduto") Long idProduto) {
		EstoqueProdutoService estoqueProdutoService = EstoqueProdutoService.getInstance();
		boolean removido = estoqueProdutoService.removerProdutoDoEstoque(nomeEstoque, idProduto);
		if (removido) {
			return
				Response
				.status(200)
				.build();
		} else {
			return
				Response
				.status(Status.NOT_FOUND)
				.build();
		}
	}
	
	@POST
	@Path("/{nomeEstoque}/{idProduto}/status")
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_JSON})
	public Response atualizarStatusDoProdutoNoEstoque(
			@PathParam("nomeEstoque") String nomeEstoque,
			@PathParam("idProduto") Long idProduto,
			String requestBody) {
		EstoqueProdutoService estoqueProdutoService = EstoqueProdutoService.getInstance();
		Gson gson = new Gson();
		StatusHelper statusHelper = gson.fromJson(requestBody, StatusHelper.class);
		if (!statusHelper.getStatus().toUpperCase().equals(StatusProduto.DISPONIVEL.toString()) &&
			!statusHelper.getStatus().toUpperCase().equals(StatusProduto.INDISPONIVEL.toString())) {
			return Response
				   .status(Status.BAD_REQUEST)
				   .build();
		}
		StatusProduto statusProduto = statusHelper.getStatus().toUpperCase().equals(StatusProduto.DISPONIVEL.toString()) ? StatusProduto.DISPONIVEL : StatusProduto.INDISPONIVEL;
		boolean statusAtualizado = estoqueProdutoService.atualizarStatusDoProdutoNoEstoque(nomeEstoque, idProduto, statusProduto);
		if (statusAtualizado) {
			return
				Response
				.status(200)
				.build();
		} else {
			return
				Response
				.status(Status.NOT_FOUND)
				.build();
		}
	}
	
	@GET
	@Path("/produtos/quantidadeMinima")
	@Produces({MediaType.APPLICATION_JSON})
	public Response verificaEstoqueMinimo() {
		EstoqueProdutoService estoqueProdutoService = EstoqueProdutoService.getInstance();
		List<EstoqueProduto>produtosEmEstoqueMinimo = estoqueProdutoService.listarProdutosEmEstoqueMinimo();
		if (produtosEmEstoqueMinimo != null && !produtosEmEstoqueMinimo.isEmpty()) {
			return Response
			.status(200)
			.entity(produtosEmEstoqueMinimo)
			.build();
		} else {
			return Response
			.status(Status.NO_CONTENT)
			.build();
		}
	}
	
	/*Class interna apenas para converter o JSON
	 * {"status" : "Indisponivel"} para objeto
	 * */
	private class StatusHelper{
		private String status;

		public String getStatus() {
			return status;
		}
	}
}
