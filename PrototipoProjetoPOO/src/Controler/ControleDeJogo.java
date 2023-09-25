package Controler;

import java.awt.event.KeyEvent;
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
                    if(hero.getLastMovement() == 'u'){
                        // o objeto so pode se mover se na posicao (linha + 1) nao tiver um objeto fixo
                        
                        /* Supondo que existe uma funcao chamada retornaObjeto(linha, coluna) que dado
                        uma posicao na matriz ele retorna o objeto nela:
                        if(!retornaObjeto(pIesimoPersonagem.getLinha() + 1, pIesimoPersonagem.getColuna()).isbMovimenta())
                        */
                        
                        pIesimoPersonagem.moveUp();
                    }
                    else if(hero.getLastMovement() == 'd'){
                        pIesimoPersonagem.moveDown();
                    }
                    else if(hero.getLastMovement() == 'l'){
                        pIesimoPersonagem.moveLeft();
                    }
                    else if(hero.getLastMovement() == 'r'){
                        pIesimoPersonagem.moveRight();
                    }
                }
            }
        }
    }
    
    /*Retorna true se a posicao p é válida para Hero com relacao a todos os personagens no array*/
    public boolean ehPosicaoValida(ArrayList<Personagem> umaFase, Posicao p){
        Personagem pIesimoPersonagem;
        for(int i = 1; i < umaFase.size(); i++){
            pIesimoPersonagem = umaFase.get(i);            
            if(!pIesimoPersonagem.isbTransponivel() && !pIesimoPersonagem.isbMovimenta())
                if(pIesimoPersonagem.getPosicao().igual(p))
                    return false;
        }
        return true;
    }
}
