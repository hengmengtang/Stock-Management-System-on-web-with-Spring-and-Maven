package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnection {
	private static dbConnection instance;
	private static Connection cnn=null;
	
	private dbConnection(){	
	}
	
	public static dbConnection getInstance(){
		if(instance==null){
			instance=new dbConnection();
		}
		
		return instance;
	}
	
	public Connection getConnection(){
		if(cnn==null){
			try {
				Class.forName("org.postgresql.Driver");
				String url="jdbc:postgresql://localhost:5432/Stocks";
				String name="postgres";
				String pass="1234";
				cnn=DriverManager.getConnection(url,name,pass);
				System.out.println("Connected!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return cnn;
	}
	
}
