package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

public class MainProgram extends JFrame{
	/**
	 * Main Program
	 */
	private static final long serialVersionUID = -317342943177356942L;
	private JPanel panelTitle;
	private JPanel panelInformation;
	private JLabel title;
	private JToolBar toolBar;
	private JToggleButton listButton;
	private JToggleButton exportButton;
	private JToggleButton importButton;
	private JButton aboutButton;
	private ButtonGroup groupButton;
	
	public MainProgram(){
		super("Product Management");
		title = new JLabel();
		panelTitle = new JPanel();
		toolBar = new JToolBar();
		groupButton = new ButtonGroup();
		listButton = new JToggleButton("Product List");
		exportButton = new JToggleButton("Exporting Products");
		importButton = new JToggleButton("Importing Products");
		aboutButton = new JButton("About");
		panelInformation = new JPanel();
		
		groupButton.add(listButton);
		groupButton.add(exportButton);
		groupButton.add(importButton);
		
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setText("<html><font color=\"#FF0000\"><font size=\"36\">Product Management</font></font></html>");
		
		toolBar.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		toolBar.setPreferredSize(new Dimension(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, 50));
		toolBar.add(listButton);
		toolBar.add(exportButton);
		toolBar.add(importButton);
		toolBar.add(aboutButton);
		
		listButton.setSelected(true);
		listButton.setFont(new Font("Tahoma", 1, 24));
		exportButton.setFont(new Font("Tahoma", 1, 24));
		importButton.setFont(new Font("Tahoma", 1, 24));
		listButton.addActionListener(new ListListener());
		exportButton.addActionListener(new ExportListener());
		importButton.addActionListener(new ImportListener());
		aboutButton.addActionListener(new AboutListener());
		
		panelTitle.setLayout(new BorderLayout());
		panelTitle.add(title, BorderLayout.PAGE_START);
		panelTitle.add(toolBar, BorderLayout.CENTER);
		panelInformation.setLayout(new BorderLayout());
		panelInformation.add(PanelProductList.getInstance(), BorderLayout.CENTER);
		
		add(panelTitle, BorderLayout.PAGE_START);
		add(panelInformation, BorderLayout.CENTER);
	}
	
	public void buildGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getSize());
		setVisible(true);
	}
	
	private void reloadPanel(int model){
		panelInformation.removeAll();
		if (model == 0)
			panelInformation.add(PanelProductList.getInstance());
		else if (model == 1)
			panelInformation.add(PanelExportProduct.getInstance());
		else if (model == 2)
			panelInformation.add(PanelImportProduct.getInstance());
		panelInformation.updateUI();
	}
	
	private class ListListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			reloadPanel(0);
		}
	}
	
	private class ExportListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			reloadPanel(1);
		}
	}
	
	private class ImportListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			reloadPanel(2);
		}
	}
	
	private class AboutListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			AboutDialog app = AboutDialog.getInstance();
			app.buildGUI();
		}
	}
}
