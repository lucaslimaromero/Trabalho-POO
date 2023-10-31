/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

/**
 *
 * @author lucas
 */
import Modelo.Personagem;
import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    private int currentPhase;
    private ArrayList<Personagem> arrayFaseAtual;
    // Adicione outros campos relevantes, como pontuação, itens coletados, etc.
    
    public GameState(ArrayList<Personagem> a, int faseAtual){
        this.arrayFaseAtual = a;
        this.currentPhase = faseAtual;
    }

    public ArrayList<Personagem> getArrayFaseAtual() {
        return arrayFaseAtual;
    }

    public void setArrayFaseAtual(ArrayList<Personagem> arrayFaseAtual) {
        this.arrayFaseAtual = arrayFaseAtual;
    }

    public int getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(int currentPhase) {
        this.currentPhase = currentPhase;
    }
}

