package studyplanner.Model;
import java.io.Serializable;
import java.util.HashSet;
/**
 * Class to model a Module of the Study Planner.
 * @author Moaz
 */
public class Module implements Serializable{
    private static final long serialVersionUID = 6L;
    private String name;                //Name for the module.
    private String code;                //Code for the module.
    HashSet<Assignment> assignments;    //Set of assignments of the module to prevent
                                        //adding same assignments.
    
    /**
     * Default constructor for this Module.
     */
    public Module(){
        this.name = "";
        this.code = "";
        this.assignments = new HashSet<>();
    }
    
    /**
     * Overloaded constructor with additional properties.
     * @param name Name for this module.
     * @param code University code for this module.
     */
    public Module(String name, String code){
        this.name = name;
        this.code = code;
        this.assignments = new HashSet<>();
    }
    
    /*******************
     * GET/SET METHODS *
     *******************/
    
    /**
     * @return The name of this module.
     */
    public String getName(){
        return name;
    }
    
    /** 
     * @return The university code of this module.
     */
    public String getCode(){
        return code;
    }
    
    /**
     * @return Set of assignments of this module.
     */
    public HashSet<Assignment> getAssignments(){
        return assignments;
    }
    
    /**
     * @param name Name for the module.
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * @param code University code for the module.
     */
    public void setCode(String code){
        this.code = code;
    }
    
    /**
     * @param assignment An assignment to add.
     */
    public void add(Assignment assignment){
        this.assignments.add(assignment);
    }
    
    @Override
    public String toString(){
        return this.name;
    }
}
