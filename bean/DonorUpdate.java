package com.kce.bean;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.kce.util.DbUtil;

public class DonorUpdate extends Exception implements ActionListener {
	JFrame f;
	JLabel l,l1;
	JTextField t1;
	Button b;

	Connection con;
    PreparedStatement psmt,psmt1,psmt2;
 public DonorUpdate() {
	 con=DbUtil.getConnection();
	 f=new JFrame();
	 f.getContentPane().setBackground(Color.GRAY);
	 f.setSize(2000,2000);
	 f.setVisible(true);
	 f.setLayout(null);
	 
	 l=new JLabel("Donor Updation Form");
	 Font fo=new Font("Batang",Font.BOLD,45);
	 l.setForeground(Color.BLACK);
	 l.setBackground(Color.WHITE);
	 l.setBounds(500,50,700,100);
     l.setFont(fo);
	 f.add(l);
	 
	 Font fo1=new Font("Batang",Font.BOLD,15);
	 l1=new JLabel("Aadhar number");
	 l1.setForeground(Color.WHITE);
	 l1.setBounds(200,250,150,40);
	 l1.setFont(fo1);
	 f.add(l1);
	 
	 t1=new JTextField();
	 t1.setBounds(350,260,150,20);
	 f.add(t1);
	 b=new Button("submit");
	 b.setBounds(270,350,150,40);
	 b.setFont(fo1);
	 f.add(b);
	 b.addActionListener((ActionListener) this);
 }
	 public void actionPerformed(ActionEvent e) {
		 String adnum=t1.getText();
		int flag=0;
		 try {
			 if(adnum.length()!=12) {
				 flag=1;
				  throw new IllegalLengthException("enter the valid 12 digits aadharnumber");
				  
				  }
				for(int i=0;i<adnum.length();i++) {
						if(!Character.isDigit(adnum.charAt(i))) {
							flag=2;
							throw new InputMisMatchException("enter the valid aadharnumber");
							
						}
					}
				
			}catch(IllegalLengthException e1) {
				e1.getMessage();
			}catch(InputMisMatchException d) {
				d.getMessage();
			} 
		 try {
			if(flag==0) { 
		 if(e.getSource().equals(b)) {
			    int u=0;int unit=0;
			    psmt=con.prepareStatement("select *from bloodbank");
			    ResultSet rs=psmt.executeQuery("select aadhar_number,units,blood_group from bloodbank ");
			    
			    String bloodgroup="";
			  while(rs.next()) {
			    if(rs.getString(1).equals(adnum)) {
				     u=1+rs.getInt(2);
				     System.out.println(u);	
				     psmt1=con.prepareStatement("update bloodbank set units=? where aadhar_number=?");
				     psmt1.setInt(1, u);
				     psmt1.setString(2,adnum);
				     bloodgroup=rs.getString(3);
					 psmt1.executeUpdate();
			    }
			   
				     psmt2=con.prepareStatement("update blood set units=? where blood_group=?");
				     psmt2.setInt(1, u);
				     psmt2.setString(2,bloodgroup);
					 psmt2.executeUpdate();
					 JOptionPane.showMessageDialog(b,"Thankyou for donating blood.\nYou will be blessed.");
			   }
		 }
			    }	
			
				}catch(SQLException e1) {
					e1.printStackTrace();
				}


}

}