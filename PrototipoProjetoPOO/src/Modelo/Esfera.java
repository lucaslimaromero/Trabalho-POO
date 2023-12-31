/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.io.Serializable;

/**
 *
 * @author Samsung
 */
public class Esfera extends Personagem implements Serializable{
    private char sentido;
    
    public Esfera(String sNomeImagePNG, char sentido) {
        super(sNomeImagePNG);
        this.bMortal = false;
        this.sentido = sentido;
        this.bixo = false;
    }

    @Override
    public void autoDesenho() {
        super.autoDesenho();
        if(this.sentido == 'u'){
            if(!this.moveUp()) {
                Desenho.acessoATelaDoJogo().addPersonagem(this);
            }
        } else if(this.sentido == 'd'){
            if(!this.moveDown()) {
                Desenho.acessoATelaDoJogo().addPersonagem(this);
            }
        } else if(this.sentido == 'r'){
            if(!this.moveRight()) {
                Desenho.acessoATelaDoJogo().addPersonagem(this);
            }
        } else if(this.sentido == 'l'){
            if(!this.moveLeft()) {
                Desenho.acessoATelaDoJogo().addPersonagem(this);
            }
        }
    }
}
