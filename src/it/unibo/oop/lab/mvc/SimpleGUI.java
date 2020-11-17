package it.unibo.oop.lab.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) It has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextField in the upper part of the frame, 
     * a JTextArea in the center and two buttons below it: "Print", and "Show history". 
     * SUGGESTION: Use a JPanel with BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The behavior of the program is that, if "Print" is pressed, the
     * controller is asked to show the string contained in the text field on standard output. 
     * If "show history" is pressed instead, the GUI must show all the prints that
     * have been done to this moment in the text area.
     * 
     */

    /**
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUI() {
        final ControllerImpl controller = new ControllerImpl();
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);

        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        frame.setContentPane(mainPanel);
        final JPanel belowPanel = new JPanel();
        belowPanel.setLayout(new GridLayout(1, 2));
        final JTextField txfNextString = new JTextField();
        final JTextArea txaHistory = new JTextArea();
        txaHistory.setEditable(false);
        final JButton btnPrint = new JButton("Print");
        final JButton btnShowHistory = new JButton("Show history");

        belowPanel.add(btnPrint, 0, 0);
        belowPanel.add(btnShowHistory, 0, 1);
        mainPanel.add(txfNextString, BorderLayout.NORTH);
        mainPanel.add(txaHistory, BorderLayout.CENTER);
        mainPanel.add(belowPanel, BorderLayout.SOUTH);

        btnShowHistory.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                txaHistory.setText(controller.getHistory().toString());
            }
        });

        btnPrint.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                controller.setNextString(txfNextString.getText());
                controller.print();
            }
        });
    }

    private void show() {
        frame.setVisible(true);
    }

    public static void main(final String[] args) {
        final SimpleGUI gui = new SimpleGUI();
        gui.show();
    }

}
