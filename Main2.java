package grazdobadznagrode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main2  {
    ImageIcon ii = new ImageIcon("C:\\Java\\Snakee\\src\\snakee\\tlo.png");
    Image imagee = ii.getImage();
    Menu menu;
    public Main2(Main main){
        menu = new Menu(main);
    }

    public class Przycisk extends JButton {

        public Przycisk( String operacja,Main main) {


            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(operacja=="Poziom Latwy"){
                        System.out.println(operacja);
                        main.dodajRozgrywke();
                       main.setVisible(true);
                    }
                    else if(operacja=="Poziom Sredni"){
                        main.planszaRozgrywki.poziomTrudnosci="Sredni";
                        System.out.println(operacja);
                        main.dodajRozgrywke();
                        main.setVisible(true);
                    }


                }
            });

        }
    }
    public class Menu extends JPanel{
        public Menu(Main main){

            Graphics g = getGraphics();
           // g.drawImage(imagee,0,0,null);
            ImageIcon i = new ImageIcon("C:\\Java\\Snakee\\src\\snakee\\poziomLatwy.png");
            ImageIcon i2 = new ImageIcon("C:\\Java\\Snakee\\src\\snakee\\poziomSredni.png");
            Przycisk przyciskStart = new Przycisk("Poziom Latwy", main);
            przyciskStart.setIcon(i);
            przyciskStart.setPreferredSize(new Dimension(240, 68));
            Przycisk przyciskStart2 = new Przycisk("Poziom Sredni", main);
            przyciskStart2.setPreferredSize(new Dimension(240, 68));
            przyciskStart2.setIcon(i2);
            add(przyciskStart);
            add(przyciskStart2);
        }
    }
}
