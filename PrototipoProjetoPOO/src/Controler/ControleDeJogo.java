package Controler;

import Auxiliar.Consts;
import java.awt.event.KeyEvent;
import Modelo.Personagem;
import Modelo.Hero;
import Modelo.Heart;
import Modelo.Box;
import Modelo.Fogo;
import Modelo.Esfera;
import auxiliar.Posicao;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ControleDeJogo {
    public void desenhaTudo(ArrayList<Personagem> e){
        for(int i = 0; i < e.size(); i++){
            e.get(i).autoDesenho();
        }
    }
    public void processaTudo(ArrayList<Personagem> umaFase, int nHeart){
        Personagem hero = umaFase.get(0);
        Personagem porta = umaFase.get(1);
        Personagem pIesimoPersonagem;
        Personagem pJesimoPersonagem;
        for(int i = 1; i < umaFase.size(); i++){
            pIesimoPersonagem = umaFase.get(i);
            if(hero.getPosicao().igual(pIesimoPersonagem.getPosicao())){
                if(pIesimoPersonagem.isbTransponivel()){
                    if(pIesimoPersonagem.isbMortal()){
                        umaFase.remove(hero);
                    }
                    if(pIesimoPersonagem instanceof Heart){
                        hero.setnHeart(hero.getnHeart() + 1);
                    }
                    if(hero.getnHeart() == nHeart){
                        porta.setbTransponivel(true);
                        porta.setImage("porta_aberta.png");
                    }
                    if(hero.getPosicao().igual(porta.getPosicao()))
                    {
                        umaFase.clear();
                        hero.setFase(-1);
                        break;
                    }
                    umaFase.remove(pIesimoPersonagem);
                }
                else if(pIesimoPersonagem.isbMovimenta()){
                    switch (hero.getLastMovement()) {
                        case 'u':
                            pIesimoPersonagem.moveUp();
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
            if(pIesimoPersonagem instanceof Box){
                for(int j = 1; j < umaFase.size(); j++){
                    pJesimoPersonagem = umaFase.get(j);
                    if(pJesimoPersonagem != pIesimoPersonagem) {
                        if (pIesimoPersonagem.getPosicao().igual(pJesimoPersonagem.getPosicao())) {
                            if (!pJesimoPersonagem.isbTransponivel()) {
                                if (!pJesimoPersonagem.isbMovimenta()) {
                                    switch (hero.getLastMovement()) {
                                        case 'u':
                                            pIesimoPersonagem.moveDown();
                                            hero.moveDown();
                                            break;
                                        case 'd':
                                            pIesimoPersonagem.moveUp();
                                            hero.moveUp();
                                            break;
                                        case 'l':
                                            pIesimoPersonagem.moveRight();
                                            hero.moveRight();
                                            break;
                                        case 'r':
                                            pIesimoPersonagem.moveLeft();
                                            hero.moveLeft();
                                            break;
                                        default:
                                            break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if(pIesimoPersonagem instanceof Fogo){
                for(int j = 1; j < umaFase.size(); j++){
                    pJesimoPersonagem = umaFase.get(j);
                    if(pJesimoPersonagem != pIesimoPersonagem) {
                        if (pIesimoPersonagem.getPosicao().igual(pJesimoPersonagem.getPosicao())) {
                            if (!pJesimoPersonagem.isbTransponivel()) {
                                umaFase.remove(pIesimoPersonagem);
                            }
                        }
                    }
                }
            }
            else if(pIesimoPersonagem instanceof Esfera){
                for(int j = 1; j < umaFase.size(); j++){
                    pJesimoPersonagem = umaFase.get(j);
                    if(pJesimoPersonagem != pIesimoPersonagem) {
                        if (pIesimoPersonagem.getPosicao().igual(pJesimoPersonagem.getPosicao())) {
                            if (!pJesimoPersonagem.isbTransponivel()) {
                                umaFase.remove(pIesimoPersonagem);
                                if(pJesimoPersonagem.isBixo()){
                                    umaFase.remove(pJesimoPersonagem);
                                }
                            }
                            else{
                                if(pJesimoPersonagem.isBixo()){
                                    umaFase.remove(pJesimoPersonagem);
                                    umaFase.remove(pIesimoPersonagem);
                                }
                                else{
                                    umaFase.remove(pIesimoPersonagem);
                                }
                            }
                        }
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
