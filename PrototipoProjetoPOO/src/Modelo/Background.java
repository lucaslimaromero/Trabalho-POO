/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author Samsung
 */
public abstract class Background implements Serializable {
    protected ImageIcon iImage;
    protected Posicao BackPosicao;
    protected boolean BackTransponivel; /*Pode passar por cima?*/
    
    protected Background(String sNomeImagePNG) {
        this.BackPosicao = new Posicao(1, 1);
        this.BackTransponivel = false;
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
    
    public void autoDesenho(){
        Desenho.desenhar(this.iImage, this.BackPosicao.getColuna(), this.BackPosicao.getLinha());        
    }

    public Posicao getBackPosicao() {
        return BackPosicao;
    }

    public boolean setPosicao(int linha, int coluna) {
        return BackPosicao.setPosicao(linha, coluna);
    }

    public boolean isBackTransponivel() {
        return BackTransponivel;
    }

    public void setBackTransponivel(boolean bTransponivel) {
        this.BackTransponivel = bTransponivel;
    }
    
    
    
}
