package utilities;

import java.util.LinkedHashMap;
import java.util.Map;

public class Parser {
    public Map<String,String> parseFromTerminal(String[] args){
        Map<String, String> parameters = new LinkedHashMap<>();
        for(int i=1;i<args.length;i++){
            String[] parts = args[i].split("=", 2);
            String key = parts[0];
            if (key.startsWith("--")) {
                key = key.substring(2);
            } else {
                key = key.substring(1);
            }
            String value = parts[1];

            parameters.put(key, value);
        }
        return parameters;
    }
}
