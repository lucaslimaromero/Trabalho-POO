/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Auxiliar.Consts;
import java.io.Serializable;

/**
 *
 * @author Lucas
 */
public class Bolota extends Personagem  implements Serializable {
    private Personagem hero;
    
    public Bolota(String sNomeImagePNG, Personagem hero, int fase) {
        super(sNomeImagePNG);
        this.hero = hero;
        this.bTransponivel = false;
        this.bixo = true;
        this.fase = fase;
    }
}
