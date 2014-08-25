import javax.swing.SwingUtilities;

import PresentationLayer.MainProgram;

public class Main {
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				MainProgram app = new MainProgram();
				app.buildGUI();
			}
		});
	}
}
