package br.com.inatel.services;

import java.util.ArrayList;
import java.util.List;

import br.com.inatel.entities.EstoqueProduto;
import br.com.inatel.entities.Produto;
import br.com.inatel.entities.StatusProduto;

public class EstoqueProdutoService {
	private static final int ESTOQUE_MINIMO = 10;
	private static EstoqueProdutoService estoqueProdutoService = null;
	private EstoqueProdutoDAO estoqueProdutdoDAO;
	
	public static EstoqueProdutoService getInstance() {
		if (estoqueProdutoService == null) {
			estoqueProdutoService = new EstoqueProdutoService();
		}
		
		return estoqueProdutoService;
	}
	
	public EstoqueProdutoService() {
		estoqueProdutdoDAO = new EstoqueProdutoDAO();
	}
	
	public List<EstoqueProduto> getEstoquesProdutos(){
		return estoqueProdutdoDAO.all(new EstoqueProduto());
	}
	
	public List<Produto>getProdutosPorEstoqueNome(String estoqueNome){
		List<Produto>produtos = new ArrayList<Produto>();
		for (EstoqueProduto ep : estoqueProdutdoDAO.all(new EstoqueProduto())) {
			if (ep.getEstoque().getName().equals(estoqueNome)) {
				produtos.add(ep.getProduto());
			}
		}
		return produtos;
	}
	
	public List<EstoqueProduto>getQuantidadeDoProdutoPorEstoqueNome(String estoqueNome, Long idProduto){
		List<EstoqueProduto>produtosEstoque = new ArrayList<EstoqueProduto>();
		for (EstoqueProduto ep : estoqueProdutdoDAO.all(new EstoqueProduto())) {
			if (ep.getEstoque().getName().equals(estoqueNome) &&
					ep.getProduto().getId().equals(idProduto) &&
					ep.getStatus().equals(StatusProduto.DISPONIVEL)) {
				produtosEstoque.add(ep);
			}
		}
		return produtosEstoque;
	}
	
	/*Removendo item do estoque*/
	public boolean removerProdutoDoEstoque(String nomeEstoque, Long idProduto) {
		boolean atualizado = false;
		for (EstoqueProduto ep : estoqueProdutdoDAO.all(new EstoqueProduto())) {
			if(ep.getEstoque().getName().equals(nomeEstoque) &&
			   ep.getProduto().getId().equals(idProduto)) {
				int quantidade = ep.getQuantidade();
				if (quantidade > 0) {
					ep.setQuantidade(quantidade - 1);
					estoqueProdutdoDAO.update(ep);
					atualizado = true;
					break;
				}
			}
		}
		return atualizado;
	}
	
	public boolean adicionarProdutoDoEstoque(String nomeEstoque, Long idProduto) {
		boolean atualizado = false;
		for (EstoqueProduto ep : estoqueProdutdoDAO.all(new EstoqueProduto())) {
			if(ep.getEstoque().getName().equals(nomeEstoque) &&
			   ep.getProduto().getId().equals(idProduto)) {//caso exista produto no estoque apenas incrementa a quantidade
				int quantidade = ep.getQuantidade();
				ep.setQuantidade(quantidade + 1);
				estoqueProdutdoDAO.update(ep);
				atualizado = true;
				break;
			}
		}
		
		return atualizado;
	}
	
	public boolean atualizarStatusDoProdutoNoEstoque(String nomeEstoque, Long idProduto, StatusProduto status) {
		boolean atualizado = false;
		for (EstoqueProduto ep : estoqueProdutdoDAO.all(new EstoqueProduto())) {
			if(ep.getEstoque().getName().equals(nomeEstoque) &&
			   ep.getProduto().getId().equals(idProduto)) {
			   ep.setStatus(status);
			   estoqueProdutdoDAO.update(ep);
			   atualizado = true;
			   break;
			}
		}
		
		return atualizado;
	}
	
	public List<EstoqueProduto> listarProdutosEmEstoqueMinimo() {
		List<EstoqueProduto> produtosEmEstoqueMinimo = new ArrayList<EstoqueProduto>();
		for (EstoqueProduto ep : estoqueProdutdoDAO.all(new EstoqueProduto())) {
			int quantidade = ep.getQuantidade();
			if(quantidade <= ESTOQUE_MINIMO) {
				produtosEmEstoqueMinimo.add(ep);
			}
		}
		return produtosEmEstoqueMinimo;
	}
}
