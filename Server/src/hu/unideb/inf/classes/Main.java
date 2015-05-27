package hu.unideb.inf.classes;

import hu.unideb.inf.forms.ServerView;


public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ServerView().setVisible(true);
            }
        });
    }
}
