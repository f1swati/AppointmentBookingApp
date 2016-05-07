/* This Program was developed by Swati Mittal. 
 * Date - 06-May-2016
 * 
 * This object of this module store the information of all the relevant branches
 *  @jsonView notation is used to display the information in the JSON format
 */

package com.example;

import com.fasterxml.jackson.annotation.JsonView;

public class OutputBranch {
	
	
	@JsonView({ BranchView.User.class })
	String name;
	
	@JsonView({ BranchView.User.class })
	double longitude;
	
	@JsonView({ BranchView.User.class })
	double latitude;
	
	@JsonView({ BranchView.User.class })
	String Address;
	
	@JsonView({ BranchView.User.class })
	String phone;
	
	@JsonView({ BranchView.User.class })
	byte[] imgData;
	

}
