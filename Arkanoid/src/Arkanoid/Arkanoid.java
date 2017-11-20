
package Arkanoid;

import Controller.*;


public class Arkanoid {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Controller ArkanoidController=new Controller();
            }
        });
    }

}
