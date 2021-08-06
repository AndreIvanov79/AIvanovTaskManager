package com.stefanini.taskmanager.utilities;

import com.stefanini.taskmanager.entities.User;
import org.apache.log4j.Logger;

import java.io.*;

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
                    log.info("Serialization completed successfully");
                }
            }
        } catch (FileNotFoundException e) {
            log.error("Файл не может быть создан: "+ e);
        } catch (NotSerializableException e) {
            log.error("Класс не поддерживает сериализацию: " + e);
        } catch (IOException e) {
            log.error(e);
        } finally {
            try {
                if (ostream != null) {
                    ostream.close();
                }
            } catch (IOException e) {
                System.err.println("ошибка закрытия потока");
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
            System.err.println("Класс не существует: " + ce);
        } catch (FileNotFoundException fe) {
            System.err.println("Файл для десериализации не существует: "+ fe);
        } catch (InvalidClassException ice) {
            System.err.println("Несовпадение версий классов: " + ice);
        } catch (IOException ioe) {
            System.err.println("Общая I/O ошибка: " + ioe);
        } finally {
            try {
                if (istream != null) {
                    istream.close();
                }
            } catch (IOException e) {
                System.err.println("ошибка закрытия потока ");
            }
        }
        throw new InvalidObjectException("объект не восстановлен");
    }
}

