package Modelo;

import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

public class Fogo extends Personagem implements Serializable{
    private char sentido;
            
    public Fogo(String sNomeImagePNG, char sentido) {
        super(sNomeImagePNG);
        this.bMortal = true;
        this.bixo = false;
        this.sentido = sentido;
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
        if(this.sentido == 'u'){
            if(!this.moveUp()) {
                if (this.fase == 1) {
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 1);
                } else if (this.fase == 2) {
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 2);
                } else if (this.fase == 3) {
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 3);
                } else if (this.fase == 4) {
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 4);
                }
            }
        } else if(this.sentido == 'd'){
            if(!this.moveDown()) {
                if (this.fase == 1) {
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 1);
                } else if (this.fase == 2) {
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 2);
                } else if (this.fase == 3) {
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 3);
                } else if (this.fase == 4) {
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 4);
                }
            }
        } else if(this.sentido == 'r'){
            if(!this.moveRight()) {
                if (this.fase == 1) {
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 1);
                } else if (this.fase == 2) {
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 2);
                } else if (this.fase == 3) {
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 3);
                } else if (this.fase == 4) {
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 4);
                }
            }
        } else if(this.sentido == 'l'){
            if(!this.moveLeft()) {
                if(this.fase == 1){
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 1);
                } else if(this.fase == 2){
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 2);
                } else if(this.fase == 3){
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 3);
                } else if(this.fase == 4){
                    Desenho.acessoATelaDoJogo().addPersonagem(this, 4);
                }
            }
        }
    }
    
}
