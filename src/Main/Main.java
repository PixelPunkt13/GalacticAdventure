package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);


        //window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //window.setUndecorated(true);


        window.setTitle("Galactic Adventure");

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);

        window.pack(); //has tobe removed for fullscreen

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();


    }
}