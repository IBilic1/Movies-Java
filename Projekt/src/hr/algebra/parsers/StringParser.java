/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.parsers;

import hr.algebra.model.Person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author HT-ICT
 */
public class StringParser {

    public static List<String> parse(String data, String delimter) {
        String[] details = data.split(delimter);
        return Arrays.asList(details);
    }

    public static String parsefromHTML(String data, String delimter) {
        data=data.replaceAll("</a>", "");
        List<String> details = new ArrayList<String>(Arrays.asList(data.split(delimter)));
        details.removeIf(d->d.startsWith("<"));
        
        int numberOfDelimeter = details.size();
        String result = details.get(0);
        return result.substring(0, result.lastIndexOf(".") + 1).isEmpty() ? "DESCRIPTION" : result.substring(0, result.lastIndexOf(".") + 1);
    }

    public static Person parseToPerson(String s) {
        String[] details = s.split(" ");
        Person person = new Person(details[0], details[details.length - 1]);
        return person;
    }

}
