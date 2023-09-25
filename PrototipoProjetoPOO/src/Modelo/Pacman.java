/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Random;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import java.io.Serializable;

/**
 *
 * @author Samsung
 */
public class Pacman extends Personagem implements Serializable {
    private int iContaIntervalos;
    
    public Pacman(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = true;
        this.iContaIntervalos = 0;
    }

    public void autoDesenho() {
        super.autoDesenho();

        this.iContaIntervalos++;
        if(this.iContaIntervalos == Consts.TIMERPACMAN){
            iContaIntervalos = 0;
            Random rand = new Random();
            int Randomposl = rand.nextInt(12);
            int Randomposc = rand.nextInt(12);
            
            this.setPosicao(Randomposl, Randomposc);
        }
        
        super.autoDesenho();
    }    
}
