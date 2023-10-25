package Controler;


import Modelo.Personagem;
import Modelo.Box;
import Modelo.Heart;
import Modelo.Caveira;
import Modelo.Cobrinha;
import Modelo.Hero;
import Modelo.BichinhoVaiVemHorizontal;
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
        hero = new Hero("lolo.png");
        hero.setPosicao(1, 7);
        this.addPersonagem(hero);
        /*
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
        bV.setPosicao(11, 9);
        this.addPersonagem(bV);
        */
        
        for(int i = 0; i < Consts.RES; i++){
            Wallbricks whb = new Wallbricks("brick-baixo.png");
            Wallbricks wvd = new Wallbricks("brick-lateral.png");   
            
            whb.setPosicao(Consts.RES - 1, i);
            wvd.setPosicao(i, Consts.RES - 1);
            if(i != Consts.RES - 1) {
                Wallbricks wha = new Wallbricks("brick-cima.png");
                wha.setPosicao(0, i);
                this.addPersonagem(wha);
                
                Wallbricks wve = new Wallbricks("brick-lateral.png");
                wve.setPosicao(i, 0);
                this.addPersonagem(wve);
            }
            
            this.addPersonagem(wvd);
            this.addPersonagem(whb);
        }
        
        Wallbricks porta = new Wallbricks("porta.png");
        porta.setPosicao(0,7);
        this.addPersonagem(porta);
        // Posição da porta na primeira fase: (0,7)
        arbustosPrimeiraFase();
        arvoresPrimeiraFase();

        /*
        for(int i = 0; i < (int) Consts.RES/2; i++){;;;;;;;;;;
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
        }*/
        Cobrinha cobra = new Cobrinha("cobrinha.png");
        cobra.setPosicao(6, 7);
        this.addPersonagem(cobra);
        
        Heart h1 = new Heart("coracao.png");
        h1.setPosicao(2, 5);
        this.addPersonagem(h1);
        
        Heart h2 = new Heart("coracao.png");
        h2.setPosicao(5, 11);
        this.addPersonagem(h2);
        
        Box b1 = new Box("box.png");
        b1.setPosicao(6,2);
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
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "stone-brick2.png");
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
            hero.setImage("lolo-up.png");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hero.moveDown();
            hero.setLastMovement('d');
            hero.setImage("lolo.png");
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hero.moveLeft();
            hero.setLastMovement('l');
            hero.setImage("lolo-left.png");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hero.moveRight();
            hero.setLastMovement('r');
            hero.setImage("lolo-right.png");
        } else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(hero.getLastMovement() == 'u'){
                hero.shootU();
            }
            else if(hero.getLastMovement() == 'd'){
                hero.shootD();
            }
            else if(hero.getLastMovement() == 'l'){
                hero.shootL();
            }
            else if(hero.getLastMovement() == 'r'){
                hero.shootR();
            }
        }

        this.setTitle("-> Cell: " + (hero.getPosicao().getColuna()) + ", "
                + (hero.getPosicao().getLinha()));

        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }
    
    public void arvoresPrimeiraFase(){
        // Colocando as árvores da primeira fase (hard coding)
        
        int[][] matriz = new int[11][11];
        matriz[0][9] = matriz[0][10] = 1;
        matriz[1][1] = matriz[1][2] = matriz[1][9] = matriz[1][10] = 1;
        matriz[2][1] = matriz[2][2] = matriz[2][10] = 1;
        matriz[3][2] = matriz[3][3] = matriz[3][10] = 1;
        matriz[4][9] = 1;
        matriz[6][1] = matriz[6][2] = 1;
        matriz[7][0] = matriz[7][1] = matriz[7][2] = matriz[7][3] = matriz[7][7] = matriz[7][8] = 1;
        matriz[8][0] = matriz[8][1] = matriz[8][2] = matriz[8][3] = matriz[8][7] = matriz[8][8] = matriz[8][9] = 1;
        matriz[9][1] = matriz[9][2] = matriz[9][8] = matriz[9][9] = 1;
        
        for(int i = 0; i < Consts.RES - 2; i++){
            for(int j = 0; j < Consts.RES - 2; j++){
                if(matriz[i][j] == 1){
                    Wallbricks arvore = new Wallbricks("arvore.png");
                    arvore.setPosicao(i+1,j+1);
                    this.addPersonagem(arvore);
                }
            }
        }
    }

    public void arbustosPrimeiraFase(){
        // Colocando os arbustos da primeira fase

        int[][] matriz = new int[11][11];
        matriz[0][0] = matriz[0][1] = matriz[0][2] = matriz[0][3] = matriz[0][4] = matriz[0][5] = matriz[0][7] = matriz[0][8] = 1;
        matriz[1][0] = matriz[1][3] = matriz[1][7] = matriz[1][8] = 1;
        matriz[2][3] = matriz[2][4] = matriz[2][5] = matriz[2][7] = matriz[2][8] = matriz[2][9] = 1;
        matriz[3][4] = matriz[3][5] = matriz[3][7] = matriz[3][8] = matriz[3][9] = 1;
        matriz[4][4] = matriz[4][5] = matriz[4][7] = matriz[4][8] = 1;
        matriz[5][8] = 1;
        matriz[9][0] = matriz[9][3] = 1;
        matriz[10][0] = matriz[10][1] = matriz[10][2] = matriz[10][3] = matriz[10][4] = matriz[10][5] = 1;
        
        for(int i = 0; i < Consts.RES - 2; i++){
            for(int j = 0; j < Consts.RES - 2; j++){
                if(matriz[i][j] == 1){
                    Wallbricks arbusto = new Wallbricks("arbusto.png");
                    arbusto.setPosicao(i+1,j+1);
                    this.addPersonagem(arbusto);
                }
            }
        }
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
