package model;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;

public class Freelancer extends User {
    private int freelancerID;
    private String alias;
    private String description;

    public Freelancer(String email, String password){
        super(email, password);
    }

    public Freelancer(String name, String surname, String email, String password, String registration_date, String alias){
        super(name, surname, email, password, registration_date);
        this.alias = alias;
    }
    
    public Freelancer(int id, String name, String surname, String email, String password, String registration_date, String alias){
        super(id, name, surname, email, password, registration_date);
        this.alias = alias;
    }
    
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
