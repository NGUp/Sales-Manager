package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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

import BusinessLogicLayer.ExportingModel;
import Worker.Timer;

public class PanelExportProduct extends JPanel{
	/**
	 * panel for export Bill
	 */
	private static final long serialVersionUID = -7757138108362300669L;
	private static PanelExportProduct getInstance = null;
	private JTable productTable;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JPanel panel5;
	private JPanel informationPanel;
	private JLabel title;
	private JButton exportButton;
	private JButton addButton;
	private JButton removeButton;
	private JButton loadButton;
	private JButton resetButton;
	private JToggleButton informationButton;
	private JXCollapsiblePane collapsiblePane;
	private DefaultTableCellRenderer centerRenderer;
	private PanelExportInformation exportPanel;
	private PanelListExporting listPanel;
	private PanelFindingProduct findingPanel;
	private ExportingModel exportModel;
	
	public PanelExportProduct(){
		setLayout(new BorderLayout());

		centerRenderer = new DefaultTableCellRenderer();
		productTable = new JTable();
		informationPanel = new JPanel();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		title = new JLabel("Detail Information");
		collapsiblePane = new JXCollapsiblePane();
		exportButton = new JButton("Exporting Products");
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		loadButton = new JButton("Load");
		resetButton = new JButton("Reset");
		informationButton = new JToggleButton("Informations");
		exportPanel = new PanelExportInformation();
		listPanel = new PanelListExporting();
		findingPanel = new PanelFindingProduct();
		exportModel = new ExportingModel();
		
		informationPanel.setPreferredSize(new Dimension(1366, 300));
		informationPanel.setLayout(new BorderLayout());
		panel1.setLayout(new FlowLayout());
		panel2.setPreferredSize(new Dimension(300, 10));
		panel3.setPreferredSize(new Dimension(500, 10));
		panel4.setLayout(new FlowLayout());
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", 1, 32));
		panel2.setLayout(new BorderLayout());
		panel3.setLayout(new BorderLayout());
		
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		collapsiblePane.setCollapsed(true);
		collapsiblePane.addComponentListener(new CollapsibleListener());
		informationButton.addActionListener(collapsiblePane.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
		addButton.addActionListener(new AddListener());
		removeButton.addActionListener(new RemoveListener());
		exportButton.addActionListener(new ExportListener());
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
		
		panel3.setBackground(new Color(31, 160, 31));
		
		panel1.add(resetButton);
		panel1.add(informationButton);
		panel1.add(exportButton);
		panel2.add(listPanel, BorderLayout.CENTER);
		panel2.add(panel5, BorderLayout.PAGE_END);
		panel3.add(findingPanel, BorderLayout.CENTER);
		panel4.add(addButton);
		panel4.add(removeButton);
		panel5.add(loadButton);
		exportPanel.add(panel4, BorderLayout.PAGE_END);
		informationPanel.add(title, BorderLayout.PAGE_START);
		informationPanel.add(panel2, BorderLayout.WEST);
		informationPanel.add(panel3, BorderLayout.EAST);
		informationPanel.add(exportPanel, BorderLayout.CENTER);
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
			exportPanel.showInformation(
					productTable.getValueAt(productTable.getSelectedRow(), 1).toString(),
					productTable.getValueAt(productTable.getSelectedRow(), 2).toString(),
					productTable.getValueAt(productTable.getSelectedRow(), 3).toString());
		}
	}

	private class AddListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			exportPanel.addProduct(productTable);
		}
	}

	private class RemoveListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			exportPanel.removeProduct(productTable);
		}
	}

	private class ExportListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if (productTable.getRowCount() == 0){
				JOptionPane.showMessageDialog(null, "List should not be left blank.");
			}
			else if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to export product?", "Confirm", JOptionPane.YES_NO_OPTION)){
				String currentTime = Timer.getCurrentTime();
				for (int index = 0; index < productTable.getRowCount(); index++){
					exportModel.export(
							productTable.getValueAt(index, 1).toString(), 
							productTable.getValueAt(index, 2).toString(), 
							productTable.getValueAt(index, 3).toString(), 
							currentTime);
					listPanel.loadList();
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
			ResultSet result = exportModel.loadExportingList(listPanel.getSelectedItem());
			DefaultTableModel model = (DefaultTableModel) productTable.getModel();
			int index = 1;
			try {
				model.setRowCount(0);
				
				while (result.next()){
					model.addRow(new Object[] {index++, result.getString("TenMatHang"), result.getString("TenHang"), result.getString("LoaiMatHang")});
				}
				
				addButton.setEnabled(false);
				removeButton.setEnabled(false);
				exportButton.setEnabled(false);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ResetListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			DefaultTableModel model = (DefaultTableModel) productTable.getModel();
			
			model.setRowCount(0);
			exportPanel.reset();
			exportButton.setEnabled(true);
			addButton.setEnabled(true);
			removeButton.setEnabled(true);
			loadButton.setEnabled(true);
		}
	}
	
	public static PanelExportProduct getInstance(){
		if (getInstance == null){
			getInstance = new PanelExportProduct();
		}
		return getInstance;
	}
}
