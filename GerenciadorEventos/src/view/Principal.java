package view;

import java.sql.Connection;
import connection.ConnectionFactory;

public class Principal {
	
	public static void main(String [] args) {
		Connection connection = ConnectionFactory.getConnection();
		System.out.println("Conectado!");
		
		
	}

}
