package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenho;
import java.util.Random;

public class ZigueZague extends Personagem{
    private int iContaIntervalos;
    
    public ZigueZague(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.iContaIntervalos = 0;
        this.bixo = true;
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
        Random rand = new Random();
        int iDirecao = rand.nextInt(4);
        
        
        this.iContaIntervalos++;
        if(this.iContaIntervalos == Consts.TIMERZZ){
            this.iContaIntervalos = 0;
            if(iDirecao == 1)
                this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()+1);
            else if(iDirecao == 2)
                this.setPosicao(pPosicao.getLinha()+1, pPosicao.getColuna());
            else if(iDirecao == 3)
                this.setPosicao(pPosicao.getLinha(), pPosicao.getColuna()-1);
            else if(iDirecao == 4)
                this.setPosicao(pPosicao.getLinha()-1, pPosicao.getColuna());
        }
        
        super.autoDesenho();
    }    
}
