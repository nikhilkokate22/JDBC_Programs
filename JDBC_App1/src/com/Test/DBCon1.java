package com.Test;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;

public class DBCon1 {
	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@Localhost:1521:orcl","system","tiger");
			
			Statement stm = con.createStatement();
			
			ResultSet rs = stm.executeQuery("select * from product70");
			System.out.println("----------Customer-Details--------------");
			
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getDouble(3)+"\t"+rs.getInt(4));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
