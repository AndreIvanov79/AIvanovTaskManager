package com.stefanini.taskmanager.utilities;

import com.stefanini.taskmanager.entities.User;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Map;

import static com.sun.activation.registries.LogSupport.log;

public class Serializer {
    private static final Logger log = Logger.getLogger(Serializer.class);
    public boolean serialization( String fileName, String... user) {
        boolean flag = false;
        File f = new File(fileName);
        ObjectOutputStream ostream = null;
        try {
            try (FileOutputStream fos = new FileOutputStream(f)) {
                if (fos != null) {
                    ostream = new ObjectOutputStream(fos);
                    ostream.writeObject(user);
                    flag = true;
                }
            }
        } catch (FileNotFoundException e) {
            log.error("File cannot be created: "+ e);
        } catch (NotSerializableException e) {
            log.error("Class is not supported sirialization: " + e);
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                if (ostream != null) {
                    ostream.close();
                }
            } catch (IOException e) {
                log.error("Error thread closing");
            }
        }
        return flag;
    }

    public User deserialization(String fileName) throws InvalidObjectException {
        File fr = new File(fileName);
        ObjectInputStream istream = null;
        try {
            FileInputStream fis = new FileInputStream(fr);
            istream = new ObjectInputStream(fis);

            User user = (User) istream.readObject();
            return user;
        } catch (ClassNotFoundException ce) {
            log.error("Class doesn`t exist: " + ce);
        } catch (FileNotFoundException fe) {
            log.error("There is no file for deserialization: "+ fe);
        } catch (InvalidClassException ice) {
            log.error("Invalid class versions: " + ice);
        } catch (IOException ioe) {
            System.err.println("General I/O error: " + ioe);
        } finally {
            try {
                if (istream != null) {
                    istream.close();
                }
            } catch (IOException e) {
                log("Thread closing error ");
            }
        }
        throw new InvalidObjectException("object is not restored");
    }
    public User deserializationFromTerminal(Map<String,String> map){
        return new User(map.get("fn"),map.get("ln"),map.get("un"));
    }
}

