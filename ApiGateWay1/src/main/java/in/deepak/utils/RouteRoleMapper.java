package in.deepak.utils;

import org.springframework.stereotype.Component;

@Component
public class RouteRoleMapper {
	
	 public String getRequiredRoleForPath(String path) {
	        // Define required role for specific endpoint paths
	        if (path.startsWith("/insurance/user") || path.startsWith("/policy/buypolicy")) {
	            return "USER"; // Example: Require 'admin' role for these endpoints
	        }
	        else if (path.startsWith("/insurance/admin") || path.startsWith("/policy/applications")) {
	            return "ADMIN"; // Example: Require 'admin' role for these endpoints
	        }
	        else if(path.startsWith("/policy/underwriter")){
	            return "UNDERWRITER";
	        }
	        // For other endpoints, return null or any default role as needed
	        return null;
	    }

}
