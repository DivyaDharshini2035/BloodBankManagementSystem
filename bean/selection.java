package com.kce.bean;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class selection extends JFrame implements ActionListener {
	JLabel l,selection;
	JFrame f;
	Button b,b1,b2; 
	public selection() {
		 f=new JFrame();
		 f.getContentPane().setBackground(Color.GRAY);
		 f.setSize(2000,2000);
		 f.setVisible(true);
		 f.setLayout(null);
		  
		 l=new JLabel("Blood Donation");
		 Font fo=new Font("Batang",Font.BOLD,45);
		 l.setForeground(Color.BLACK);
		 l.setBackground(Color.WHITE);
		 l.setBounds(500,50,700,100);
	     l.setFont(fo);
		 f.add(l);
		 
		    
		 Font fo1=new Font("Times New Roman",Font.BOLD,25);
		 selection=new JLabel("SELECTION:");
		 selection.setBounds(50,200,250,50);
		 selection.setForeground(Color.BLACK);
	     selection.setFont(fo1);
		 f.add(selection);
		 
		 
		 b=new Button("First Donation");
		 b.setBounds(270,200,250,40);
		 b.setFont(fo1);
		 f.add(b);
		 
		 
		 b2=new Button("Donation");
		 b2.setBounds(570,200,200,40);
		 b2.setFont(fo1);
		 f.add(b2);
			
		 b1=new Button("user");
		 b1.setBounds(850,200,150,40);
		 b1.setFont(fo1);
		 f.add(b1);
		 
		 b.addActionListener((ActionListener) this);
		 b1.addActionListener((ActionListener) this);
		 b2.addActionListener((ActionListener) this);
	 }

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new selection ();
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(b)) {
			this.add(this, new donor());
		}
		else if(e.getSource().equals(b1)) {
			this.add(this, new user());
			
		}
		else if(e.getSource().equals(b2)) {
			this.add(this,new DonorUpdate());
		}
	}

}
