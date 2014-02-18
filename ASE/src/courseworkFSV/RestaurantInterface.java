package courseworkFSV;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
public class RestaurantInterface extends JFrame implements ActionListener {

	
	private Restaurant restaurant;
	
	public RestaurantInterface(Restaurant r){
		
		restaurant = r;		
	}
	
	public void run(){
		final int FRAME_WIDTH = 500;
		final int FRAME_HEIGHT = 700;
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
		
		JFrame restaurantFrame = new JFrame("Order Information for tables");
		restaurantFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		restaurantFrame.setVisible(true);
		restaurantFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel idlabel = new JLabel("Please select table id:");
		idlabel.setFont(new Font("Arial", Font.ITALIC, 16));
		JComboBox<String> tableIdChoice = new JComboBox<String>();
		tableIdChoice.addActionListener(this);
		JTextArea orderSummary = new JTextArea(20, 40);
		
		restaurantFrame.setLayout(flow);
		restaurantFrame.add(idlabel);
		tableIdChoice.addItem("table 1");
		tableIdChoice.addItem("table 2");
		tableIdChoice.addItem("table 3");
		
	
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
