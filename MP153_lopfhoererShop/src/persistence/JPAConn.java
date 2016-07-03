/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import com.sun.jndi.cosnaming.IiopUrl;
import entity.Adresse;
import entity.Benutzer;
import entity.Benutzerbestellung;
import entity.Bestellung;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author 5ia13nosiegrist
 */
public class JPAConn {
    public Session session;
    public JPAConn(){
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder ssrb = 
            new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory factory = configuration.buildSessionFactory(ssrb.build());
        session = factory.openSession();
    }
    public void createUser(Benutzer user){
        createAdress(user.getAdresse());
        Transaction t = session.beginTransaction();
        session.save(user);
        t.commit();
    }
    public void createAdress(Adresse adr){
        Transaction t = session.beginTransaction();
        session.save(adr);
        t.commit();
    }
    
    public void createOwnOrder(Bestellung best, Benutzer user){
        Transaction t = session.beginTransaction();
        Set<Benutzerbestellung> ben = new HashSet<>();
        ben.add(new Benutzerbestellung(user, best));
        best.setBenutzerbestellungs((Set) ben);

        session.save(best);
        
        t.commit();
    }
    public void buyOrder(Bestellung best){
        Transaction t = session.beginTransaction();
        best.setBestellt(true);
        session.update(best);
        t.commit();
    }
}
