package PresentationLayer;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AboutDialog extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7934003386173745397L;
	private JLabel message;
	private static AboutDialog getInstance = null;
	
	public AboutDialog(){
		message = new JLabel();
		message.setHorizontalAlignment(SwingConstants.CENTER);
		message.setText("Sử dụng JDBC để kết nối với SQL Server, ý tưởng từ project-02 ADO.NET của anh Lợi.");
		
		add(message, BorderLayout.CENTER);
	}
	
	public void buildGUI(){
		setSize(600, 150);
		setVisible(true);
		setLayout(new BorderLayout());
		
	}
	
	public static AboutDialog getInstance(){
		if (getInstance == null)
			getInstance = new AboutDialog();
		return getInstance;
	}
}
