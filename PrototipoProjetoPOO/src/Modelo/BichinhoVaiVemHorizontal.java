package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BichinhoVaiVemHorizontal extends Personagem  implements Serializable{
    private boolean bRight;
    private int iContaIntervalos;

    public BichinhoVaiVemHorizontal(String sNomeImagePNG) {
        super(sNomeImagePNG);
        bRight = true;
        this.iContaIntervalos = 0;
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
    
    public void autoDesenho(){
        this.iContaIntervalos++;
        if(this.iContaIntervalos == Consts.TIMERBICHOHORIZONTAL){
            this.iContaIntervalos = 0;
            if(bRight)
                this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()+1);
            else
                this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()-1);           
        }
        
        super.autoDesenho();
        bRight = !bRight;
    }
}
