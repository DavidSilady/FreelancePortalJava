package model;

import javax.management.InstanceAlreadyExistsException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Freelancer extends User {
    private int freelancerID;
    private String alias;
    private String description;
    ArrayList<String> languages;
    
    
    public Freelancer(User user){
        super(user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getPassword(),
                user.getRegistrationDate());
        System.out.println("Freelancer from user");
        buildFromDB();
        load_my_languages();
        ArrayList<ArrayList<String>> result = DatabaseDriver.executeQuery("Select gigs.id, categories.category_name from gigs inner join categories on gigs.category_id = categories.id;");
    }

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

    public int getLanguageIndex (String language) {
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
// language list should be hardcoded in the db, as its an enum
    public void addNewLanguage (String new_language){
        ArrayList <String> values = new ArrayList<String>();
        values.add(new_language);
        DatabaseDriver.dbInsert("languages", "(language_name)", values);
    }

    public boolean languageExists (String language) {
        ArrayList<String> colNames = new ArrayList<String>();
        colNames.add("freelancer_id");
        ArrayList<String> joins = new ArrayList<String>();
        joins.add("INNER JOIN languages AS l ON fl.language_id = l.id");
        ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "freelancer_languages AS fl ", joins,"l.language_name = '" + language + "'");
        return ! result.isEmpty();
    }

    public void deleteMyLanguage(String language){
        int language_id = getLanguageIndex(language);
        if (language_id == 0) {
            return;
        }
        DatabaseDriver.dbDelete("freelancer_languages", "freelancer_id = " + this.freelancerID + " AND language_id = " + language_id);

        this.languages.removeIf(i -> i.equals(language));
    }

    public void addMyLanguage (String languageName){
        if (languageExists(languageName)){
           // throw new InstanceAlreadyExistsException();
            return;
        }

        ArrayList <String> newLanguages = new ArrayList<String>();
        newLanguages = languages;
        newLanguages.add(languageName);
        languages = newLanguages;

        ArrayList<String> values = new ArrayList<String>();
        int languageID = getLanguageIndex(languageName);
        if (languageID == 0){
            addNewLanguage(languageName);
            languageID = getLanguageIndex(languageName);
        }
        values.add(String.valueOf(this.freelancerID));
        values.add(String.valueOf(languageID));
        DatabaseDriver.dbInsert("freelancer_languages", "(freelancer_id,language_id)", values);
    }
    
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
        return DatabaseDriver.dbUpdate("freelancers", columns,values,"id = '" + super.getId() + "'" );
    }
    
    private void buildFromDB() {
        try {
            ArrayList<String> colNames = new ArrayList<String>();
            colNames.add("freelance_id");
            colNames.add("alias");
            colNames.add("description");
    
            ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "freelancers",  new ArrayList<String>(), "id = '" + getId() + "'");
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
        if (isAlreadyRegistered()){
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
}
