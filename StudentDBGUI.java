import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentDBGUI extends JFrame implements ActionListener{
	JLabel l_name,l_rollno,l_mark,l_age,msg;
	JTextField name,rollno,mark,age,searchin;
	JButton insert,search,delete,clear;
	RandomAccessFile file;
	public StudentDBGUI(){
		setTitle("Student Details");
		setVisible(true);
		setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		l_rollno = new JLabel("Roll No: ");
		l_name = new JLabel("Name: ");
		l_age = new JLabel("Age: ");
		l_mark = new JLabel("Mark: ");
		msg = new JLabel("Welcome :) ");

		rollno = new JTextField("");
		name = new JTextField("");
		age = new JTextField("");
		mark = new JTextField("");
		searchin = new JTextField("");

		insert = new JButton("Insert");
		search = new JButton("Search");
		delete = new JButton("Delete");
		clear = new JButton("Clear");

		l_rollno.setBounds(100,100,80,20);
		l_name.setBounds(100,130,80,20);		
		l_age.setBounds(100,160,80,20);		
		l_mark.setBounds(100,190,80,20);

		rollno.setBounds(200,100,80,20);
		name.setBounds(200,130,80,20);		
		age.setBounds(200,160,80,20);		
		mark.setBounds(200,190,80,20);

		insert.setBounds(200,220,80,20);
		searchin.setBounds(20,250,80,20);
		search.setBounds(110,250,80,20);
		delete.setBounds(200,250,80,20);
		clear.setBounds(290,250,80,20);
		
		msg.setBounds(140,50,120,20);

		add(l_rollno);
		add(l_name);
		add(l_age);
		add(l_mark);
		add(msg);

		add(rollno);
		add(name);
		add(age);
		add(mark);	

		add(insert);
		add(searchin);
		add(search);
		add(delete);
		add(clear);

		insert.addActionListener(this);
		search.addActionListener(this);
		delete.addActionListener(this);
		clear.addActionListener(this);
		
		try{
			file = new RandomAccessFile("db.txt","rw");
		}
		catch(Exception e){}
	}
	public void clearAll(){
		rollno.setText("");
		name.setText("");
		age.setText("");
		mark.setText("");
	}
	public void actionPerformed(ActionEvent evt){
		JButton clicked = (JButton) evt.getSource();
		if(clicked == insert){
			String s_rollno = rollno.getText();
			String s_mark = mark.getText();
			String s_age = age.getText();
			String s_name = name.getText();
			try{
				file.seek(file.length());
				file.writeBytes(s_rollno+","+s_name+","+s_age+","+s_mark+"\n");
			}
			catch(Exception e){}
			msg.setText("Insertion success");
			clearAll();
		}
		else if(clicked == search){
			String keyword = searchin.getText();
			String result = "",line;
			try{
				file.seek(0);
				while ((line = file.readLine()) != null) {
					if(line.split(",")[0].trim().equals(keyword.trim())){
						result = line;
						break;
					}
				}
			} catch(Exception e){}
			if(result == ""){
				msg.setText("No result found");
			}
			else{
				msg.setText("Result found");
				String[] r = result.split(",");
				rollno.setText(r[0]);
				name.setText(r[1]);
				age.setText(r[2]);
				mark.setText(r[3]);
			}
		}
		else if(clicked == delete){
			String keyword = searchin.getText();
			String filecontent = "",line;
			boolean deleted = false;
			try{
				file.seek(0);
				while ((line = file.readLine()) != null) {
					if( !line.split(",")[0].trim().equals(keyword.trim()) ){
						filecontent += (line+"\n");
					}
					else
						deleted = true;
				}
				file.setLength(0);
				file.writeBytes(filecontent);
			} catch(Exception e){}
			if(deleted)
				msg.setText("Deletion success");
			else
				msg.setText("Element not found");
		}
		else if(clicked == clear){
			clearAll();
		}
	}
	public static void main(String[] args){
		new StudentDBGUI();
	}
}