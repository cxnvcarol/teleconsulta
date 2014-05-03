/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teleconsulta.servicios;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.teleconsulta.entities.Paciente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author CarolXimena
 */
@Stateless
@EJB(beanInterface = IServicioPersistenciaMockLocal.class, beanName =
"ServicioPersistenciaNoSql", name = "servicioPersistenciaNoSql")
public class ServicioPersistenciaNoSql extends MongoConfig implements IServicioPersistenciaMockLocal, Serializable {

    public ServicioPersistenciaNoSql()
    {
        super();
        int i=1;
        Date fNac=new Date();
        fNac.setYear(fNac.getYear()-5-i*i);
        //Paciente nuevo=new Paciente("000"+i,"Maria Jose "+(i+1), fNac,(i%3==0?Paciente.MASCULINO:Paciente.FEMENINO),150);
        //create(nuevo);
    }
    @Override
    public void create(Object obj) {
        DBCollection coll = null;
        BasicDBObject doc = null;
        if (obj instanceof Paciente) {
            Paciente p = (Paciente) obj;
            coll = db.getCollection("Paciente");
            doc = new BasicDBObject();
            doc.append("id", p.getId());
            doc.append("nombre", p.getNombre());
            doc.append("fechaNacimiento", p.getFechaNacimiento());
            doc.append("estatura", p.getEstatura());
            doc.append("contrasena", p.getContrasena());
        }
        coll.insert(doc);
    }

    @Override
    public void update(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List findAll(Class c) {
        if (c.equals(Paciente.class)) {
            List listaPacientes = new ArrayList();
            DBCollection coll = db.getCollection("Paciente");
            BasicDBObject query = new BasicDBObject();
            //DBCursor cursor = coll.find(query);
            DBCursor cursor=coll.find();
            while (cursor.hasNext()) {                
                DBObject dBObject = cursor.next();
                System.out.println(dBObject);
                Paciente p = new Paciente();
                p.setNombre((String) dBObject.get("nombre"));
                p.setFechaNacimiento((Date) dBObject.get("fechaNacimiento"));
                p.setEstatura((Integer) dBObject.get("estatura"));
                p.setContrasena((String) dBObject.get("contrasena"));
                listaPacientes.add(p);
            }
            cursor.close();
            return listaPacientes;
        }
        return null;
    }

    @Override
    public Object findById(Class c, Object id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
