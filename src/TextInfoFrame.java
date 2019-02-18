import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class TextInfoFrame extends JFrame{
	
	private JTextArea textArea = new JTextArea();
	private JPanel mainPanel = new JPanel();
	private int lineNumber = 6;
	private int lineCount = 0;
	

	public TextInfoFrame() {
		
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
	
	public void addToConsole(String s) {
		String string = textArea.getText();
		string += "\n" + s;
		
		textArea.setText(string);
		this.lineCount++;
	}
	
	public void clearConsole() {
		textArea.setText("");
		this.lineCount = 0;
	}
	
	public void pollConsole() {
		if(this.lineCount >= this.lineNumber) {
			this.clearConsole();
		}
	}
	
	
	
}
