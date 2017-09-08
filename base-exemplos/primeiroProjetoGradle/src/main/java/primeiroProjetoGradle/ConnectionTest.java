package primeiroProjetoGradle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionTest {
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conn = connectionFactory.getConnection();
		ResultSet rs;
		try {
			rs = conn.prepareStatement("show tables").executeQuery();
			while(rs.next()){
	            String s = rs.getString(1);
	            System.out.println(s);
	        }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
