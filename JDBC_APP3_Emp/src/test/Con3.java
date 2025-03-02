package test;

import java.sql.*;
import java.util.*;

public class Con3 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@Localhost:1521:orcl","advjava","advjava");
			Statement stm = con.createStatement();
			
			while(true) {
				System.out.println("\n---------Operations Choice-------------");
				System.out.println("\t1.AddEmployee"
						+"\n\t2.ViewAllEmployees"
						+"\n\t3.ViewEmployeeById"
						+"\n\t4.UpdateEmployeeById"
						+"\n\t5.DeleteEmployeeById"
						+"\n\t6.Exit");
				System.out.println("Enter your choice: ");
				int choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1: 
					System.out.println("-------Employee Details-----------");
					System.out.println("Enter Employee id : ");
					int eId = Integer.parseInt(sc.nextLine());
					System.out.println("Enter Employee name: ");
					String eName = sc.nextLine();
					System.out.println("Enter Designation : ");
					String eDesg = sc.nextLine();
					System.out.println("Enter Basic Salary : ");
					double eBasicSal = Double.parseDouble(sc.nextLine());
					
					double hra = (eBasicSal*0.96);
					double da = (eBasicSal*0.61);
					double totSal = eBasicSal+hra+da;
					
					if(eBasicSal>=12000) {
						int k1 = stm.executeUpdate("insert into employee70 values("+eId+", '"+eName+"', '"+eDesg+"', "+eBasicSal+", "+hra+", "+da+", "+totSal+")");
						if(k1>0) {
							System.out.println("Emplyee added successfully..");
						}
					}
					else {
						throw new Exception("Salary is less than 12000");
					}
					break;
					
				case 2:
					ResultSet rs1 = stm.executeQuery("Select * from employee70");
					System.out.println("---------Employee Details----------");
					while(rs1.next()) {
						System.out.println(rs1.getInt(1)+"\t"
								+rs1.getString(2)+"\t"
								+rs1.getString(3)+"\t"
								+rs1.getDouble(4)+"\t"
								+rs1.getDouble(5)+"\t"
								+rs1.getDouble(6)+"\t"
								+rs1.getDouble(6));
					}
					break;
				case 3:
					System.out.println("Enter Id to display Employee details : ");
					int eIdIp = Integer.parseInt(sc.nextLine());
					ResultSet rs2 = stm.executeQuery("Select * from employee70 where eid="+eIdIp);
					if(rs2.next()) {
						System.out.println(rs2.getInt(1)+"\t"
								+rs2.getString(2)+"\t"
								+rs2.getString(3)+"\t"
								+rs2.getDouble(4)+"\t"
								+rs2.getDouble(5)+"\t"
								+rs2.getDouble(6)+"\t"
								+rs2.getDouble(7));
					}
					else {
						System.err.println("Invalid employee Id..");
					}
					break;
				case 4:
					System.out.println("Enter ID to update Employee details : ");
					int updateEId = Integer.parseInt(sc.nextLine());
					
					ResultSet rs3 = stm.executeQuery("select * from employee70 where eid = "+updateEId+"");
					if(rs3.next()) {
						System.out.println("Existing basic salary : "+rs3.getDouble(4));
						System.out.println("Enter new Salary : ");
						double newBsal = Double.parseDouble(sc.nextLine());
						double nhra = newBsal*0.96;
						double nda = newBsal*0.61;
						double ntotSal = newBsal+nhra+nda;
						
						 int k2 = stm.executeUpdate("update employee70 set bsal="+newBsal+", hra="+nhra+", da="+nda+", totsal="+ntotSal+" where eid="+updateEId+"");
						if(k2>0) {
							System.out.println("Employee details Updated successfully..");
						}
					}
					else {
						System.err.println("Invalid Employee-id...");
					}
					break;
				case 5:
					System.out.println("Enter Employee id to delete employee details..");
					int dltId = Integer.parseInt(sc.nextLine());
					ResultSet rs4 = stm.executeQuery("select * from employee70 where eid="+dltId+"");
					if(rs4.next()) {
						int k3 = stm.executeUpdate("delete from employee70 where eid = "+dltId+"");
						if(k3>0) {
							System.out.println("Employee details deleted succefully..");
						}
					}else {
						System.err.println("Invalid Employee-Id..");
					}
					break;
				case 6:
					System.out.println("Application exited....");
					System.exit(0);
				default:
					System.err.println("Invalid choice..");
				}// end of switch
			}// end of while loop
		}
		catch(SQLIntegrityConstraintViolationException sq) {
			System.err.println("Employee already exists...");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
