package DataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class SQL {
	private Statement statement = null;
	private Connection connection = null;
	private ResultSet result = null;
	
	public Connection getConnection(){
		try{
			String url = "jdbc:sqlserver://" + Config.server + ";databaseName=" + Config.database + ";integratedSecurity=true";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			connection = DriverManager.getConnection(url);
		} catch(Exception exception){
			JOptionPane.showMessageDialog(null, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return connection;
	}
	
	public Statement getStatement(){
		try{
			if (statement == null ? true : statement.isClosed()){
				statement = getConnection().createStatement();
			}
		} catch(Exception exception){
			JOptionPane.showMessageDialog(null, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return statement;
	}
	
	public ResultSet executeQuery(String sql){
		try{
			result = getStatement().executeQuery(sql);
		} catch(Exception exception){
			JOptionPane.showMessageDialog(null, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return result;
	}
	
	public int executeUpdate(String sql){
		int res = Integer.MIN_VALUE;
		
		try{
			res = getStatement().executeUpdate(sql);
		} catch(Exception exception){
			JOptionPane.showMessageDialog(null, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		} finally{
			close();
		}
		
		return res;
	}
	
	public void close(){
		try{
			if (result != null && !result.isClosed()){
				result.close();
				result = null;
			}
			if (statement != null && !statement.isClosed()){
				statement.close();
				statement = null;
			}
			if (connection != null && !connection.isClosed()){
				connection.close();
				connection = null;
			}
		} catch(Exception exception){
			JOptionPane.showMessageDialog(null, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
