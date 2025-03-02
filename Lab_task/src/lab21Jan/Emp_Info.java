package lab21Jan;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Emp_Info {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","labadvjava","labadvjava");
			PreparedStatement ps1 = con.prepareStatement("insert into emp_info values (?,?,?,?,?,?)");
			PreparedStatement ps2 = con.prepareStatement("select * from emp_info");
			
			System.out.println("Enter emp id : ");
			int empId = Integer.parseInt(sc.nextLine());
			System.out.println("Enter emp name : ");
			String empName = sc.nextLine();
			System.out.println("Enter emp address : ");
			String empAddr = sc.nextLine();
			System.out.println("Enter emp Mail : ");
			String empMail = sc.nextLine();
			System.out.println("Enter emp phone no : ");
			long empPhno = Long.parseLong(sc.nextLine());
			System.out.println("Enter fPath&fName(Source of file)");
			String path = sc.nextLine();
			File f = new File(path);
			if(f.exists()) {
				FileReader fr  = new FileReader(path);
				ps1.setInt(1, empId);
				ps1.setString(2, empName);
				ps1.setString(3, empAddr);
				ps1.setString(4, empMail);
				ps1.setLong(5, empPhno);
				ps1.setClob(6, fr, f.length());
				int k = ps1.executeUpdate();
				if(k>0) {
					System.out.println("Data inserted successfully..");
				}
				
			}
			else {
				System.err.println("Invalid file path.");
			}
			
			System.out.println("===========Stored data================");
			ResultSet rs = ps2.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getLong(5)+"\t"+rs.getClob(6));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
