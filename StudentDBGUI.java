
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;





// we start by creating necessary classes. Student class first.
// format of constuctor string for student is
//  admno,name,age,mark
// sample :  "5,Appu,17,89"
class Student{
	public int admno,age,mark;
	public String name;

	Student(String s){
		String[] input = s.split(",");
		this.admno = Integer.parseInt(input[0]);
		this.name = input[1];
		this.age = Integer.parseInt(input[2]);
		this.mark = Integer.parseInt(input[3]);
	}
	public String toString(){
		return admno + "," + name + "," + age + "," + mark+"\n";
	}
}
class Database{
	RandomAccessFile file;
	String filename;
	Database(String filename){
		this.filename = filename;
		try{
			file = new RandomAccessFile(filename,"rw");
			try{
				int n = file.readInt();
			}
			catch(Exception e){
				file.writeChars("0\n");
			}
		} catch(Exception e){}
	}
	public void add(Student s){
		try{
			String toAdd = s.toString();
			file.seek(file.length());
			file.writeChars(s.toString());
			file.seek(0);
			int n = Integer.parseInt(file.readLine());
			System.out.println(n+"");
			file.seek(0);
			file.writeChars(n+"\n");
		}
		catch(Exception e){}
	}
}


class StudentDBGUI extends Frame implements ActionListener{
	public static void main(String[] args){
		Student s = new Student("5,Appu,17,89");
		Database db = new Database("db.txt");
		db.add(s);
		

	}
	public void actionPerformed(ActionEvent evt){

	}

}