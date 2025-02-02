public class Task {
    String id;
    int duration;
    int deadline;
    int resources;
    int startTime;
    int endTime;

    public Task(String id, int duration, int deadline,int resources) 
    {
        this.id = id;
        this.duration = duration;
        this.deadline=deadline;
        this.resources = resources;
        this.startTime = -1;
        this.endTime = -1;
    }

    // Getters and Setters
    public String getId() { return id; }
    public int getDuration() { return duration; }
    public int getDeadline() { return deadline; }
    public int getResources() { return resources; }
    public int getStartTime() { return startTime; }
    public int getEndTime() { return endTime; }
    
    public void setStartTime(int startTime) 
    {
    	this.startTime = startTime; 
    }
    public void setEndTime(int endTime) 
    {
    	this.endTime = endTime; 
    }
}
