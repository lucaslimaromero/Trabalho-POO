package Controler;

import Auxiliar.Consts;
import java.awt.event.KeyEvent;
import Modelo.Personagem;
import Modelo.Hero;
import Modelo.Heart;
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
                    if(pIesimoPersonagem instanceof Heart){
                        hero.setnHeart(hero.getnHeart() + 1);
                    }
                    umaFase.remove(pIesimoPersonagem);
                }
                
                else if(pIesimoPersonagem.isbMovimenta()){ // se é uma caixa (se movimenta)
                    
                    int linhap = pIesimoPersonagem.pPosicao.getLinha(); // linha da caixa
                    int colunap = pIesimoPersonagem.pPosicao.getColuna(); // coluna da caixa
                    
                    Personagem p; // personagem que está imediatamente depois da caixa (na direção a ser especificada)
                    
                    switch (hero.getLastMovement()) {
                        case 'u':
                            //pIesimoPersonagem.moveUp();
                            
                            // o objeto so pode se mover se na posicao (linha - 1) nao tiver um objeto fixo
                            linhap = linhap - 1;
                            p = retornaObjeto(umaFase, linhap, colunap);
                            if(p == null){
                                pIesimoPersonagem.moveUp();
                                pIesimoPersonagem.autoDesenho();
                            }
                            break;
                        case 'd':
                            pIesimoPersonagem.moveDown();
                            break;
                        case 'l':
                            pIesimoPersonagem.moveLeft();
                            break;
                        case 'r':
                            pIesimoPersonagem.moveRight();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
    
    public Personagem retornaObjeto(ArrayList<Personagem> personagem, int linha, int coluna){
        Personagem p = null;
        for(int i = 0; i < Consts.RES; i++){
            for(int j = 0; j < Consts.RES; j++){
                if(personagem.get(i).pPosicao.getLinha() == linha && personagem.get(j).pPosicao.getColuna() == coluna){
                    p = personagem.get(i);
                    return p;
                }
            }
        }
        return p;
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
