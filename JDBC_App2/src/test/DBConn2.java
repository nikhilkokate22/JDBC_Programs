package test;

import java.sql.*;
import java.util.*;

public class DBConn2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@Localhost:1521:orcl","system","tiger");
			Statement stm = con.createStatement();
			
			while(true) {
				System.out.println("\n---------Operations Choice-------------");
				System.out.println("\t1.AddProduct"
						+"\n\t2.ViewAllProducts"
						+"\n\t3.ViewProductByCode"
						+"\n\t4.UpdateProductByCode"
						+"\n\t5.DeleteProductByCode"
						+"\n\t6.Exit");
				System.out.println("Enter your choice: ");
				int choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1: 
					System.out.println("-------Product Details-----------");
					System.out.println("Enter code : ");
					int code = Integer.parseInt(sc.nextLine());
					System.out.println("Enter the name: ");
					String name = sc.nextLine();
					System.out.println("Enter Price : ");
					double price = Double.parseDouble(sc.nextLine());
					System.out.println("Enter quantity : ");
					int qty = Integer.parseInt(sc.nextLine());
					
					int k = stm.executeUpdate("insert into product70 values("+code+", '"+name+"', "+price+", "+qty+")");
					
					if(k>0) {
						System.out.println("Product added successfully..");
					}
					break;
				case 2:
					ResultSet rs1 = stm.executeQuery("Select * from product70");
					System.out.println("---------Product Details----------");
					while(rs1.next()) {
						System.out.println(rs1.getInt(1)+"\t"
								+rs1.getString(2)+"\t\t"
								+rs1.getDouble(3)+"\t\t"
								+rs1.getInt(4));
					}
					break;
				case 3:
					System.out.println("Enter code to display details : ");
					int codeIn = Integer.parseInt(sc.nextLine());
					ResultSet rs2 = stm.executeQuery("Select * from product70 where code="+codeIn);
					if(rs2.next()) {
						System.out.println(rs2.getInt(1)+"\t"
								+rs2.getString(2)+"\t"
								+rs2.getDouble(3)+"\t"
								+rs2.getInt(4));
					}
					else {
						System.err.println("Invalid Product Id..");
					}
					break;
				case 4:
					System.out.println("Enter code to Update product details : ");
					int updCodeIn = Integer.parseInt(sc.nextLine());
					
					ResultSet rs3 = stm.executeQuery("select * from product70 where code="+updCodeIn+"");
					if(rs3.next()) {
						System.out.println("Existing price is : "+rs3.getDouble(3));
						System.out.println("Enter new price : ");
						double newPrice = Double.parseDouble(sc.nextLine());
						System.out.println("Existing quantity is : "+rs3.getInt(4));
						System.out.println("Enter new quantity : ");
						int newQty = Integer.parseInt(sc.nextLine());
						
						int k2 = stm.executeUpdate("update product70 set price="+newPrice+", qty="+newQty+" where code="+updCodeIn+"");
						if(k2>0) {
							System.out.println("Product details update successfully..");
						}
					}else {
						System.err.println("Invalid customer id...");
					}
					break;
				case 5: 
					System.out.println("Enter Code to delete product details:");
					int dltCode = Integer.parseInt(sc.nextLine());
					ResultSet rs4 = stm.executeQuery("select * from product70 where code="+dltCode+"");
					if(rs4.next()) {
						int k2 = stm.executeUpdate("delete from product70 where code="+dltCode+"");
						if(k2>0) {
							System.out.println("Product details deleted successfully..");
						}
					}else {
						System.out.println("Invalid product code..");
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
			System.err.println("Product already exist...");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
