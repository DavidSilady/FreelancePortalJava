package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;

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


    public static void main(String[] args) throws Exception {
        initHibernate();
        launch(args);
    }
}
