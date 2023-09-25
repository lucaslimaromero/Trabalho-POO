package Controler;


import Modelo.Personagem;
import Modelo.Box;
import Modelo.Heart;
import Modelo.Caveira;
import Modelo.Hero;
import Modelo.BichinhoVaiVemHorizontal;
import Modelo.Pacman;
import Modelo.Wallbricks;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import Modelo.ZigueZague;
import auxiliar.Posicao;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.swing.JButton;


public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {
    
    private Hero hero;
    private ArrayList<Personagem> faseAtual;
    
    private ControleDeJogo cj = new ControleDeJogo();
    private Graphics g2;

    public Tela() {
        Desenho.setCenario(this);
        initComponents();
        this.addMouseListener(this); /*mouse*/

        this.addKeyListener(this);   /*teclado*/
        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

        faseAtual = new ArrayList<Personagem>();

        /*Cria faseAtual adiciona personagens*/
        hero = new Hero("skoot.png");
        hero.setPosicao(1, 7);
        this.addPersonagem(hero);
        
        ZigueZague zz = new ZigueZague("robo.png");
        zz.setPosicao(11, 9);
        this.addPersonagem(zz);

        BichinhoVaiVemHorizontal bBichinhoH = new BichinhoVaiVemHorizontal("roboPink.png");
        bBichinhoH.setPosicao(11, 5);
        this.addPersonagem(bBichinhoH);

        BichinhoVaiVemHorizontal bBichinhoH2 = new BichinhoVaiVemHorizontal("roboPink.png");
        bBichinhoH2.setPosicao(11, 3);
        this.addPersonagem(bBichinhoH2);

        Caveira bV = new Caveira("caveira.png");
        bV.setPosicao(8, 1);
        this.addPersonagem(bV);
        
        /*Pacman pac = new Pacman("Pacman.png");
        pac.setPosicao(5, 5);
        this.addPersonagem(pac);*/
        
        for(int i = 0; i < Consts.RES; i++){
            Wallbricks whb = new Wallbricks("Wallbricks.png");
            Wallbricks wvd = new Wallbricks("Wallbricks.png");
            Wallbricks wha = new Wallbricks("Wallbricks.png");
            Wallbricks wve = new Wallbricks("Wallbricks.png");
            whb.setPosicao(Consts.RES - 1, i);
            wvd.setPosicao(i, Consts.RES - 1);
            wha.setPosicao(0, i);
            wve.setPosicao(i, 0);
            this.addPersonagem(whb);
            this.addPersonagem(wvd);
            this.addPersonagem(wha);
            this.addPersonagem(wve);
        }
        
        for(int i = 0; i < (int) Consts.RES/2; i++){
            Wallbricks we = new Wallbricks("Wallbricks.png");
            Wallbricks wd = new Wallbricks("Wallbricks.png");
            we.setPosicao(7, i);
            wd.setPosicao(7, Consts.RES - i - 1);
            this.addPersonagem(we);
            this.addPersonagem(wd);
        }
        
        for(int i = 0; i < (int) (Consts.RES/2) - 2; i++){
            Wallbricks we = new Wallbricks("Wallbricks.png");
            Wallbricks wd = new Wallbricks("Wallbricks.png");
            we.setPosicao(9, i);
            wd.setPosicao(9, Consts.RES - i - 1);
            this.addPersonagem(we);
            this.addPersonagem(wd);
        }
        
        Heart h1 = new Heart("coracao.png");
        h1.setPosicao(11, 7);
        this.addPersonagem(h1);
        
        Box b1 = new Box("box.png");
        b1.setPosicao(2,2);
        this.addPersonagem(b1);
    }

    public boolean ehPosicaoValida(Posicao p){
        return cj.ehPosicaoValida(this.faseAtual, p);
    }
    public void addPersonagem(Personagem umPersonagem) {
        faseAtual.add(umPersonagem);
    }

    public void removePersonagem(Personagem umPersonagem) {
        faseAtual.remove(umPersonagem);
    }

    public Graphics getGraphicsBuffer(){
        return g2;
    }
    public void paint(Graphics gOld) {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        /*Criamos um contexto gráfico*/
        g2 = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);
        /*************Desenha cenário de fundo**************/
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "bricks.png");
                    g2.drawImage(newImage,
                            j * Consts.CELL_SIDE, i * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (!this.faseAtual.isEmpty()) {
            this.cj.desenhaTudo(faseAtual);
            this.cj.processaTudo(faseAtual);
        }

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    public void go() {
        TimerTask task = new TimerTask() {
            public void run() {
                repaint();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0, Consts.PERIOD);
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_C) {
            this.faseAtual.clear();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            hero.moveUp();
            hero.setLastMovement('u');
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.moveDown();
            hero.setLastMovement('d');
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.moveLeft();
            hero.setLastMovement('l');
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.moveRight();
            hero.setLastMovement('r');
        }

        this.setTitle("-> Cell: " + (hero.getPosicao().getColuna()) + ", "
                + (hero.getPosicao().getLinha()));

        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }

    public void mousePressed(MouseEvent e) {
        /* Clique do mouse desligado*/
         int x = e.getX();
         int y = e.getY();
     
         this.setTitle("X: "+ x + ", Y: " + y +
         " -> Cell: " + (y/Consts.CELL_SIDE) + ", " + (x/Consts.CELL_SIDE));
        
         this.hero.getPosicao().setPosicao(y/Consts.CELL_SIDE, x/Consts.CELL_SIDE);
         
        repaint();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2023-1 - Skooter");
        setAlwaysOnTop(true);
        setAutoRequestFocus(false);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
