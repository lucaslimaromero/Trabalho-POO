package Modelo;

import java.io.Serializable;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dino extends Personagem implements Serializable {
    private Personagem hero;
    private int iContaIntervalos;

    public Dino(String sNomeImagePNG, Personagem hero, int fase) {
        super(sNomeImagePNG);
        this.iContaIntervalos = Consts.TIMERFOGO - 1;
        this.hero = hero;
        this.bTransponivel = false;
        this.bixo = true;
        this.fase = fase;
    }

    public void autoDesenho() {
        super.autoDesenho();

        this.iContaIntervalos++;
        if(this.iContaIntervalos == Consts.TIMERFOGO) {
            if (this.getPosicao().getColuna() > hero.getPosicao().getColuna() && this.getPosicao().getLinha() == hero.getPosicao().getLinha()) {
                Fogo f = new Fogo("fire.png", 'l');
                f.setPosicao(pPosicao.getLinha(), pPosicao.getColuna());
                Desenho.acessoATelaDoJogo().addPersonagem(f);
            } else if (this.getPosicao().getColuna() < hero.getPosicao().getColuna() && this.getPosicao().getLinha() == hero.getPosicao().getLinha()) {
                Fogo f = new Fogo("fire.png", 'r');
                f.setPosicao(pPosicao.getLinha(), pPosicao.getColuna());
                Desenho.acessoATelaDoJogo().addPersonagem(f);
            } else if (this.getPosicao().getLinha() > hero.getPosicao().getLinha() && this.getPosicao().getColuna() == hero.getPosicao().getColuna()) {
                Fogo f = new Fogo("fire.png", 'u');
                f.setPosicao(pPosicao.getLinha(), pPosicao.getColuna());
                Desenho.acessoATelaDoJogo().addPersonagem(f);
            } else if (this.getPosicao().getLinha() < hero.getPosicao().getLinha() && this.getPosicao().getColuna() == hero.getPosicao().getColuna()) {
                Fogo f = new Fogo("fire.png", 'd');
                f.setPosicao(pPosicao.getLinha(), pPosicao.getColuna());
                Desenho.acessoATelaDoJogo().addPersonagem(f);
            }
            
            
            this.iContaIntervalos = 0;
        }
    }
}
