/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Auxiliar.Consts;
import java.io.Serializable;
import Auxiliar.Desenho;
import Controler.Tela;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class Agua extends Personagem implements Serializable {
    private Personagem hero;
    private int iContaIntervalos;

    public Agua(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.iContaIntervalos = 0;
        this.bixo = false;
    }

    public void autoDesenho() {
        super.autoDesenho();
    }
    
}
