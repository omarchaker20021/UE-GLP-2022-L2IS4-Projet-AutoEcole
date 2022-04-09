package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class RulesPanel extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton answer1;
	private JButton answer2;
	private JButton answer3;
	private JButton answer4;
	
	
	public RulesPanel() {
		super();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		answer1 = new JButton();
		answer2 = new JButton();
		answer3 = new JButton();
		answer4 = new JButton();
		
		answer1.addActionListener(new ActionAnswer1());
		answer2.addActionListener(new ActionAnswer2());
		answer3.addActionListener(new ActionAnswer3());
		answer4.addActionListener(new ActionAnswer4());
		
		this.add(answer1);
		this.add(answer2);
		this.add(answer3);
		this.add(answer4);
	}
	
	
	private class ActionAnswer1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	private class ActionAnswer2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	private class ActionAnswer3 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	private class ActionAnswer4 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
		}
		
	}

}
