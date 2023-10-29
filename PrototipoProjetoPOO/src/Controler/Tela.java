package Controler;

import Modelo.Bau;
import Modelo.Personagem;
import Modelo.Box;
import Modelo.Heart;
import Modelo.Cobrinha;
import Modelo.Hero;
import Modelo.Dino;
import Modelo.Agua;
import Modelo.Cenario;
import Auxiliar.Consts;
import Auxiliar.Desenho;
import auxiliar.Posicao;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {
    
    private Hero hero1;
    private Hero hero2;
    private Hero hero3;
    private Hero hero4;
    private ArrayList<Personagem> primeiraFase;
    private ArrayList<Personagem> segundaFase;
    private ArrayList<Personagem> terceiraFase;
    private ArrayList<Personagem> quartaFase;
    
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

        primeiraFase = new ArrayList<Personagem>();
        segundaFase = new ArrayList<Personagem>();
        terceiraFase = new ArrayList<Personagem>();
        quartaFase = new ArrayList<Personagem>();

        // ------------ PRIMEIRA FASE -------------------- //
        hero1 = new Hero("lolo.png", 1);
        hero1.setPosicao(1, 7);
        this.addPersonagem(hero1, 1);

        // Criando a porta da fase 1 (0,7)
        Cenario porta1 = new Cenario("porta.png");
        porta1.setPosicao(0,7);
        this.addPersonagem(porta1, 1);
        
        Bau bau1 = new Bau("bau.png");
        bau1.setPosicao(10,5);
        this.addPersonagem(bau1, 1);

        criaMuros(primeiraFase, 1);

        arvoresArbustosPrimeiraFase();

        Cobrinha cobra = new Cobrinha("cobrinha.png");
        cobra.setPosicao(6, 7);
        this.addPersonagem(cobra, 1);
        
        Heart h1 = new Heart("coracao.png");
        h1.setPosicao(2, 5);
        this.addPersonagem(h1, 1);
        
        Heart h2 = new Heart("coracao.png");
        h2.setPosicao(5, 11);
        this.addPersonagem(h2, 1);
        
        Box b1 = new Box("box.png");
        b1.setPosicao(6,2);
        this.addPersonagem(b1, 1);
        
        Cenario agua = new Cenario("agua.png");
        agua.setPosicao(5,5);
        this.addPersonagem(agua, 1);
        
        // -------------------------------------------- //
        
        // SEGUNDA FASE
        hero2 = new Hero("lolo.png", 2);
        hero2.setPosicao(5, 5);
        this.addPersonagem(hero2, 2);
        
        Cenario porta2 = new Cenario("porta.png");
        porta2.setPosicao(0,10);
        this.addPersonagem(porta2, 2);
        
        Bau bau2 = new Bau("bau.png");
        bau2.setPosicao(6,1);
        this.addPersonagem(bau2, 2);

        Heart h3 = new Heart("coracao.png");
        h3.setPosicao(11, 1);
        this.addPersonagem(h3, 2);

        Heart h4 = new Heart("coracao.png");
        h4.setPosicao(2, 2);
        this.addPersonagem(h4, 2);

        Heart h5 = new Heart("coracao.png");
        h5.setPosicao(2, 10);
        this.addPersonagem(h5, 2);

        Heart h6 = new Heart("coracao.png");
        h6.setPosicao(9, 8);
        this.addPersonagem(h6, 2);

        Box b2 = new Box("box.png");
        b2.setPosicao(10,7);
        this.addPersonagem(b2, 2);

        Dino dino1 = new Dino("pacman.png", hero2, 2);
        dino1.setPosicao(9, 1);
        this.addPersonagem(dino1, 2);

        Dino dino2 = new Dino("pacman.png", hero2, 2);
        dino2.setPosicao(1, 4);
        this.addPersonagem(dino2, 2);

        criaMuros(segundaFase, 2);
        arvoresArbustosSegundaFase();
        

        // -------------------------------------------- //

        // TERCEIRA FASE
        hero3 = new Hero("lolo.png", 3);
        hero3.setPosicao(9, 7);
        this.addPersonagem(hero3, 3);

        Cenario porta3 = new Cenario("porta.png");
        porta3.setPosicao(0,6);
        this.addPersonagem(porta3, 3);
        
        Bau bau3 = new Bau("bau.png");
        bau3.setPosicao(5,3);
        this.addPersonagem(bau3, 3);
        
        Heart h7 = new Heart("coracao.png");
        h7.setPosicao(7, 7);
        this.addPersonagem(h7, 3);

        Heart h8 = new Heart("coracao.png");
        h8.setPosicao(9, 5);
        this.addPersonagem(h8, 3);

        Heart h9 = new Heart("coracao.png");
        h9.setPosicao(10, 11);
        this.addPersonagem(h9, 3);

        Heart h10 = new Heart("coracao.png");
        h10.setPosicao(11, 8);
        this.addPersonagem(h10, 3);
        
        Heart h11 = new Heart("coracao.png");
        h11.setPosicao(3, 7);
        this.addPersonagem(h11, 3);

        criaMuros(terceiraFase, 3);
        arvoresArbustosTerceiraFase();

        // -------------------------------------------- //

        // QUARTA FASE
        hero4 = new Hero("lolo.png", 4);
        hero4.setPosicao(11, 6);
        this.addPersonagem(hero4, 4);
        
        Cenario porta4 = new Cenario("porta.png");
        porta4.setPosicao(0,6);
        this.addPersonagem(porta4, 4);
        
        Bau bau4 = new Bau("bau.png");
        bau4.setPosicao(6,5);
        this.addPersonagem(bau4, 4);
        
        Heart h12 = new Heart("coracao.png");
        h12.setPosicao(5, 1);
        this.addPersonagem(h12, 4);

        Heart h13 = new Heart("coracao.png");
        h13.setPosicao(7, 1);
        this.addPersonagem(h13, 4);

        Heart h14 = new Heart("coracao.png");
        h14.setPosicao(5, 8);
        this.addPersonagem(h14, 4);

        Heart h15 = new Heart("coracao.png");
        h15.setPosicao(6, 8);
        this.addPersonagem(h15, 4);
        
        Heart h16 = new Heart("coracao.png");
        h16.setPosicao(7, 8);
        this.addPersonagem(h16, 4);


        criaMuros(quartaFase, 4);
        arvoresArbustosQuartaFase();
    }
    
    public void criaMuros(ArrayList<Personagem> a, int fase){
        
        for(int i = 0; i < Consts.RES; i++){
            Cenario whb = new Cenario("brick-baixo.png");
            Cenario wvd = new Cenario("brick-lateral.png");
            
            whb.setPosicao(Consts.RES - 1, i);
            wvd.setPosicao(i, Consts.RES - 1);
            if(i != Consts.RES - 1) {
                Cenario wha = new Cenario("brick-cima.png");
                wha.setPosicao(0, i);
                this.addPersonagem(wha, fase);
                if(fase == 1 && i == 7){ // Se for a primeira fase
                    this.removePersonagem(wha, fase);
                }
                else if(fase == 2 && i == 10){
                    this.removePersonagem(wha, fase);
                }
                else if(fase == 3 && i == 6){
                    this.removePersonagem(wha, fase);
                }
                else if(fase == 4 && i == 6){
                    this.removePersonagem(wha, fase);                   
                }
                
                Cenario wve = new Cenario("brick-lateral.png");
                wve.setPosicao(i, 0);
                this.addPersonagem(wve, fase);
            }
            
            this.addPersonagem(wvd, fase);
            this.addPersonagem(whb, fase);
        }
    }

    public boolean ehPosicaoValida(Posicao p){
        if(hero1.getFase() != -1){
            return cj.ehPosicaoValida(this.primeiraFase, p);
        } else if(hero2.getFase() != -1){
            return cj.ehPosicaoValida(this.segundaFase, p);
        } else if(hero3.getFase() != -1){
            return cj.ehPosicaoValida(this.terceiraFase, p);
        } else if(hero4.getFase() != -1){
            return cj.ehPosicaoValida(this.quartaFase, p);
        }
        return false;
    }
    public void addPersonagem(Personagem umPersonagem, int fase) {
        switch (fase) {
            case 1:
                primeiraFase.add(umPersonagem);
                break;
            case 2:
                segundaFase.add(umPersonagem);
                break;
            case 3:
                terceiraFase.add(umPersonagem);
                break;
            case 4:
                quartaFase.add(umPersonagem);
                break;
            default:
                break;
        }
    }

    public void removePersonagem(Personagem umPersonagem, int fase) {
        switch (fase) {
            case 1:
                primeiraFase.remove(umPersonagem);
                break;
            case 2:
                segundaFase.remove(umPersonagem);
                break;
            case 3:
                terceiraFase.remove(umPersonagem);
                break;
            case 4:
                quartaFase.remove(umPersonagem);
                break;
            default:
                break;
        }
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
        if (hero1.getFase() != -1) {
            this.cj.desenhaTudo(primeiraFase);
            this.cj.processaTudo(primeiraFase, 2);
        } else if(hero2.getFase() != -1){
            this.cj.desenhaTudo(segundaFase);
            this.cj.processaTudo(segundaFase, 4);
        } else if(hero3.getFase() != -1){
            this.cj.desenhaTudo(terceiraFase);
            this.cj.processaTudo(terceiraFase, 5);
        } else if(hero4.getFase() != -1){
            this.cj.desenhaTudo(quartaFase);
            this.cj.processaTudo(quartaFase, 5);
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
            this.primeiraFase.clear();
            this.segundaFase.clear();
            this.terceiraFase.clear();
            this.quartaFase.clear();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            if(hero1.getFase() != -1){
                hero1.moveUp();
                hero1.setLastMovement('u');
                hero1.setImage("lolo-up.png");
            } else if(hero2.getFase() != -1){
                hero2.moveUp();
                hero2.setLastMovement('u');
                hero2.setImage("lolo-up.png");
            } else if(hero3.getFase() != -1){
                hero3.moveUp();
                hero3.setLastMovement('u');
                hero3.setImage("lolo-up.png");
            } else if(hero4.getFase() != -1){
                hero4.moveUp();
                hero4.setLastMovement('u');
                hero4.setImage("lolo-up.png");
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if(hero1.getFase() != -1){
                hero1.moveDown();
                hero1.setLastMovement('d');
                hero1.setImage("lolo.png");
            } else if(hero2.getFase() != -1){
                hero2.moveDown();
                hero2.setLastMovement('d');
                hero2.setImage("lolo.png");
            } else if(hero3.getFase() != -1){
                hero3.moveDown();
                hero3.setLastMovement('d');
                hero3.setImage("lolo.png");
            } else if(hero4.getFase() != -1){
                hero4.moveDown();
                hero4.setLastMovement('d');
                hero4.setImage("lolo.png");
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if(hero1.getFase() != -1){
                hero1.moveLeft();
                hero1.setLastMovement('l');
                hero1.setImage("lolo-left.png");
            } else if(hero2.getFase() != -1){
                hero2.moveLeft();
                hero2.setLastMovement('l');
                hero2.setImage("lolo-left.png");
            } else if(hero3.getFase() != -1){
                hero3.moveLeft();
                hero3.setLastMovement('l');
                hero3.setImage("lolo-left.png");
            } else if(hero4.getFase() != -1){
                hero4.moveLeft();
                hero4.setLastMovement('l');
                hero4.setImage("lolo-left.png");
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(hero1.getFase() != -1){
                hero1.moveRight();
                hero1.setLastMovement('r');
                hero1.setImage("lolo-right.png");
            } else if(hero2.getFase() != -1){
                hero2.moveRight();
                hero2.setLastMovement('r');
                hero2.setImage("lolo-right.png");
            } else if(hero3.getFase() != -1){
                hero3.moveRight();
                hero3.setLastMovement('r');
                hero3.setImage("lolo-right.png");
            } else if(hero4.getFase() != -1){
                hero4.moveRight();
                hero4.setLastMovement('r');
                hero4.setImage("lolo-right.png");
            }
        } else if(e.getKeyCode() == KeyEvent.VK_SPACE){
            if(hero1.getFase() != -1){
                if(hero1.getLastMovement() == 'u'){
                    hero1.shootU();
                }
                else if(hero1.getLastMovement() == 'd'){
                    hero1.shootD();
                }
                else if(hero1.getLastMovement() == 'l'){
                    hero1.shootL();
                }
                else if(hero1.getLastMovement() == 'r'){
                    hero1.shootR();
                }
            } else if(hero2.getFase() != -1){
                if(hero2.getLastMovement() == 'u'){
                    hero2.shootU();
                }
                else if(hero2.getLastMovement() == 'd'){
                    hero2.shootD();
                }
                else if(hero2.getLastMovement() == 'l'){
                    hero2.shootL();
                }
                else if(hero2.getLastMovement() == 'r'){
                    hero2.shootR();
                }
            } else if(hero3.getFase() != -1){
                if(hero3.getLastMovement() == 'u'){
                    hero3.shootU();
                }
                else if(hero3.getLastMovement() == 'd'){
                    hero3.shootD();
                }
                else if(hero3.getLastMovement() == 'l'){
                    hero3.shootL();
                }
                else if(hero3.getLastMovement() == 'r'){
                    hero3.shootR();
                }
            } else if(hero4.getFase() != -1){
                if(hero4.getLastMovement() == 'u'){
                    hero4.shootU();
                }
                else if(hero4.getLastMovement() == 'd'){
                    hero4.shootD();
                }
                else if(hero4.getLastMovement() == 'l'){
                    hero4.shootL();
                }
                else if(hero4.getLastMovement() == 'r'){
                    hero4.shootR();
                }
            }
        }

        if(hero1.getFase() != -1){
            this.setTitle("-> Cell: " + (hero1.getPosicao().getColuna()) + ", "
                    + (hero1.getPosicao().getLinha()));
        } else if(hero2.getFase() != -1){
            this.setTitle("-> Cell: " + (hero2.getPosicao().getColuna()) + ", "
                    + (hero2.getPosicao().getLinha()));
        } else if(hero3.getFase() != -1){
            this.setTitle("-> Cell: " + (hero3.getPosicao().getColuna()) + ", "
                    + (hero3.getPosicao().getLinha()));
        } else if(hero4.getFase() != -1){
            this.setTitle("-> Cell: " + (hero4.getPosicao().getColuna()) + ", "
                    + (hero4.getPosicao().getLinha()));
        }

        //repaint(); /*invoca o paint imediatamente, sem aguardar o refresh*/
    }
    
    public void arvoresArbustosPrimeiraFase(){
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
        
        // Arbustos
        matriz[0][0] = matriz[0][1] = matriz[0][2] = matriz[0][3] = matriz[0][4] = matriz[0][5] = matriz[0][7] = matriz[0][8] = 2;
        matriz[1][0] = matriz[1][3] = matriz[1][7] = matriz[1][8] = 2;
        matriz[2][3] = matriz[2][4] = matriz[2][5] = matriz[2][7] = matriz[2][8] = matriz[2][9] = 2;
        matriz[3][4] = matriz[3][5] = matriz[3][7] = matriz[3][8] = matriz[3][9] = 2;
        matriz[4][4] = matriz[4][5] = matriz[4][7] = matriz[4][8] = 2;
        matriz[5][8] = 2;
        matriz[9][0] = matriz[9][3] = 2;
        matriz[10][0] = matriz[10][1] = matriz[10][2] = matriz[10][3] = matriz[10][4] = matriz[10][5] = 2;
        
        for(int i = 0; i < Consts.RES - 2; i++){
            for(int j = 0; j < Consts.RES - 2; j++){
                if(matriz[i][j] == 1){
                    Cenario arvore = new Cenario("arvore.png");
                    arvore.setPosicao(i+1,j+1);
                    this.addPersonagem(arvore, 1);
                }
                if(matriz[i][j] == 2){
                    Cenario arbusto = new Cenario("arbusto.png");
                    arbusto.setPosicao(i+1,j+1);
                    this.addPersonagem(arbusto, 1);
                }
            }
        }
    }

    public void arvoresArbustosSegundaFase(){
        // Colocando as árvores da primeira fase (hard coding)

        int[][] matriz = new int[11][11];
        matriz[2][6] = matriz[2][7] = 1;
        matriz[5][2] = matriz[5][3] = 1;
        matriz[6][2] = matriz[6][3] = matriz[6][7] = matriz[6][8] = 1;
        matriz[7][7] = matriz[7][8] = 1;
        
        matriz[0][5] = matriz[0][6] = matriz[0][7] = 2;
        matriz[1][5] = matriz[1][6] = matriz[1][7] = 2;
        matriz[7][5] = matriz[7][6] = 2;
        matriz[8][5] = matriz[8][6] = 2;

        for(int i = 0; i < Consts.RES - 2; i++){
            for(int j = 0; j < Consts.RES - 2; j++){
                if(matriz[i][j] == 1){
                    Cenario arvore = new Cenario("arvore.png");
                    arvore.setPosicao(i+1,j+1);
                    this.addPersonagem(arvore, 2);
                }
                if(matriz[i][j] == 2){
                    Cenario arbusto = new Cenario("arbusto.png");
                    arbusto.setPosicao(i+1,j+1);
                    this.addPersonagem(arbusto, 2);
                }
            }
        }
    }
    
    public void arvoresArbustosTerceiraFase(){
        // Colocando as árvores da primeira fase (hard coding)

        int[][] matriz = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 3, 3, 3, 0},
            {0, 3, 1, 1, 1, 1, 0, 3, 3, 3, 0},
            {0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0},
            {0, 3, 0, 1, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 2, 0, 2, 0, 1, 0, 0, 0},
            {0, 3, 0, 2, 0, 2, 0, 0, 0, 0, 0},
            {0, 3, 0, 2, 0, 2, 1, 3, 3, 3, 0},
            {0, 3, 0, 1, 0, 0, 0, 3, 3, 3, 0},
            {0, 3, 0, 3, 3, 3, 3, 3, 3, 3, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}
        };

        for(int i = 0; i < Consts.RES - 2; i++){
            for(int j = 0; j < Consts.RES - 2; j++){
                if(matriz[i][j] == 1){
                    Cenario arvore = new Cenario("arvore.png");
                    arvore.setPosicao(i+1,j+1);
                    this.addPersonagem(arvore, 3);
                }
                if(matriz[i][j] == 2){
                    Cenario arbusto = new Cenario("arbusto.png");
                    arbusto.setPosicao(i+1,j+1);
                    this.addPersonagem(arbusto, 3);
                }
                if(matriz[i][j] == 3){
                    Cenario agua = new Cenario("agua.png");
                    agua.setPosicao(i+1,j+1);
                    this.addPersonagem(agua, 3);
                }
            }
        }
    }
    
    public void arvoresArbustosQuartaFase(){
        // Colocando as árvores da primeira fase (hard coding)

        int[][] matriz = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0},
            {0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0},
            {0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        for(int i = 0; i < Consts.RES - 2; i++){
            for(int j = 0; j < Consts.RES - 2; j++){
                if(matriz[i][j] == 2){
                    Cenario arbusto = new Cenario("arbusto.png");
                    arbusto.setPosicao(i+1,j+1);
                    this.addPersonagem(arbusto, 4);
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

        if(hero1.getFase() != -1){
            this.hero1.getPosicao().setPosicao(y/Consts.CELL_SIDE, x/Consts.CELL_SIDE);
        } else if(hero2.getFase() != -1){
            this.hero2.getPosicao().setPosicao(y/Consts.CELL_SIDE, x/Consts.CELL_SIDE);
        }
        else if(hero3.getFase() != -1){
            this.hero3.getPosicao().setPosicao(y/Consts.CELL_SIDE, x/Consts.CELL_SIDE);
        }
        else if(hero4.getFase() != -1){
            this.hero4.getPosicao().setPosicao(y/Consts.CELL_SIDE, x/Consts.CELL_SIDE);
        }

         
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
