package Controler;

import Modelo.Bau;
import Modelo.Personagem;
import Modelo.Box;
import Modelo.Heart;
import Modelo.Cobrinha;
import Modelo.Hero;
import Modelo.Dino;
import Modelo.Bolota;
import Modelo.Agua;
import Modelo.Caveira;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Tela extends javax.swing.JFrame implements MouseListener, KeyListener {
    
    private Hero hero;
    private ArrayList<Personagem> umaFase;
    
    private ControleDeJogo cj = new ControleDeJogo();
    private Graphics g2;
    private GameState estadoAtual; // Guarda o estado atual da Tela
                                   // Isso inclui a fase e corações pegos (?)
                                   // Poderá incluir a posição do heroi também

    // PONTOS IMPORTANTES (LEIA BOMBZZ):

    // PRINCIPAIS PONTOS Q FALTAM:
    // - Deixar a caveira parada até abrir o baú (senão literalmente fica injogável): check
    // - Caveira morrer para a esfera do herói (senão literalmente fica injogável tbm): check
    // - Bolota ser implemetado: check porem bolota ta meio burro ainda
    // - Tentar consertar a implementação da caixa q está sujeito a alguns bugs principalmente na fase 4: check
    //      - Como a implementação é de um "vai e vem" o personagem pode empurrar a caixa pra dentro do coração e mesmo assim coletá-lo, tornando a fase
    //      4 bem mais fácil (aconteceu isso quando pedi pra gabi e meus primos jogarem)
    //      - O ideal seria se a implementação, ao invés de fazer o vai e vem, não deixasse o jogador empurrar o coração em cima de nenhum objeto, mesmo q ele seja transponível
    //      - As caixas podem ser empurradas dentro de outras caixas, daí fazendo o de cima, resolveria isso tbm
    // - Fazer o Diagrama UML do Projeto


    // (SOLVED) - Um bug estranho no baú, que volta com a imagem da pérola (msm dps de ser coletada) quando na 4ta fase o jogador anda em direção a linha 0
    // (SOLVED) - Personagem entrando debaixo do bau (detalhe)
    // (SOLVED) - Uma implementação mais completa do respawn do heroi, fazendo com que os corações se reposicionem e zere o contador interno do heroi (nHearts)
    // (SOLVED) - Implementação de avisos no jogo
    // (SOLVED) - Implementação de uma forma de reiniciar a fase (tecla r)
    // (SOLVED) - Implementação de salvar uma fase (tecla x)
    // (SOLVED) - Implementação de carregar uma fase com o .ser
    
    public Tela() {
        Desenho.setCenario(this);
        initComponents();
        this.addMouseListener(this); /*mouse*/

        this.addKeyListener(this);   /*teclado*/
        /*Cria a janela do tamanho do tabuleiro + insets (bordas) da janela*/
        this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

        // Instancia o ArrayList que representa a fase atual
        umaFase = new ArrayList<Personagem>();
        
        // Temos que dar load no GameState Salvo
        if(loadGameState() == null) // Se retornar nulo, indica que é a primeira vez, logo deve seguir o fluxo natural do jogo (começar na fase 1)
            estadoAtual = new GameState(umaFase, 1);
        else // Se tiver algo salvo, eu dou load no estado atual (inclui o arraylist e o num da fase)
            estadoAtual = loadGameState();
        
        hero = new Hero("lolo.png", estadoAtual.getFaseAtual());
        // Constrói a fase atual baseado no arraylist salvo
        constroiFaseAtual(estadoAtual.getFaseAtual());
        
        System.out.println("----- Bem vindo a Las Aventuras de Bombonari! -----");
        System.out.println(" Autores: ");
        System.out.println(" - Guilherme Augusto Fincatti da Silva");
        System.out.println(" - Lucas Lima Romero\n");
        System.out.println("W A S D: Movimentos");
        System.out.println("SPACE  : Ejetar a esfera de forca");
        System.out.println("X      : Salvamento do Progresso");
        System.out.println("R      : Reiniciar a Fase");
        System.out.println("MOUSE  : Movimento do personagem para desenvolvedor");
    }
    
    public void constroiFaseAtual(int faseAtual){
        switch (faseAtual){
            case 1:
                constroiFase1();
                break;
            case 2:
                constroiFase2();
                break;
            case 3:
                constroiFase3();
                break;
            case 4:
                constroiFase4();
                break;
            default:
                break;
        }
    }
    
    public void constroiFase1(){
        hero.setPosicao(1, 7);
        this.addPersonagem(hero);

        // Criando a porta da fase 1
        Cenario porta1 = new Cenario("porta.png");
        porta1.setPosicao(0,7);
        this.addPersonagem(porta1);
        
        Bau bau1 = new Bau("bau.png");
        bau1.setPosicao(10,5);
        this.addPersonagem(bau1);
        
        criaMuros(umaFase, 1);
        
        int[][] matriz1 = {
            {2, 2, 2, 2, 2, 2, 0, 2, 2, 1, 1},
            {2, 1, 1, 2, 5, 0, 0, 2, 2, 1, 1},
            {0, 1, 1, 2, 2, 2, 0, 2, 2, 2, 1},
            {0, 0, 1, 1, 2, 2, 0, 2, 2, 2, 1},
            {0, 0, 0, 0, 2, 2, 0, 2, 2, 1, 5},
            {0, 0, 0, 0, 0, 0, 7, 0, 2, 0, 0},
            {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 1, 1, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0},
            {2, 1, 1, 2, 0, 0, 0, 0, 1, 1, 0},
            {2, 2, 2, 2, 2, 2, 0, 0, 0, 0, 0}
        };
        
        criaCenarioFase(matriz1);
        
    }
    
    public void constroiFase2(){
        hero.setPosicao(11, 5);
        this.addPersonagem(hero);
        
        Cenario porta2 = new Cenario("porta.png");
        porta2.setPosicao(0,10);
        this.addPersonagem(porta2);
        
        Bau bau2 = new Bau("bau.png");
        bau2.setPosicao(6,1);
        this.addPersonagem(bau2);

        criaMuros(umaFase, 2);
        
        int[][] matriz2 = {
            {0, 0, 0, 8, 0, 2, 2, 2, 0, 0, 0},
            {0, 5, 0, 0, 0, 2, 2, 2, 0, 5, 0},
            {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {3, 3, 3, 3, 4, 3, 3, 3, 3, 4, 3},
            {3, 3, 3, 3, 4, 3, 3, 3, 3, 4, 3},
            {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 3},
            {0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 3},
            {0, 0, 0, 0, 0, 2, 2, 1, 1, 0, 3},
            {8, 0, 0, 0, 0, 2, 2, 5, 0, 0, 3},
            {0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 3},
            {5, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3}
        };
        
        criaCenarioFase(matriz2);
    }
    
    public void constroiFase3(){
        hero.setPosicao(9, 7);
        this.addPersonagem(hero);
        
        Cenario porta3 = new Cenario("porta.png");
        porta3.setPosicao(0,6);
        this.addPersonagem(porta3);
        
        Bau bau3 = new Bau("bau.png");
        bau3.setPosicao(5,3);
        this.addPersonagem(bau3);

        Cenario ponte = new Cenario("ponte_lateral.png");
        ponte.setPosicao(6, 2);
        ponte.setbTransponivel(true);
        this.addPersonagem(ponte);
        
        criaMuros(umaFase, 3);
        
        int[][] matriz3 = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 3, 3, 3, 0},
            {0, 3, 1, 1, 1, 1, 5, 3, 3, 3, 0},
            {0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0},
            {0, 3, 0, 1, 0, 7, 0, 1, 0, 0, 0},
            {0, 0, 0, 2, 0, 2, 0, 1, 0, 0, 0},
            {0, 3, 0, 2, 0, 2, 5, 0, 0, 0, 0},
            {0, 3, 0, 2, 0, 2, 1, 3, 3, 3, 0},
            {0, 3, 0, 1, 5, 0, 0, 3, 3, 3, 0},
            {0, 3, 4, 3, 3, 3, 3, 3, 3, 3, 5},
            {0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 1}
        };
        
        criaCenarioFase(matriz3);
        
        Bolota b4 = new Bolota("Bolota.png", hero, estadoAtual.getFaseAtual());
        b4.setPosicao(11, 7);
        this.addPersonagem(b4);
    }
    
    public void constroiFase4(){
        hero.setPosicao(11, 6);
        this.addPersonagem(hero);
        
        Cenario porta4 = new Cenario("porta.png");
        porta4.setPosicao(0,6);
        this.addPersonagem(porta4);
        
        Bau bau4 = new Bau("bau.png");
        bau4.setPosicao(6,5);
        this.addPersonagem(bau4);
        
        Caveira c1 = new Caveira("caveira.png", 'h');
        c1.setPosicao(1, 4);
        this.addPersonagem(c1);

        Caveira c2 = new Caveira("caveira.png", 'h');
        c2.setPosicao(11, 4);
        this.addPersonagem(c2);

        Caveira c3 = new Caveira("caveira.png", 'v');
        c3.setPosicao(6, 9);
        this.addPersonagem(c3);

        criaMuros(umaFase, 4);
        
        int[][] matriz4 = {
            {0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0},
            {0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0},
            {0, 6, 0, 0, 0, 0, 0, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0},
            {5, 3, 3, 3, 3, 0, 2, 5, 0, 6, 0},
            {4, 3, 3, 3, 0, 0, 2, 5, 0, 6, 0},
            {5, 3, 3, 3, 3, 0, 2, 5, 0, 6, 0},
            {0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0},
            {0, 6, 0, 0, 0, 0, 0, 0, 0, 2, 0},
            {0, 2, 2, 2, 2, 2, 2, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0}
        };
        
        criaCenarioFase(matriz4);
    }
    
    private GameState loadGameState() { // Retomando
        try (FileInputStream fileIn = new FileInputStream("savegame.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn)) {
            
            GameState gameState = (GameState) in.readObject();
            return gameState; // Retorna o estado que o jogo parou (arraylist e a fase)
            
        } catch (FileNotFoundException | ClassNotFoundException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Vamos salvar a fase
    // Para isso, enviaremos o estadoAtual para ela
    public void saveGameState(GameState estadoAtual) { // Salvando o estado do jogo em um arquivo
        try (FileOutputStream fileOut = new FileOutputStream("savegame.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            
            // Aqui se escreve no arquivo o último estado atual (tecla x)
            out.writeObject(estadoAtual);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                this.addPersonagem(wha);
                if(fase == 1 && i == 7){ // Se for a primeira fase
                    this.removePersonagem(wha);
                }
                else if(fase == 2 && i == 10){
                    this.removePersonagem(wha);
                }
                else if(fase == 3 && i == 6){
                    this.removePersonagem(wha);
                }
                else if(fase == 4 && i == 6){
                    this.removePersonagem(wha);                   
                }
                
                Cenario wve = new Cenario("brick-lateral.png");
                wve.setPosicao(i, 0);
                this.addPersonagem(wve);
            }
            
            this.addPersonagem(wvd);
            this.addPersonagem(whb);
        }
    }

    public boolean ehPosicaoValida(Posicao p){
        return cj.ehPosicaoValida(this.umaFase, p);
    }
        
    public void addPersonagem(Personagem umPersonagem) {
        umaFase.add(umPersonagem);
    }

    public void removePersonagem(Personagem umPersonagem) {
        umaFase.remove(umPersonagem);
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



        // Se trocou a fase, o array "umaFase" deverá ser atualizado para a fase correspondente
        if(estadoAtual.isTrocouFase()){ // Se trocou de fase, preciso limpar o array atual e atualizar
            umaFase.clear();
            constroiFaseAtual(estadoAtual.getFaseAtual());
            estadoAtual.setTrocouFase(false);
        }
        this.cj.desenhaTudo(umaFase);
        this.cj.processaTudo(umaFase, estadoAtual);

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
    
    public void criaCenarioFase(int [][]matriz){
        // Colocando as árvores da primeira fase (hard coding)
        
        for(int i = 0; i < Consts.RES - 2; i++){
            for(int j = 0; j < Consts.RES - 2; j++){
                if(matriz[i][j] == 1){
                    Cenario arvore = new Cenario("arvore.png");
                    arvore.setPosicao(i+1,j+1);
                    this.addPersonagem(arvore);
                }
                if(matriz[i][j] == 2){
                    Cenario arbusto = new Cenario("arbusto.png");
                    arbusto.setPosicao(i+1,j+1);
                    this.addPersonagem(arbusto);
                }
                if(matriz[i][j] == 3){
                    Cenario agua = new Cenario("agua.png");
                    agua.setPosicao(i+1,j+1);
                    this.addPersonagem(agua);
                }
                if(matriz[i][j] == 4){
                    Cenario ponte = new Cenario("ponte.png");
                    ponte.setPosicao(i+1, j+1);
                    ponte.setbTransponivel(true);
                    this.addPersonagem(ponte);
                }
                if(matriz[i][j] == 5){
                    Heart heart = new Heart("coracao.png");
                    heart.setPosicao(i+1,j+1);
                    this.addPersonagem(heart);
                }
                if(matriz[i][j] == 6){
                    Box box = new Box("box.png");
                    box.setPosicao(i+1,j+1);
                    this.addPersonagem(box);
                }
                if(matriz[i][j] == 7){
                    Cobrinha cobrinha = new Cobrinha("cobrinha.png");
                    cobrinha.setPosicao(i+1,j+1);
                    this.addPersonagem(cobrinha);
                }
                if(matriz[i][j] == 8){
                    Dino dino = new Dino("dino-bravo.png", hero, estadoAtual.getFaseAtual());
                    dino.setPosicao(i+1, j+1);
                    this.addPersonagem(dino);
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)
            hero.moveUp();
        else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S)
            hero.moveDown();
        else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A)
            hero.moveLeft();
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D)
            hero.moveRight();
        else if (e.getKeyCode() == KeyEvent.VK_SPACE)
            hero.shootEsfera(hero.getLastMovement());
        else if(e.getKeyCode() == KeyEvent.VK_X){
            saveGameState(this.estadoAtual);
            System.out.println("Progresso salvo - Fase " + estadoAtual.getFaseAtual());
        }
        else if(e.getKeyCode() == KeyEvent.VK_R){
            // Reinicia a fase
            estadoAtual.reiniciaFase(hero);
            System.out.println("Reiniciando... - Fase " + estadoAtual.getFaseAtual());
        }

        this.setTitle("-> Cell: " + (hero.getPosicao().getColuna()) + ", " + (hero.getPosicao().getLinha()));

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
