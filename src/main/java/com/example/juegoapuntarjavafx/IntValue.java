package com.example.juegoapuntarjavafx;

public class IntValue {
    public int puntos = 0;
    public int bonusRacha = 0;
    public boolean jugado = false;
    public int record;

    public void sumaPunto(int i){
        puntos += i;
    }

    public void restaPunto(int i){
        puntos -= i;
    }

    public void reseteaPuntos(){
        puntos = 0;
    }

    public void sumaBonus(int i){
        bonusRacha += i;
    }

    public void reseteaBonus(){
        bonusRacha = 0;
    }

    public int getBonusRacha() {
        return bonusRacha;
    }

    public void cambiarJugado(){
        if (jugado == true){
            jugado = false;
        }else{
            jugado = true;
        }
    }

    public void setRecord(){
        if (puntos > record){record = puntos;}
    }
}
