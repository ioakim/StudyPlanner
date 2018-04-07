package studyplanner.Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
//XML imports below
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;
/**
 * Class to model the Study Profile of the Planner.
 * @author Moaz
 */
public class StudyProfile implements Serializable{
    private static final long serialVersionUID = 1L;    //Serialisation ID
    private String name;                                //Name of the study prrofile
    //TODO remove static
    private ArrayList<Module> modules;           //List of all modules 
   
    /**
     * Default constructor for an instance of Study Profile.
     */
    public StudyProfile(){
        this.name = "";
        this.modules = new ArrayList<>();
    }
    
    /*******************
     * GET METHODS *
     *******************/

    /**
     * @return Name of the Study Profile.
     */
    public String getName() {
        return name;
    }
    
    /**
     * @return List of all modules.
     */
    public ArrayList<Module> getModules() {
        return modules;
    }
    
    /**
     * @param name Name for a Study Profile.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return this.name;
    }
    /**
     * method to initialise a study profile by reading a HUB file
     * @param profile   the profile to be initialised
     * @param file      the hub file to get the profile information from
     */
    public static void InitialiseStudyProfile(StudyProfile profile, File file){
        try{
            File xmlFile = file;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Module");//returns list of modules
            
            // for each module it reads the name, code and the assignment details associated to the module
            for (int i = 0; i < nList.getLength(); i++) {
                Module module = new Module();
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String mname = eElement.getAttribute("mname");//gets module name
                    String code = eElement.getAttribute("code");//gets module code

                    module.setName(mname);
                    module.setCode(code);

                    profile.getModules().add(module);//adding the new module to the semester profile modules list
                }

                NodeList assignmentList = nNode.getChildNodes();//gets the assignments associated to the module
                
                /*
                for each assignment associated to the module it gets the name,
                weighting,start and end date and the description
                */
                for (int j = 0; j < assignmentList.getLength(); j++) {
                    Node n1Node = assignmentList.item(j);
                    if (n1Node.getNodeType() == Node.ELEMENT_NODE) {

                        Element e1Element = (Element) n1Node;

                        String name2 = e1Element.getElementsByTagName("name").item(0).getTextContent();//gets assignment name
                        double weighting = Double.parseDouble(e1Element.getElementsByTagName("weighting").item(0).getTextContent());//gets assignment wighting
                        Date start = new Date();
                        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                        try {
                            start = df.parse(e1Element.getElementsByTagName("start").item(0).getTextContent());//gets start date
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Date end  = new Date();
                        try {
                            end = df.parse(e1Element.getElementsByTagName("end").item(0).getTextContent());//gets end date
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        String description = e1Element.getElementsByTagName("Description").item(0).getTextContent();//gets assignment description

                        Assignment assignment = new Assignment(weighting,name2,description,start,end);
                        module.getAssignments().add(assignment);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Checks whether XML file is able to initialize study profile
     * @param profile
     * @return 
     */
    public static boolean isValid(File file){
        StudyProfile phProfile = new StudyProfile();
        InitialiseStudyProfile(phProfile, file);
        boolean result = false;
        if(!phProfile.getModules().isEmpty()){
            if(!phProfile.getModules().get(0).getAssignments().isEmpty())
                result = true;
        }else{
            result = false;
        }
        return result;
    }
    public static void updateStudyProfile(StudyProfile profile, File file){
        try{
            File xmlFile = file;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Module");//returns list of modules
            
            // for each module it reads the name, code and the assignment details associated to the module
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                NodeList assignmentList;
                    
                ArrayList<Module> modules = new ArrayList<>();
                modules = profile.getModules();
                System.out.println("modules empty  " + modules.isEmpty());
                Element eElement = (Element) nNode;
                //System.out.println(modules.contains(eElement.getAttribute("mname")));
                
                for(Module module : modules){
                    if(module.getName().equals(eElement.getAttribute("mname"))){
                        System.out.println("passes first if");
                        assignmentList = nNode.getChildNodes();
                        HashSet<Assignment> assignments = new HashSet<>();
                        assignments = module.getAssignments();
                        System.out.println("assignments empty   " + assignments.isEmpty());
                        
                        for (int j = 0; j < assignmentList.getLength(); j++){
                            Node n1Node = assignmentList.item(j);
                            
                            if (n1Node.getNodeType() == Node.ELEMENT_NODE) {
                                
                                System.out.println("passes second if");
                                Element e1Element = (Element) n1Node;                        
                                for(Assignment assignment : assignments){
                                    System.out.println("assignment name   " + e1Element.getElementsByTagName("name").item(0).getTextContent());
                                    System.out.println("assignment exists   " + assignment.getName().equals(e1Element.getElementsByTagName("name").item(0).getTextContent()));
                                    if(assignment.getName().equals(e1Element.getElementsByTagName("name").item(0).getTextContent())){
                                        System.out.println("finds assignments and gets in if");
                                        Date start = new Date();

                                        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                        try {
                                            start = df.parse(e1Element.getElementsByTagName("start").item(0).getTextContent());//gets start date
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        System.out.println("old start date   " + assignment.getStart());
                                        if(!(assignment.getStart().equals(start))){
                                            assignment.setStart(start);
                                        }
                                        System.out.println("new start date   " + assignment.getStart());
                                        

                                        Date end  = new Date();
                                        
                                        
                                        try {
                                            end = df.parse(e1Element.getElementsByTagName("end").item(0).getTextContent());//gets end date
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        System.out.println("old end date   "  + assignment.getEnd());
                                        if(!(assignment.getEnd().equals(end))){
                                            assignment.setEnd(end);
                                        }
                                        System.out.println("new end date   "  + assignment.getEnd());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }catch (IOException | ParserConfigurationException | DOMException | SAXException e){
        }
    }
}

