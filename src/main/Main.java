package main;

import classesORM.CategoryORM;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import java.util.Iterator;
import java.util.List;

public class Main extends Application {
    private static final SessionFactory ourSessionFactory;
    
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session getSession () throws HibernateException {
        return ourSessionFactory.openSession();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        //User jeff = new User();
        //jeff.findGigByCategory("Tax Accountant");
        Parent root = FXMLLoader.load(getClass().getResource("/view/template/startScreen.fxml"));
        primaryStage.setTitle("Freelance Portal");
        primaryStage.setScene(new Scene(root, 720, 540));
        primaryStage.show();
    }
    
    private static void initHibernate () {
        final Session session = getSession();
        try {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        } finally {
            session.close();
        }
    }
    
    /* Method to  READ all the employees */
    public static void listCategories (){
        Session session = ourSessionFactory.openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            List categories = session.createQuery("FROM classesORM.CategoryORM").list();
            for (Iterator iterator = categories.iterator(); iterator.hasNext();){
                CategoryORM categoryORM = (CategoryORM) iterator.next();
                System.out.print("Name: " + categoryORM.getCategoryName());
                System.out.print("  Description: " + categoryORM.getDescription());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public static void main(String[] args) throws Exception {
        initHibernate();
        listCategories();
        launch(args);
    }
}
