package br.com.inatel.services;

import java.util.List;

import br.com.inatel.entities.Estoque;

public class EstoqueService {
	
	private static EstoqueService estoqueService = null;
	private EstoqueDAO estoqueDAO;
	
	public static EstoqueService getInstance() {
		if (estoqueService == null) {
			estoqueService = new EstoqueService();
		}
		return estoqueService;
	}
	
	public EstoqueService() {
		estoqueDAO = new EstoqueDAO();
	}
	
	public List<Estoque> getEstoques(){
		return estoqueDAO.all(new Estoque());
	}
}

