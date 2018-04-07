package studyplanner.Model;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Class to model an Objective (e.g. a task) of the Study Planner.
 * @author Michail
 */
public class Objective implements Serializable{   
    private static final long serialVersionUID = 7L;
    private String name;                //Name of this Objective.
    private String description;         //Description of this Objective.
    private Date start;                 //Beginning date of this Objective.
    private Date end;                   //Deadline of this Objective.
    private boolean isDone;             //Flag to check if this Objective is met.
    
    /**
     * Default constructor for an instance of Objective.
     */
    public Objective(){
        this.name = "";
        this.description = "";
        this.start = new Date(); //sets the start date at time of object 
                                 //creation
        this.end = null;
        this.isDone = false;    //Objective is not met by default
    }
    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    /**
     * Overloaded constructor with additional parameters.
     * @param name
     * @param description
     * @param start
     * @param end 
     */ 
    public Objective(String name, String description, Date start, Date end){
        this.name = name;
        this.description = description;
        this.start = start;
        this.end =  end;
        this.isDone = false;
    }
    
    /*******************
     * GET/SET METHODS *
     *******************/
    
    /**
     * @return The name of this objective.
     */
    public String getName() {
        return name;
    }
    
    /**
     * @param name Name for the objective.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return The description of this objective.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description Description of the objective.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return Beginning date of this objective.
     */
    public Date getStart() {
        return start;
    }
    
    /**
     * @param start Beginning date for the objective. 
     */
    public void setStart(Date start) {
        this.start = start;
    }
    /**
     * @return The deadline of this objective.
     */
    public Date getEnd() {
        return end;
    }

    /**
     * @param end Deadline for the objective.
     */
    public void setEnd(Date end) {
        this.end = end;
    }
    /**
     * @return True if objective is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * @param isDone Set the objective to be done or not.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }
    
    /**
	 * Checks if all criteria is met for Objective. Since Objectivehas no criteria, isDone can only be set to true manually.If this Objective is done, then end date is set at the momentthis method was called.
	 */
    public void update(){
        if(this.isDone()){
            this.end = new Date();
        }
    }
    
}
