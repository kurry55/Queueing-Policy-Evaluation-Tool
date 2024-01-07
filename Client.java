//package project2;

/**
 * all the fields for client method
 * 
 *
 */

public class Client {
	private int id;
	private String firstName;
	private String lastName;
	private int yearOfBirth;
	private Gender gender;
	private Request[] requests;
	private int arrivalTime;
	private int waitingTime;
	private int timeInQueue;
	private int serviceTime;
	private int departureTime;
	private int patience;
	private static int IncrementId=1;

/**
 * all the getters and setters for client class
 * 
 */
	
public int getId() {
	return id;
}
public int setId(int id) {
	return this.id = id;
}

public String getFirstName() {
	return firstName;
}
public String setFirstName(String firstName) {
	return this.firstName=firstName;
}

public String getLastName() {
	return lastName;
}
public String setLastName(String lastName) {
	return this.lastName=lastName;
}

public int getYearOfBirth() {
    return yearOfBirth;
}
public void setYearOfBirth(int yearOfBirth) {
    this.yearOfBirth = yearOfBirth;
}

public Gender getGender() {
    return gender;
}
public void setGender(Gender gender) {
    this.gender = gender;
}

public Request[] getRequests() {
    return requests;
}
public void setRequests(Request[] requests) {
    this.requests = requests;
}

public int getArrivalTime() {
	
	return arrivalTime;
}
public void setArrivalTime(int arrivalTime) {
	if (arrivalTime>0) {
	this.arrivalTime=arrivalTime;
}}

public int getWaitingTime(){
	
    return waitingTime;
}
public void setWaitingTime(int waitingTime) {
	 if(waitingTime > 0) {
	        this.waitingTime = waitingTime;
	 }
}

public int getTimeInQueue() {
	
    return timeInQueue;
}
public void setTimeInQueue(int timeInQueue) {
	if (timeInQueue>0) {
    this.timeInQueue = timeInQueue;
}}

public int getServiceTime() {
    return serviceTime;
}
public void setServiceTime(int serviceTime) {
    this.serviceTime = serviceTime;
}

public int getDepartureTime() {
	
    return departureTime;
}
public void setDepartureTime(int departureTime) {
	int total = arrivalTime + waitingTime + timeInQueue;
	if (departureTime>0||departureTime>=total) {
		
	
    this.departureTime = departureTime;
}}

public int getPatience() {
    return patience;
}
public void setPatience(int patience) {
    this.patience = patience;
}

/**
 * 
 * @param firstName
 * @param lastName
 * @param yearOfBirth
 * @param gender
 * @param arrivalTime
 * @param patience
 * @param requests
 */
public Client (String firstName, String lastName, int yearOfBirth, String gender,int arrivalTime, int patience, Request[] requests) {
	setWaitingTime(0);
	setTimeInQueue(0);
	setServiceTime(0);
	setDepartureTime(0);
	setFirstName( firstName);
	setLastName(lastName);
	setYearOfBirth( yearOfBirth);
	setArrivalTime( arrivalTime); 
	setPatience(patience);
	this.id=IncrementId++;		
	this.requests = requests;
}

/**
 * 
 * @param firstName
 * @param lastName
 * @param yearOfBirth
 * @param gender
 * @param patience
 * @param requests
 * overlaoding the constructer for being more flexable in inputs
 */

public Client (String firstName, String lastName, int yearOfBirth, String gender,int patience, Request[] requests) {
	setWaitingTime(0);
	setTimeInQueue(0);
	setServiceTime(0);
	setDepartureTime(0);
	setFirstName( firstName);
	setLastName(lastName);
	setYearOfBirth( yearOfBirth);
	setArrivalTime( arrivalTime);  
	this.requests = requests;
	this.id=IncrementId++;
}
/**
 * 
 * @param firstName
 * @param lastName
 * @param yearOfBirth
 * @param gender
 * @param patience
 */
public Client (String firstName, String lastName, int yearOfBirth, String gender,int patience) {
	setWaitingTime(0);
	setTimeInQueue(0);
	setServiceTime(0);
	setDepartureTime(0);
	setFirstName( firstName);
	setLastName(lastName);
	setYearOfBirth( yearOfBirth);
	setArrivalTime( arrivalTime);  

	this.id=IncrementId++;
}
/**
 * 
 * @return the service level which is decreased everytime the time in queue increase and same for 
 * timeinqueue, they are then combined to give an abs value
 */
public int estimatedServiceLevel() {
	if (departureTime==0) {
		return -1;
	}
	int serviceLevel = 0;
	 if (waitingTime > 4) {
		 serviceLevel-=1;
	    }
	    if (waitingTime > 6) {
	    	serviceLevel-=1;
	    }
	    if (waitingTime > 8) {
	    	serviceLevel-=1;
	    }
	    if (waitingTime > 10) {
	    	serviceLevel-=1;
	    }
	    if (waitingTime > 12) {
	    	serviceLevel-=1;
	    }
	    
	    if (timeInQueue > 4) {
	    	serviceLevel-=1;
	    }
	    if (timeInQueue > 6) {
	    	serviceLevel-=1;
	    }
	    if (timeInQueue > 8) {
	    	serviceLevel-=1;
	    }
	    if (timeInQueue > 10) {
	    	serviceLevel-=1;
	    }
	    if (timeInQueue > 12) {
	    	serviceLevel-=1;
	    }

	    return Math.abs(serviceLevel);
	}

/**
 * tostring for user representation
 */
public String toString() {
	return "Client:"+ lastName+"," + firstName+"\n"+
		   "**"+"arrival time"+ ":"+ arrivalTime+"\n"+ 
	       "**"+"waiting time"+ ":"+ waitingTime+"\n"+
	       "**"+"Time in queue"+ ":"+ timeInQueue+"\n"+
	       "**"+"Service Time"+ ":"+ serviceTime+"\n"+
	       "**"+"Departure Time"+ ":"+ departureTime+"\n"+
    	   "**"+"Service Level"+ ":"+ estimatedServiceLevel();
}



	
    public static void main(String[] args) {
//    	Client client = new Client("John", "Doe", 1990, "Male", 1, 5, null);	
//        client.setWaitingTime(7);
//        client.setTimeInQueue(10);
//        client.setServiceTime(2);
//        client.setDepartureTime(10);
//        System.out.println(client.toString());
    }
	
}