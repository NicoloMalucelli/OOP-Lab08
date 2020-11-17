package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import it.unibo.oop.lab.mvcio.Controller;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {

    private final JFrame frame = new JFrame();

    public SimpleGUIWithFileChooser() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();

        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final Controller controller = new Controller();
        final JPanel mainPanel = new JPanel();
        final JPanel upperPanel = new JPanel();
        final JTextArea txaToSave = new JTextArea();
        final JTextField txfToBrows = new JTextField();
        txfToBrows.setEditable(false);
        txfToBrows.setText(controller.getPath());
        final JButton btnSave = new JButton("save");
        final JButton btnBrowse = new JButton("browse");

        upperPanel.setLayout(new BorderLayout());
        mainPanel.setLayout(new BorderLayout());

        upperPanel.add(txfToBrows, BorderLayout.CENTER);
        upperPanel.add(btnBrowse, BorderLayout.EAST);
        mainPanel.add(upperPanel, BorderLayout.NORTH);
        mainPanel.add(txaToSave, BorderLayout.CENTER);
        mainPanel.add(btnSave, BorderLayout.SOUTH);
        frame.setContentPane(mainPanel);

        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final int res = JOptionPane.showConfirmDialog(frame, "Do you want to save? ", "Saving", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                    try {
                        controller.write(txaToSave.getText());
                    } catch (IOException ex) {
                        System.out.println("error writing on file");
                    }
                }
            }
        });

        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fileSelector = new JFileChooser();
                if (fileSelector.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    controller.setFile(fileSelector.getSelectedFile());
                    txfToBrows.setText(controller.getPath());
                }
            }
        });
    }

    private void show() {
        frame.setVisible(true);
    }
    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */
    public static void main(final String[] args) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser();
        gui.show();
    }


}
