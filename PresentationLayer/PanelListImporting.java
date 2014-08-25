package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import BusinessLogicLayer.ImportModel;

public class PanelListImporting extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7602320021187076133L;
	private JPanel panel1;
	private JPanel panel2;
	private JList<String> list;
	private ImportModel importModel;
	private DefaultListModel<String> model;
	
	public PanelListImporting(){
		setLayout(new BorderLayout());
		
		panel1 = new JPanel();
		panel2 = new JPanel();
		list = new JList<String>();
		importModel = new ImportModel();
		model = new DefaultListModel<String>();
		list.setModel(model);
		
		panel1.setPreferredSize(new Dimension(100, 10));
		panel2.setPreferredSize(new Dimension(100, 10));
		
		add(panel1, BorderLayout.WEST);
		add(panel2, BorderLayout.EAST);
		add(new JScrollPane(list), BorderLayout.CENTER);
		
		loadList();
	}
	
	public String getSelectedItem(){
		return list.getSelectedValue();
	}
	
	public void loadList(){
		ResultSet result = importModel.getImportingList();
		model.removeAllElements();
		
		try {
			while (result.next()){
				model.addElement(result.getString("NgayNhap"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
