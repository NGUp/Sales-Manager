package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import BusinessLogicLayer.ExportingModel;

public class PanelFindingProduct extends JPanel{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -5223103573000210806L;
	private static PanelFindingProduct getInstance = null;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JTextField findTextField;
	private JComboBox<String> categoryComboBox;
	private ExportingModel exportModel;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private String[] category = {
			"Product Name",
			"Company",
			"Category"
	};
	
	public PanelFindingProduct(){
		setLayout(new BorderLayout());
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		findTextField = new JTextField(20);
		categoryComboBox = new JComboBox<String>(category);
		list = new JList<String>();
		exportModel = new ExportingModel();
		listModel = new DefaultListModel<String>();
		
		list.setModel(listModel);
		findTextField.addKeyListener(new FindListener());
		
		panel1.setPreferredSize(new Dimension(80, 10));
		panel2.setPreferredSize(new Dimension(80, 10));
		panel3.add(findTextField);
		panel3.add(categoryComboBox);
		
		add(new JScrollPane(list), BorderLayout.CENTER);
		add(panel1, BorderLayout.WEST);
		add(panel2, BorderLayout.EAST);
		add(panel3, BorderLayout.PAGE_START);
	}
	
	private class FindListener extends KeyAdapter{
		public void keyReleased(KeyEvent event){
			ResultSet result;
			listModel.removeAllElements();
			
			if (findTextField.getText().length() != 0){
				if (categoryComboBox.getSelectedIndex() == 0){
					result = exportModel.getListByName(findTextField.getText());
				}
				else if (categoryComboBox.getSelectedIndex() == 1){
					result = exportModel.getListByCompany( findTextField.getText());
				}
				else
					result = exportModel.getListByCategory(findTextField.getText());
				
				try {
					while (result.next()){
						listModel.addElement(result.getString(1));
					}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	public static PanelFindingProduct getInstance(){
		if (getInstance == null)
			getInstance = new PanelFindingProduct();
		return getInstance;
	}
}
