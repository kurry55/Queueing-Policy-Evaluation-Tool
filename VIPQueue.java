//package project2;

public class VIPQueue extends Queue {
	public VIPQueue (String serverName, int queueSize) {
		super(serverName,queueSize);
    	generateQueue();
	}

}
