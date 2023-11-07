package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class Caveira extends Personagem implements Serializable{
    private int iContaIntervalos;
    private static boolean parada;
    
    public Caveira(String sNomeImagePNG, char direcao) {
        super(sNomeImagePNG);
        this.bMortal = true;
        this.bTransponivel = false;
        this.iContaIntervalos = 0;
        this.bixo = true;
        this.direcao = direcao;
        Caveira.parada = true;
        if(direcao == 'h'){
            this.setSentido('r');
        } else{
            this.setSentido('u');
        }
    }

    public static boolean isParada() {
        return parada;
    }

    public static void setParada(boolean parada) {
        Caveira.parada = parada;
    }

    public void autoDesenho() {
        super.autoDesenho();
        
        if(!(this.isParada())){ // Se a classe caveira puder se movimentar (!parada)
            this.iContaIntervalos++;
            if(this.iContaIntervalos == Consts.TIMERBICHOHORIZONTAL){
                if(this.getDirecao() == 'h'){
                    if(this.getSentido() == 'r'){
                        this.moveRight();
                    } else {
                        this.moveLeft();
                    }
                } else{
                    if(this.getSentido() == 'u'){
                        this.moveUp();
                    } else {
                        this.moveDown();
                    }
                }
                this.iContaIntervalos = 0;
            }
        }
        
        
    }    
}
