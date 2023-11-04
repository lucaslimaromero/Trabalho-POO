/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

/**
 *
 * @author Lucas
 */
public class Bau extends Personagem implements Serializable {
    private boolean bAberto; // 1: bau abriu; 0: bau fechado (se abriu não fecha mais)
    
    public Bau(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.bTransponivel = false;
        this.bMovimenta = false;
        this.bixo = false;
        this.bAberto = false;
    }

    public boolean isbAberto() {
        return bAberto;
    }

    public void setbAberto(boolean bAberto) {
        this.bAberto = bAberto;
    }
    
    public void autoDesenho(){
        super.autoDesenho();
    }
}
