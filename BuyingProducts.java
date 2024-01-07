//package project2;

public class BuyingProducts extends Request {
	private String[] itemsToBuy;
	 private String description;
		private int priority;
		private int difficulty;
		
		
	public String[] getItemsToBuy() {
        return itemsToBuy;
    }

    public void setItemsToBuy(String[] itemsToBuy) {
        this.itemsToBuy = itemsToBuy;
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
    
    public BuyingProducts (String description, int priority, int difficulty, String[]itemsToBuy) {
    	setDescription( description);
    	setPriority( priority);
    	setDifficulty( difficulty);
    	setFactor(2);
    	setStatus(Status.NEW);
    	this.itemsToBuy=itemsToBuy;
    }
    @Override
    public int calculateProcessingTime() {
        return getDifficulty() * getFactor() * itemsToBuy.length;
    }
}
