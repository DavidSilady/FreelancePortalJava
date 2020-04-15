package model;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;
import java.util.Iterator;

/*<<<<<<< HEAD
public class Freelancer {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String registration_date;
    private int freelance_id;
    private String alias;
    private String description;
    private ArrayList<String> languages;
    */
public class Freelancer extends User {
    private int freelancerID;
    private String alias;
    private String description;
    ArrayList<String> languages;


  /*  public void setLanguages(){
        ArrayList <String> new_languages = new ArrayList<String>();
        new_languages.add("Boi");
        new_languages.add("hol up");
        this.languages = new_languages;
    }*/

    public void load_my_languages() {
        ArrayList <String> colNames = new ArrayList<String>();
        colNames.add("language_name");
        ArrayList<String> joins = new ArrayList<String>();
        joins.add("INNER JOIN languages AS l ON fl.language_id = l.id");
        ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "freelancer_languages AS fl ", joins,"fl.freelancer_id = " + this.freelancerID);
        ArrayList <String> new_languages = new ArrayList<String>();
        for (ArrayList<String> row : result){
            new_languages.add(row.get(0));
        }
        languages = new_languages;
    }

    public int get_language_index(String language) {
        ArrayList <String> colNames = new ArrayList<String>();
        ArrayList <String> joins = new ArrayList<String>();
        colNames.add("id");
        ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "languages ", joins,"language_name = '" + language + "'");
        if (result.isEmpty()){
            return 0;
        }
        else{
            return Integer.parseInt(result.get(0).get(0));
        }
    }

    public void add_new_language(String new_language){
        ArrayList <String> values = new ArrayList<String>();
        values.add(new_language);
        DatabaseDriver.dbInsert("languages", "(language_name)", values);
    }

    public boolean checkIfLanguageExists(String language) {
        ArrayList<String> colNames = new ArrayList<String>();
        colNames.add("freelancer_id");
        ArrayList<String> joins = new ArrayList<String>();
        joins.add("INNER JOIN languages AS l ON fl.language_id = l.id");
        ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "freelancer_languages AS fl ", joins,"l.language_name = '" + language + "'");
        if (result.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    public void deleteMyLanguage(String language){
        int language_id = get_language_index(language);
        if (language_id == 0) {
            return;
        }
        DatabaseDriver.dbDelete("freelancer_languages", "freelancer_id = " + this.freelancerID + " AND language_id = " + language_id);

        this.languages.removeIf(i -> i.equals(language));
    }

    public void add_my_language(String language_name){
        if (checkIfLanguageExists(language_name) == true){
           // throw new InstanceAlreadyExistsException();
            return;
        }

        ArrayList <String> new_languages = new ArrayList<String>();
        new_languages = languages;
        new_languages.add(language_name);
        languages = new_languages;

        ArrayList<String> values = new ArrayList<String>();
        int language_id = get_language_index(language_name);
        if (language_id == 0){
            add_new_language(language_name);
            language_id = get_language_index(language_name);
        }
        values.add(String.valueOf(this.freelancerID));
        values.add(String.valueOf(language_id));
        DatabaseDriver.dbInsert("freelancer_languages", "(freelancer_id,language_id)", values);
    }



    //public String getName(){ return this.name; }
    //public String getSurname(){ return this.surname;}
    public String getDescription(){ return this.description;}
    public ArrayList<String> getLanguages() { return this.languages;}

    public Freelancer(String email, String password){
        super(email, password);
    }

    public Freelancer(String name, String surname, String email, String password, String registration_date, String alias){
        super(name, surname, email, password, registration_date);
        this.alias = alias;
    }


    public boolean saveDescription(String text) {
        this.description = text;
        ArrayList<String> columns = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        columns.add("description");
        values.add(text);
        return DatabaseDriver.dbUpdate("freelancers", columns,values,"email = '" + super.getEmail() + "'" );
    }

    /*public boolean checkIfEmailAlreadyRegistered() {
        ArrayList<String> colNames = new ArrayList<String>();
        colNames.add("id");
        ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "users",  new ArrayList<String>(), "email = '" + this.email + "'");
        if (result.isEmpty()){
            return false;
        }
        else{
            return true;*/

    
    public Freelancer(User user){
        super(user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPassword(),
                user.getRegistrationDate());
        buildFromDB();
    }
    
    private void buildFromDB() {
        try {
            ArrayList<String> colNames = new ArrayList<String>();
            colNames.add("freelance_id");
            colNames.add("alias");
            colNames.add("description");
    
            ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "freelancers",  new ArrayList<String>(), "WHERE id = '" + getId() + "'");
            this.freelancerID = Integer.parseInt(result.get(0).get(0));
            this.alias = result.get(0).get(1);
            this.description = result.get(0).get(2);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
       
    }

    
    public String getMenuSceneName() {
        return "freelancerMenu";
    }

    public boolean register() throws Exception {
        if (EmailAlreadyRegistered()){
            throw new InstanceAlreadyExistsException();
        }

        ArrayList <String> userData = new ArrayList<String>();
        userData.add(super.getName());
        userData.add(super.getSurname());
        userData.add(super.getEmail());
        userData.add(super.getPassword());
        userData.add(super.getRegistrationDate());
        userData.add(alias);

        return DatabaseDriver.dbInsert("freelancers", "(name, surname, email, password, registration_date,alias)", userData);
    }
//<<<<<<< HEAD

    public boolean verify(){
        try {
            //ArrayList<String> joins = new ArrayList<String>();
            ArrayList<String> colNames = new ArrayList<String>();
            colNames.add("id");
            colNames.add("name");
            colNames.add("surname");
            colNames.add("email");
            colNames.add("password");
            colNames.add("registration_date");
            colNames.add("freelance_id");
            colNames.add("alias");
            colNames.add("description");

            ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "freelancers",  new ArrayList<String>(), "email = '" + super.getEmail() + "'");
            if (result.isEmpty()){
                return false;
            }

            String temp_id = result.get(0).get(0);
            String temp_name = result.get(0).get(1);
            String temp_surname = result.get(0).get(2);
            String temp_email = result.get(0).get(3);
            String temp_password = result.get(0).get(4);
            String temp_date = result.get(0).get(5);
            String temp_freelance_id = result.get(0).get(6);
            String temp_alias = result.get(0).get(7);
            String temp_description = result.get(0).get(8);

            if (super.getPassword().equals(temp_password)){
                super.setId(Integer.parseInt(temp_id));
                super.setName(temp_name);
                super.setSurname(temp_surname);
                super.setRegistrationDate(temp_date);
                this.freelancerID = Integer.parseInt(temp_freelance_id);
                this.alias = temp_alias;
                this.description = temp_description;
                return true;
            }
            else{
                return false;
            }
        }
        catch (Exception ex){
            return false;
        }
    }
}
