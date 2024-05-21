package treppendmx.ui;

import javax.swing.*;

public class TreppenDmx extends JFrame {

    public static String TITLE = "TreppenDmx";

    public TreppenDmx() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().add(new RootPanel());
    }

    public void display() {

        setTitle(TITLE);
        setSize(200,200);
        setVisible(true);

    }
}
