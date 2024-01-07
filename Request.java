//package project2;
/**
 * 
 * fields
 *
 */
public abstract class Request implements Prioritizable {
		private String description;
		private int priority;
		private int difficulty;
		private int factor;
		private int startTime;
		private int endTime;
		private Status status;
	    private int processingTime;

		


	    public int getProcessingTime() {
	        return processingTime;
	    }
/**
 * 
 * getters and setters
 */
public String getDescription() {
    return description;
}
public void setDescription(String description) {
    this.description = description;
}

public int getPriority() {
    return priority;
}
public void setPriority(int priority) {
    this.priority = priority;
}

public int getDifficulty() {
    return difficulty;
}
public void setDifficulty(int difficulty) {
    this.difficulty = difficulty;
}

public int getFactor() {
    return factor;
}
public void setFactor(int factor) {
    this.factor = factor;
}

public int getStartTime() {
    return startTime;
}
public void setStartTime(int startTime) {
    this.startTime = startTime;
}

public int getEndTime() {
    return endTime;
}
public void setEndTime(int endTime) {
    this.endTime = endTime;
}

public Status getStatus() {
    return status;
}
public void setStatus(Status status) {
    this.status = status;

}
/**
 * 
 * @param amount
 * for decreasing time after calling increasetime
 */
public void decreaseProcessingTime(int amount) {
    this.processingTime -= amount;
    if (this.processingTime < 0) {
        this.processingTime = 0;
    }
}


public abstract int calculateProcessingTime();


public static void main(String[] args) {
	

}}