import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ActionConsoleInfo extends JFrame{
	
		
	private JTextArea textArea = new JTextArea();
	private JPanel mainPanel = new JPanel();
		

	public ActionConsoleInfo() {
			
			textArea.setEditable(false);
			mainPanel.add(textArea);
			this.setContentPane(mainPanel);
			
			this.setTitle("infoConsole");
			this.setSize(300, 200);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setAlwaysOnTop(true);
			this.setVisible(true);
			
	}
		
	public void printConsole(String s) {
		textArea.setText(s);
	}
		
		
		
}
	


