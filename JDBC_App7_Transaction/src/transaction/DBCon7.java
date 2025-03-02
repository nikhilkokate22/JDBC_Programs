package transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.Scanner;

public class DBCon7 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try(sc;){
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","advjava","advjava");
			PreparedStatement ps1 = con.prepareStatement("select * from bank70 where accno = ?");
			PreparedStatement ps2 = con.prepareStatement("update bank70 set balance = balance + ? where accno = ?");
			System.out.println("Commit Status : "+con.getAutoCommit());
			con.setAutoCommit(false);
			System.out.println("Commit status : "+con.getAutoCommit());
			Savepoint sp = con.setSavepoint();
			System.out.println("Enter the Home Accno : ");
			long hAccNo = sc.nextLong();
			ps1.setLong(1, hAccNo);
			ResultSet rs1 = ps1.executeQuery();
			if(rs1.next()) {
				float bl = rs1.getFloat(3);
				System.out.println("Enter benificiery AccNo: ");
				long bAccNo = sc.nextLong();
				ps1.setLong(1, bAccNo);
				ResultSet rs2 = ps1.executeQuery();
				if(rs2.next()) {
					System.out.println("Enter the amount to be transferred: ");
					float amt = sc.nextFloat();
					if(amt<=bl) {
						// Statement 2 -> sub amt:5000 from accno: 90232323
						ps2.setFloat(1, -amt);
						ps2.setLong(2, hAccNo);
						int p = ps2.executeUpdate(); //Buffer updated
						
						// Statement 2 -> Add amt:5000 to accno: 90232324
						ps2.setFloat(1, +amt);
						ps2.setLong(2, bAccNo);
						int q = ps2.executeUpdate(); //Buffer updated
						
						if(p==1 && q==1) {
							con.commit();
							System.out.println("Transaction Successfull..");
						}
						else {
							throw new TransactionFailException("Transaction failed..");
//							System.err.println("Transaction failed..");
						}
					}
					else {
						con.rollback(sp);
						throw new InsufficientbalanceException("Insufficient balance..");
//						System.out.println("Insufficient balance..");
					}
				}
				else {
					throw new InvalidAccNoException("Invalid beneficiery acc. no.");
//					System.err.println("Invalid beneficiery acc. no.");
				}
			}
			else {
				throw new InvalidAccNoException("Invalid home acc. no.");
//				System.err.println("Invalid home acc. no.");
			}
			
		}
		catch(InvalidAccNoException e) {
			System.out.println(e.getMessage());
		}
		catch(TransactionFailException tfe) {
			System.out.println(tfe.getMessage());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
