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
                Desenho.acessoATelaDoJogo().addPersonagem(this, this.fase);
            }
        } else if(this.sentido == 'd'){
            if(!this.moveDown()) {
                Desenho.acessoATelaDoJogo().addPersonagem(this, this.fase);
            }
        } else if(this.sentido == 'r'){
            if(!this.moveRight()) {
                Desenho.acessoATelaDoJogo().addPersonagem(this, this.fase);
            }
        } else if(this.sentido == 'l'){
            if(!this.moveLeft()) {
                Desenho.acessoATelaDoJogo().addPersonagem(this, this.fase);
            }
        }
    }
    
}
