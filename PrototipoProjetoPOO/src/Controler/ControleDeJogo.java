package Controler;

import Modelo.Personagem;
import Modelo.Hero;
import auxiliar.Posicao;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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
                else if(pIesimoPersonagem.isbMovimenta()){
                    pIesimoPersonagem.setPosicao(pIesimoPersonagem.pPosicao.getLinha(), pIesimoPersonagem.pPosicao.getColuna());
                }
            }
        }
    }
    
    /*Retorna true se a posicao p é válida para Hero com relacao a todos os personagens no array*/
    public boolean ehPosicaoValida(ArrayList<Personagem> umaFase, Posicao p){
        Personagem pIesimoPersonagem;
        for(int i = 1; i < umaFase.size(); i++){
            pIesimoPersonagem = umaFase.get(i);            
            if(!pIesimoPersonagem.isbTransponivel())
                if(pIesimoPersonagem.getPosicao().igual(p))
                    return false;
        }
        return true;
    }
}
