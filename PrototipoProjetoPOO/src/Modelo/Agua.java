/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Auxiliar.Consts;
import java.io.Serializable;
import Auxiliar.Desenho;


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

        this.iContaIntervalos++;
        if(this.iContaIntervalos == Consts.TIMERFOGO){
            if(this.getImage() == "agua.png"){
                this.setImage("agua2.png");
            }
            if(this.getImage() == "agua2.png"){
                this.setImage("agua.png");
            }
            this.iContaIntervalos = 0;
        }
    }
    
}
