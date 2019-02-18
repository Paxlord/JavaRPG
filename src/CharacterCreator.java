import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class CharacterCreator extends JFrame{
	
	private JPanel mainPanel = new JPanel();
	private BorderLayout bl = new BorderLayout();
	private PreviewPanel previewPanel = new PreviewPanel();
	
	private JPanel controlPanel = new JPanel();
	
	private JPanel namePanel = new JPanel();
	private JPanel forcePanel = new JPanel();
	private JPanel agilityPanel = new JPanel();
	private JPanel constitutionPanel = new JPanel();
	
	private JPanel classButton = new JPanel();
	private JToggleButton warrior = new JToggleButton("Guerrier");
	private JToggleButton archer = new JToggleButton("Archer");
	private JToggleButton mage = new JToggleButton("Mage");
	
	private JButton ok = new JButton("Ok");

	private JTextField nameArea = new JTextField(10);
	private JLabel name = new JLabel("Nom : ");
	
	private JSlider strengthSlider = new JSlider(0,18);
	private JLabel force = new JLabel("Force : ");

	private JSlider agilitySlider = new JSlider(0,18);
	private JLabel agility = new JLabel("Agilite : ");

	private JSlider constitutionSlider = new JSlider(0,18);
	private JLabel constitution = new JLabel("constitution : ");


	
	
	public CharacterCreator() {
		
		
		
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
		classButton.add(warrior);
		classButton.add(archer);
		classButton.add(mage);
		
		warrior.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				archer.setSelected(false);
				mage.setSelected(false);
				
				previewPanel.setDrawingIndex(0);
				previewPanel.repaint();
				
				
				
			}
			
		});
		
		archer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				warrior.setSelected(false);
				mage.setSelected(false);
				
				previewPanel.setDrawingIndex(1);
				previewPanel.repaint();
			}
			
		});
		
		mage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				warrior.setSelected(false);
				archer.setSelected(false);
				
				previewPanel.setDrawingIndex(2);
				previewPanel.repaint();
			}
			
		});
		
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int totalStats = strengthSlider.getValue() + agilitySlider.getValue() + constitutionSlider.getValue();
				
				int movementRange = 2;
				int attackRange = 2;
				
				if(warrior.isSelected()) {
					movementRange = 2;
					attackRange = 1;
				}
				
				if(archer.isSelected()) {
					movementRange = 2;
					attackRange = 2;
				}
				
				if(mage.isSelected()) {
					movementRange = 1;
					attackRange = 3;
				}
				
				if((nameArea.getText() != null || !nameArea.getText().equals("")) && totalStats == 18) {
					
					Character newChar = new Character(name.getText(), strengthSlider.getValue(), agilitySlider.getValue(),
							constitutionSlider.getValue(), movementRange, attackRange, previewPanel.getDrawingIndex());
					newChar.setPos(new Vector2(1,1));
					GameWindow gw = new GameWindow();
					dispose();
				}
			}
			
		});
		
		namePanel.add(name);
		namePanel.add(nameArea);
		
		forcePanel.add(force);
		forcePanel.add(strengthSlider);
		
		agilityPanel.add(agility);
		agilityPanel.add(agilitySlider);
		
		constitutionPanel.add(constitution);
		constitutionPanel.add(constitutionSlider);
		
		controlPanel.add(namePanel);
		controlPanel.add(classButton);
		controlPanel.add(forcePanel);
		controlPanel.add(agilityPanel);
		controlPanel.add(constitutionPanel);
		controlPanel.add(ok);
		
		
		strengthSlider.setMajorTickSpacing(2);
		strengthSlider.setMinorTickSpacing(1);
		
		agilitySlider.setMajorTickSpacing(2);
		agilitySlider.setMinorTickSpacing(1);
		
		constitutionSlider.setMajorTickSpacing(2);
		constitutionSlider.setMinorTickSpacing(1);
				
		strengthSlider.setPaintTicks(true);
		agilitySlider.setPaintTicks(true);
		constitutionSlider.setPaintTicks(true);
		
		strengthSlider.setPaintLabels(true);
		agilitySlider.setPaintLabels(true);
		constitutionSlider.setPaintLabels(true);
		
		
		
		mainPanel.setLayout(bl);
		mainPanel.add(controlPanel, BorderLayout.EAST);
		mainPanel.add(previewPanel, BorderLayout.WEST);

		this.setContentPane(mainPanel);
		this.setSize(650,600);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}





	

}
