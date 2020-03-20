package model;

import javax.management.InstanceAlreadyExistsException;
import java.util.ArrayList;

public class Freelancer {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String registration_date;
    private String alias;

    public String getName(){ return this.name; }
    public String getSurname(){ return this.surname;}

    public Freelancer(String email, String password){
        this.password = password;
        this.email = email;
    }

    public Freelancer(String name, String surname, String email, String password, String registration_date, String alias){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.registration_date = registration_date;
        this.alias = alias;
    }

    public boolean checkIfEmailAlreadyRegistered() throws Exception{
        ArrayList<String> colNames = new ArrayList<String>();
        colNames.add("id");
        ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "users",  new ArrayList<String>(), "WHERE email = '" + this.email + "'");
        if (result.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean register() throws Exception {
        if (checkIfEmailAlreadyRegistered() == true){
            throw new InstanceAlreadyExistsException();
        }

        ArrayList <String> userData = new ArrayList<String>();
        userData.add(name);
        userData.add(surname);
        userData.add(email);
        userData.add(password);
        userData.add(registration_date);
        userData.add(alias);

        return DatabaseDriver.dbInsert("freelancers", "(name, surname, email, password, registration_date,alias)", userData);
    }

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

            ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect(colNames, "freelancers",  new ArrayList<String>(), "WHERE email = '" + this.email + "'");
            if (result.isEmpty()){
                return false;
            }

            String temp_id = result.get(0).get(0);
            String temp_name = result.get(0).get(1);
            String temp_surname = result.get(0).get(2);
            String temp_email = result.get(0).get(3);
            String temp_password = result.get(0).get(4);
            String temp_date = result.get(0).get(5);

            if (this.password.equals(temp_password)){
                this.id = Integer.parseInt(temp_id);
                this.name = temp_name;
                this.surname = temp_surname;
                this.registration_date = temp_date;
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