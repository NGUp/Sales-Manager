package PresentationLayer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelInformation extends JPanel{
	/**
	 * panel of Product information
	 */
	private static final long serialVersionUID = -6056547592631227164L;
	private JTextField name;
	private JTextField company;
	private JTextField category;
	private JLabel labelName;
	private JLabel labelCompany;
	private JLabel labelCategory;
	private JPanel panel1;
	private JPanel panel2;
	private JLabel title;
	
	public PanelInformation(){
		setLayout(new BorderLayout());
		panel1 = new JPanel();
		panel2 = new JPanel();
		name = new JTextField(30);
		company = new JTextField(30);
		category = new JTextField(30);
		title = new JLabel("Product Information");
		labelName = new JLabel("Product Name: ");
		labelCompany = new JLabel("Company: ");
		labelCategory = new JLabel("Category: ");
		
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", 1, 36));
		title.setPreferredSize(new Dimension(400, 60));
		category.setEditable(false);
		
		panel1.setLayout(new GridLayout(3, 0));
		panel2.setLayout(new GridLayout(3, 0));
		
		panel1.add(labelName);
		panel1.add(labelCompany);
		panel1.add(labelCategory);
		
		panel2.add(name);
		panel2.add(company);
		panel2.add(category);
		
		add(title, BorderLayout.PAGE_START);
		add(panel1, BorderLayout.WEST);
		add(panel2, BorderLayout.CENTER);
	}
	
	public void showInformation(String name, String company, String type){
		this.name.setText(name);
		this.company.setText(company);
		this.category.setText(type);
	}
	
	public void resetInformation(){
		name.setText("");
		company.setText("");
		category.setText("");
	}
	
	public String getName(){
		return name.getText();
	}
	
	public String getCompany(){
		return company.getText();
	}
	
	public String getCategory(){
		return category.getText();
	}
}
