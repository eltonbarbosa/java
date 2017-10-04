package br.com.inatel.jersey;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public Connection getConnection() throws Exception{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/dm107", "root", "");
	}
}
