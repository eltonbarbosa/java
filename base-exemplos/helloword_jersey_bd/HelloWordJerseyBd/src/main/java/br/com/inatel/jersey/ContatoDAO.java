package br.com.inatel.jersey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
	
	private Connection conn = null;
	
	public ContatoDAO(){
		if (conn == null) {
			try {
				this.conn = new ConnectionFactory().getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Contato> list() throws SQLException{
		String sql = "SELECT *FROM contato";
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		Contato contato = null;
		List<Contato>contatos = new ArrayList<Contato>();
		
		while(rs.next()) {
			contato = new Contato();
			contato.setId(rs.getInt("id"));
			contato.setName(rs.getString("name"));
			contato.setEmail(rs.getString("email"));
			contato.setCel(rs.getString("cel"));
			contatos.add(contato);
		}
		return contatos;
	}
	
	public Contato insertContato (Contato contato) throws SQLException {
		String sql = "INSERT INTO contato (name, email, cel) VALUES (?, ?, ?);";
		PreparedStatement pstm;
		pstm = conn.prepareStatement(sql);
		pstm.setString(1, contato.getName());
		pstm.setString(2, contato.getEmail());
		pstm.setString(3, contato.getCel());
		pstm.execute();
		
		//get last inserted
		return getLastInserted();
	}
	
	private Contato getLastInserted()  throws SQLException{
		String sql = "SELECT *FROM contato WHERE id = (SELECT MAX(id) FROM contato)";
		Statement stm = conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		Contato contato = null;
		
		while(rs.next()) {
			contato = new Contato();
			contato.setId(rs.getInt("id"));
			contato.setName(rs.getString("name"));
			contato.setEmail(rs.getString("email"));
			contato.setCel(rs.getString("cel"));
		}
		
		return contato;
	}
}
