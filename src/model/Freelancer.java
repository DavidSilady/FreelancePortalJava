package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.management.InstanceAlreadyExistsException;
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
    }
/// _________________________________________________________________________________________LANGUAGES__________________________________________________________
    public void load_my_languages() {
        ArrayList <String> colNames = new ArrayList<String>();
        colNames.add("language_name");
        ArrayList<String> joins = new ArrayList<String>();
        joins.add("INNER JOIN languages AS l ON fl.language_id = l.language_id");
        ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "freelancer_languages AS fl ", joins," WHERE fl.freelancer_id = " + this.freelancerID);
        ArrayList <String> new_languages = new ArrayList<String>();
        for (ArrayList<String> row : result){
            new_languages.add(row.get(0));
        }
        languages = new_languages;
    }

    public int getLanguageIndex (String language) {
        ArrayList <String> colNames = new ArrayList<String>();
        ArrayList <String> joins = new ArrayList<String>();
        colNames.add("language_id");
        ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "languages ", joins," WHERE language_name = '" + language + "'");
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
        joins.add("INNER JOIN languages AS l ON fl.language_id = l.language_id");
        ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "freelancer_languages AS fl ", joins," WHERE l.language_name = '" + language + "'");
        return ! result.isEmpty();
    }

    public void deleteMyLanguage(String language){
        int language_id = getLanguageIndex(language);
        if (language_id == 0) {
            return;
        }
        DatabaseDriver.dbDelete("freelancer_languages", " WHERE freelancer_id = " + this.freelancerID + " AND language_id = " + language_id);

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
/// ________________________________________________________________________________________________________________________________________________________________


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
        return DatabaseDriver.dbUpdate("freelancers", columns,values," WHERE user_id = '" + super.getId() + "'" );
    }
    
    private void buildFromDB() {
        try {
            ArrayList<String> colNames = new ArrayList<String>();
            colNames.add("freelance_id");
            colNames.add("alias");
            colNames.add("description");
    
            ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "freelancers",  new ArrayList<String>(), " WHERE  user_id = '" + super.getId() + "'");
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


    public ArrayList<String> getAllCategories(){
        ArrayList<String> columns = new ArrayList<>();
        columns.add("category_name");
        ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(columns, "categories",  new ArrayList<String>(), " ORDER BY category_name");
        ArrayList<String> categories = new ArrayList<>();
        for (ArrayList<String> row : result){
            categories.add(row.get(0));
        }
        return categories;
    }

    public int getCategoryID(String category){
        ArrayList<String> columns = new ArrayList<>();
        columns.add("category_id");
        ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(columns, "categories",  new ArrayList<String>(), " WHERE category_name = '" + category + "'");
        return Integer.parseInt(result.get(0).get(0));
    }

    public void addGig(String gigName, String categoryName){
        int categoryID = getCategoryID(categoryName);
        ArrayList<String> values = new ArrayList<String>();
        values.add(String.valueOf(this.freelancerID));
        values.add(String.valueOf(categoryID));
        values.add(gigName);
         DatabaseDriver.dbInsert("gigs", "(freelancer_id,category_id,gig_name)", values);
    }

    public ObservableList<Gig> loadMyGigs(){
        ArrayList<String> colNames = new ArrayList<String>();
        ArrayList<String> joins = new ArrayList<String>();
        colNames.add("gig_id");
        colNames.add("freelancer_id");
        colNames.add("category_name");
        colNames.add("gig_name");
        joins.add("INNER JOIN categories AS c ON gigs.category_id = c.category_id");
        ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "gigs ",  joins, " WHERE freelancer_id = '" + this.freelancerID + "'");
        ObservableList<Gig> my_gigs = FXCollections.observableArrayList();
        for (ArrayList<String> row : result){
            String temp_id = row.get(0);
            String temp_freelancer_id = row.get(1);
            String temp_categoryName = row.get(2);
            String temp_gigName = row.get(3);
            my_gigs.add(new Gig(Integer.parseInt(temp_id),Integer.parseInt(temp_freelancer_id),temp_categoryName,temp_gigName));
        }
        return my_gigs;
    }

    public void deleteMyGig(String gigName, String category){
        int categoryID = getCategoryID(category);
        DatabaseDriver.dbDelete("gigs", " WHERE gig_name = '" + gigName + "' AND category_id = " + categoryID + " AND freelancer_id = " + this.freelancerID);
    }

}