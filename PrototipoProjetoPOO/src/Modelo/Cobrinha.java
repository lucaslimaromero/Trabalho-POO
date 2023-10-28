/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Random;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import java.io.Serializable;
import java.awt.event.KeyEvent;

import java.io.Serializable;

/**
 *
 * @author Lucas
 */
public class Cobrinha extends Personagem implements Serializable {
    public Cobrinha(String sNomeImagePNG){
        super(sNomeImagePNG);
        this.bTransponivel = true;
        this.bMovimenta = false;
        this.bixo = true;
        this.bMortal = true;
    }


    public void autoDesenho(){
        super.autoDesenho();
    }    
}


    
    
    

    
    
