package com.example.carpooling;

public class User {
    public String name,lastname,phone,email,coche,matricula,foto;
    public boolean isConductor,isPasajero;
    public int totalviajes,totalreseñas,calificacion;

    public boolean conductor= false;

    public  User(){

    }

    public User(String _name,String _lastname, String _phone,String _email){ //Clase de creacion del usuario Prototipo V1.0
        name = _name; //Nombre
        lastname = _lastname; //Apellido
        phone = _phone; //Telefono
        email = _email; //Email
    }

    public User(String _name,String _lastname, String _phone,String _email,boolean isConductor){ //Clase de creacion del conductor Prototipo V1.0
        name = _name; //Nombre
        lastname = _lastname; //Apellido
        phone = _phone; //Telefono
        email = _email; //Email
    }
}
