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
public class Chave extends Personagem implements Serializable{
    
    public Chave(String sNomeImagePNG){
        super(sNomeImagePNG);
        this.bTransponivel = true;
        this.bMovimenta = false;
    }
}
