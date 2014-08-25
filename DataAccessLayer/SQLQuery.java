package DataAccessLayer;

import java.sql.ResultSet;

public class SQLQuery {
	SQL connect = new SQL();
	
	public ResultSet selectTable(String tableName){
		String sql = "Select * From " + tableName;
		return connect.executeQuery(sql);
	}
	
	public ResultSet selectTableWithCondition(String tableName, String condition){
		String sql = "Select * From " + tableName + " Where " + condition;
		return connect.executeQuery(sql);
	}
	
	public void insertValues(String tableName, String values){
		String sql = "Insert " + tableName + " Values (" + values + ")";
		connect.executeUpdate(sql);
	}
	
	public void updateValues(String tableName, String currentName, String currentCompany,
							String name, String company){
		String sql = "Update " + tableName + 
					" Set TenMatHang = N'" + name + "', TenHang = N'" + company + 
					"' Where (TenMatHang = N'" + currentName + "' and TenHang = N'" + currentCompany +  "')";
		connect.executeUpdate(sql);
	}
	
	public void deleteValues(String tableName, String condition){
		String sql = "Delete " + tableName + " Where (" + condition + ")";
		connect.executeUpdate(sql);
	}
	
	public ResultSet selectDistinctCondition(String tableName, String values){
		String sql = "Select Distinct(" + values + ") From " + tableName;
		return connect.executeQuery(sql);
	}
	
	public ResultSet getList(String tableName, String condition, String values){
		String sql = "Select " + values + " From " + tableName + " Where (" + condition + ")";
		return connect.executeQuery(sql);
	}
	
	public void close(){
		connect.close();
	}
}
