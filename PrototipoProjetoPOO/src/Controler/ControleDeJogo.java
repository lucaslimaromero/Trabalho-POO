package Controler;

import Modelo.Personagem;
import Modelo.Background;
import Modelo.Hero;
import auxiliar.Posicao;
import java.util.ArrayList;

public class ControleDeJogo {
    public void desenhaTudo(ArrayList<Personagem> e){
        for(int i = 0; i < e.size(); i++){
            e.get(i).autoDesenho();
        }
    }
    public void processaTudo(ArrayList<Personagem> umaFase){
        Personagem hero = umaFase.get(0);
        Personagem pIesimoPersonagem;
        for(int i = 1; i < umaFase.size(); i++){
            pIesimoPersonagem = umaFase.get(i);
            if(hero.getPosicao().igual(pIesimoPersonagem.getPosicao())){
                if(pIesimoPersonagem.isbTransponivel()){
                    /*TO-DO: verificar se o personagem eh mortal antes de retirar*/ 
                    if(pIesimoPersonagem.isbMortal()){
                        umaFase.remove(hero);
                    }
                    umaFase.remove(pIesimoPersonagem);
                }
            }
        }
    }
    
    public void desenhaTudoCenario(ArrayList<Background> e){
        for(int i = 0; i < e.size(); i++){
            e.get(i).autoDesenho();
        }
    }
    
    public void processaTudoCenario(ArrayList<Personagem> umaFase, ArrayList<Background> umBloco){
        Personagem hero = umaFase.get(0);
        Background pIesimoBackground;
        for(int i = 1; i < umBloco.size(); i++){
            pIesimoBackground = umBloco.get(i);
            if(hero.getPosicao().igual(pIesimoBackground.getBackPosicao()))
                if(pIesimoBackground.isBackTransponivel())
                    /*TO-DO: verificar se o personagem eh mortal antes de retirar*/                    
                    umBloco.remove(pIesimoBackground);
        }
    }
    
    /*Retorna true se a posicao p é válida para Hero com relacao a todos os personagens no array*/
    public boolean ehPosicaoValida(ArrayList<Personagem> umaFase, ArrayList<Background> umBloco, Posicao p){
        Personagem pIesimoPersonagem;
        Background pIesimoBackground;
        for(int i = 1; i < umaFase.size(); i++){
            pIesimoPersonagem = umaFase.get(i);            
            if(!pIesimoPersonagem.isbTransponivel())
                if(pIesimoPersonagem.getPosicao().igual(p))
                    return false;
        }

        for(int i = 1; i < umBloco.size(); i++){
            pIesimoBackground = umBloco.get(i);            
            if(!pIesimoBackground.isBackTransponivel())
                if(pIesimoBackground.getBackPosicao().igual(p))
                    return false;
        }
        return true;
    }
}
