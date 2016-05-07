/* This Program was developed by Swati Mittal. 
 * Date - 06-May-2016
 * 
 * This is the Controller module. I have implements the RST Api calls this module.
 * Rest APi : GET method is used to take the input from the user.
 * Input Variables:
 * Distance 
 * Longitude
 * Latitude
 * Appointment Type
 * 
 * Sample Api to GET (This we will use take the input from the user):
 * http://localhost:8080/user?distance=40&latitude=37.323&longitude=-122.883&appType=Checkup
 * 
 *  
 */

package com.example;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	DatabaseConn dbConn;
	ArrayList<OutputBranch> output = new ArrayList<OutputBranch>();

	// In the constructor MySQL database connection will occur 
	public Controller() {
		dbConn = new DatabaseConn();
	}

	//This is the GET method of the REST Api. Inputs like distance, latitude, longitude, 
	//	appointment type will be given in the APi
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ResponseEntity<Object> updateDistance(@RequestParam("distance") double d,
			@RequestParam("longitude") double lo, @RequestParam("latitude") double la, @RequestParam("appType") String aT)
			throws SQLException {
		
		// Check for any bad input. If there is any error in the input type it will throw error
		

		//Check if the distance enter should be positive value. If it is negative it will throw error
		if(d < 0 ){
			return new ResponseEntity<Object>("Distance cann't be negative or Blank", HttpStatus.BAD_REQUEST);
		}
		
		//Output Arraylist receives the details of all available appointments
		output = dbConn.fetchData(d, la, lo, aT);

		//Check if there is no appointment available it will display "NO Appointment" else program will display all
		//available appointment in JSON format
		if (output.isEmpty())
			return new ResponseEntity<Object>("No Appointment", HttpStatus.OK);
		else
			return new ResponseEntity<Object>(output, HttpStatus.OK);

	}

}
