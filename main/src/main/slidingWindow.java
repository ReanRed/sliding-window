package main;

import java.time.Duration;
import java.time.LocalTime;

import java.util.HashMap;
import java.util.ArrayList;

import java.time.temporal.ChronoUnit;

public class slidingWindow {

	//window to set the size of it
	int requestWindow = 60;
	//count, to keep track of number of request
	int requestCount = 0;
	//limit, enforce number of request per minute
	int requestLimit = 100;
	
	//record LocalTime of request
	LocalTime receivedRequestTime = LocalTime.of(0,0,0);
	//record the ArrayList at element index 0
	LocalTime currTimeStart = LocalTime.of(0,0,0);
	
	
	//Store UserID & ArrayList LocalTime
	HashMap <String,timeStamp> users = new HashMap<String,timeStamp>();
	

	public boolean allowRequest(String user) throws InterruptedException{
		
		//check if the program just started, if not return the current hashmap;
		if(users.size()>0) {
			IO.println("Entering program users hashmap is " + users);
		}else {
			IO.println("Program first initialization");
		}
		
		//timestamp the request received to the current time;
		receivedRequestTime = LocalTime.now(); 
		IO.println("received request @ "+ receivedRequestTime);
		

		// store time elapsed between 2 timestamp
		long timeElapsed;
		
		//assign currTimeStart to receivedRequest time to initialized the program
		
		//check if user exist, if new user, add user & LocalTime to the HashMap
		if (!users.containsKey(user)) {
			IO.println("No user found, inserting new user" + user);
			timeStamp stampTime = new timeStamp();
			stampTime.addLocalTime(receivedRequestTime);
			users.put(user,stampTime);
			requestCount = 0;
			requestCount += 1;
			
			IO.println("count: " + requestCount);
			IO.println(users);
			IO.println("exit program 1....");
			IO.println("=========================");
			
			return true;
		}
		
		//increment count to keep track of number of request sent
		requestCount++;
		
		
		IO.println("value of running 1 currTimeStart is " + users.get(user).returnElement(0) +" " + "receivedRequestTime is " + receivedRequestTime);
		
		//check the differences between 2 LocalTime in seconds;
		timeElapsed = Duration.between(users.get(user).returnElement(0),receivedRequestTime).toSeconds();
        IO.println("time elapsed: "+ timeElapsed);
	
        //if TimeElapsed is greater than window, consider sliding the window and removing oldest LocalTime if older than window
				if(timeElapsed > requestWindow) {
					LocalTime Window = receivedRequestTime.minusSeconds(requestWindow);
					IO.println("entered sliding window & window time is " + Window);
					
					
				if(users.get(user).returnElement(0).isBefore(Window)) {
					if(users.get(user).returnArraySize() == requestLimit) {
						users.get(user).removeElement(0);
						users.get(user).addLocalTime(receivedRequestTime);
						IO.println("Current HashMap after sliding pop is " + users);
						return true;
					}
					else {
						users.get(user).addLocalTime(receivedRequestTime);
						IO.println("Current HashMap is " + users);
						return true;
					}
				}
					
				else {
					IO.println("rejected window will not slide, current hashmap is " + users);
					return false;
				}
			}	
		
				
			//normal circumstance, if timeElapsed less than window and does not exceed request limit, add LocalTime to ArrayList
				if (timeElapsed <= requestWindow && requestCount <= requestLimit) {
					
					IO.println("user found" + users.keySet());
					IO.println("Entered in the window & adding to user, the time stamp");
					
					
					users.get(user).addLocalTime(receivedRequestTime);
					IO.println("count:" +requestCount);
					IO.println(users);
					IO.println("exit program 3....");
					IO.println("=========================");
				
					return true;
				}
				else {
					return false;
				}
				
		}

	// Function to inform how many millisecond before next request can be sent out
	public long retryAfterMs (String user) {
		
		LocalTime timeCurrWindow = users.get(user).returnElement(0).plusSeconds(requestWindow);
		long durationToWait = 0;
		
		IO.println("timeCurrWindow " + timeCurrWindow);

		IO.println("timeRequestReceivedWindow " + receivedRequestTime);
	
		
		
		durationToWait = receivedRequestTime.until(timeCurrWindow,ChronoUnit.MILLIS);	
		
		if(receivedRequestTime.isBefore(timeCurrWindow)) {
			durationToWait = receivedRequestTime.until(timeCurrWindow,ChronoUnit.MILLIS);
		}
		if(receivedRequestTime.isAfter(timeCurrWindow)) {
			durationToWait = 1;
		}
			return durationToWait;
		
	}
}
