package lab21Jan;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Player_info {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","labadvjava","labadvjava");
			PreparedStatement ps1 = con.prepareStatement("insert into player_info values(?,?,?,?)");
			
			System.out.println("Enter pid : ");
			int pId = Integer.parseInt(sc.nextLine());
			System.out.println("Enter name : ");
			String pName = sc.nextLine();
			System.out.println("Enter fPath&fName(source of file) : ");
			String path = sc.nextLine();
			System.out.println("Enter DOB : ");
			String pDOB = sc.nextLine();

			File f = new File(path);
			if(f.exists()) {
				FileInputStream fis = new FileInputStream(f);// focus on this
				ps1.setInt(1, pId);
				ps1.setString(2, pName);
				ps1.setBinaryStream(3, fis, f.length());  // on this also
				ps1.setString(4, pDOB);
				
				int k = ps1.executeUpdate();
				if(k>0) {
					System.out.println("Data stored successfully...");
				}
			}
			else {
				System.err.println("Invalid file path..");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
