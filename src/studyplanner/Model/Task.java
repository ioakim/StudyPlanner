package studyplanner.Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Class to model a Task of the Study Planner.
 * @author Ioakim
 */
public class Task extends Objective implements Serializable{
    private static final long serialVersionUID = 8L;
    private String type;                            //Type of the task.
    private ArrayList<Criterion> criteria;          //Criteria of the task.
    private ArrayList<Activity> activityHistory;    //Activities of the task.
    private ArrayList<Task> dependentOn;            //Dependencies of the task.
    
    /**
    * Default constructor for an instance of Task.
    */
    public Task(){
        this.type = "";
        this.criteria = new ArrayList<>();
        this.activityHistory = new ArrayList<>();
        this.dependentOn = new ArrayList<>();
    }
    
    /**
     * Overloaded constructor with additional parameters.
     * @param type Type of the task.
     * @param criteria List of task criteria.
     * @param activityHistory List of previous activities.
     * @param dependencies Tasks on which this task is dependent on.
     * @param name Name of a task.
     * @param description Description of task.
     * @param start Beginning date of a task.
     * @param end Deadline of a task.
     */
    public Task(String type, ArrayList<Criterion> criteria, 
            ArrayList<Activity> activityHistory, ArrayList <Task> dependencies,
                            String name, String description,Date start, Date end){
        
        super(name, description, start, end);
        this.type = type;
        this.criteria = criteria;
        this.activityHistory = activityHistory;
        this.dependentOn = dependencies;
        
    }

    /*******************
     * GET/SET METHODS *
     *******************/
    
    /**
     * @return The type of this task.
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type for the task.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return List of criteria of this task.
     */
    public ArrayList<Criterion> getCriteria() {
        return criteria;
    }

    /**
     * @param criteria List of criteria for the task to be complete.
     */
    public void setCriteria(ArrayList<Criterion> criteria) {
        this.criteria = criteria;
    }

    /**
     * @return List of previous activities.
     */
    public ArrayList<Activity> getActivityHistory() {
        return activityHistory;
    }

    /**
     * @param activityHistory A list of activities for the task.
     */
    public void setActivityHistory(ArrayList<Activity> activityHistory) {
        this.activityHistory = activityHistory;
    }

    /**
     * @return List of tasks on which this task is dependent.
     */
    public ArrayList<Task> getDependencies() {
        return dependentOn;
    }
    
    public void setDependencies(ArrayList<Task> dependencies) {
        this.dependentOn = dependencies;
    }
    
    @Override
    public String toString(){
        return this.getName();
    }
}
