package lab21Jan;

import java.io.FileWriter;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Retrieve_resume {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","labadvjava","labadvjava");
			PreparedStatement ps1 = con.prepareStatement("select empResume from emp_info where empPhno = ?");
			
			System.out.println("Enter empPhno to retrieve resume : ");
			long empPhno = Long.parseLong(sc.nextLine());
			ps1.setLong(1, empPhno);
			ResultSet rs = ps1.executeQuery();
			if(rs.next()) {
				Clob cl = rs.getClob(1); // Retrieve the CLOB
                Reader characterStream = cl.getCharacterStream(); // Get character stream from CLOB
                
                // File path to save the retrieved resume
                String filePath = "G:\\new\\ABC.txt";
                FileWriter fw = new FileWriter(filePath);
                
                // Buffer to read CLOB data
                char[] buffer = new char[1024];
                int charsRead;
                
                // Read from CLOB and write to file
                while ((charsRead = characterStream.read(buffer)) != -1) {
                    fw.write(buffer, 0, charsRead);
                }
                
                fw.close(); // Close the FileWriter
                System.out.println("Resume retrieved and saved to: " + filePath);
			}
			else{
				System.out.println("Invalid Mobile no.");
			}
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
