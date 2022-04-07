package Helper;
import java.sql.*;


public class DBConnection {
	Connection c = null;
	//String url = "jdbc:sqlserver://DESKTOP-EFD0KAH:1433;databasename=hospital"encrypt=true;trustServerCertificate=true;
	String url = "jdbc:sqlserver://DESKTOP-EFD0KAH:1433;DatabaseName= Hospital ;encrypt=true;trustServerCertificate=true";
	String user="sa";
	String password="123";
	
	public DBConnection() {}
	
	public Connection connDb() {
		try {
			this.c = DriverManager.getConnection(url,user,password);
			System.out.println("connected.");
			return c;
		} catch (SQLException e) {
			System.out.println("oopss");
			e.printStackTrace();
		}
		return c;
	}
}
