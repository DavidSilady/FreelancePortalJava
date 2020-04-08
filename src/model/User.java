package model;
import model.DatabaseDriver;

import java.sql.ResultSet;
import java.util.ArrayList;

public class User {

    public void findGigByCategory(String category) {
        try {
            ArrayList<String> joins = new ArrayList<String>();
            joins.add("INNER JOIN categories AS c ON g.category_id = c.id");
            joins.add("INNER JOIN freelancers AS f ON g.freelancer_id = f.freelance_id");
            ArrayList<ArrayList<String>> result = DatabaseDriver.dbSelect("gig_name,category_name,alias,email","gigs AS g", joins, "WHERE category_name = '" + category + "'");
            int matchesNum = 0;
            for (ArrayList<String> line : result){
                matchesNum++;
                System.out.println("match number "+ matchesNum);
                System.out.println( "gig_name = " + line.get(0) );
                System.out.println( "category_name = " + line.get(1));
                System.out.println( "alias = " + line.get(2) );
                System.out.println( "email = " + line.get(3) );
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}
