package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.ControleDeJogo;
import Controler.Tela;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Hero extends Personagem implements Serializable{
    public Hero(String sNomeImagePNG, int fase) {
        super(sNomeImagePNG);
        this.fase = fase;
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
        if(super.moveUp())
            return validaPosicao();
        return false;
    }

    public boolean moveDown() {
        if(super.moveDown())
            return validaPosicao();
        return false;
    }

    public boolean moveRight() {
        if(super.moveRight())
            return validaPosicao();
        return false;
    }

    public boolean moveLeft() {
        if(super.moveLeft())
            return validaPosicao();
        return false;
    }    
    
    public void shootR(){
        Esfera f = new Esfera("Esfera.png", 'r');
        f.setPosicao(pPosicao.getLinha(),pPosicao.getColuna());
        if(this.fase == 1){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 1);
        } else if(this.fase == 2){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 2);
        } else if(this.fase == 3){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 3);
        } else if(this.fase == 4){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 4);
        }
    }
    
    public void shootL(){
        Esfera f = new Esfera("Esfera.png", 'l');
        f.setPosicao(pPosicao.getLinha(),pPosicao.getColuna());
        if(this.fase == 1){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 1);
        } else if(this.fase == 2){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 2);
        } else if(this.fase == 3){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 3);
        } else if(this.fase == 4){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 4);
        }
    }
    
    public void shootU(){
        Esfera f = new Esfera("Esfera.png", 'u');
        f.setPosicao(pPosicao.getLinha(),pPosicao.getColuna());
        if(this.fase == 1){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 1);
        } else if(this.fase == 2){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 2);
        } else if(this.fase == 3){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 3);
        } else if(this.fase == 4){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 4);
        }
    }
    
    public void shootD(){
        Esfera f = new Esfera("Esfera.png", 'd');
        f.setPosicao(pPosicao.getLinha(),pPosicao.getColuna());
        if(this.fase == 1){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 1);
        } else if(this.fase == 2){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 2);
        } else if(this.fase == 3){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 3);
        } else if(this.fase == 4){
            Desenho.acessoATelaDoJogo().addPersonagem(f, 4);
        }
    }
}
