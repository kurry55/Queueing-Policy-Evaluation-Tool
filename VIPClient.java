//package project2;
/**
 * 
 * fields
 *
 */
public class VIPClient extends Client implements Prioritizable  {
	private int memberSince;
	private int priority;
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
	/**
	 * setter and getters
	 */
	@Override
    public void setPriority(int a) {
        this.priority = a;
    }

    @Override
    public int getPriority() {
        return this.priority;
    }
	
	public int getMemberSince() {
		return memberSince;
	}
	public void setMemberSince(int memberSince) {
		this.memberSince=memberSince;
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
	//inherited class fields
public VIPClient (String firstName, String lastName, int birthYear, String gender,int arrivalTime, int patience, Request[] requests, int memberSince, int priority) {
	super(firstName,lastName,birthYear,gender,arrivalTime,patience,requests);
//	setWaitingTime(0);
//	setTimeInQueue(0);
//	setServiceTime(0);
//	setDepartureTime(0);
//	setFirstName( firstName);
//	setLastName(lastName);
//	setYearOfBirth( yearOfBirth);
//	setArrivalTime( arrivalTime);  
//	setPatience( patience);  
	setMemberSince( memberSince);
	setPriority( priority);
	
	}

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





public String toString() {
	return "Client:"+ getLastName()+"," + getFirstName()+"\n"+
			   "**"+"arrival time"+ ":"+ getArrivalTime()+"\n"+ 
		       "**"+"waiting time"+ ":"+ getWaitingTime()+"\n"+
		       "**"+"Time in queue"+ ":"+ getTimeInQueue()+"\n"+
		       "**"+"Service Time"+ ":"+ getServiceTime()+"\n"+
		       "**"+"Departure Time"+ ":"+ getDepartureTime()+"\n"+
	    	   "**"+"Service Level"+ ":"+ estimatedServiceLevel()+"\n"+
	    	   "**"+"VIP since"+ ":"+ memberSince+"\n"+
	    	   "**"+"priority"+ ":"+ getPriority();
}
public static void main(String[] args) {
   
}


}
