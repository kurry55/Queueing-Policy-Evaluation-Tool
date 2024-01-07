//package project2;
/**
 * 
 * all the fields
 *
 */
public class QueueSystem {
	private static int clock;
	private static int totalWaitingTime;
	private static Client[] clientsWorld;
	private static int totalClientsInSystem;
	private static int waitingLineSize;
	private static Client[] waitingLine;
	private static boolean tvInWaitingArea;
	private static boolean coffeeInWaitingArea;
	private static Queue[] queues;
	
	
/**
 * 
 * @param waitingLineSize
 * @param tvInWaitingArea
 * @param coffeeInWaitingArea
 */
	
	
public QueueSystem(int waitingLineSize, boolean tvInWaitingArea, boolean coffeeInWaitingArea) {
	    setClock(0);
	    setWaitingLineSize(waitingLineSize);
	    setTvInWaitingArea(tvInWaitingArea);
	    setCoffeeInWaitingArea(coffeeInWaitingArea);
	    waitingLine = new Client[waitingLineSize];
	}



	
/**
 * 
 * getters and setters
 */
public static int getClock() {
    return clock;
}
public static void setClock(int clock) {
    QueueSystem.clock = clock;
}

public static int getTotalWaitingTime() {
    return totalWaitingTime;
}
public static void setTotalWaitingTime(int totalWaitingTime) {
    QueueSystem.totalWaitingTime = totalWaitingTime;
}

public static Client[] getClientsWorld() {
    return clientsWorld;
}
public static void setClientsWorld(Client[] clientsWorld) {
    QueueSystem.clientsWorld = clientsWorld;
}

public static int getTotalClientsInSystem() {
    return totalClientsInSystem;
}
public static void setTotalClientsInSystem(int totalClientsInSystem) {
    QueueSystem.totalClientsInSystem = totalClientsInSystem;
}

public static int getWaitingLineSize() {
    return waitingLineSize;
}
public static void setWaitingLineSize(int waitingLineSize) {
    QueueSystem.waitingLineSize = waitingLineSize;
}

public static Client[] getWaitingLine() {
    return waitingLine;
}
public static void setWaitingLine(Client[] waitingLine) {
    QueueSystem.waitingLine = waitingLine;
}

public static boolean isTvInWaitingArea() {
    return tvInWaitingArea;
}
public static void setTvInWaitingArea(boolean tvInWaitingArea) {
	QueueSystem.tvInWaitingArea = tvInWaitingArea;
}

public static boolean isCoffeeInWaitingArea() {
    return coffeeInWaitingArea;
}
public static void setCoffeeInWaitingArea(boolean coffeeInWaitingArea) {
    QueueSystem.coffeeInWaitingArea = coffeeInWaitingArea;
}

public static Queue[] getQueues() {
    return queues;
}
public static void setQueues(Queue[] queues) {
	QueueSystem.queues = queues;
}

/**
 * 
 * @param client
 * to remove the client from waiting line if they found a queue to join
 */
private void removeClientFromWaitingLine(Client client) {
	//for indexing [0]
    int index = -1;
    //to loop in waitingline
    for (int i = 0; i < waitingLineSize; i++) {
    	//if it matches the client we want to remove
        if (waitingLine[i].equals(client)) {
            index = i;
            break;
        }
    }
    //, adding place to waiting line
    if (index != -1) {
        for (int i = index; i < waitingLineSize - 1; i++) {
            waitingLine[i] = waitingLine[i + 1];
        }
        waitingLineSize--;
    } }




/**
 * 
 * @return shortestQueue
 * 
 */
private Queue returnShortestQueue() {
    Queue[] queues = QueueSystem.getQueues();
    //checking for nullpoint exceptions
    if (queues == null || queues.length == 0) return null;
    //initial size
    Queue shortestQueue = null;
    //assuming the shortest queue
    int shortestSize = Integer.MAX_VALUE;
    //for all the queues
    for (int i = 0; i < queues.length; i++) {
        int queueSize = queues[i].getQueueSize();
        //assigning the found size from temp variable
        if (queueSize < shortestSize) {
            shortestSize = queueSize;
            shortestQueue = queues[i];
        }
    }
    return shortestQueue;
}
/**
 * 
 * @return shortestVIP queues
 */
//same logic for queues
private VIPQueue returnShortestVIPQueue() {
    Queue[] queues = QueueSystem.getQueues();
    if (queues == null || queues.length == 0) return null;

    VIPQueue shortestVIPQueue = null;
    int shortestSize = Integer.MAX_VALUE;
    for (int i = 0; i < queues.length; i++) {
        if (queues[i] instanceof VIPQueue) {
            VIPQueue vipQueue = (VIPQueue) queues[i];
            int queueSize = vipQueue.getQueueSize();
            if (queueSize < shortestSize) {
                shortestSize = queueSize;
                shortestVIPQueue = vipQueue;
            }
        }
    }
    return shortestVIPQueue;
}

/**
 * 
 * @param fqueue
 * @param fclient
 */

private void sendClientToHistory(Queue fqueue, Client fclient) {	
	//array starts for [0]. Indexing
	int findArray=-1;
	for (int i=0;i<fqueue.getClientsHistory().length;i++) {
		//for adding them into client history
		if(fqueue.getClientsHistory()[i]==null) {
			//adding to target array
			findArray=i;
			break;
		}
	}
	//setting up the client in that array
	fqueue.getClientsHistory()[findArray] = fclient;
}
/**
 * 
 * @param vipQueue
 * @param vipClient
 */
//smiliar logic for vip instance
private void sendVIPClientToHistory(VIPQueue vipQueue, VIPClient vipClient) {
    int findArray = -1;
    for (int i = 0; i < vipQueue.getClientsHistory().length; i++) {
        if (vipQueue.getClientsHistory()[i] == null) {
            findArray = i;
            break;
        }
    }
    if (findArray != -1) {
        vipQueue.getClientsHistory()[findArray] = vipClient;
    } 
}

/**
 * 
 * @param client
 * @return
 */
//to calculate the total workload by looping through all the requests
private int workLoad(Client client) {
    int totalTime = 0;
    Request[] requests = client.getRequests();
    if (requests != null) {
        for (int i = 0; i < requests.length; i++) {
            totalTime += requests[i].calculateProcessingTime();
        }
    }
    return totalTime;
}


/**
 * 
 * @return the most important client
 */
private Client ReturnPriorityClient() {
	//setting empty values to compare
    Client earlyClient = null;
    VIPClient earlyVIPClient = null;
    int maxVIPPriority = -1; 
    //for all the clients in waiting line
    for (int j = 0; j < QueueSystem.getWaitingLineSize(); j++) {
    	//if vipqueue found
        if (QueueSystem.getWaitingLine()[j] instanceof VIPClient) {
        	VIPClient currentVIPClient = (VIPClient) QueueSystem.getWaitingLine()[j];
        	//checking for higher priority or if the priority matches then we check how long they have been a member for and decide
            if (currentVIPClient.getPriority() > maxVIPPriority || 
                (currentVIPClient.getPriority() == maxVIPPriority && currentVIPClient.getMemberSince() < earlyVIPClient.getMemberSince())) {
                earlyVIPClient = currentVIPClient;
                maxVIPPriority = currentVIPClient.getPriority();
            }
        } else {
        	//assiging the early client to empty for comparision
            if (earlyClient == null) {
                earlyClient = QueueSystem.getWaitingLine()[j];
            } else {
            	//we check the total work for all the clients
                int currentClientWorkLoad = workLoad(QueueSystem.getWaitingLine()[j]);
                int earlyClientWorkLoad = workLoad(earlyClient);
                //checking for year of birtth, if same, workload, if same then the smallest id.
                if (QueueSystem.getWaitingLine()[j].getYearOfBirth() < earlyClient.getYearOfBirth() || 
                    (currentClientWorkLoad < earlyClientWorkLoad) || 
                    (currentClientWorkLoad == earlyClientWorkLoad && QueueSystem.getWaitingLine()[j].getId() < earlyClient.getId())) {
                    earlyClient = QueueSystem.getWaitingLine()[j];
                }
            }
        }
    }
    //returns who ever is passed
    if (earlyVIPClient != null) {
        return earlyVIPClient;
    } else {
        return earlyClient;
    }
}
/**
 * 
 * @param queue
 * @param client
 */
private void addToQueue(Queue queue, Client client) {
    Client[] clientsInQueue = queue.getClientsInQueue();
    if (queue.getQueueSize() < clientsInQueue.length) {
    	//assigning client to queue
        clientsInQueue[queue.getQueueSize()] = client;
        queue.setQueueSize(queue.getQueueSize() + 1);
     // Update the clients in the queue
        queue.setClientsInQueue(clientsInQueue); 
        //removing from waitingline
        removeClientFromWaitingLine(client);
    } 
}



public void increaseTime() {
	//increasing clock time everytime method is called
	clock++;
	//checking for exception
	if (waitingLine != null) {
		//looping through the clients world
	    for (int i = 0; i < QueueSystem.getClientsWorld().length; i++) {
	    	//if the arrivaltime matches the clock
	        if (QueueSystem.getClientsWorld()[i].getArrivalTime() == clock) {
	        	//checking the waiting line size
	            if (waitingLineSize < waitingLine.length) {
	            	//adding patience
	            	if (isTvInWaitingArea()==true) {
	            		QueueSystem.getClientsWorld()[i].setWaitingTime(QueueSystem.getClientsWorld()[i].getWaitingTime()+20);
	            	}
	            	//checking for clients who are adults
	            	if (isCoffeeInWaitingArea()==true) {
	            		if(QueueSystem.getClientsWorld()[i].getYearOfBirth()<2005) {
		            	QueueSystem.getClientsWorld()[i].setWaitingTime(QueueSystem.getClientsWorld()[i].getWaitingTime()+15);
	            		}
	            	}
	            	//addig them to waitingline and increaseing size
	                waitingLine[waitingLineSize] = QueueSystem.getClientsWorld()[i];
	                waitingLineSize++;
	            }
	        }
	    }
	}
	

	//only if the waitingline is more than 0
	while (waitingLineSize > 0) {
		//calling the method to find the most prefered client
	    Client earlyClient = ReturnPriorityClient();
	    
	    if (earlyClient != null) {
	        Queue findQueue = null;
	        //for vip cleints
	        if (earlyClient instanceof VIPClient) {
	        	//to return the shortes vipqueues
	            VIPQueue shortestVIPQueue = returnShortestVIPQueue();
	            if (shortestVIPQueue != null && shortestVIPQueue.getQueueSize() < shortestVIPQueue.getClientsInQueue().length) {
	                findQueue = shortestVIPQueue;
	            } else {
	            	//removing them if there is no vip client queue
	                removeClientFromWaitingLine(earlyClient);
	                continue; 
	            }
	        }
	        //find the shortest and the most nearest queue
	        if (findQueue == null) {
	            findQueue = returnShortestQueue();
	        }
	        //adding them to the queues found
	        if (findQueue != null && findQueue.getQueueSize() < findQueue.getClientsInQueue().length) {
	            addToQueue(findQueue, earlyClient);
	        } else {
	        	//if no queues are there, we are decreaing patience and removing if less than 0
	            earlyClient.setPatience(earlyClient.getPatience() - 1);
	            if (earlyClient.getPatience() < 0) {
	                removeClientFromWaitingLine(earlyClient);
	            }
	        }
	    } else {
	        break;
	    }
	}


/**
 * for the queues when clients are added
 */
//looping through all the queues
for (int k = 0; k < QueueSystem.getQueues().length; k++) {
    Queue queue = QueueSystem.getQueues()[k];
    //for vips
    if (queue instanceof VIPQueue) {
        VIPQueue vipQueue = (VIPQueue) queue;
        //checking for clients who are being served
        if (vipQueue.getClientBeingServed()==null&&vipQueue.getQueueSize()>0) {
        	//setting the clients up who are first in the array, if no requets we set their departure time send them history
        	if (vipQueue.getClientsInQueue()[0].getRequests()==null||vipQueue.getClientsInQueue()[0].getRequests().length==0) {
        		vipQueue.getClientsInQueue()[0].setDepartureTime(clock);
        		vipQueue.getClientsInQueue()[0].getRequests()[0].setStartTime(0);
        		VIPClient vipClient = (VIPClient) vipQueue.getClientsInQueue()[0];
        		sendVIPClientToHistory(vipQueue, vipClient);
        		//decreasing queue size
        		 for (int i = 1; i < vipQueue.getQueueSize(); i++) {
                     vipQueue.getClientsInQueue()[i - 1] = vipQueue.getClientsInQueue()[i];
                 }
        		 //resetting queues size
        		 vipQueue.getClientsInQueue()[vipQueue.getQueueSize() - 1] = null;
                 vipQueue.setQueueSize(vipQueue.getQueueSize() - 1);
                 continue;
        	}
        	//if there are requests
        	vipQueue.setClientBeingServed(vipQueue.getClientsInQueue()[0]);
        	if (vipQueue.getRequestInProgress()!=null) {
        	//change status
        	vipQueue.getRequestInProgress().setStatus(Status.IN_PROGRESS);
        	}
        	for (int i = 1; i < vipQueue.getQueueSize(); i++) {
                vipQueue.getClientsInQueue()[i - 1] = vipQueue.getClientsInQueue()[i];
            }
        	 vipQueue.getClientsInQueue()[vipQueue.getQueueSize() - 1] = null;
             vipQueue.setQueueSize(vipQueue.getQueueSize() - 1);
        }
    }
    /**
     * same logic for regular clients
     */

    if (queue.getClientBeingServed() == null && queue.getQueueSize() > 0) {
        Client clientToServe = queue.getClientsInQueue()[0];
        if (clientToServe != null) {
            if (clientToServe.getRequests() == null || clientToServe.getRequests().length == 0) {
                clientToServe.setDepartureTime(clock);
                sendClientToHistory(queue, clientToServe);
                for (int i = 1; i < queue.getQueueSize(); i++) {
                    queue.getClientsInQueue()[i - 1] = queue.getClientsInQueue()[i];
                }
                queue.getClientsInQueue()[queue.getQueueSize() - 1] = null;
                queue.setQueueSize(queue.getQueueSize() - 1);
                continue;
            }
            queue.setClientBeingServed(clientToServe);
            if (queue.getRequestInProgress() != null) {
                queue.getRequestInProgress().setStatus(Status.IN_PROGRESS);
            }
            for (int i = 1; i < queue.getQueueSize(); i++) {
                queue.getClientsInQueue()[i - 1] = queue.getClientsInQueue()[i];
            }
            queue.getClientsInQueue()[queue.getQueueSize() - 1] = null;
            queue.setQueueSize(queue.getQueueSize() - 1);
    }
}


//for all the queues where clients are present
for (int y = 0; y < queues.length; y++) {
    Queue queue1 = queues[y];
    //for vip queue
    if (queue1 instanceof VIPQueue) {
        VIPQueue vipQueue = (VIPQueue) queue1;
        //for all the clients whose requests are in progress
        if (vipQueue.getRequestInProgress() != null && vipQueue.getRequestInProgress().getStatus().equals(Status.IN_PROGRESS)) {
        	//we start pocressing, if it reaches 0, we remove them
            if (vipQueue.getClientBeingServed().getRequests()[0].getStartTime() + vipQueue.getClientBeingServed().getRequests()[0].calculateProcessingTime() == clock) {
            	//setting departure time
                if (vipQueue.getClientBeingServed() != null) {
                    vipQueue.getClientBeingServed().setDepartureTime(clock);
                }
                //setting end time for service level
                vipQueue.getRequestInProgress().setEndTime(clock);
                //chaning the status
                vipQueue.getRequestInProgress().setStatus(Status.PROCESSED);
                VIPClient vipClient = (VIPClient) vipQueue.getClientBeingServed();
                sendVIPClientToHistory(vipQueue, vipClient);
                //adding them to history
                vipQueue.setRequestInProgress(null);
                vipQueue.setClientBeingServed(null);
            }
        }
        //if they are not being served
        if (vipQueue.getClientsInQueue() != null) {
            for (int x = 0; x < vipQueue.getClientsInQueue().length; x++) {
                VIPClient vipClient = (VIPClient) vipQueue.getClientsInQueue()[x];
                if (vipClient != null) {
                	//decrease patience
                    vipClient.setPatience(vipClient.getPatience() - 1);
                    //if less than 0
                    //remove them and reseize the queue
                    if (vipClient.getPatience() < 0) {
                        sendVIPClientToHistory(vipQueue, vipClient);
                        vipClient.setDepartureTime(clock);
                        for (int i = x + 1; i < vipQueue.getQueueSize(); i++) {
                            vipQueue.getClientsInQueue()[i - 1] = vipQueue.getClientsInQueue()[i];
                        }
                        vipQueue.getClientsInQueue()[vipQueue.getQueueSize() - 1] = null;
                        vipQueue.setQueueSize(vipQueue.getQueueSize() - 1);
                    }
                }
            }
        }
       
    } else {
/**
 * same logic for regular clients
 */
    if (queue1.getRequestInProgress() != null && queue1.getRequestInProgress().getStatus().equals(Status.IN_PROGRESS)) {
        if (queue1.getClientBeingServed().getRequests()[0].getStartTime() + queue1.getClientBeingServed().getRequests()[0].calculateProcessingTime() == clock) {
            if (queue1.getClientBeingServed() != null) {
                queue1.getClientBeingServed().setDepartureTime(clock);
            } else {
            	Request[] requests = queue1.getClientBeingServed().getRequests();
            	requests[0].decreaseProcessingTime(1);
            }
            queue1.getRequestInProgress().setEndTime(clock);
            queue1.getRequestInProgress().setStatus(Status.PROCESSED);
            sendClientToHistory(queue1, queue1.getClientBeingServed());
            queue1.setRequestInProgress(null);
            queue1.setClientBeingServed(null);
        }
    }

    if (queue1.getClientsInQueue() != null) {
        for (int x = 0; x < queue1.getClientsInQueue().length; x++) {
            Client client = queue1.getClientsInQueue()[x];
            if (client != null) {
                client.setPatience(client.getPatience() - 1);
                if (client.getPatience() < 0) {
                    sendClientToHistory(queue1, client);
                    client.setDepartureTime(clock);
                    for (int i = x + 1; i < queue1.getQueueSize(); i++) {
                        queue1.getClientsInQueue()[i - 1] = queue1.getClientsInQueue()[i];
                    }
                    queue1.getClientsInQueue()[queue1.getQueueSize() - 1] = null;
                    queue1.setQueueSize(queue1.getQueueSize() - 1);
                }
            }
        }
    }
}

}  }  
}

//increasing time for given input of time
public void increaseTime(int time) {
	for (int i=0;i<time;i++) {
		increaseTime();
	}
}

public String toString() {
	//string builder for ease
	StringBuilder getRep = new StringBuilder();
	//formating for waitingline
	//if waiting line is there
	//we print according to the workload
	getRep.append("[WaitingLine]-");
	for (int i =0;i<waitingLine.length;i++) {
		if (waitingLine[i]!=null) {
			int estProcessingTime = workLoad(waitingLine[i]);
			getRep.append("[").append(String.format("%02d", estProcessingTime)).append("]");
		}
	}
	getRep.append("\n---\n");
	
	//for all the queues
	for (int i=0;i<queues.length;i++) {
		if (queues[i]!=null) {
			//we add all the clients who are being served with their id
            getRep.append("[Queue:").append(i + 1).append("]");
            Client clientBeingServed = queues[i].getClientBeingServed();
            if(clientBeingServed!=null) {
                int remainingProcessingTime = workLoad(clientBeingServed);
                //formating for 9 being 09
                getRep.append("[").append(String.format("%02d", remainingProcessingTime)).append("]");
            }else {
                getRep.append("[--]");
            }
            getRep.append("-----");
            Client[] clientsInQueue = queues[i].getClientsInQueue();
            for (int j =0;j<clientsInQueue.length;j++) {
            	if(clientsInQueue[j]!=null) {
            		 int estimatedProcessingTime = workLoad(clientsInQueue[j]);
                     getRep.append("[").append(String.format("%02d", estimatedProcessingTime)).append("]");
                 }
            	}
            getRep.append("\n");
            }
		}
    return getRep.toString();
	}
	


public String toString(boolean showID) {
	StringBuilder newBuilder = new StringBuilder();
	newBuilder.append("[WaitingLine]-");
	//if its ture
	for (int i=0;i<waitingLine.length;i++) {
		if (waitingLine[i]!=null) {
			if (showID==true) {
				//we append according to their ids for waiting line if null
				newBuilder.append("[").append(waitingLine[i].getId()).append("]");
			} else {
				// we add clients ids according to the waiting line and check if we need to add 0 if num>10
                if (waitingLine[i].getRequests() != null && i < waitingLine[i].getRequests().length && waitingLine[i].getRequests()[i] != null) {
                	if (waitingLine[i].getRequests()[i].calculateProcessingTime() > 10) {
                        newBuilder.append("[").append(waitingLine[i].getRequests()[i].calculateProcessingTime());
                	} else {
                        newBuilder.append("[").append(waitingLine[i].getRequests()[i].calculateProcessingTime()).append("]");
                	}
                }

			}
		}
	}
	newBuilder.append("\n---\n");
	for (int j=0;j<queues.length;j++) {
		if (queues[j] != null) {
            newBuilder.append("[Queue:").append(j + 1).append("]");
            if (queues[j].getClientBeingServed() != null) {
            	//if it is false
                if (showID==true) {
                	// we append according to the remaing time for request in waiting line
                    newBuilder.append("[").append(String.format("%02d", queues[j].getClientBeingServed().getId())).append("]");
                } else {
                    newBuilder.append("[").append(String.format("%02d", queues[j].getClientBeingServed().getRequests()[j].calculateProcessingTime())).append("]");
                }
            } else {
                newBuilder.append("[--]");
            }
        	// we append according to the remaing time for request in queue
            newBuilder.append("-----");
            for (int k = 0; k < queues[j].getClientsInQueue().length; k++) {
                if (queues[j].getClientsInQueue() != null && queues[j].getClientsInQueue()[k] != null) {
                    if (showID) {
                        newBuilder.append(String.format("%02d", queues[j].getClientsInQueue()[k].getId()));
                    } else {
                        if (queues[j].getClientsInQueue()[k].getRequests() != null && k < queues[j].getClientsInQueue()[k].getRequests().length && queues[j].getClientsInQueue()[k].getRequests()[k] != null) {
                            newBuilder.append(String.format("%02d", queues[j].getClientsInQueue()[k].getRequests()[k].calculateProcessingTime()));
                        }
                    }
                }
            }
            newBuilder.append("\n");
	}
}
	return newBuilder.toString();
}

/**
 * 
 * @return clients found
 */
public Client[] getClientsBeingServed() {
	Client[] clientsBeingServed = new Client[queues.length];
    int count = 0;
    //for all the queues
    for (int i =0;i<queues.length;i++) {
    	//if the clients are set to being served
    	if (queues[i].getClientBeingServed()!=null) {
    		//return the found clients
    		clientsBeingServed[count] = queues[i].getClientBeingServed();
    		count++;
    	}
    }
    //adding them to queue
    Client[] foundClients = new Client[count];
    for (int j=0;j<count;j++) {
    	foundClients[j]=clientsBeingServed[j];
    }
    return foundClients;
}
public static void main(String[] args) {
	
}


}
