package courseworkFSV;

import java.awt.*;

import javax.swing.*;
import javax.swing.plaf.TableUI;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
public class RestaurantInterface extends JFrame implements ActionListener {

	
	
	private Restaurant restaurant;
	private Map<Integer, List<Order>> ihm;
	public JComboBox<String> tableIdChoice;
	public JFrame restaurantFrame;
	
	
	public RestaurantInterface(Restaurant r){
		
		restaurant = r;
		ihm = restaurant.getOrders();
	}
	
	public void run(){
		//setting dimensions of Gui
		final int FRAME_WIDTH = 500;
		final int FRAME_HEIGHT = 700;
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
		
		restaurantFrame = new JFrame("Order Information for tables");
		restaurantFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		restaurantFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel idlabel = new JLabel("Please select table id:");
		idlabel.setFont(new Font("Arial", Font.ITALIC, 16));
		
		tableIdChoice = new JComboBox<String>();
		tableIdChoice.addActionListener(this);
		
		
		restaurantFrame.setLayout(flow);
		restaurantFrame.add(idlabel);
		tableIdChoice.addItem("Tables: ");
		tableIdChoice.addItem("table 1");
		tableIdChoice.addItem("table 2");
		tableIdChoice.addItem("table 3");
		restaurantFrame.add(tableIdChoice);
		
		
		restaurantFrame.setVisible(true);
		
	
		
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		
		int tblId = 0;
		String [] columnNames = {"Item", "Amount", "Item Price", "Total Item Price"};
		DefaultTableModel table = new DefaultTableModel();
		JTable summary = new JTable(table);
		summary.setModel(table);
		table.setColumnIdentifiers(columnNames);
		
		if (e.getSource() == tableIdChoice){
			tblId = tableIdChoice.getSelectedIndex();
			
			
		}
		if(ihm.containsKey(tblId)){
			JLabel tbl = new JLabel("TABLE "+ tblId);
			tbl.setPreferredSize(new Dimension(490,18));
			tbl.setHorizontalAlignment(JLabel.LEFT);
			restaurantFrame.add(tbl);
			restaurantFrame.add(summary.getTableHeader());
			restaurantFrame.add(new JScrollPane(summary));
			
			double totalcost = 0.00;
			double discount = 0.00;
			List<Order> tabl = ihm.get(tblId);
			for(Order tableOrder : tabl){
				Object [] a = new Object[4];
				a[0] = tableOrder.getMenuItemName();
				a[1] = tableOrder.getQuantity();
				a[2] = tableOrder.getItemPrice();
				a[3] = tableOrder.totalCost();
				table.addRow(a);
				
				totalcost += ((double)a[3]);
				if ((int)a[1] >= 3){
					discount +=	(((double)a[3])*0.1);
				}
				
			}
			
			//write total, discount
			JLabel total = new JLabel("Total for this table:                          " + totalcost );
			JLabel discountAmount = new JLabel("Discount:                                      " + discount);
			JLabel discounted = new JLabel("Discounted total:                              " + (totalcost - discount));
			total.setPreferredSize(new Dimension(490,18));
			discountAmount.setPreferredSize(new Dimension(490,18));
			discounted.setPreferredSize(new Dimension(490,18));
			total.setHorizontalAlignment(JLabel.LEFT);
			discountAmount.setHorizontalAlignment(JLabel.LEFT);
			discounted.setHorizontalAlignment(JLabel.LEFT);
			restaurantFrame.add(total);
			restaurantFrame.add(discountAmount);
			restaurantFrame.add(discounted);
			
			restaurantFrame.setVisible(true);
		}
	}

}
