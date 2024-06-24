package com.kce.bean;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import com.kce.util.DbUtil;

public class  donor extends Exception implements ActionListener{
	JFrame f;
	JLabel l,l1,l2,l3,l4,l5;
	JTextField t1,t2,t3,t4,t5;
	JComboBox cb;
	Button b;
	String a[]= {" ","O_Positive","O_Negative","A_Positive","A_Negative","B_Positive","B_Negative","AB_Negative","AB_Positive"};
   
	Connection con;
    PreparedStatement psmt,psmt1,psmt2;
	 public donor() {
		 con=DbUtil.getConnection();
		 f=new JFrame();
		 f.getContentPane().setBackground(Color.GRAY);
		 f.setSize(2000,2000);
		 f.setVisible(true);
		 f.setLayout(null);
		 
		 l=new JLabel("New Donor Form");
		 Font fo=new Font("Batang",Font.BOLD,45);
		 l.setForeground(Color.BLACK);
		 l.setBackground(Color.WHITE);
		 l.setBounds(500,50,700,100);
	     l.setFont(fo);
		 f.add(l);
		 
		 l1=new JLabel("Name");
		 l1.setForeground(Color.WHITE);
		 l1.setBounds(100,200,100,40);
		 Font fo1=new Font("Batang",Font.BOLD,15);
		 l1.setFont(fo1);
		 f.add(l1);
		 
		 l2=new JLabel("Aadhar number");
		 l2.setForeground(Color.WHITE);
		 l2.setBounds(100,250,150,40);
		 l2.setFont(fo1);
		 f.add(l2);
		 
		 l3=new JLabel("Blood group");
		 l3.setForeground(Color.WHITE);
		 l3.setBounds(100,300,100,40);
		 l3.setFont(fo1);
		 f.add(l3);
		 
		 l4=new JLabel("units");
		 l4.setForeground(Color.WHITE);
		 l4.setBounds(100,350,150,40);
		 l4.setFont(fo1);
		 f.add(l4);
		 
		 t1=new JTextField();
		 t1.setBounds(350,200,150,20);
		 f.add(t1);
		 
		 t2=new JTextField();
		 t2.setBounds(350,250,150,20);
		 f.add(t2);
		  
			 cb=new JComboBox(a);
			
			 cb.setBounds(350,300,150,20);
			 cb.setFont(fo1);
			 f.add(cb);


		 t4=new JTextField();
		 t4.setBounds(350,350,150,20);
		 f.add(t4);
		 t5=new JTextField();
		 t5.setBounds(350,400,150,20);
		 f.add(t5);
		 
		 b=new Button("submit");
		 b.setBounds(270,400,150,40);
		 b.setFont(fo1);
		 f.add(b);
		 b.addActionListener((ActionListener) this);
		 }
	@Override
	public void actionPerformed(ActionEvent e) {
		int flag=0;
		// TODO Auto-generated method stub
		String name=t1.getText();
		String adnum=t2.getText();
		String bloodgroup=(String) cb.getSelectedItem();
		String units=t4.getText();
		
		try {
			
		 if(adnum.length()!=12) {
			 flag=1;
			  throw new IllegalLengthException("enter the valid 12 digits aadharnumber");
			  
			  }
		 else if(name.length()==0 || adnum.length()==0 || bloodgroup.length()==0 || units.length()==0) {
			 flag=3;
			 throw new emptyStatementException("Enter all the fields");
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
		} catch (emptyStatementException e1) {
			// TODO Auto-generated catch block
			e1.getMessage();
		} 
		System.out.println(name+" "+adnum+" "+" "+bloodgroup+" "+units);
		if(e.getSource().equals(b)) {
		    String query="insert into bloodbank(name,aadhar_number,blood_group,units) values(?,?,?,?)";
		    String s=bloodgroup;
		    
		    int u=0;
		   
		    try {
		    	if(flag==0) {
			    psmt= con.prepareStatement(query);
			    psmt1= con.prepareStatement("select * from blood");
				psmt.setString(1,name);
				psmt.setString(2,adnum);
				psmt.setString(3, bloodgroup);
				psmt.setString(4,units);
				psmt.executeUpdate();
				ResultSet rs=psmt1.executeQuery("select blood_group,units from blood ");
				while(rs.next()) {
					if(rs.getString(1).equals(bloodgroup)) {
					     u=Integer.parseInt(units)+rs.getInt(2);
					     System.out.println(u);					    							     		
					     psmt2=con.prepareStatement("update blood set units=? where blood_group=?");
					     psmt2.setInt(1, u);
					     psmt2.setString(2,bloodgroup);
						 psmt2.executeUpdate();
						 JOptionPane.showMessageDialog(b,"Thankyou for donating blood.\n Don't stop this wonderful service.");
					}
				}				
		    	}
		    }catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    
		}
		}
	}
	

