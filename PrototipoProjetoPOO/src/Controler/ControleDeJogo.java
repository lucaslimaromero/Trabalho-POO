package Controler;

import Auxiliar.Consts;
import java.awt.event.KeyEvent;
import Modelo.Personagem;
import Modelo.Bolota;
import Modelo.Hero;
import Modelo.Heart;
import Modelo.Caveira;
import Modelo.Agua;
import Modelo.Box;
import Modelo.Bau;
import Modelo.Fogo;
import Modelo.Esfera;
import auxiliar.Posicao;
import java.io.*;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ControleDeJogo {
    
    public void desenhaTudo(ArrayList<Personagem> e){
        for(int i = 1; i < e.size(); i++){
            e.get(i).autoDesenho();
        }
        e.get(0).autoDesenho(); // O herói agora passa por cima do baú
    }
    
    public void processaTudo(ArrayList<Personagem> umaFase, GameState estadoAtual){
        Personagem hero = umaFase.get(0);
        Personagem porta = umaFase.get(1);
        Bau bau = (Bau) umaFase.get(2);
        Personagem pIesimoPersonagem;
        Personagem pJesimoPersonagem;
        for(int i = 1; i < umaFase.size(); i++){
            pIesimoPersonagem = umaFase.get(i);
            // Tratamento de Colisões
            if(hero.getPosicao().igual(pIesimoPersonagem.getPosicao())){
                if(pIesimoPersonagem.isbTransponivel()){
                    if(pIesimoPersonagem.isbMortal()){
                        estadoAtual.reiniciaFase((Hero) hero);
                        hero.setnTiro(0);
                        System.out.println("Voce morreu! Respawn efetuado!");
                    }

                    if(pIesimoPersonagem instanceof Heart){ // Para ele coletar os corações
                        hero.setnHeart(hero.getnHeart() + 1);
                        hero.setnTiro(hero.getnTiro() + 1);
                        umaFase.remove(pIesimoPersonagem);
                    }

                    if(hero.getPosicao().igual(porta.getPosicao()))
                    {
                        // Esse é o momento em que ocorre a troca de fase
                        hero.setnHeart(0); // Reinicia o número de corações
                        estadoAtual.avancaProximaFase(); // Atualizo o estado do jogo
                        hero.setFase(hero.getFase() + 1);
                        hero.setnTiro(0);
                        break;
                    }
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
                
                if (hero.getnHeart() == estadoAtual.getNumCoracoesFase()) {
                    Caveira.setParada(false);

                    if(!bau.isbAberto()){
                        // Quando todos os corações são coletados, a caveira deve começar a se movimentar
                        bau.setImage("bau_aberto_perola.png");
                        bau.setbTransponivel(true);
                        bau.setbAberto(true);
                    }

                    if (hero.getPosicao().igual(bau.getPosicao())) {
                        bau.setImage("bau_aberto.png");
                        porta.setbTransponivel(true);
                        porta.setImage("porta_aberta.png");
                        bau.setbTransponivel(false);
                    }
                }
            }


            
            if(pIesimoPersonagem instanceof Box){
                Box box = (Box) pIesimoPersonagem;
                for(int j = 1; j < umaFase.size(); j++){
                    pJesimoPersonagem = umaFase.get(j);
                    if(pJesimoPersonagem != box) {
                        if (box.getPosicao().igual(pJesimoPersonagem.getPosicao())) {
                            if (!pJesimoPersonagem.isbTransponivel() || (pJesimoPersonagem instanceof Heart) || (pJesimoPersonagem instanceof Caveira)) {
                                if (!pJesimoPersonagem.isbMovimenta() || (pJesimoPersonagem instanceof Box)) {
                                    switch (hero.getLastMovement()) {
                                        case 'u':
                                            box.moveDown();
                                            hero.moveDown();
                                            break;
                                        case 'd':
                                            box.moveUp();
                                            hero.moveUp();
                                            break;
                                        case 'l':
                                            box.moveRight();
                                            hero.moveRight();
                                            break;
                                        case 'r':
                                            box.moveLeft();
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
            } else if(pIesimoPersonagem instanceof Caveira){
                if(!Caveira.isParada()){
                    for(int j = 1; j < umaFase.size(); j++){
                        Caveira caveira = (Caveira) pIesimoPersonagem;
                        pJesimoPersonagem = umaFase.get(j);
                        if(pJesimoPersonagem != caveira) {
                            if (caveira.getPosicao().igual(pJesimoPersonagem.getPosicao()) && !(pJesimoPersonagem instanceof Esfera)) {
                                if(caveira.getDirecao() == 'h'){
                                    if(caveira.getSentido() == 'r'){
                                        caveira.setSentido('l');
                                        caveira.moveLeft();
                                    } else{
                                        caveira.setSentido('r');
                                        caveira.moveRight();
                                    }
                                } else {
                                    if(caveira.getSentido() == 'u'){
                                        caveira.setSentido('d');
                                        caveira.moveDown();
                                    } else{
                                        caveira.setSentido('u');
                                        caveira.moveUp();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else if(pIesimoPersonagem instanceof Bolota){
                for(int j = 1; j < umaFase.size(); j++){
                    pJesimoPersonagem = umaFase.get(j);
                    if(pJesimoPersonagem != pIesimoPersonagem) {
                        if (pIesimoPersonagem.getPosicao().igual(pJesimoPersonagem.getPosicao())) {
                            if (!pJesimoPersonagem.isbTransponivel() || (pJesimoPersonagem instanceof Heart)) {
                                if (!pJesimoPersonagem.isbMovimenta() || (pJesimoPersonagem instanceof Box)) {
                                    switch (pIesimoPersonagem.getLastMovement()) {
                                        case 'u':
                                            pIesimoPersonagem.moveDown();
                                            break;
                                        case 'd':
                                            pIesimoPersonagem.moveUp();
                                            break;
                                        case 'l':
                                            pIesimoPersonagem.moveRight();
                                            break;
                                        case 'r':
                                            pIesimoPersonagem.moveLeft();
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
