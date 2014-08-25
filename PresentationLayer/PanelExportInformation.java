package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import BusinessLogicLayer.ProductModel;

public class PanelExportInformation extends JPanel{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 8470009717937051066L;
	private static PanelExportInformation getInstance = null;
	private JLabel labelName;
	private JLabel labelCompany;
	private JLabel labelCategory;
	private JTextField name;
	private JTextField company;
	private JComboBox<String> category;
	private JPanel panel1;
	private JPanel panel2;
	
	public PanelExportInformation(){
		setLayout(new BorderLayout());

		labelName = new JLabel("Product Name: ");
		labelCompany = new JLabel("Company: ");
		labelCategory = new JLabel("Category: ");
		name = new JTextField(30);
		company = new JTextField(30);
		category = new JComboBox<String>();
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		category.setModel(new DefaultComboBoxModel<String>(new String[] {
				"Điện thoại, máy tính bảng",
				"Laptop",
				"Quần áo, giày dép",
				"Máy ảnh số",
				"Ba lô, túi xách",
				"Sách báo, tạp chí",
				"Xe hơi",
				"Rượu vang"
		}));
		
		panel1.setLayout(new GridLayout(3, 1));
		panel2.setLayout(new GridLayout(3, 1));
		
		panel1.add(labelName);
		panel1.add(labelCompany);
		panel1.add(labelCategory);
		panel2.add(name);
		panel2.add(company);
		panel2.add(category);

		add(panel1, BorderLayout.WEST);
		add(panel2, BorderLayout.CENTER);
	}
	
	private void addIndex(JTable table){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for (int index = 0; index < model.getRowCount(); index++){
			model.setValueAt(index + 1, index, 0);
		}
	}
	
	private boolean isDuplicate(JTable table, String name, String company, String category){
		for (int index = 0; index < table.getRowCount(); index++){
			if (table.getValueAt(index, 1).toString().equals(name) &&
				table.getValueAt(index, 2).equals(company) &&
				table.getValueAt(index, 3).equals(category)){
				return true;
			}
		}
		return false;
	}
	
	private boolean isExisted(){
		ProductModel model = new ProductModel();
		return model.isExisted(name.getText(), company.getText(), category.getSelectedItem().toString());
	}

	public void addProduct(JTable table){
		if (name.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "Product Name should not be left blank.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else if (company.getText().length() == 0){
			JOptionPane.showMessageDialog(null, "Company should not be left blank.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else{
			if (isDuplicate(table, name.getText(), company.getText(), category.getSelectedItem().toString())){
				JOptionPane.showMessageDialog(null, "This product already exist in this exporting list.", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else{
				if (isExisted()){
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(new Object[] {1, name.getText(), company.getText(), category.getSelectedItem().toString()});
					addIndex(table);
				}
				else{
					JOptionPane.showMessageDialog(null, "This product is not exist in product list.", "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
			
			reset();
		}
	}
	
	public void removeProduct(JTable table){
		if (table.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(null, "You must select a product.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to remove this product?", "Confirm", JOptionPane.YES_NO_OPTION)){
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.removeRow(table.getSelectedRow());
			addIndex(table);
			reset();
		}
	}
	
	public void reset(){
		name.setText("");
		company.setText("");
	}
	
	public void showInformation(String name, String company, String category){
		this.name.setText(name);
		this.company.setText(company);
		this.category.setSelectedItem(category);
	}
	
	public static PanelExportInformation getInstance(){
		if (getInstance == null){
			getInstance = new PanelExportInformation();
		}
		return getInstance;
	}
}
