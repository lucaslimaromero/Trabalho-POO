package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.ControleDeJogo;
import Controler.Tela;
import auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Hero extends Personagem implements Serializable{
    public Hero(String sNomeImagePNG, int faseAtual) {
        super(sNomeImagePNG);
        this.setFase(faseAtual); // Sempre começa na fase 1 ? aqui é o lugar que podemos alterar pra salvar a fase tlvz
    }

    public void setFase(int faseNova){
        if(faseNova == 1){
            this.pPosicao = new Posicao(1, 7);
        } else if(faseNova == 2){
            this.pPosicao = new Posicao(11, 5);
        } else if(faseNova == 3){
            this.pPosicao = new Posicao(9, 7);
        } else if(faseNova == 4){
            this.pPosicao = new Posicao(11, 6);
        }
        this.fase = faseNova;
    }

    public void voltaAUltimaPosicao(){
        this.pPosicao.volta();
    }
    

    
    public boolean setPosicao(int linha, int coluna){
        if(this.pPosicao.setPosicao(linha, coluna)){
            if (!Desenho.acessoATelaDoJogo().ehPosicaoValida(this.getPosicao())) {
                this.voltaAUltimaPosicao();
            }
            return true;
        }
        return false;       
    }

    /*TO-DO: este metodo pode ser interessante a todos os personagens que se movem*/
    private boolean validaPosicao(){
        if (!Desenho.acessoATelaDoJogo().ehPosicaoValida(this.getPosicao())) {
            this.voltaAUltimaPosicao();
            return false;
        }
        return true;       
    }
    
    public boolean moveUp() {
        this.setLastMovement('u');
        this.setImage("lolo-up.png");
        if(super.moveUp())
            return validaPosicao();
        return false;
    }

    public boolean moveDown() {
        this.setLastMovement('d');
        this.setImage("lolo.png");
        if(super.moveDown())
            return validaPosicao();
        return false;
    }

    public boolean moveRight() {
        this.setLastMovement('r');
        this.setImage("lolo-right.png");
        if(super.moveRight())
            return validaPosicao();
        return false;
    }

    public boolean moveLeft() {
        this.setLastMovement('l');
        this.setImage("lolo-left.png");
        if(super.moveLeft())
            return validaPosicao();
        return false;
    }    
    
    public void shootEsfera(char sentido){ // Recebe a fase
        Esfera esf = new Esfera("bola.png", sentido); // Pode ser r,l,u,d
        esf.setPosicao(pPosicao.getLinha(),pPosicao.getColuna());
        Desenho.acessoATelaDoJogo().addPersonagem(esf);
    }
}
