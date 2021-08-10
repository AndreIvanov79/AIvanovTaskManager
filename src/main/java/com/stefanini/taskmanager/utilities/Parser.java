package com.stefanini.taskmanager.utilities;

import com.stefanini.taskmanager.entities.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {

    public Map<String,String> parseFromTerminal(String[] args){
        //System.out.println(Arrays.asList(args));
        Map<String, String> parameters = new LinkedHashMap<>();
        for (String arg : args) {
            String[] parts = arg.split("=", 2);
            String key = parts[0];
            if (key.startsWith("--")) {
                key = key.substring(2);
            } else {
                key = key.substring(1);
            }
            String value = parts[1];

            parameters.put(key, value);
        }// System.out.println(parameters);
       return parameters;
    }

    public User parseFromFile(String file){
        User user = new User();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String firstName;
            while((firstName = reader.readLine()) != null) {
                user.setFirstName(firstName);
                user.setLastName(reader.readLine());
                user.setUserName(reader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
