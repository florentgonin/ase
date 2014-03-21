package courseworkFSV.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import courseworkFSV.view.RestaurantGUI;

public class ReportListener implements ActionListener{
	private RestaurantGUI restaurantGUI;
	
	public ReportListener (RestaurantGUI restaurantGUI) {
		this.restaurantGUI = restaurantGUI;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		restaurantGUI.report();
	}

}
