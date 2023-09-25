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
public class Wallbricks extends Background implements Serializable {
    public boolean BackMovimenta;
    
    public Wallbricks(String sNomeImagePNG){
        super(sNomeImagePNG);
        this.BackTransponivel = false;
        this.BackMovimenta = true;
    }
    
    
}
