package com.example.carpooling;

public class User {
    public String name,lastname,phone,email;
    public boolean conductor= false;

    public  User(){

    }

    public User(String _name,String _lastname, String _phone,String _email){
        name = _name;
        lastname = _lastname;
        phone = _phone;
        email = _email;
    }
}
