package com.tap1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CRUD {
	public static Connection connection;
	public static PreparedStatement statement;
	public static Scanner scan = new Scanner(System.in);
	public static String url="jdbc:mysql://localhost:3306/jdbc";
	public static String password="8924";
	public static String username="root";

	public static void main(String[] args) {
		while(true) {
		System.out.println("Chose Your Option");
		System.out.println("1.Insert");
		System.out.println("2.Show Table");
		System.out.println("3.Update");
		System.out.println("4.Delete");
		System.out.println("5.Exit");
		int A=scan.nextInt();
		if(A==1) {
			Insert();
		}
		else if(A==2) {
			showTable();
		}
		else if(A==3) {
			Update();
		}
		else if(A==4) {
			delete();
		}
		else if(A==5) {
			System.out.println("Thank You");
			System.exit(0);
		}
		}
	}
	private static void showTable() {
		try {
			display(connection, statement, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public static void delete() {
		String SQL="DELETE FROM `employee_table` WHERE `id`=?";
		try {
			display(connection, statement, null);	
			connection=DriverManager.getConnection(url, username, password);
			statement=connection.prepareStatement(SQL);
			statement.setInt(1, scan.nextInt());
			statement.addBatch();
			System.out.println();
			int[] ar=statement.executeBatch();
			for(int i=0;i<ar.length;i++) {
				System.out.println(ar[i]+"");
				display(connection, statement, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public static void Insert() {
		String SQL="INSERT `employee_table`(`Id`,`name`,`Dept`,`Salary`) values(?,?,?,?)";
		  try {
			  connection=DriverManager.getConnection(url, username, password);
			  display(connection, statement, null);
			  statement=connection.prepareStatement(SQL);

					statement.setInt(1, scan.nextInt());
					statement.setString(2, scan.next());
					statement.setString(3, scan.next());
					statement.setInt(4, scan.nextInt());
					statement.addBatch();
					System.out.println();
					int[] ar=statement.executeBatch();
					for(int i=0;i<ar.length;i++) {
						System.out.println(ar[i]+" ");
					}
					display(connection, statement, null);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void Update() {
		String SQL="UPDATE employee_table SET name = ?, Dept = ?, Salary = ? WHERE id = ?";
		try {
		
			  connection=DriverManager.getConnection(url, username, password);
			  display(connection, statement, null);
			  statement=connection.prepareStatement(SQL);

					statement.setString(1, scan.next());
					statement.setString(2, scan.next());
					statement.setInt(3, scan.nextInt());
					statement.setInt(4, scan.nextInt());
					statement.addBatch();
					System.out.println();
					int[] ar=statement.executeBatch();
					for(int i=0;i<ar.length;i++) {
						System.out.println(ar[i]+" ");
					}
					display(connection, statement, null);	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public static void display(Connection connection, Statement statement, ResultSet res) throws SQLException {
		String SQL="SELECT * from employee_table";
		  connection=DriverManager.getConnection(url, username, password);
		statement=connection.createStatement();
		 res=statement.executeQuery(SQL);
		while(res.next()) {
			int Id=res.getInt("Id");
			String Name=res.getString("Name");
			String Dept=res.getString("Dept");
			int Salary=res.getInt("Salary");
System.out.printf("%d %-9s %-19s  %d\n",Id,Name,Dept,Salary);		
		}
	}
}
