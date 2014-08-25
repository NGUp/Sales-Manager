package BusinessLogicLayer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import DataAccessLayer.SQLQuery;
import DataAccessObject.Product;

public class ProductModel {
	SQLQuery sqlQuery = new SQLQuery();
	
	public List<Product> getProduct(){
		ResultSet result = sqlQuery.selectTable("MatHang");
		List<Product> list = new ArrayList<Product>();
		
		try {
			while (result.next()){
				try {
					Product product = new Product(result.getString("TenMatHang"), result.getString("TenHang"), result.getString("LoaiMatHang"));
					list.add(product);
				} catch(SQLException exception) {
					JOptionPane.showMessageDialog(null, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (SQLException exception) {
			JOptionPane.showMessageDialog(null, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return list;
	}
	
	public List<Product> getProductByTypeProduct(String type){
		ResultSet result = sqlQuery.selectTableWithCondition("MatHang", "LoaiMatHang = N'" + type + "'");
		List<Product> list = new ArrayList<Product>();
		
		try {
			while (result.next()){
				try {
					Product product = new Product(result.getString("TenMatHang"), result.getString("TenHang"), result.getString("LoaiMatHang"));
					list.add(product);
				} catch(SQLException exception) {
					JOptionPane.showMessageDialog(null, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (SQLException exception) {
			JOptionPane.showMessageDialog(null, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return list;
	}
	
	public List<Product> getProductByNameProduct(String type){
		ResultSet result = sqlQuery.selectTableWithCondition("MatHang", "TenMatHang = N'" + type + "'");
		List<Product> list = new ArrayList<Product>();
		
		try {
			while (result.next()){
				try {
					Product product = new Product(result.getString("TenMatHang"), result.getString("TenHang"), result.getString("LoaiMatHang"));
					list.add(product);
				} catch(SQLException exception) {
					JOptionPane.showMessageDialog(null, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (SQLException exception) {
			JOptionPane.showMessageDialog(null, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return list;
	}
	
	public List<Product> getProductByCompany(String type){
		ResultSet result = sqlQuery.selectTableWithCondition("MatHang", "TenHang = N'" + type + "'");
		List<Product> list = new ArrayList<Product>();
		
		try {
			while (result.next()){
				try {
					Product product = new Product(result.getString("TenMatHang"), result.getString("TenHang"), result.getString("LoaiMatHang"));
					list.add(product);
				} catch(SQLException exception) {
					JOptionPane.showMessageDialog(null, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (SQLException exception) {
			JOptionPane.showMessageDialog(null, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return list;
	}
	
	public void updateProduct(String currentName, String currentCompany, String name, String company){
		sqlQuery.updateValues("MatHang", currentName, currentCompany, name, company);
	}
	
	public boolean isExisted(String name, String company, String category){
		ResultSet result = sqlQuery.selectTableWithCondition("MatHang", "TenMatHang = N'" + name + "' and TenHang = N'" + company + "' and LoaiMatHang = N'" + category + "'");
		try{
			if (result.next()){
				return true;
			}
		} catch (SQLException exception){
			JOptionPane.showMessageDialog(null, exception.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		return false;
	}
}
