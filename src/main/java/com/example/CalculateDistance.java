/* This Program was developed by Swati Mittal. 
 * Date - 06-May-2016
 * 
 * This objective of this module is to calculate the distance between the latitude and longitude
 * 
 */

package com.example;

import java.util.*;
import java.lang.*;
import java.io.*;

class CalculateDistance {

	static double distance(double lat1, double lon1, double lat2, double lon2) {

		if (lat1 == lat2 && lon1 == lon2) {
			return 0.0;
		}

		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return (dist);
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
