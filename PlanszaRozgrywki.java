package grazdobadznagrode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PlanszaRozgrywki extends JPanel implements KeyListener {
    Random rand = new Random();
    ImageIcon[] ix = new ImageIcon[5];
    //i[0] = new ImageIcon("C:\\Java\\Snakee\\src\\snakee\\snake.png");
    //Image image = i[0].getImage();
    ImageIcon i = new ImageIcon("C:\\Java\\Snakee\\src\\snakee\\snake.png");
    Image image = i.getImage();
    ImageIcon i2 = new ImageIcon("C:\\Java\\Snakee\\src\\snakee\\snake2.png");
    Image image2 = i2.getImage();
    ImageIcon i3 = new ImageIcon("C:\\Java\\Snakee\\src\\snakee\\snake3.png");
    Image image3 = i3.getImage();
    ImageIcon iN = new ImageIcon("C:\\Java\\Snakee\\src\\snakee\\nagroda.png");
    Image imageN = iN.getImage();
    ImageIcon iT = new ImageIcon("C:\\Java\\Snakee\\src\\snakee\\tlo.png");
    Image imageT = iT.getImage();
    int[] x = new int[8];
    int[] y = new int[8];
    int[] polozenieX = new int[8];
    int[] polozenieY = new int[8];

    int punkty = 0;
    int a = 0;
    int b = 0;
    Licznik licznik;

    String string = Integer.toString(punkty);
    JTextField text = new JTextField(string);
    boolean przegrana = false;
    boolean start = false;
    String poziomTrudnosci = "Latwy";
    int keyCode;

    public PlanszaRozgrywki() {
        for (int i = 1; i <= 4; i++) {
            x[i] = rand.nextInt(19) + 1;
            polozenieX[i] = x[i] * 50;
            y[i] = rand.nextInt(12) + 1;
            polozenieY[i] = y[i] * 50;
        }

        odswiezPunktacje();
        licznik = new Licznik();

    }

    public void sprawdzPrzegrana() {
        if (punkty >= 3 || poziomTrudnosci == "Sredni") {
            if ((polozenieX[1] == polozenieX[2] && polozenieY[1] == polozenieY[2]) || (polozenieX[0] == polozenieX[3] && polozenieY[0] == polozenieY[3])) {
                System.out.println("error!");
                przegrana = true;
            }
        } else {
            if (polozenieX[1] == polozenieX[2] && polozenieY[1] == polozenieY[2]) {
                System.out.println("error!");
                przegrana = true;
            }
        }
    }

    public void odswiezTlo() {
        Graphics g = getGraphics();
        g.drawImage(imageT, 0, 30, null);
        rysujKwadrat('S', polozenieX[1], polozenieY[1], 1);
        rysujKwadrat('N', polozenieX[4], polozenieY[4], 1);
        if (poziomTrudnosci == "Latwy" && punkty < 3) {
            rysujKwadrat('S', polozenieX[2], polozenieY[2], 2);
        } else if (poziomTrudnosci == "Sredni" || punkty >= 3) {
            rysujKwadrat('S', polozenieX[2], polozenieY[2], 2);
            rysujKwadrat('S', polozenieX[3], polozenieY[3], 3);
        }
    }

    public void losuj() {
        while (true) {
            for (int i = 1; i <= 4; i++) {
                x[i] = rand.nextInt(19) + 1;
                polozenieX[i] = x[i] * 50;
                y[i] = rand.nextInt(12) + 1;
                polozenieY[i] = y[i] * 50;
            }
            if ((x[1] != x[2] && y[1] != y[2]) && (x[1] != x[4] && y[1] != y[4]) && (x[2] != x[4] && y[2] != y[4]) && (x[1] != x[3] && y[1] != y[3]) && (x[2] != x[3] && y[2] != y[3]) && (x[3] != x[4] && y[3] != y[4])) {
                odswiezTlo();
                break;
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_Q && przegrana != true) {
            licznik.start();
            start = true;
            rysujKwadrat('S', polozenieX[1], polozenieY[1], 1);
            rysujKwadrat('N', polozenieX[4], polozenieY[4], 1);

        }
        if (keyCode == KeyEvent.VK_R) {
            punkty = 0;
            przegrana = false;
            poziomTrudnosci = "Poziom Latwy";
            losuj();

        }
        if (keyCode == KeyEvent.VK_D && przegrana != true && start == true) {
            if (polozenieX[1] >= 950) {
                System.out.println("Jestes na skraju planszy!");
            } else {
                polozenieX[1] += 50;
                rysujKwadrat('P', polozenieX[1], polozenieY[1], 1);
            }
            odswiezTlo();
            sprawdzPrzegrana();
        } else if (keyCode == KeyEvent.VK_S && przegrana != true && start == true) {
            if (polozenieY[1] >= 600) {
                System.out.println("Jestes na skraju planszy!");
            } else {
                polozenieY[1] += 50;
                rysujKwadrat('D', polozenieX[1], polozenieY[1], 1);
            }
            odswiezTlo();
            sprawdzPrzegrana();
        } else if (keyCode == KeyEvent.VK_W && przegrana != true && start == true) {
            if (polozenieY[1] <= 50) {
                System.out.println("Jestes na skraju planszy!");
            } else {
                polozenieY[1] -= 50;
                rysujKwadrat('U', polozenieX[1], polozenieY[1], 1);
            }
            odswiezTlo();
            sprawdzPrzegrana();
        } else if (keyCode == KeyEvent.VK_A && przegrana != true && start == true) {
            if (polozenieX[1] <= 50) {
                System.out.println("Jestes na skraju planszy!");
            } else {
                polozenieX[1] -= 50;
                rysujKwadrat('L', polozenieX[1], polozenieY[1], 1);
            }
            odswiezTlo();
            sprawdzPrzegrana();
        }

    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    public class Licznik {

        int czas = 0;

        java.util.Timer timer = new Timer();
        TimerTask zadanie = new TimerTask() {
            public void run() {
                if (przegrana == false) {
                    czas++;
                    sprawdzPrzegrana();

                    if (polozenieX[1] == polozenieX[4] && polozenieY[1] == polozenieY[4]) {
                        System.out.println("wygrales");
                        punkty++;

                        losuj();

                        odswiezPunktacje();


                    }
                    rysujKwadrat('S', polozenieX[1], polozenieY[1], 1);
                    rysujKwadrat('N', polozenieX[4], polozenieY[4], 1);
                    a = rand.nextInt(4);
                    b = rand.nextInt(4);
                    if (a == 0) {
                        if (polozenieY[2] <= 50) {
                            System.out.println("Jestes na skraju planszy!");
                        } else {
                            polozenieY[2] -= 50;
                            rysujKwadrat('L', polozenieX[2], polozenieY[2], 2);
                        }

                    }
                    if (a == 1) {
                        if (polozenieY[2] >= 950) {
                            System.out.println("Jestes na skraju planszy!");
                        } else {
                            polozenieY[2] += 50;
                            rysujKwadrat('P', polozenieX[2], polozenieY[2], 2);
                        }

                    }
                    if (a == 2) {
                        if (polozenieY[2] >= 600) {
                            System.out.println("Jestes na skraju planszy!");
                        } else {
                            polozenieY[2] += 50;
                            rysujKwadrat('D', polozenieX[2], polozenieY[2], 2);
                        }

                    }
                    if (a == 3) {
                        if (polozenieY[2] <= 50) {
                            System.out.println("Jestes na skraju planszy!");
                        } else {
                            polozenieY[2] -= 50;
                            rysujKwadrat('U', polozenieX[2], polozenieY[2], 2);
                        }

                    }
                    if (punkty >= 3 || poziomTrudnosci == "Sredni") {
                        if (b == 0) {
                            if (polozenieX[3] <= 50) {
                                System.out.println("Jestes na skraju planszy!");
                            } else {
                                polozenieX[3] -= 50;
                                rysujKwadrat('L', polozenieX[3], polozenieY[3], 3);
                            }

                        }
                        if (b == 1) {
                            if (polozenieX[3] >= 950) {
                                System.out.println("Jestes na skraju planszy!");
                            } else {
                                polozenieX[3] += 50;
                                rysujKwadrat('P', polozenieX[3], polozenieY[3], 3);
                            }

                        }
                        if (b == 2) {
                            if (polozenieX[3] >= 600) {
                                System.out.println("Jestes na skraju planszy!");
                            } else {
                                polozenieX[3] += 50;
                                rysujKwadrat('D', polozenieX[3], polozenieY[3], 3);
                            }

                        }
                        if (b == 3) {
                            if (polozenieY[3] <= 50) {
                                System.out.println("Jestes na skraju planszy!");
                            } else {
                                polozenieY[3] -= 50;
                                rysujKwadrat('U', polozenieX[3], polozenieY[3], 3);
                            }

                        }
                    }
                    odswiezTlo();
                } else {
                    System.out.println("Przegrales!");
                }
            }

        };

        public void start() {
            timer.scheduleAtFixedRate(zadanie, 1000, 1000);
        }
    }

    public void odswiezPunktacje() {
        remove(text);
        string = Integer.toString(punkty);
        text = new JTextField(string);
        //Przycisk przyciskStartu = new Przycisk("Start",'S');
        //Przycisk przyciskResetu= new Przycisk("Reset", 'R');
        //add(przyciskStartu);
        //add(przyciskResetu);
        add(text);
        repaint();
        revalidate();
    }

    /*
    public class Przycisk extends JButton{
        public Przycisk(String nazwa, char operacja) {
            super(nazwa);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (operacja == 'R') {
                        punkty = 0;
                        przegrana = false;
                        poziomTrudnosci = "Poziom Latwy";
                        losuj();
                    }
                    if(operacja=='S'){
                        licznik.start();
                        start=true;
                        rysujKwadrat('S', polozenieX[1], polozenieY[1], 1);
                        rysujKwadrat('N',polozenieX[4],polozenieY[4],1);
                    }
                }
            });
        }
    }
    */

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.GRAY);
        g.setColor(Color.lightGray);
        g.drawImage(imageT, 0, 30, null);
    }

    public void rysujKwadrat(char znak, int Polozenie, int Polozenie2, int gracz) {
        Graphics g = getGraphics();

        if (znak == 'N') {
            g.drawImage(imageN, Polozenie, Polozenie2, null);
        }
        if (znak == 'S' || znak == 'D' || znak == 'U' ||znak == 'P' ||znak == 'L') {
            if (gracz == 1) {
                g.drawImage(image, Polozenie, Polozenie2, null);
            } else if (gracz == 2) {
                g.drawImage(image2, Polozenie, Polozenie2, null);
            } else if (gracz == 3) {
                g.drawImage(image3, Polozenie, Polozenie2, null);
            }

        }

    }


}
