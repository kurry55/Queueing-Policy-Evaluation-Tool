//package project2;
/**
 * 
 * all the fields of queues
 *
 */
public class Queue {
	private String serverName;
	private int queueSize;
	private int processingStartTime;
	private Client[] clientsHistory;
	private Client[] clientsInQueue;
	private Client clientBeingServed;
	private Request requestInProgress;	
    private int queueNumber; 
    private static int queueCounter = 0;
/**
 * 
 * getters and setters
 */
	public String getServerName() {
        return serverName;
    }
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public int getQueueSize() {
        return queueSize;
    }
    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public int getProcessingStartTime() {
        return processingStartTime;
    }
    public void setProcessingStartTime(int processingStartTime) {
        this.processingStartTime = processingStartTime;
    }

    public Client[] getClientsHistory() {
        return clientsHistory;
    }
    public void setClientsHistory(Client[] clientsHistory) {
        this.clientsHistory = clientsHistory;
    }

    public Client[] getClientsInQueue() {
        return clientsInQueue;
    }
    public void setClientsInQueue(Client[] clientsInQueue) {
        this.clientsInQueue = clientsInQueue;
    }

    public Client getClientBeingServed() {
        return clientBeingServed;
    }
    public  void setClientBeingServed(Client clientBeingServed) {
    	this.clientBeingServed = clientBeingServed;
    }

    public Request getRequestInProgress() {
        return requestInProgress;
    }
    public void setRequestInProgress(Request requestInProgress) {
        this.requestInProgress = requestInProgress;
    }
    /**
     * to get the total number of queue. create them and them in the queues
     */
    public void generateQueue() {
        Queue[] existingQueues = QueueSystem.getQueues();
        
        if (existingQueues == null) {
            QueueSystem.setQueues(new Queue[] { this });
        } else {
            Queue[] updatedQueues = new Queue[existingQueues.length + 1];
            for (int i = 0; i < existingQueues.length; i++) {
                updatedQueues[i] = existingQueues[i];
            }
            updatedQueues[existingQueues.length] = this;
            QueueSystem.setQueues(updatedQueues);
        }
    }

    /**
     * 
     * @param serverName
     * @param queueSize
     * initializing the values
     */
    public Queue (String serverName, int queueSize) {
    	setQueueSize( queueSize);
    	setServerName( serverName);
    	this.clientsInQueue = new Client[getQueueSize()];
    	this.clientsHistory = new Client[0];
    	generateQueue();
    	this.queueNumber = ++queueCounter;
    }
    

   
    
    /**
     * to string representation
     */
    @Override
    public String toString() {
    	//used string builder for easiler creation of tostring
        StringBuilder result = new StringBuilder();
        //it gets the number of queues that are the to represent in the tostring
        int queueNumber = findQueueNumber();
        //for indexing purpose
        if (queueNumber != -1) {
        	//if it is true then the queue is created with a number
            result.append(String.format("[Queue:%d]", queueNumber));
        } else {
            result.append("[Queue: ]");
        }
        //only works when clients being served are not null
        if (getClientBeingServed() != null) {
        	//add all the clients being served and format them. 02d for 0 in front 
            result.append(String.format("[%02d]-----", getClientBeingServed().getId()));
        } else {
        	//if not there then empty is printed
            result.append("[ ]-----");
        }

        Client[] clientsInQueue = getClientsInQueue();
     // For the clients who are in queue
     if (clientsInQueue != null) {
    	 //looping through the clients
         for (int i = 0; i < clientsInQueue.length; i++) {
        	 //if found
             if (clientsInQueue[i] != null) {
            	 //add them by proper formating
                 result.append(String.format("[%02d]", clientsInQueue[i].getId()));
             } else {
            	 // else no returned
                 result.append("[ ]");
             }
         }
     }
     return result.toString();
    }

    private int findQueueNumber() {
    	//for the queues in queuestyen get queues part
        Queue[] queues = QueueSystem.getQueues();
        //if there are queues
        if (queues != null) {
        	//loop through queues
            for (int i = 0; i < queues.length; i++) {
            	//get the value of number of queues
                if (queues[i] == this) {
                	//return the number
                    return i + 1; 
                }
            }
        }
        return -1; 
    }

    public String toString(boolean showID) {
    	//using for easier string building
        StringBuilder stringBuilder = new StringBuilder();
        //formating the queues and its number
        stringBuilder.append("[Queue:").append(this.queueNumber).append("]");
        //if its true
        if (showID==true) {
        	//if there are clients being served
            if (this.clientBeingServed != null) {
            	//append the clients who are being served with their ids
                stringBuilder.append("[").append(String.format("%02d", this.clientBeingServed.getId())).append("]-----");
            } else {
            	//or empty
                stringBuilder.append("[]-----");
            }
            //looping through queues
            for (int i = 0; i < this.queueSize; i++) {
            	//if they are not null
                if (i < this.clientsInQueue.length && this.clientsInQueue[i] != null) {
                	//formating the clients in queue as per given
                    stringBuilder.append("[").append(String.format("%02d", this.clientsInQueue[i].getId())).append("]");
                } else {
                	//if non found retrun empty
                    stringBuilder.append("[]");
                }
            }
        } else {
        	//if false
            if (this.requestInProgress != null) {
            	//get all the request number situation
                stringBuilder.append("[").append(String.format("%02d", this.requestInProgress.calculateProcessingTime())).append("]-----");
            } else {
                stringBuilder.append("[]-----");
            }
            //looping through queuesize for find the clients in queue
            for (int i = 0; i < this.queueSize; i++) {
            	//if they are present
                if (i < this.clientsInQueue.length && this.clientsInQueue[i] != null) {
                	//for finding total workload
                    int totalProcessingTime = 0;
                    //for all their requests
                    Request[] requests = this.clientsInQueue[i].getRequests();
                    if (requests != null) {
                    	//looping through requests 
                        for (int j = 0; j < requests.length; j++) {
                            if (requests[j] != null) {
                            	//adding all the requests together
                                totalProcessingTime += requests[j].calculateProcessingTime();
                            }
                        }
                    }
                    //format according to the total time
                    stringBuilder.append("[").append(String.format("%02d", totalProcessingTime)).append("]");
                } else {
                	//if non found return empty 
                    stringBuilder.append("[]");
                }
            }
        }

        return stringBuilder.toString();
    }

  
    
    public static void main(String[] args) {

       
    
    }

    

    
}	
    	
    






