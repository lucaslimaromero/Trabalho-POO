package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import Controler.Tela;
import auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Personagem implements Serializable {

    protected ImageIcon iImage;
    public Posicao pPosicao;
    protected boolean bTransponivel; /*Pode passar por cima?*/
    protected boolean bMortal;       /*Se encostar, morre?*/
    protected boolean bMovimenta;
    protected char lastMovement;
    protected int nHeart;
    protected boolean bixo;
    protected int fase;
    public String imagem;
    
    protected Personagem(String sNomeImagePNG) {
        this.pPosicao = new Posicao(1, 1);
        this.bTransponivel = true;
        this.bMortal = false;
        this.bMovimenta = false;
        this.imagem = sNomeImagePNG;
        try {
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + sNomeImagePNG);
            Image img = iImage.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iImage = new ImageIcon(bi);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void setImage(String sNomeImagePNG){
        try {
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + sNomeImagePNG);
            Image img = iImage.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iImage = new ImageIcon(bi);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setNomeImagem(String imagem) {
        this.imagem = imagem;
    }
    
    public String getImage(){
        return this.imagem;
    }

    public int getFase() {
        return fase;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public boolean isBixo() {
        return bixo;
    }

    public void setBixo(boolean bixo) {
        this.bixo = bixo;
    }

    public int getnHeart() {
        return nHeart;
    }

    public void setnHeart(int nHeart) {
        this.nHeart = nHeart;
    }

    public boolean isbMortal() {
        return bMortal;
    }

    public void setbMortal(boolean bMortal) {
        this.bMortal = bMortal;
    }
    
    public Posicao getPosicao() {
        /*TODO: Retirar este método para que objetos externos nao possam operar
         diretamente sobre a posição do Personagem*/
        return pPosicao;
    }

    public boolean isbTransponivel() {
        return bTransponivel;
    }

    public void setbTransponivel(boolean bTransponivel) {
        this.bTransponivel = bTransponivel;
    }

    public boolean isbMovimenta() {
        return bMovimenta;
    }

    public void setbMovimenta(boolean bMovimenta) {
        this.bMovimenta = bMovimenta;
    }

    public void autoDesenho(){
        Desenho.desenhar(this.iImage, this.pPosicao.getColuna(), this.pPosicao.getLinha());        
    }

    public boolean setPosicao(int linha, int coluna) {
        return pPosicao.setPosicao(linha, coluna);
    }

    public boolean moveUp() {
        return this.pPosicao.moveUp();
    }

    public boolean moveDown() {
        return this.pPosicao.moveDown();
    }

    public boolean moveRight() {
        return this.pPosicao.moveRight();
    }

    public boolean moveLeft() {
        return this.pPosicao.moveLeft();
    }
    
    public char getLastMovement() {
        return lastMovement;
    }

    public void setLastMovement(char lastMovement) {
        this.lastMovement = lastMovement;
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
