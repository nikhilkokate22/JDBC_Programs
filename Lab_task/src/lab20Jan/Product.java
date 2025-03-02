package lab20Jan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Product {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","labadvjava","labadvjava");
			PreparedStatement ps1 = con.prepareStatement("insert into product values(?,?,?,?)");
			PreparedStatement ps2 = con.prepareStatement("select * from product",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
											//by the help of  this we can read data both directions(forward and backward)
			
			while(true) {
				System.out.println("-------Operation choice----------");
				System.out.println("          1.Insert productdetails into product table.\r\n"
						+ "          2.Retrieve productdetails in forward direction.\r\n"
						+ "          3.Retrieve productdetails in reverse direction.\r\n"
						+ "          4.Retrieve 3rd record from top.\r\n"
						+ "          5.Retrieve 3rd record from bottom. \r\n"
						+ "          6.Retrieve last three record from product table.\n"
						+ "          7.Exit");
				System.out.println("Enter your choice : ");
				int choice = Integer.parseInt(sc.nextLine());
				switch (choice) {
				case 1: 
					System.out.println("Enter product id : ");
					int pId = Integer.parseInt(sc.nextLine());
					System.out.println("Enter product name : ");
					String pName = sc.nextLine();
					System.out.println("Enter product price : ");
					int pPrice = Integer.parseInt(sc.nextLine());
					System.out.println("Enter product quantity : ");
					int pQty = Integer.parseInt(sc.nextLine());
					
					ps1.setInt(1, pId);
					ps1.setString(2, pName);
					ps1.setInt(3, pPrice);
					ps1.setInt(4, pQty);
					int k = ps1.executeUpdate();
					if(k>0) {
						System.out.println("Data inserted successfully..");
					}
					
					break;
					
				case 2:
					System.out.println("Data in forward direction..");
					ResultSet rs1 = ps2.executeQuery();
					while(rs1.next()) {
						System.out.println(rs1.getInt(1)+"\t"
								+rs1.getString(2)+ "\t"
										+rs1.getInt(3)+ "\t"
												+rs1.getInt(4));
						
					}
					break;
				case 3:
					System.out.println("Data in reverse direction");
					ResultSet rs2 = ps2.executeQuery();
					rs2.afterLast();
					while(rs2.previous()) {
						System.out.println(rs2.getInt(1)+"\t"
								+rs2.getString(2)+ "\t"
										+rs2.getInt(3)+ "\t"
												+rs2.getInt(4));
					}
					break;
				case 4:
					System.out.println("3rd record from top");
					ResultSet rs3 = ps2.executeQuery();
					while(rs3.relative(3)) {
						System.out.println(rs3.getInt(1)+"\t"
								+rs3.getString(2)+ "\t"
										+rs3.getInt(3)+ "\t"
												+rs3.getInt(4));
					}
					break;
				case 5:
					System.out.println("3rd record from bottom");
					ResultSet rs4 = ps2.executeQuery();
					rs4.afterLast();
					while(rs4.relative(-3)) {
						System.out.println(rs4.getInt(1)+"\t"
								+rs4.getString(2)+ "\t"
										+rs4.getInt(3)+ "\t"
												+rs4.getInt(4));
					}
					break;
				case 6:
					System.out.println("last three record");
					ResultSet rs5 = ps2.executeQuery();
					rs5.last();
					rs5.relative(-3);
					while(rs5.next()) {
						System.out.println(rs5.getInt(1)+"\t"
								+rs5.getString(2)+ "\t"
										+rs5.getInt(3)+ "\t"
												+rs5.getInt(4));
					}
					break;
				case 7:
					System.out.println("Operations exited...");
					System.exit(0);

				default:
					System.out.println("Invalid choice..");
					break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
