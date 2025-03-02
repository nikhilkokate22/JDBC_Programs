package sqlProcedures;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Procedures_Std70 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try (sc;) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "advjava", "advjava");
			CallableStatement cs = con.prepareCall("{call createstudentdata70(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			System.out.println("Enter student roll no : ");
			int rno = Integer.parseInt(sc.nextLine());
			System.out.println("Enter student name:");
			String sName = sc.nextLine();
			System.out.println("Enter student branch:");
			String branch = sc.nextLine();
			System.out.println("Enter student city:");
			String city = sc.nextLine();
			System.out.println("Enter student state:");
			String state = sc.nextLine();
			System.out.println("Enter Pin code no:");
			long pin = Long.parseLong(sc.nextLine());
			System.out.println("Enter student MailId:");
			String mId = sc.nextLine();
			System.out.println("Enter student phno:");
			long phNo = Long.parseLong(sc.nextLine());
			System.out.println("Enter student sub1 marks:");
			int sub1 = Integer.parseInt(sc.nextLine());
			System.out.println("Enter student sub2 marks:");
			int sub2 = Integer.parseInt(sc.nextLine());
			System.out.println("Enter student sub3 marks:");
			int sub3 = Integer.parseInt(sc.nextLine());
			System.out.println("Enter student sub4 marks:");
			int sub4 = Integer.parseInt(sc.nextLine());
			System.out.println("Enter student sub5 marks:");
			int sub5 = Integer.parseInt(sc.nextLine());
			System.out.println("Enter student sub6 marks:");
			int sub6 = Integer.parseInt(sc.nextLine());
			double totMarks = sub1 + sub2 + sub3 + sub4 + sub5 + sub6;
			double perc = (totMarks / 600) * 100;
			String res = null;
			if (perc <= 100 && perc >= 35) {
				if (perc >= 85) {
					res = "FC with Disc";

				}
				if (perc >= 75 && perc<85) {
					res = "First Class";
				}
				if (perc >= 65 && perc<75) {
					res = "Second Class";
				}
				if (perc < 65 && perc >= 35) {
					res = "pass";
				}
			}else {
				res = "Fail..";
			}

			cs.setInt(1, rno);
			cs.setString(2, sName);
			cs.setString(3, branch);
			cs.setString(4, city);
			cs.setString(5, state);
			cs.setLong(6, pin);
			cs.setString(7, mId);
			cs.setLong(8, phNo);
			cs.setInt(9, sub1);
			cs.setInt(10, sub2);
			cs.setInt(11, sub3);
			cs.setInt(12, sub4);
			cs.setInt(13, sub5);
			cs.setInt(14, sub6);
			cs.setDouble(15, totMarks);
			cs.setDouble(16, perc);
			cs.setString(17, res);

			cs.execute();
			System.out.println("Student data created successfully..");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
