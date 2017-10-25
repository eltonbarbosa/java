package br.com.inatel.services;

import java.util.List;

import br.com.inatel.entities.Produto;

public class ProdutoService {
	private static ProdutoService produtoService = null;
	private ProdutoDAO produtoDAO;
	
	public static ProdutoService getInstance() {
		if (produtoService == null) {
			produtoService = new ProdutoService();
		}
		
		return produtoService;
	}
	
	public ProdutoService() {
		produtoDAO = new ProdutoDAO();
	}
	
	public Produto getProdutoById(Long id) {
		return produtoDAO.getById(new Produto(), id);
	}
	
	public List<Produto> getProdutos(){
		return produtoDAO.all(new Produto());
	}
	
	public void update(Produto produto) {
		produtoDAO.update(produto);
	}
	
}
