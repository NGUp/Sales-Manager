package BusinessLogicLayer;

import java.sql.ResultSet;

import DataAccessLayer.SQLQuery;

public class ExportingModel {
	SQLQuery sqlQuery = new SQLQuery();
	
	public void export(String name, String company, String category, String dateTime){
		String values = "N'" + name + "', N'" + company + "', N'" + category + "', '" + dateTime + "'";
		String condition = "TenMatHang = N'" + name + "' and TenHang = N'" + company + "'";
		sqlQuery.insertValues("HoaDon", values);
		sqlQuery.deleteValues("MatHang", condition);
	}
	
	public ResultSet getExportingList(){
		return sqlQuery.selectDistinctCondition("HoaDon", "NgayXuat");
	}
	
	public ResultSet loadExportingList(String dateTime){
		return sqlQuery.selectTableWithCondition("HoaDon", "NgayXuat = '" + dateTime + "'");
	}
	
	public ResultSet getListByName(String condition){
		return sqlQuery.getList("MatHang", "TenMatHang Like '%" + condition + "%'", "Distinct(TenMatHang)");
	}
	
	public ResultSet getListByCompany(String condition){
		return sqlQuery.getList("MatHang", "TenHang Like '%" + condition + "%'", "Distinct(TenHang)");
	}
	
	public ResultSet getListByCategory(String condition){
		return sqlQuery.getList("MatHang", "LoaiMatHang Like '%" + condition + "%'", "Distinct(LoaiMatHang)");
	}
}
