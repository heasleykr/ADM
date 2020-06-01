import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Class that initializes a error window GUI for the package ADM.
 * ErrorNameWindow will run when user's name attempts to
 * initialize with either a default value or "null".
 *
 * @author Katelynn Heasley
 */
public class ErrorNameWindow extends JFrame{

    ///////////////////fields/////////////////////
    //Fields to initialze window dimensions
    private final int WINDOW_WIDTH = 200;
    private final int WINDOW_HEIGHT = 200;

    //Fields for game colors & text inset
    private Color backgroundColor = new Color(40,40,40);
    private Color fontWhite = Color.WHITE;
    private Insets center = new Insets(5,11,5,5);

    //Field for display message
    private String error = "Please enter your name to continue";

    //Fields for Java GUI Swing components
    private JButton okay;
    private JTextArea errorMessage = new JTextArea(6,0);

//////////////constructor/////////////////////
    /**No-Argument constructor. Initializes an Error Window GUI object.
     * Message displays: "please enter your name to continue".
     * File ends when user chooses JButton "okay".
     */
    public ErrorNameWindow(){

        //Call the super class constructor
        super("Error Name");

        //Set the size of the window & set background color
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setBackground(backgroundColor);

        //Set application to exit when window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set size of window to be unchangable
        setResizable(false);

        //Set restrictions & look for error text
        errorMessage.setText(error);
        errorMessage.setWrapStyleWord(true);
        errorMessage.setLineWrap(true);
        errorMessage.setOpaque(false);
        errorMessage.setEditable(false);
        errorMessage.setFocusable(false);
        errorMessage.setMargin(center);

        //initialize JButton and add ActionListener
        okay = new JButton("Okay");
        okay.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });

        //Set Layout and add panels
        setLayout(new GridLayout(2,2));
        add(errorMessage);
        add(okay);

        //Make window visible
        setVisible(true);
    }

}




