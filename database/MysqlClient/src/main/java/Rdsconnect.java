import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Rdsconnect {
	
	public static Connection connectJDBCToAWSEC2() {

	    System.out.println("----MySQL JDBC Connection Testing -------");
	    
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        System.out.println("Where is your MySQL JDBC Driver?");
	        e.printStackTrace();
	        return null;
	    }

	    System.out.println("MySQL JDBC Driver Registered!");
	    Connection connection = null;

	    try {
	        connection = DriverManager.
	                getConnection("jdbc:mysql://revtest.chhyet6jkerf.eu-west-1.rds.amazonaws.com:3306/pipefish", "root", "Changem3");
	    } catch (SQLException e) {
	        System.out.println("Connection Failed!:\n" + e.getMessage());
	    }

	    if (connection != null) {
	        System.out.println("SUCCESS!!!! You made it, take control     your database now!");
	    } else {
	        System.out.println("FAILURE! Failed to make connection!");
	    }
	    return connection;
	}
	
	private static void runTestQuery(Connection conn) {
	    Statement statement = null;
	    try {

	        System.out.println("Creating statement...");
	        statement = conn.createStatement();
	        String sql;
	        sql = "CREATE TABLE users ( id smallint unsigned not null auto_increment, user_name varchar(20), date_of_birth varchar(20), constraint pk_example primary key (id) )";
	        statement.executeQuery(sql);
	        statement.close();
	        conn.close();
	    } catch (SQLException se) {
	        //Handle errors for JDBC
	        se.printStackTrace();
	    } catch (Exception e) {
	        //Handle errors for Class.forName
	        e.printStackTrace();
	    } finally {
	        //finally block used to close resources
	        try {
	            if (statement != null)
	                statement.close();
	        } catch (SQLException se2) {
	        }// nothing we can do
	        try {
	            if (conn != null)
	                conn.close();
	        } catch (SQLException se) {
	            se.printStackTrace();
	        }//end finally try
	    }//end try
	}
	
	public static void main(String [] args) {
		Connection conn = connectJDBCToAWSEC2();
		runTestQuery(conn);
	}


}
