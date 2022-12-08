package com.automation;

public class Person {
    private String name;
    private String surname;
    private int id;
    
    public Person(){
        this.name = null;
        this.surname = null;
        this.id = -1;
    }

    public Person(String name, String surname, int id){
        this.name = name;
        this.surname = surname;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public String getSurname(){
        return this.surname;
    }

    public int getId(){
        return this.id;
    }
}
