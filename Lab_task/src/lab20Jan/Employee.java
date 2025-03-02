package lab20Jan;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Employee {
	int id;
	String name;
	int age;
	double salary;
	
	public Employee(int id2, String name2, int age2, double salary2) {
		this.id=id2;
		this.name=name2;
		this.age=age2;
		this.salary=salary2;
	}
	

	@Override
	public String toString() {
		return "" + id + "\t" + name + "\t" + age + "\t" + salary + "";
	}


	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","labadvjava","labadvjava");
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from employee where age >25");
			
			List<Employee> list = new ArrayList<Employee>();
			while(rs.next()) {
				
				int id = rs.getInt(1);
				String name = rs.getString(2);
				int age = rs.getInt(3);
				double salary = rs.getDouble(4);
				
				list.add(new Employee(id,name, age, salary));
				
			}
			
//			list.forEach(System.out::println);
			for (Employee employee : list) {
				System.out.println(employee);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
