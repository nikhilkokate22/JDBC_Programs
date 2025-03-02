package lab08Jan;

import java.sql.*;
import java.util.Scanner;

public class EmployeeInfo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@Localhost:1521:orcl","advjava","advjava");
			Statement stm = con.createStatement();
			while(true) {
				System.out.println("*************Operation choice*************");
				System.out.println("\t1.Insert Employee data"
						+ "\n\t2.Update employee details"
						+ "\n\t3.Find employee based on starting letter"
						+ "\n\t4.Count no. of employees records"
						+ "\n\t5.Delete Max salary employee"
						+ "\n\t6.Show all employee"
						+ "\n\t7.Exit");
				System.out.println("Enter your choice: ");
				int choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1:
					System.out.println("Enter employee id: ");
					int empId = Integer.parseInt(sc.nextLine());
					System.out.println("Enter employee name : ");
					String empName = sc.nextLine();
					System.out.println("Enter empSalary : ");
					double empSalary = Double.parseDouble(sc.nextLine());
					System.out.println("Enter address : ");
					String empAddr = sc.nextLine();
					System.out.println("Enter EmpMailId : ");
					String empMailId = sc.nextLine();
					System.out.println("Enter phone no. : ");
					int empPhoneNo = Integer.parseInt(sc.nextLine());
					
					int k = stm.executeUpdate("insert into employee values("+empId+", '"+empName+"', "+empSalary+", '"+empAddr+"', '"+empMailId+"',"+empPhoneNo+")");
					if(k>0) {
						System.out.println("Data inserted successfully..");
					}
					break;
				case 2:
					System.out.println("Enter id : ");
					int updId = Integer.parseInt(sc.nextLine());
					ResultSet rs1 = stm.executeQuery("select * from employee where empid="+updId+"");
					if(rs1.next()) {
						System.out.println("Existing salary is : "+rs1.getDouble(3));
						System.out.println("Enter new salary : ");
						double nSal = Double.parseDouble(sc.nextLine());
						System.out.println("Existing mail is : "+rs1.getString(5));
						System.out.println("Enter new mail id : ");
						String nMailId = sc.nextLine();
						System.out.println("Existing phone no : "+rs1.getInt(6));
						int nPhno = Integer.parseInt(sc.nextLine());
						
						int k2 = stm.executeUpdate("update employee set empsalary="+nSal+", empmailid='"+nMailId+"', empphno="+nPhno+" where empid="+updId+"");
						if(k2>0) {
							System.out.println("Employee details updated successfully..");
						}
					}
					else {
						System.err.println("Id not found..");
					}
					break;
				case 3:
					System.out.println("Enter only first letter : ");
					char fLetter = sc.nextLine().charAt(0);
					ResultSet rs2 = stm.executeQuery("select * from employee where empName like('"+fLetter+"%')");
					if(rs2.next()) {
						System.out.println(rs2.getInt(1)+"\t"
								+rs2.getString(2) +"\t"
								+rs2.getDouble(3) +"\t"
								+rs2.getString(4) +"\t"
								+rs2.getString(5) +"\t"
								+rs2.getInt(6));
					}
					else {
						System.err.println("Name not found..");
					}
					break;
				case 4:
					System.out.print("Total no. of employees are : ");
					ResultSet rs3 = stm.executeQuery("select count(*) from employee");
					while(rs3.next()) {
						System.out.println(rs3.getInt(1));
					}
					break;
				case 5:
					System.out.println("Deleting maximum salaried employee...");
					int k2 = stm.executeUpdate("delete from employee where empsalary = (select max(empsalary) from employee)");
					if(k2>0) {
						System.out.println("Employee data deleted successfully...");
					}
					break;
				case 6: 
					System.out.println("__________Employee details___________");
					ResultSet rs5 = stm.executeQuery("select * from employee");
					while(rs5.next()) {
						System.out.println(rs5.getInt(1)+"\t"
								+rs5.getString(2) +"\t"
								+rs5.getDouble(3) +"\t"
								+rs5.getString(4) +"\t"
								+rs5.getString(5) +"\t"
								+rs5.getInt(6));
					}
					break;
				case 7:
					System.out.println("Application Closed...");
					System.exit(0);
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
