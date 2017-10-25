package br.com.inatel.services;

import java.util.ArrayList;
import java.util.List;

import br.com.inatel.entities.Estoque;
import br.com.inatel.entities.EstoqueProduto;
import br.com.inatel.entities.Produto;
import br.com.inatel.entities.StatusProduto;

/*Apenas um 'mock' dos dados, não estou usando banco de dados nessa versão*/
public class DataSource<T> {
	private static DataSource dataSource = null;
	private List<Produto> produtos;
	private List<Estoque> estoques;
	private List<EstoqueProduto> estoquesProdutos;
	
	public static DataSource<?> getInstance() {
		if (dataSource == null) {
			dataSource = new DataSource();
		}
		return dataSource;
	}
	
	public DataSource() {
		estoquesProdutos = new ArrayList<EstoqueProduto>();
		produtos = new ArrayList<Produto>();
		estoques = new ArrayList<Estoque>();
		
		//init produto
		for(int i = 0; i < 50; i ++) {//considerando apenas 50 produtos
			Produto p = new Produto();
			p.setDescricao("Produto " + (i + 1));
			p.setId(new Long((i + 1)));
			produtos.add(p);
		}
		
		//init estoque
		for(int i = 0; i < 3; i ++) {//considerando apenas três estoques
			Estoque e = new Estoque();
			e.setName("Estoque " + (i + 1));
			e.setId(new Long((i + 1)));
			estoques.add(e);
		}
		
		/*Iniciando o estoque produto
		 * Inicialmente estou considerando:
		 * Produto 1 pertence ao Estoque 1
		 * Produto 1 tem 50 Unidades no Estoque 1
		 * Produto 1 está disponível no Estoque 1
		 * EstoqueProduto tem o ID 1
		 * */
		EstoqueProduto estoqueProduto = new EstoqueProduto();
		estoqueProduto.setId(new Long(1));
		estoqueProduto.setProduto(produtos.get(0));
		estoqueProduto.setEstoque(estoques.get(0));
		estoqueProduto.setQuantidade(50);
		estoqueProduto.setStatus(StatusProduto.DISPONIVEL);
		estoquesProdutos.add(estoqueProduto);
		/*Iniciando o estoque produto
		 * Inicialmente estou considerando:
		 * Produto 1 pertence ao Estoque 2
		 * Produto 1 tem 15 Unidades no Estoque 2
		 * Produto 1 está disponível no Estoque 2
		 * EstoqueProduto tem o ID 2
		 * */
		
		estoqueProduto = new EstoqueProduto();
		estoqueProduto.setId(new Long(2));
		estoqueProduto.setProduto(produtos.get(0));
		estoqueProduto.setEstoque(estoques.get(1));
		estoqueProduto.setQuantidade(15);
		estoqueProduto.setStatus(StatusProduto.DISPONIVEL);
		estoquesProdutos.add(estoqueProduto);
		
		/*Iniciando o estoque produto
		 * Inicialmente estou considerando:
		 * Produto 2 pertence ao Estoque 2
		 * Produto 2 tem 25 Unidades no Estoque 2
		 * Produto 2 está disponível no Estoque 2
		 * EstoqueProduto tem o ID 2
		 * */
		
		estoqueProduto = new EstoqueProduto();
		estoqueProduto.setId(new Long(2));
		estoqueProduto.setProduto(produtos.get(1));
		estoqueProduto.setEstoque(estoques.get(1));
		estoqueProduto.setQuantidade(25);
		estoqueProduto.setStatus(StatusProduto.DISPONIVEL);
		estoquesProdutos.add(estoqueProduto);
	}
	
	public boolean add(T entity) {
		if (entity instanceof Produto) {
			return produtos.add((Produto) entity);
		} else if (entity instanceof Estoque) {
			return estoques.add((Estoque) entity);
		} else if (entity instanceof EstoqueProduto) {
			return estoquesProdutos.add((EstoqueProduto) entity);
		}
		
		return false;
	}
	
	public T getElementById(T entity, Long id) {
		if (entity instanceof Produto) {
			for (Produto p : produtos) {
				if (id.equals(p.getId())){
					return (T) p;
				}	
			}
		} else if (entity instanceof Estoque) {
			for (Estoque e : estoques) {
				if (id.equals(e.getId())){
					return (T) e;
				}	
			}
		} else if (entity instanceof EstoqueProduto) {
			for (EstoqueProduto ep : estoquesProdutos) {
				if (id.equals(ep.getId())){
					return (T) ep;
				}
			}
		}
		return null;
	}
	
	public boolean remove(T entity) {
		if (entity instanceof Produto) {
			return produtos.remove(entity);
		} else if (entity instanceof Estoque) {
			return estoques.remove(entity);
		} else if (entity instanceof EstoqueProduto) {
			return estoquesProdutos.remove(entity);
		}
		return false;
	}
	
	public boolean update(T entity) {
		if (entity instanceof Produto) {
			Produto toBeRemoved = null;
			for (Produto p : produtos) {
				if (((Produto) entity).getId().equals(p.getId())){
					toBeRemoved = p;
					if (produtos.remove(toBeRemoved)) {
						return produtos.add((Produto) entity);
					}
				}	
			}
			
		} else if (entity instanceof Estoque) {
			Estoque toBeRemoved = null;
			for (Estoque e : estoques) {
				if (((Estoque) entity).getId().equals(e.getId())){
					toBeRemoved = e;
					if (estoques.remove(toBeRemoved)) {
						return estoques.add((Estoque) entity);
					}
				}	
			}
		} else if (entity instanceof EstoqueProduto) {
			EstoqueProduto toBeRemoved = null;
			for (EstoqueProduto ep : estoquesProdutos) {
				if (((EstoqueProduto) entity).getId().equals(ep.getId())){
					toBeRemoved = ep;
					Produto p = toBeRemoved.getProduto();
					if (p.getId().equals(((EstoqueProduto) entity).getProduto().getId())) {
						if (estoquesProdutos.remove(toBeRemoved)) {
							return estoquesProdutos.add((EstoqueProduto) entity);
						}
					}
				}	
			}
		}
		return false;
	}
	
	public List<T> getAll(T entity) {
		if (entity instanceof Produto) {
			return (List<T>) produtos;
		} else if (entity instanceof Estoque) {
			return (List<T>) estoques;
		} else if (entity instanceof EstoqueProduto) {
			return (List<T>) estoquesProdutos;
		}
		return null;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Estoque> getEstoques() {
		return estoques;
	}

	public void setEstoques(List<Estoque> estoques) {
		this.estoques = estoques;
	}

	public List<EstoqueProduto> getEstoquesProdutos() {
		return estoquesProdutos;
	}

	public void setEstoquesProdutos(List<EstoqueProduto> estoquesProdutos) {
		this.estoquesProdutos = estoquesProdutos;
	}
}
