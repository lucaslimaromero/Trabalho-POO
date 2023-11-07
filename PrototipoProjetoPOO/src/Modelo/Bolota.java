/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import java.io.Serializable;

/**
 *
 * @author Lucas
 */
public class Bolota extends Personagem  implements Serializable {
    private Personagem hero;
    private int iContaIntervalos;
    private int iContaIntervalos2;
    
    public Bolota(String sNomeImagePNG, Personagem hero, int fase) {
        super(sNomeImagePNG);
        this.hero = hero;
        this.bTransponivel = false;
        this.bixo = true;
        this.bMortal = true;
        this.fase = fase;
        this.iContaIntervalos = 0;
        this.iContaIntervalos2 = 0;
    }
    
    public void autoDesenho() {
        super.autoDesenho();

        this.iContaIntervalos++;
        this.iContaIntervalos2++;
        if(this.iContaIntervalos == Consts.TIMERBOLOTA && this.iContaIntervalos2 != Consts.TIMERBOLOTA + 1) {
            if (this.getPosicao().getColuna() > hero.getPosicao().getColuna()) {
                this.moveLeft();
                this.setLastMovement('l');
            }
            if (this.getPosicao().getColuna() < hero.getPosicao().getColuna()) {
                this.moveRight();
                this.setLastMovement('r');
            }
            this.iContaIntervalos = 0;
        }
        if(this.iContaIntervalos2 == Consts.TIMERBOLOTA + 1) {
            if (this.getPosicao().getLinha() > hero.getPosicao().getLinha()) {
                this.moveUp();
                this.setLastMovement('u');
            }
            if (this.getPosicao().getLinha() < hero.getPosicao().getLinha()) {
                this.moveDown();
                this.setLastMovement('d');
            }
            this.iContaIntervalos2 = 0;
            this.iContaIntervalos = 0;
        }
                
        
    }
}
