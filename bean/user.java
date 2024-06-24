package com.kce.bean;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.kce.util.DbUtil;
import com.mysql.cj.xdevapi.Statement;

public class user implements ActionListener {
	JComboBox cb;
	Button b;
	JFrame f;
	JLabel l,l1,l2;
	JTextField t1;
	String a[]= {" ","O_Positive","O_Negative","A_Positive","A_Negative","B_Positive","B_Negative","AB_Positive","AB_Negative"};
	Connection con;
    PreparedStatement psmt;
    
  public user() {
	  con=DbUtil.getConnection();
	  f=new JFrame();
		 f.getContentPane().setBackground(Color.GRAY);
		 f.setSize(2000,2000);
		 f.setVisible(true);
		 f.setLayout(null);
		 
		 l2=new JLabel("User Form");
		 Font fo=new Font("Batang",Font.BOLD,45);
		 l2.setForeground(Color.BLACK);
		 l2.setBackground(Color.WHITE);
		 l2.setBounds(500,50,700,100);
	     l2.setFont(fo);
		 f.add(l2);
		 
		 Font fo1=new Font("Batang",Font.BOLD,15);
		 l=new JLabel("BLOODGROUP SELECTION");
		 l.setForeground(Color.WHITE);
		 l.setBounds(100,240,250,40);
		 l.setFont(fo1);
		 f.add(l);
		 
		 l1=new JLabel("units");
		 l1.setForeground(Color.WHITE);
		 l1.setBounds(100,290,150,40);
		 l1.setFont(fo1);
		 f.add(l1);
		 
		 cb=new JComboBox(a);
		 cb.setBounds(350,250,150,20);
		 cb.setFont(fo1);
		 f.add(cb);

		 t1=new JTextField();
		 t1.setBounds(350,300,150,20);
		 f.add(t1);
		 
		 b=new Button("submit");
		 b.setBounds(270,400,150,40);
		 b.setFont(fo1);
		 f.add(b);
		 b.addActionListener( (this));
		 }
  public void actionPerformed(ActionEvent e) {
	  String bloodgroup=(String) cb.getSelectedItem();
		String units=t1.getText();
       int u=0;
       
	  if(e.getSource().equals(b)) {
		 
		    try {
		    	psmt= con.prepareStatement("select * from blood");
		    	 ResultSet rs=psmt.executeQuery("select blood_group,units from blood "); 
		    	 while(rs.next()) {
		    	if(rs.getString(1).equals(bloodgroup)) {
		    		if(rs.getInt(2)==0) {
		    			JOptionPane.showMessageDialog(b,"No Blood Units in this bloodgroup");
		    			break;
		    		}
		    		else if(rs.getInt(2)<Integer.parseInt(units)) {
		    			JOptionPane.showMessageDialog(b,"Not enough Blood Units in this bloodgroup\n The available units in this bloodgroup is "+rs.getInt(2));
		    			
		    		}
		    		if(rs.getInt(2)>Integer.parseInt(units)) {
				     u=rs.getInt(2)-Integer.parseInt(units);
				     System.out.println(u);					    							     		
				     psmt=con.prepareStatement("update blood set units=? where blood_group=?");
				     psmt.setInt(1, u);
				     psmt.setString(2,bloodgroup);
					 psmt.executeUpdate();
				}
		    	}
		    	 }
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
		    
		}
	 }

}
