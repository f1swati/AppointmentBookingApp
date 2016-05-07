/* This Program was developed by Swati Mittal. 
 * Date - 06-May-2016
 * 
 *The Object of this is application is to fetch the longitude, latitudes, name, address  of the
 *branches on the basis user's given location, range and type of the appointment
 *
 * To built this application I have used Spring MVC framework, Java, MySQL, RESTApi
 * 
 * This is the main module to boot the spring application
 */
package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainConfiguration {

	public static void main(String[] args) {

		SpringApplication.run(MainConfiguration.class, args);

	}
}
