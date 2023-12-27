package inueron.Log4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
//import org.apache.log4j.Level;
import org.apache.log4j.SimpleLayout;

public class JdbcApp {
	
	
	public static Logger logger=Logger.getLogger(JdbcApp.class);
	static {
		
		
		//create objects for layouts 
		SimpleLayout simplelayout=new SimpleLayout();
		ConsoleAppender appender=new ConsoleAppender(simplelayout);
       logger.addAppender(appender);	
	
        logger.setLevel(Level.DEBUG);
	}
	
	public static void main(String[] args) throws SQLException {
		
		
		Statement statement=null;
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter id number : ");
		int id=sc.nextInt();
		System.out.println("Enter Name : ");
		String name=sc.next();
		System.out.println("Enter Dept : ");
		String dept=sc.next();
		System.out.println("Enter Salary : ");
		double salary=sc.nextDouble();
		
		
		//jdbc logic
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			logger.debug("Driver loading ....");
			
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/empdetails","root","2003Ganesh@");
			logger.info("connection established .. ");
			
			Statement stmt=con.createStatement();
			
			logger.debug("object created .. ");
			
			String sql="insert into emp values("+id+",'"+name+"','"+dept+"',"+salary+")";
			int r=stmt.executeUpdate(sql);
			logger.info("inserted success");
			
			if(r>0) {
				System.out.println("inserted successfully");
			}
			else
				System.out.println("Error!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("ClassNotFoundException occured..."+e.getMessage());
		}catch(SQLException r) {
			logger.error("ClassNotFoundException occured...");
			
		}catch(Exception e) {
			logger.error("unknow exception occured...");
			
		}finally {
			try {
				if(statement!=null) {
					statement.close();
				}
			}catch(SQLException r) {
				logger.error("unknow exception occured..."+r.getMessage() +" error code "+r.getErrorCode());
			}
		}
		
	}

}
