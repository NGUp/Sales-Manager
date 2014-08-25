package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableCellRenderer;

import org.jdesktop.swingx.JXCollapsiblePane;

import BusinessLogicLayer.ProductModel;
import BusinessLogicLayer.ProductValuesModel;

public class PanelProductList extends JPanel{
	/**
	 * Panel Product List
	 */
	private static final long serialVersionUID = 2809725789389713576L;
	private static PanelProductList getInstance = null;
	private JTable tableInformation;
	private ButtonGroup radioGroup;
	private JComboBox<Object> typeComboBox;
	private JButton buttonReset;
	private JButton buttonFinding;
	private JButton buttonUpdate;
	private JToggleButton buttonDetail;
	private JRadioButton buttonName;
	private JRadioButton buttonCompany;
	private JTextField findingTextField;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panelFinding;
	private JPanel panelInformation;
	private JXCollapsiblePane informationPane;
	private PanelInformation panelCenter;
	private ProductModel modelProduct;
	private DefaultTableCellRenderer centerRenderer;
	
	private static final String[] types = {
		"Tất cả",
		"Điện thoại, máy tính bảng",
		"Laptop",
		"Quần áo, giày dép",
		"Máy ảnh số",
		"Ba lô, túi xách",
		"Sách báo, tạp chí",
		"Xe hơi",
		"Rượu vang"
	};
	
	public PanelProductList(){
		setLayout(new BorderLayout());
		
		typeComboBox = new JComboBox<Object>(types);
		radioGroup = new ButtonGroup();
		buttonReset = new JButton("Reset");
		buttonUpdate = new JButton("Update");
		buttonFinding = new JButton("Find");
		buttonDetail = new JToggleButton("Detail Information");
		buttonName = new JRadioButton("Product Name");
		buttonCompany = new JRadioButton("Company");
		findingTextField = new JTextField(30);
		panelFinding = new JPanel();
		panelInformation = new PanelInformation();
		informationPane = new JXCollapsiblePane();
		panelCenter = new PanelInformation();
		modelProduct = new ProductModel();
		tableInformation = new JTable();
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		centerRenderer = new DefaultTableCellRenderer();
		
		panel1.setPreferredSize(new Dimension(300, 10));
		panel2.setPreferredSize(new Dimension(300, 10));
		panel3.setLayout(new FlowLayout());
		panelFinding.setLayout(new FlowLayout());
		panelInformation.setLayout(new BorderLayout());
		buttonName.setSelected(true);
		buttonName.setToolTipText("Finding by Product Name");
		buttonCompany.setToolTipText("Finding by Company");
		buttonDetail.addActionListener(informationPane.getActionMap().get(JXCollapsiblePane.TOGGLE_ACTION));
		buttonFinding.addActionListener(new FindingListener());
		buttonReset.addActionListener(new ResetListener());
		buttonUpdate.addActionListener(new UpdateListener());
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		typeComboBox.setSelectedIndex(0);
		typeComboBox.addItemListener(new ShowListener());
		radioGroup.add(buttonName);
		radioGroup.add(buttonCompany);
		
		tableInformation.setModel(new ProductValuesModel(modelProduct.getProduct()));
		tableInformation.addMouseListener(new ClickedListener());
		formatTable();
		
		panelFinding.add(buttonReset);
		panelFinding.add(typeComboBox);
		panelFinding.add(findingTextField);
		panelFinding.add(buttonFinding);
		panelFinding.add(buttonName);
		panelFinding.add(buttonCompany);
		panelFinding.add(buttonDetail);
		panel3.add(buttonUpdate);
		panelInformation.add(panel1, BorderLayout.WEST);
		panelInformation.add(panel2, BorderLayout.EAST);
		panelInformation.add(panel3, BorderLayout.PAGE_END);
		panelInformation.add(panelCenter, BorderLayout.CENTER);
		informationPane.setCollapsed(true);
		informationPane.add(panelInformation);
		
		add(panelFinding, BorderLayout.PAGE_START);
		add(new JScrollPane(tableInformation));
		add(informationPane, BorderLayout.PAGE_END);
		addComponentListener(new CollapsibleListener());
	}
	
	private void formatTable(){
		tableInformation.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableInformation.getColumnModel().getColumn(1).setPreferredWidth(700);
		tableInformation.getColumnModel().getColumn(2).setPreferredWidth(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize().width - 900);
		tableInformation.getColumnModel().getColumn(3).setPreferredWidth(150);
		tableInformation.getTableHeader().setReorderingAllowed(false);
		tableInformation.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	}
	
	private class CollapsibleListener extends ComponentAdapter{
		public void componentResized(ComponentEvent event){
			if (informationPane.isCollapsed()){
				panelInformation.setPreferredSize(new Dimension(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, 200));
			}
		}
	}

	private class ShowListener implements ItemListener{
		public void itemStateChanged(ItemEvent event){
			if (typeComboBox.getSelectedIndex() == 0)
				tableInformation.setModel(new ProductValuesModel(modelProduct.getProduct()));
			else
				tableInformation.setModel(new ProductValuesModel(modelProduct.getProductByTypeProduct(typeComboBox.getSelectedItem().toString())));
			
			formatTable();
		}
	}

	private class FindingListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if (findingTextField.getText().length() == 0){
				JOptionPane.showMessageDialog(null, "Do not empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			else{
				if (buttonName.isSelected()){
					tableInformation.setModel(new ProductValuesModel(modelProduct.getProductByNameProduct(findingTextField.getText())));
				}
				else{
					tableInformation.setModel(new ProductValuesModel(modelProduct.getProductByCompany(findingTextField.getText())));
				}
				formatTable();
				
				if (tableInformation.getRowCount() == 0){
					JOptionPane.showMessageDialog(null, "Not Found", "Result", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	private class ResetListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			panelCenter.resetInformation();
			findingTextField.setText("");
			tableInformation.setModel(new ProductValuesModel(modelProduct.getProduct()));
			formatTable();
		}
	}
	
	private class ClickedListener extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent event) {
			panelCenter.showInformation(tableInformation.getValueAt(tableInformation.getSelectedRow(), 1).toString(),
										tableInformation.getValueAt(tableInformation.getSelectedRow(), 2).toString(),
										tableInformation.getValueAt(tableInformation.getSelectedRow(), 3).toString());
		}
	}
	
	private class UpdateListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Do you want to update?", "Confirm", JOptionPane.YES_NO_OPTION)){
				if (modelProduct.isExisted(panelCenter.getName(), panelCenter.getCompany(), panelCenter.getCategory())){
					JOptionPane.showMessageDialog(null, "This product already exists in the list.", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				else{
					modelProduct.updateProduct(tableInformation.getValueAt(tableInformation.getSelectedRow(), 1).toString(), 
											tableInformation.getValueAt(tableInformation.getSelectedRow(), 2).toString(), 
											panelCenter.getName(), panelCenter.getCompany());
					tableInformation.setModel(new ProductValuesModel(modelProduct.getProduct()));
					formatTable();
					JOptionPane.showMessageDialog(null, "Successfully!", "Congratulation", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	public static PanelProductList getInstance(){
		if (getInstance == null)
			getInstance = new PanelProductList();
		return getInstance;
	}
}
