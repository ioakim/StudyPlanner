package studyplanner.Model;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Class to model an Activity of Study Planner.
 * @author Kiril
 */
public class Activity implements Serializable{
    private static final long serialVersionUID = 2L;
    private String name;                        //Name of an activity.
    private String description;                 //Description of an activity.
    private Double value;                       //Value of an activity.
    private ArrayList<Criterion> criteria;      //List of all criteria of an activity.
    /**
     * Default constructor for an instance of Activity.
     */
    public Activity() {
        //TODO put checks in place for unexpected behaviour - make error message shown in GUI and program not terminate
        this.name = null;
        this.description = null;
        this.value = 0.0;
        this.criteria = new ArrayList<>();
    }
    
    /**
     * Overloaded constructor with additional parameters.
     * @param name
     * @param desc
     * @param value 
     */
    public Activity(String name, String desc, Double value) {
        //TODO put checks in place for unexpected behaviour - make error message shown in GUI and program not terminate
        this.name = name;
        this.description = desc;
        this.value = value;
        this.criteria = new ArrayList<>();
    }

    /** 
     * Updates the criterion to be set as "Criterion is met".
     * @param c A criterion to update
     */
    public void updateCriterion(Criterion c){
        if(c.getType().equals(CriterionType.Boolean)){
            c.setMet(true);
        }else
        {
            c.setValue(c.getValue() - this.getValue());
            if(c.getValue() <= 0.0) c.setMet(true);
        }
        
        if(c.isMet()) c.setName(c.getName() + " ✓");
        //TODO check if the method has to do something besides setting boolean isMet
    }

    /*******************
     * GET/SET METHODS *
     *******************/

    /**
     * @return Activity name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Name for an activity.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return  Activity description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description Description for an activity.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return Activity value.
     */
    public Double getValue() {
        return value;
    }

    /**
     * @param value Value for an activity.
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     * @return List of activity criteria.
     */
    public ArrayList<Criterion> getCriteria() {
        return criteria;
    }

    /**
     * @param criteria Criterion to add for an activity.
     */
    public void add(Criterion criteria){
        this.criteria.add(criteria);
    }

    /**
     * @param criteria Existing list of criteria to add for an activity.
     */
    public void add(ArrayList<Criterion> criteria) {
        this.criteria = criteria;
    }
}
