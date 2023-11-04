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

/**
 *
 * @author lucas
 */
public class Box extends Personagem implements Serializable {
    
    public Box(String sNomeImagePNG){
        super(sNomeImagePNG);
        this.bTransponivel = false;
        this.bMovimenta = true;
        this.bixo = false;
    }


    public void autoDesenho(){
        super.autoDesenho();
    }
    
    
}
