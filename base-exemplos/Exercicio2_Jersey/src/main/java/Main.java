/*
import br.com.inatel.entities.EstoqueProduto;
import br.com.inatel.entities.Produto;
import br.com.inatel.services.DataSource;
import br.com.inatel.services.EstoqueProdutoDAO;

public class Main {

	private static DataSource ds;

	public static void main(String[] args) {
		EstoqueProdutoDAO epDao = new EstoqueProdutoDAO();
		ds = epDao.getDs();
		for(Object obj : ds.getEstoquesProdutos()) {
			EstoqueProduto ep = (EstoqueProduto) obj;
			System.out.println(ep.getEstoque().getName());
			System.out.println(ep.getProduto().getDescricao());
			System.out.println(ep.getQuantidade());
			System.out.println(ep.getStatus());
			System.out.println("-----------------");
		}
		
		Produto pNew = new Produto();
		pNew.setDescricao("NEW PPPP");
		pNew.setId(new Long(100));
		ds.add(pNew);
		
		for(Object obj : ds.getProdutos()) {
			Produto p = (Produto)obj;
			System.out.println(p.getDescricao());
			System.out.println("-----------------");
		}
		
		Produto pEdit = new Produto();
		pEdit.setDescricao("NEW PPPP - Edit");
		pEdit.setId(new Long(100));
		ds.update(pEdit);
		
		for(Object obj : ds.getProdutos()) {
			Produto p = (Produto)obj;
			System.out.println(p.getDescricao());
			System.out.println("-----------------");
		}
		
		Produto pRemove = (Produto) ds.getElementById(new Produto(), new Long(100));
		ds.remove(pRemove);
		
		for(Object obj : ds.getProdutos()) {
			Produto p = (Produto)obj;
			System.out.println(p.getDescricao());
			System.out.println("-----------------");
		}
	}

}*/
