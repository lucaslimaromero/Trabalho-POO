package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class Caveira extends Personagem implements Serializable{
    private int iContaIntervalos;
    private boolean parada;
    
    public Caveira(String sNomeImagePNG, char direcao) {
        super(sNomeImagePNG);
        this.bMortal = true;
        this.bTransponivel = false;
        this.iContaIntervalos = 0;
        this.bixo = false;
        this.direcao = direcao;
        if(direcao == 'h'){
            this.setSentido('r');
        } else{
            this.setSentido('u');
        }
    }

    public void autoDesenho() {
        super.autoDesenho();
        
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
    }    
}
