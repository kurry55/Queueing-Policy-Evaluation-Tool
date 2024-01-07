//package project2;
/**
 * 
 * fields of request
 *
 */
public class ReturningItems extends Request {
	private String[] itemsToReturn;
	 private String description;
		private int priority;
		private int difficulty;
		
/**
 * 
 * setter and getter
 */
	public String[] getItemsToReturn() {
        return itemsToReturn;
    }
	

    public void setItemsToReturn(String[] itemsToReturn) {
        this.itemsToReturn = itemsToReturn;
    }
    public String getDescription() {
    	return description;
    }
    
    public void setDescription(String description) {
    	this.description=description;
    }
    
    public int getPriority() {
    	return priority;
    }
    public void setPriority(int priority) {
    	this.priority=priority;
    }
    public int getDifficulty() {
    	return difficulty;
    }
    public void setDifficulty(int difficulty) {
    	this.difficulty=difficulty;
    }
    /**
     * 
     * @param description
     * @param priority
     * @param difficulty
     * @param itemsToReturn
     */
    public ReturningItems (String description, int priority, int difficulty, String[]itemsToReturn) {
    	setDescription( description);
    	setPriority( priority);
    	setDifficulty( difficulty);
    	setFactor(3);
    	setStatus(Status.NEW);
    	this.itemsToReturn=itemsToReturn;	
    }
    public int calculateProcessingTime() {
        return getDifficulty() * getFactor() * itemsToReturn.length;
    }
   

}
