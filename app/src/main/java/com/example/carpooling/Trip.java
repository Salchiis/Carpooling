package com.example.carpooling;

public class Trip {
    public String salida,llegada,tiempoSalida,fecha,idConductor;
    public boolean active = false, finished = false;
    public int precio,totalPasajeros,tiempo;
    public  Trip(){

    }

    public Trip(String _salida, int _precio,String _llegada,String _tiempoSalida,String _fecha,boolean _active,boolean _finished,int _totalPasajeros,int _tiempo,String _idConductor){
        salida = _salida;
        llegada = _llegada;
        tiempoSalida = _tiempoSalida;
        active = _active;
        fecha= _fecha;
        finished = _finished;
        totalPasajeros = _totalPasajeros;
        precio = _precio;
        tiempo = _tiempo;
        idConductor = _idConductor;
    }


}
