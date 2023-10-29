/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Auxiliar.Consts;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;

/**
 *
 * @author Samsung
 */
public class Cenario extends Personagem implements Serializable {
    
    public Cenario(String sNomeImagePNG){
        super(sNomeImagePNG);
        this.bTransponivel = false;
        this.bMovimenta = false;
        this.bixo = false;
    }
}
