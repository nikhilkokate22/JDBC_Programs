package student70;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Student_info {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try(sc;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@locahost:1521:orcl","advjava","advjava");
			PreparedStatement ps1 = con.prepareStatement("insert into student70 values(?,?,?,?,?,?,?,?)");
			PreparedStatement ps2 = con.prepareStatement("select * from student70");
			PreparedStatement ps3 = con.prepareStatement("select * from student70 where rollno=?");
			PreparedStatement ps4 = con.prepareStatement("update student70 set mid=?, phno=?");
			PreparedStatement ps5 = con.prepareStatement("delete from student70 where rollno = ?");
			while(true) {
				System.out.println("---------Operations choice-------");
				System.out.println("\t1.1.AddStudent\n"
						+ "\t2.ViewAllStudentsr\n"
						+ "\t3.ViewStudentByRollNo\n"
						+ "\t4.UpdateStudentByRollNo(mid-phno)\n"
						+ "\t5.DeleteStudentByRollNo");
				System.out.print("Enter choice : ");
				int choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1:
					System.out.println("Enter roll no : ");
					int rollno = Integer.parseInt(sc.nextLine());
					System.out.println("Enter name : ");
					String name = sc.nextLine();
					System.out.println("Enter branch : ");
					String branch = sc.nextLine();
					System.out.println("Enter mail id : ");
					String mid = sc.nextLine();
					System.out.println("Enter phone no : ");
					int phno = Integer.parseInt(sc.nextLine());
					System.out.println("Enter total 6 sub marks : ");
					System.out.println("Enter sub1 marks : ");
					int s1Marks = Integer.parseInt(sc.nextLine());
					System.out.println("Enter sub2 marks : ");
					int s2Marks = Integer.parseInt(sc.nextLine());
					System.out.println("Enter sub3 marks : ");
					int s3Marks = Integer.parseInt(sc.nextLine());
					System.out.println("Enter sub4 marks : ");
					int s4Marks = Integer.parseInt(sc.nextLine());
					System.out.println("Enter sub5 marks : ");
					int s5Marks = Integer.parseInt(sc.nextLine());
					System.out.println("Enter sub6 marks : ");
					int s6Marks = Integer.parseInt(sc.nextLine());
					
					double totMarks=0;
					if((0<s1Marks && s1Marks<=100) && (s2Marks>0 && s2Marks<=100) && (s3Marks>0 && s3Marks<=100) && (s4Marks>0 && s4Marks<=100) && 
							(s5Marks>0 && s5Marks<=100) && (s6Marks>0 && s6Marks<=100)) {
						totMarks = (double)s1Marks+s2Marks+s3Marks+s4Marks+s5Marks+s6Marks;
					}
					else {
						throw new Exception("Marks must be in between 0-100");
					}
					String res;
					double per= (totMarks/600)*100; 
					if(per>=85) {
						 res = "First class with Distinction";
					}
					else if(per>=75) {
						 res = "First class";
					}
					else if(per>=65) {
						 res = "Second class";
					}
					else if(per>=55 && per<=35) {
						 res = "Pass";
					}
					else {
						 res = "Fail";
					}
					
					ps1.setInt(1, rollno);
					ps1.setString(2, name);
					ps1.setString(3, branch);
					ps1.setString(4, mid);
					ps1.setInt(5, phno);
					ps1.setDouble(6, totMarks);
					ps1.setDouble(7, per);
					ps1.setString(8, res);
					int k1 = ps1.executeUpdate();
					if(k1>0) {
						System.out.println("Data inserted successfully...");
					}
					break;
				case 2:
					System.out.println("******Student Data********");
					ResultSet rs1 = ps2.executeQuery();
					while(rs1.next()) {
						System.out.println(rs1.getInt(1)+"\t"
								+rs1.getString(2) +"\t"
								+rs1.getString(3) +"\t"
								+rs1.getString(4) +"\t"
								+rs1.getInt(5) +"\t"
								+rs1.getDouble(6) +"\t"
								+rs1.getDouble(7) +"\t"
								+rs1.getString(8) +"\t");
					}
					break;
				case 3:
					System.out.println("Enter roll no to view student : ");
					int rollNo = Integer.parseInt(sc.nextLine());
					ps3.setInt(1, rollNo);
					
					break;
				default:
					break;
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
