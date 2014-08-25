package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXCollapsiblePane;

import BusinessLogicLayer.ImportModel;
import Worker.Timer;

public class PanelImportProduct extends JPanel{

	/**
	 * 
	 */

	private static final long serialVersionUID = 6743776780979625482L;
	private static PanelImportProduct getInstance = null;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel informationPanel;
	private JButton importButton;
	private JButton addButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JButton loadButton;
	private JButton resetButton;
	private JToggleButton informationButton;
	private JTable productTable;
	private JLabel title;
	private JXCollapsiblePane collapsiblePane;
	private DefaultTableCellRenderer centerRenderer;
	private PanelImportInformation panelCenter;
	private PanelListImporting listPanel;
	private ImportModel importModel;

	public PanelImportProduct(){
		setLayout(new BorderLayout());

		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		informationPanel = new JPanel();
		importButton = new JButton("Importing Products");
		addButton = new JButton("Add");
		updateButton = new JButton("Update");
		deleteButton = new JButton("Delete");
		loadButton = new JButton("Load");
		resetButton = new JButton("Reset");
		informationButton = new JToggleButton("Informations");
		productTable = new JTable();
		title = new JLabel("Detail Information");
		centerRenderer = new DefaultTableCellRenderer();
		collapsiblePane = new JXCollapsiblePane();
		panelCenter = new PanelImportInformation();
		listPanel = new PanelListImporting();
		importModel = new ImportModel();

		collapsiblePane.setCollapsed(true);
		collapsiblePane.addComponentListener(new CollapsibleListener());
		panel2.setPreferredSize(new Dimension(350, 10));
		panel3.setPreferredSize(new Dimension(350, 10));
		panel2.setLayout(new BorderLayout());
		panel2.add(listPanel, BorderLayout.CENTER);
		panel4.add(addButton);
		panel4.add(updateButton);
		panel4.add(deleteButton);
		panel5.add(loadButton);
		panel2.add(panel5, BorderLayout.PAGE_END);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", 1, 32));
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		informationPanel.setLayout(new BorderLayout());
		informationButton.addActionListener(collapsiblePane.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
		addButton.addActionListener(new AddListener());
		updateButton.addActionListener(new UpdateListener());
		deleteButton.addActionListener(new DeleteListener());
		importButton.addActionListener(new ImportListener());
		loadButton.addActionListener(new LoadListener());
		resetButton.addActionListener(new ResetListener());
		productTable.addMouseListener(new ClickedListener());
		productTable.setModel(new DefaultTableModel(
			new Object[][]{
			},
			new String[]{
				"Index", "Product Name", "Company", "Category"
			}
		));
		
		panel1.add(resetButton);
		panel1.add(informationButton);
		panel1.add(importButton);
		informationPanel.add(title, BorderLayout.PAGE_START);
		informationPanel.add(panelCenter, BorderLayout.CENTER);
		informationPanel.add(panel4, BorderLayout.PAGE_END);
		informationPanel.add(panel2, BorderLayout.WEST);
		informationPanel.add(panel3, BorderLayout.EAST);
		collapsiblePane.add(informationPanel);

		add(panel1, BorderLayout.PAGE_START);
		add(new JScrollPane(productTable), BorderLayout.CENTER);
		add(collapsiblePane, BorderLayout.PAGE_END);

		formatTable();
	}

	private void formatTable(){
		productTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		productTable.getColumnModel().getColumn(1).setPreferredWidth(700);
		productTable.getColumnModel().getColumn(2).setPreferredWidth(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().width - 900);
		productTable.getColumnModel().getColumn(3).setPreferredWidth(150);
		productTable.getTableHeader().setReorderingAllowed(false);
		productTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	}

	private class CollapsibleListener extends ComponentAdapter{
		public void componentResized(ComponentEvent event){
			if (collapsiblePane.isCollapsed()){
				informationPanel.setPreferredSize(new Dimension(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, 200));
			}
		}
	}

	private class ClickedListener extends MouseAdapter{
		public void mouseClicked(MouseEvent event){
			panelCenter.showInformation(
					productTable.getValueAt(productTable.getSelectedRow(), 1).toString(),
					productTable.getValueAt(productTable.getSelectedRow(), 2).toString(),
					productTable.getValueAt(productTable.getSelectedRow(), 3).toString());
		}
	}

	private class AddListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			panelCenter.addProduct(productTable);
		}
	}

	private class UpdateListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			panelCenter.updateProduct(productTable);
		}
	}

	private class DeleteListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			panelCenter.deleteProduct(productTable);
		}
	}

	private class ImportListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if (productTable.getRowCount() == 0){
				JOptionPane.showMessageDialog(null, "List should not be left blank.");
			}
			else if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to import this?", "Cofirm", JOptionPane.YES_NO_OPTION)){
				importModel = new ImportModel();
				String dateTime = Timer.getCurrentTime();
				
				for (int index = 0; index < productTable.getRowCount(); index++){
					importModel.insert(
							productTable.getValueAt(index, 1).toString(),
							productTable.getValueAt(index, 2).toString(),
							productTable.getValueAt(index, 3).toString(),
							dateTime);
				}
				DefaultTableModel model = (DefaultTableModel) productTable.getModel();
				model.getDataVector().removeAllElements();
				model.fireTableDataChanged();
				JOptionPane.showMessageDialog(null, "Successfully!", "Confirm", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	private class LoadListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			ResultSet result = importModel.loadImportingList(listPanel.getSelectedItem());
			DefaultTableModel model = (DefaultTableModel) productTable.getModel();
			int index = 1;
			
			try {
				
				model.setRowCount(0);
				while (result.next()){
					model.addRow(new Object[] {index++, result.getString("TenMatHang"), result.getString("TenHang"), result.getString("LoaiMatHang")});
				}
				
				addButton.setEnabled(false);
				updateButton.setEnabled(false);
				deleteButton.setEnabled(false);
				importButton.setEnabled(false);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ResetListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			DefaultTableModel model = (DefaultTableModel) productTable.getModel();
			model.setRowCount(0);
			addButton.setEnabled(true);
			updateButton.setEnabled(true);
			deleteButton.setEnabled(true);
			importButton.setEnabled(true);
			panelCenter.reset();
		}
	}

	public static PanelImportProduct getInstance(){
		if (getInstance == null)
			getInstance = new PanelImportProduct();
		return getInstance;
	}
}