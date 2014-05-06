/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.teleconsulta.servicios;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CarolXimena
 */
public class MongoConfig {
    
    private MongoClient mongoClient; 
 public DB db; 
 public MongoConfig() { 
 try { 
MongoClientURI mongoURI = new MongoClientURI("mongodb://teleconsulta:teleconsulta@oceanic.mongohq.com:10060/teleconsultadb");
 mongoClient = new MongoClient(mongoURI); 
 
 } catch (UnknownHostException ex) { 
 Logger.getLogger(MongoConfig.class.getName()).log(Level.SEVERE, null, ex); 
 } 
 db = mongoClient.getDB("teleconsultadb"); 
 }
    
}
