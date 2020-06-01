package edu.sdccd.cisc191.heaslykr;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

/*
 * The opening UI for the Program ADM.  This class launches a UI that
 * displays information and a Name Entry field to user. The User can
 * choose their name as well as the directory on their computer
 * where they would like downloaded files to be stored.
 *
 * @author Katelynn Heasley
 */
public class IntroGUI extends JFrame{
    ///////////////////fields/////////////////////
    //Fields to initialize window dimensions
    private final int WINDOW_WIDTH = 600;
    private final int WINDOW_HEIGHT = 300;

    //Fields for aesthetic colors &amp; text insets
    private Color backgroundColor = new Color(231,231,231);
    private Color pinkColor = new Color(227,162,155);
    private Color dustyColor = new Color(219,172,164);
    private Color fontWhite = Color.WHITE;
    private Color metal = new Color(40,40,40);
    private Insets center = new Insets(5,5,5,5);

    //Fields for border layout main panels
    private JPanel westPanel;
    private JPanel centerPanel;
    private JPanel southPanel;
    private JPanel northPanel;
    private JPanel eastPanel;

    //Fields for Icon Images
    private ImageIcon logo;
    private ImageIcon eastPane;

    //Fields for game messages
    private String rules1 = "Welcome to a java-run Document Download Manager. " +
           "This program takes a url and downloads applicable hyperlinks from that website.";
    private String rules2 = "Type in your name and choose your local file directory to start.";
    private String directory;
    private String path;
    private String fullPath;
    private String nameFinal;
    private File saveFolder;

    //Fields for Error &amp; Main Game GUIs
    private ErrorNameWindow enw;
    private MainGUI mainGo;

    //Fields Java GUI Swing components
    private JButton start = new JButton("Choose File Directory");
    private JLabel welcomeHeader = new JLabel("Task Download Manager", 0);
    private JLabel name = new JLabel("Name");
    private JLabel logo1;
    private JLabel eastPane1;
    private JTextField enterName = new JTextField("Enter name here", 3);
    private JTextArea welcomeRules1 = new JTextArea(6,0);
    private JTextArea welcomeRules2 = new JTextArea(6,0);

//////////////constructor/////////////////////
    /**No-Argument constructor. Initializes a welcome GUI object.
     * Displays information content. Name is initialized to a default and
     * must be updated by user in order to run main GUI file.
     */
    public IntroGUI(){

        //Call the super class constructor
        super("Task Download Manager");

        //Set the size of the window
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        //Set application to exit when window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set size of window to be unchangeable
        setResizable(false);

        //Create BorderLayout panels
        makeNorthPanel();
        makeSouthPanel();
        makeWestPanel();
        makeCenterPanel();
        makeEastPanel();

        //Set layout and add panels
        setLayout(new BorderLayout());
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
        add(westPanel, BorderLayout.WEST);
        add(eastPanel, BorderLayout.EAST);
        add(centerPanel, BorderLayout.CENTER);

        //Make window visible
        setVisible(true);
    }

////////////////methods///////////////////////

    /**Method to initialize North Panel. Panel will
     * house space, color &amp; dimension only and
     * will not interact with user.
     */
    public void makeNorthPanel(){
        //Initialize panel &amp; design
        northPanel = new JPanel();
        northPanel.setBackground(backgroundColor);
    }

    /**Method to initialize South Panel. Panel will
     * house space, color &amp; dimension only and
     * will not interact with user.
     */
    public void makeSouthPanel(){
        //Initialize panel &amp; design
        southPanel = new JPanel();
        southPanel.setBackground(backgroundColor);
    }

    /**Method to initialize West Panel. Panel will
     * house an ImageIcon &amp; dimension only and
     * will not interact with user.
     */
    public void makeWestPanel(){
        //Initialize panel
        westPanel = new JPanel();

        //Initialize logo, set design, &amp; set to JLabel
        try {
            logo = new ImageIcon(ImageIO.read(getClass().getResource(("/img/logoSmall.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        logo1 = new JLabel();
        logo1.setIcon(logo);
        logo1.setForeground(pinkColor);

        //Add components &amp; set design
        westPanel.add(logo1);
        westPanel.setBackground(backgroundColor);
    }

    /**Method to initialize East Panel. Panel will
     * house game Logo, color &amp; dimension only and
     * will not interact with user.
     */
    public void makeEastPanel(){
        //Initialize panel
        eastPanel = new JPanel();

        //Initialize ImageIcon &amp; add to Jlabel
        try {
            eastPane = new ImageIcon(ImageIO.read(getClass().getResource(("/img/eastPanel.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        eastPane1 = new JLabel();
        eastPane1.setIcon(eastPane);

        //Add components &amp; set design
        eastPanel.add(eastPane1);
        eastPanel.setBackground(backgroundColor);
    }

    public void makeCenterPanel(){
        //Initialize panel &amp; set layout
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5,1));

        //Set restrictions &amp; look for game instruction text 1
        welcomeRules1.setText(rules1);
        welcomeRules1.setWrapStyleWord(true);
        welcomeRules1.setLineWrap(true);
        welcomeRules1.setOpaque(false);
        welcomeRules1.setEditable(false);
        welcomeRules1.setFocusable(false);
        welcomeRules1.setMargin(center);

        //Set restrictions &amp; look for game instruction text 2
        welcomeRules2.setText(rules2);
        welcomeRules2.setWrapStyleWord(true);
        welcomeRules2.setLineWrap(true);
        welcomeRules2.setOpaque(false);
        welcomeRules2.setEditable(false);
        welcomeRules2.setFocusable(false);
        welcomeRules2.setMargin(center);

        start.setBackground(dustyColor);
        start.setOpaque(true);

        //Add Event Handling
        start.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        //If name field is default, initialize error window
                        if("Enter name here".equals(enterName.getText()))
                        {
                            enw = new ErrorNameWindow();
                        }

                        //If name field is null, initialize error window
                        else if("".equals(enterName.getText()))
                        {
                            enw = new ErrorNameWindow();
                        }

                        //If name field text has been updated, initialize main game
                        else
                        {
                            //Save user name to String
                            nameFinal = enterName.getText();

                            //Initialize FileChooser GUI
                            JFileChooser chooser = new JFileChooser();
                            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                            int returnVal = chooser.showOpenDialog(chooser);
                            if(returnVal == JFileChooser.APPROVE_OPTION) {

                                //grab directory path and save to String
                                File file = chooser.getCurrentDirectory();
                                String directory = chooser.getSelectedFile().getName();
                                String path = file.getAbsolutePath();
                                String fullPath = path + "/" + directory;



                                //Initialize MainGUI
                                mainGo = new MainGUI(nameFinal, directory, fullPath);
                                setVisible(false);
                            }
                        }
                    }
                });

        //Initialize text field
        enterName.setHorizontalAlignment(0);
        enterName.setForeground(metal);

        //Add components &amp; set design
        centerPanel.add(welcomeHeader);
        centerPanel.add(welcomeRules1);
        centerPanel.add(welcomeRules2);
        centerPanel.add(enterName);
        centerPanel.add(start);
        centerPanel.setBackground(backgroundColor);
        centerPanel.setForeground(pinkColor);
    }

    /**Method to obtain the user's name.
     * @return the user's name.
     */
    public String getName(){
        return enterName.getText();
    }

    /**Method to obtain the user's file wish to write to.
     * @return the user's file destination.
     */
    public String getFilePath(){ return fullPath; }

}



