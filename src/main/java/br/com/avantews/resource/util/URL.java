package br.com.avantews.resource.util;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

    public static String decodeParam(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        }catch (Exception e){
            return "";
        }
    }

    public static List<Integer> decodeIntList(String s) {
        String[] strings = s.split(",");
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < strings.length; i++){
            list.add(Integer.parseInt(strings[i]));
        }
        return list;
        // Exemplo em lambda:
        // return Arrays.asList(s.split(",")).stream().map(x -> Integer.parseInt(x)).collect(Collectors.toList());
    }
}
