/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Random;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import java.io.Serializable;

/**
 *
 * @author Samsung
 */
public class Porta extends Personagem implements Serializable {
    
    public Porta(String sNomeImagePNG){
        super(sNomeImagePNG);
        this.bTransponivel = false;
        this.bMovimenta = false;
    }
}
