package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Color;
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

public class PanelImportInformation extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2800222388602876086L;
	private JLabel nameLabel;
	private JLabel companyLabel;
	private JLabel categoryLabel;
	private JTextField name;
	private JTextField company;
	private JComboBox<String> category;
	private JPanel panel1;
	private JPanel panel2;
	
	public PanelImportInformation(){
		setLayout(new BorderLayout());
		setBackground(new Color(30, 30, 180));
		
		nameLabel = new JLabel("Product Name: ");
		companyLabel = new JLabel("Company: ");
		categoryLabel = new JLabel("Category: ");
		name = new JTextField(40);
		company = new JTextField(40);
		category = new JComboBox<String>();
		panel1 = new JPanel();
		panel2 = new JPanel();
		
		panel1.setLayout(new GridLayout(3, 1));
		panel2.setLayout(new GridLayout(3, 1));
		
		panel1.add(nameLabel);
		panel1.add(companyLabel);
		panel1.add(categoryLabel);
		panel2.add(name);
		panel2.add(company);
		panel2.add(category);
		
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
		
		add(panel1, BorderLayout.WEST);
		add(panel2, BorderLayout.CENTER);
	}
	
	public void reset(){
		name.setText("");
		company.setText("");
	}
	
	private void addIndex(DefaultTableModel model){
		for (int index = 0; index < model.getRowCount(); index++){
			model.setValueAt(index + 1, index, 0);
		}
	}
	
	private boolean isDuplicate(DefaultTableModel model){
		for (int index = 0; index < model.getRowCount(); index++){
			if (model.getValueAt(index, 1).equals(name.getText()) &&
				model.getValueAt(index, 2).equals(company.getText()) &&
				model.getValueAt(index, 3).equals(category.getSelectedItem().toString())){
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
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		if (isDuplicate(model)){
			JOptionPane.showMessageDialog(null, "This product is duplicate.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else{
			if (isExisted()){
				JOptionPane.showMessageDialog(null, "This product is exist in list", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else{
				model.addRow(new Object[] {1, name.getText(), company.getText(), category.getSelectedItem().toString()});
				addIndex(model);
				reset();
			}
		}
	}
	
	public void updateProduct(JTable table){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		if (isDuplicate(model)){
			JOptionPane.showMessageDialog(null, "This product is duplicate.", "Warning", JOptionPane.WARNING_MESSAGE);
		}
		else{
			if (isExisted()){
				JOptionPane.showMessageDialog(null, "This product is exist in list", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else{
				if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to update this product?", "Confirm", JOptionPane.YES_NO_OPTION)){
					model.setValueAt(name.getText(), table.getSelectedRow(), 1);
					model.setValueAt(company.getText(), table.getSelectedRow(), 2);
					model.setValueAt(category.getSelectedItem().toString(), table.getSelectedRow(), 3);
					reset();
					addIndex(model);
					JOptionPane.showMessageDialog(null, "Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	public void deleteProduct(JTable table){
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to delete this product?", "Confirm", JOptionPane.YES_NO_OPTION)){
			model.removeRow(table.getSelectedRow());
			reset();
			addIndex(model);
			JOptionPane.showMessageDialog(null, "Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void showInformation(String name, String company, String category){
		this.name.setText(name);
		this.company.setText(company);
		this.category.setSelectedItem(category);
	}
}
