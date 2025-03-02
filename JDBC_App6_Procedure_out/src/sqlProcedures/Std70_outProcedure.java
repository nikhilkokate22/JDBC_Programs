package sqlProcedures;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class Std70_outProcedure {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","advjava","advjava");
			CallableStatement cs = con.prepareCall("{call getstudata70(?,?,?,?,?,?,?,?,?,?,?)}");
			
			System.out.println("Enter roll no to retrieve details : ");
			int rollNo = Integer.parseInt(sc.nextLine());
			
			cs.setInt(1, rollNo);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.INTEGER);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.registerOutParameter(8, Types.BIGINT);
			cs.registerOutParameter(9, Types.DOUBLE);
			cs.registerOutParameter(10, Types.DOUBLE);
			cs.registerOutParameter(11, Types.VARCHAR);
			cs.execute();
			
			System.out.println("---------Student details----------");
			System.out.println("Stud-Name:"+cs.getString(2));
			System.out.println("Stud-Branch:"+cs.getString(3));
			System.out.println("Stud-City:"+cs.getString(4));
			System.out.println("Stud-State:"+cs.getString(5));
			System.out.println("Stud-PinCode:"+cs.getInt(6));
			System.out.println("Stud-MailId:"+cs.getString(7));
			System.out.println("Stud-PhoneNo:"+cs.getLong(8));
			System.out.println("Stud-TotalMarks:"+cs.getDouble(9));
			System.out.println("Stud-Percentage:"+cs.getDouble(10));
			System.out.println("Stud-Result:"+cs.getString(11));
			
			con.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
