//package project2;
/**
 * 
 * inherits requests
 * all the field for this class
 */
public class InformationRequest extends Request {
	 private String[] questions;
	 private String description;
		private int priority;
		private int difficulty;

/**
 * all the getter and setters for this class
 * 
 */

public String[] getQuestions() {
    return questions;
}
public void setQuestions(String[] questions) {
    this.questions = questions;
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
 * @param questions
 */
public InformationRequest (String description, int priority, int difficulty,String[] questions) {
	setDescription( description);
	setPriority( priority);
	setDifficulty( difficulty);
	setFactor(1);
	setStatus(Status.NEW);
	this.questions=questions;	
}
/**
 * to calculate the total processing time for this request
 */
public int calculateProcessingTime() {
    return getDifficulty() * getFactor() * questions.length;
}

}


