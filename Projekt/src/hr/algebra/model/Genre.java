/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

/**
 *
 * @author HT-ICT
 */
public enum Genre {
    ANIMIRANI("animirani"), AKCIJA("akcija"), TRILER("triler"), KOMEDIJA("komedija"), HOROR("horor"), NEPOZNATO("nepoznato");
    
    private final String name;

    private Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public static Genre from(String name){
        for (Genre value : Genre.values()) {
            if (value.name.equals(name)) {
                return value;
            }     
        }
        return NEPOZNATO;
    }
    
}
