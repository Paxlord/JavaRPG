import javax.swing.JButton;
import javax.swing.JPanel;

public class ActionUI extends JPanel{
	
	private JButton move = new JButton("Déplacement");
	private JButton attack = new JButton("Attaquer");
	private JButton defendre = new JButton("Défendre");
	
	public ActionUI() {
		this.add(move);
		this.add(attack);
		this.add(defendre);
	}
	
	public JButton getDeplacement() {
		return move;
	}
	
	public JButton getAttack() {
		return attack;
	}
	
	public JButton getDefendre() {
		return defendre;
	}

}
