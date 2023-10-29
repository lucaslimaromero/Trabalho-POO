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
public class Agua extends Personagem implements Serializable {
    private int iContaFrames;

    public Agua(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.iContaFrames = 0;
        this.bixo = false;
    }
    
    public void autoDesenho(){
        
        this.iContaFrames++;
        if(this.iContaFrames % 2000 == 0){
            if(this.getImage() == "agua.png"){
                this.setImage("agua2.png");
            }
            else{
                this.setImage("agua.png");
            }
        }
        
        super.autoDesenho();
    }    

    public int getiContaFrames() {
        return iContaFrames;
    }

    public void setiContaFrames(int iContaFrames) {
        this.iContaFrames = iContaFrames;
    }
    
}
