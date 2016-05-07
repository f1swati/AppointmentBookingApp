/*
 * This Program was developed by Swati Mittal. 
 * Date - 07-May-2016
 * 
 * This Program is to connect the MySQL database to the Java application.
 * This module fetch the longitude and latitude of all the branches which are at the 
 * required distance and have required appointment type. 
 * 
 */

package com.example;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DatabaseConn {

	Connection connection;
	OutputBranch ob;

	// As soon as the DatabaseConn class object is created. Connection with the
	// MySQL database will be established

	public DatabaseConn() {

		// Check if the MySQL driver is present
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("No Driver present");
			e.printStackTrace();
			return;
		}

		// Check for the connection
		try {
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://usercmpe281nstance.ceeen7aldzyj.us-west-1.rds.amazonaws.com:3306/pharmacy", "admin123",
					"Amit1234");
		} catch (SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("Connected to database ");
		} else {
			System.out.println("Failed to make connection!");
		}

	}

	// This method run the SQL query and fetch the all relevant branches from
	// the MySQL database and
	// return the Array list of the all the relevant branches
	public ArrayList<OutputBranch> fetchData(double d, double la, double lo, String aT) throws SQLException {

		ArrayList<OutputBranch> outputArray = new ArrayList<OutputBranch>();

		String query = "SELECT * FROM branches WHERE ID In (SELECT branch_id FROM appointmenttype WHERE appointmenType =  "
				+ "\"" + aT + "\"" + ")";

		Statement st = null;
		st = (Statement) connection.createStatement();
		ResultSet rs;

		// This statement execute the SQL query
		rs = st.executeQuery(query);

		double distance;

		// All result are executed one by one store their value in local
		// variable
		while (rs.next())

		{
			int id = rs.getInt("ID");
			String name = rs.getString("Name");
			double longitude = rs.getDouble("Longitude");
			double latitude = rs.getDouble("Latitude");
			String address = rs.getString("Address");
			String phone = rs.getString("Phone");
			byte[] imgData = rs.getBytes("pic");

			// Calculate the distance between the given longitude, latitude with
			// the branches longitude and latitudes
			distance = CalculateDistance.distance(latitude, longitude, la, lo);

			// Check if the distance is in the given range or not. If it is than
			// create object of the branch and store all its information
			if (distance <= d) {
				ob = null;
				ob = new OutputBranch();
				ob.Address = address;
				ob.longitude = longitude;
				ob.latitude = latitude;
				ob.phone = phone;
				ob.name = name;
				ob.imgData = imgData;
				outputArray.add(ob);
			}
		}

		st.close();
		return outputArray;

	}
}
