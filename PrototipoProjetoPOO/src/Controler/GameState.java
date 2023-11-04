/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

/**
 *
 * @author lucas
 */
import Modelo.Hero;
import Modelo.Personagem;
import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable {
    private int faseAtual;
    private ArrayList<Personagem> arrayFaseAtual;
    private int numCoracoesFase;
    private boolean trocouFase; // flag que se for 1 indica que houve mudanca de fase, e 0 se nao
    // Cada vez que a fase é trocada é colocado 1 e ao atualizar, volta para 0.
    // Adicione outros campos relevantes, como pontuação, itens coletados, etc.
    
    public GameState(ArrayList<Personagem> a, int faseAtual){
        this.arrayFaseAtual = a;
        this.faseAtual = faseAtual; // 
        setNumCoracoesFase(); //
        setTrocouFase(false);
    }

    public boolean isTrocouFase() {
        return trocouFase;
    }
    public void setTrocouFase(boolean trocouFase) {
        this.trocouFase = trocouFase;
    }
    
    public int getNumCoracoesFase() {
        return numCoracoesFase;
    }
    public void setNumCoracoesFase() {
        switch(this.getFaseAtual()){
            case 1:
                this.numCoracoesFase = 2;
                break;
            case 2:
                this.numCoracoesFase = 4;
                break;
            case 3:
                this.numCoracoesFase = 5;
                break;
            case 4:
                this.numCoracoesFase = 5;
                break;
            default:
                break;
        }
    }
    
    public ArrayList<Personagem> getArrayFaseAtual() {
        return arrayFaseAtual;
    }
    public void setArrayFaseAtual(ArrayList<Personagem> arrayFaseAtual) {
        this.arrayFaseAtual = arrayFaseAtual;
    }

    public int getFaseAtual() {
        return faseAtual;
    }

    public void setFaseAtual(int faseAtual) {
        this.faseAtual = faseAtual;
    }
    
    public void avancaProximaFase() { // Se for a 4ta o jogo reinicia para a primeira
    // Lógica para avançar para a próxima fase
    
        if(this.getFaseAtual() == 4){
            System.out.println("Voce zerou Las Aventuras de Bombonari!");
            this.setFaseAtual(1);
        }
        else
            this.setFaseAtual(this.getFaseAtual() + 1);
        
        this.setNumCoracoesFase();
        this.setTrocouFase(true);
    }
    
    public void reiniciaFase(Hero hero){
        hero.respawnHeroi(this.getFaseAtual());
        hero.setnHeart(0);
        this.setNumCoracoesFase();
        this.setTrocouFase(true);
    }
}

