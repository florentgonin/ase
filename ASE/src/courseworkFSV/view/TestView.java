package courseworkFSV.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import courseworkFSV.model.Kitchen;
import courseworkFSV.model.Order;

public class TestView extends JFrame implements Observer{

	private JTextArea kitchenInfo;
	private JPanel panel = new JPanel ();
	private Kitchen kitchen;
	
	public TestView (Kitchen kitchen) {
		super("kitchen");
		this.kitchen = kitchen;
	}

	public void update() {
		String s = "";
		for (Order o : kitchen) {
			s += o.toString()+"\n\n";
		}
		kitchenInfo.setText(s);
	}
	
	public void run(){
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		kitchen.addObserver(this);
		kitchenInfo = new JTextArea("Empty.\n\n\n\n\n\n\n");
		panel.add(kitchenInfo);
		panel.setSize(200,200);
		
		getContentPane().add(panel);
		
		this.setVisible(true);

	}
	
}
