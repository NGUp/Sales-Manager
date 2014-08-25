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

import BusinessLogicLayer.ExportingModel;

public class PanelListExporting extends JPanel{

	/**
	 * 
	 */

	private static final long serialVersionUID = 7493840400891586979L;
	private JList<String> historyList;
	private DefaultListModel<String> listModel;
	private ExportingModel exportModel;
	private ResultSet result;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	
	public PanelListExporting(){
		setLayout(new BorderLayout());
		historyList = new JList<String>();
		listModel = new DefaultListModel<String>();
		exportModel = new ExportingModel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		
		historyList.setModel(listModel);
		
		panel2.setPreferredSize(new Dimension(70, 30));
		panel3.setPreferredSize(new Dimension(70, 30));
		
		add(new JScrollPane(historyList), BorderLayout.CENTER);
		add(panel1, BorderLayout.PAGE_END);
		add(panel2, BorderLayout.WEST);
		add(panel3, BorderLayout.EAST);
		
		loadList();
	}
	
	public String getSelectedItem(){
		return historyList.getSelectedValue();
	}
	
	public void loadList(){
		result = exportModel.getExportingList();
		listModel.removeAllElements();
		try {
			while (result.next()){
				listModel.addElement(result.getString("NgayXuat"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
