/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import entity.Adresse;
import entity.Benutzer;
import entity.Bestellung;

/**
 *
 * @author 5ia13nosiegrist
 */
public class main {
    public static void main(String[] args) {
        JPAConn db = new JPAConn();
        Benutzer b = new Benutzer(new Adresse("Dietikon", "8953", "Edelweissstrasse", "8", null), "asd", "Hash", true, null);
        db.createUser(b);
        Bestellung best = new Bestellung(null, false, null, null, null);
        db.createOwnOrder(best, b);
        db.buyOrder(best);
    }
}
