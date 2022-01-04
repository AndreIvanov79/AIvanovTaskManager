package util;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<String> parse(String[] args){
        List<String> param=new ArrayList<>();
        for (int i=1;i<args.length;i++){
            param.add(args[i].substring(4));
        }
        return param;
    }
}
