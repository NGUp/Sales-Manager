package BusinessLogicLayer;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import DataAccessObject.Product;

public class ProductValuesModel extends AbstractTableModel{
	/**
	 * Product
	 */
	private static final long serialVersionUID = -4391953463148979222L;
	
	private List<Product> list;
	public Vector<Vector<String>> rows;
	private Vector<String> columnHeader;
	
	public ProductValuesModel(List<Product> table){
		rows = new Vector<Vector<String>>();
		columnHeader = new Vector<String>();
		list = table;
		
		for (int index = 0; index < list.size(); index++){
			Product product = (Product) list.get(index);
			Vector<String> row = new Vector<String>();
			row.add(Integer.toString(index + 1));
			row.add(product.getName());
			row.add(product.getCompany());
			row.add(product.getCategory());
			rows.add(row);
		}
		
		columnHeader.add("Index");
		columnHeader.add("Product Name");
		columnHeader.add("Company");
		columnHeader.add("Category");
	}
	
	@Override
	public int getColumnCount() {
		return columnHeader.size();
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return rows.elementAt(rowIndex).elementAt(columnIndex);
	}

	@Override
	public String getColumnName(int column){
		return columnHeader.elementAt(column);
	}
}
