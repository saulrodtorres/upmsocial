package es.upm.fi.sos.persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public abstract class AbstractDAO {
	
	private Connection connection;
	protected String url = "jdbc:mysql://localhost:3306/upmsocial";
	protected String user = "root";
	protected String pass = "releasethekraken";//change it to yours
	
	public AbstractDAO() {
	}
	
	protected void openConnection() throws Exception{
		System.out.println("Abrir Conexion");		
		this.connection = DriverManager.getConnection(url,user,pass);
		
	}
	
	protected void closeConnection() throws Exception{
		if(this.connection != null){
			System.out.println("Cerrar Conexion");
			this.connection.close();
		}
	}
	
	protected Connection getConnection(){
		return this.connection;
	}
	
}
