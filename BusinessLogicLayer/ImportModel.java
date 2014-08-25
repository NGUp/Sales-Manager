package BusinessLogicLayer;

import java.sql.ResultSet;

import DataAccessLayer.SQLQuery;

public class ImportModel {
	SQLQuery sqlQuery = new SQLQuery();

	public void insert(String name, String company, String category, String dateTime){
		sqlQuery.insertValues("PhieuNhap", "N'" + name + "', N'" + company + "', N'" + category + "', '" + dateTime + "'");
		sqlQuery.insertValues("MatHang", "N'" + name + "', N'" + company + "', N'" + category + "'");
	}

	public ResultSet getImportingList(){
		return sqlQuery.selectDistinctCondition("PhieuNhap", "NgayNhap");
	}

	public ResultSet loadImportingList(String dateTime){
		return sqlQuery.selectTableWithCondition("PhieuNhap", "NgayNhap = '" + dateTime + "'");
	}
}
