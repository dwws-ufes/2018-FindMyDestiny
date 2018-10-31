package find_my_destiny;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import javax.enterprise.inject.Model;

@Model
public class FindMyDestinyController {
	private String visitor = "visitor";
	private Connection connection;
	
	public String getVisitor() {return visitor;}
	
	public void setVisitor(String visitor) {this.visitor= visitor;}
	
	public void connectToMySQLDatabase()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/FindMyDestinyDB","root","root");
		}
		catch(Exception e)
		{
			System.out.println("Erro conexao");
		}
		
	}
	
	public Date getTime() {return new Date();}
}
