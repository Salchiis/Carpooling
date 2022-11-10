package com.example.carpooling;

public class User {
    public String name,lastname,phone,email,coche,matricula;
    public boolean isConductor = false;
   public int totalviajes,calificacion;
    public  User(){

    }

    public User(String _name,String _lastname, String _phone,String _email,String _coche,String _matricula,Boolean _isConductor,int _totalviajes, int _calificacion){ //Clase de creacion del usuario Prototipo V1.0
        name = _name; //Nombre
        lastname = _lastname; //Apellido
        phone = _phone; //Telefono
        email = _email; //Email
        coche = _coche;
        matricula = _matricula;
        isConductor = _isConductor;
        calificacion = _calificacion;
        totalviajes = _totalviajes;

    }


}
