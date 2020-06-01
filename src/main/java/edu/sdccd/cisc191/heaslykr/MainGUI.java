package edu.sdccd.cisc191.heaslykr;

import java.awt.*;
import java.awt.event.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/*
 * The main UI for the Program ADM.  This class launches a UI that
 * displays all information and allows the user to interact with the
 * Program. The User can request downloaded files from Strings and
 * open those request files once donwloaded.
 *
 * @author Katelynn Heasley
 */
public class MainGUI extends JFrame{

    ///////////////////fields/////////////////////
    //Fields to initialize window dimensions
    private final int WINDOW_WIDTH = 950;
    private final int WINDOW_HEIGHT = 700;

    //Fields for aesthetic colors &amp; text insets
    private Color backgroundColor = new Color(231,231,231);
    private Color pinkColor = new Color(227,162,155);
    private Color dustyColor = new Color(219,172,164);
    private Color fontWhite = Color.WHITE;
    private Color metal = new Color(40,40,40);
    private Insets center = new Insets(5,11,5,5);
    private Insets centered = new Insets(5,5,5,5);

    //Fields for border layout main panels
    private JPanel eastPanel;
    private JPanel centerPanel;
    private JPanel westPanel;
    private JPanel northPanel;
    private JPanel southPanel;


    //Field for graphic images
    private ImageIcon gridSouth;
    private ImageIcon gridNorthR;
    private ImageIcon gridNorthL;
    private ImageIcon gridEast;
    private ImageIcon hold1;
    private ImageIcon arrow;
    private ImageIcon greyArrow;

    //Fields for messages &amp; user interaction
    private String userName;
    private String instructions1;
    private String urlJob;
    private String filePath;
    private String acknowledgeUserM;
    private JTextField enterUrl;
    private JTextArea acknowledgeUser;
    private JTextArea instructions;

    //Fields for JButtons
    private JButton startDownload;

    //Fields for Swing GUI label and text components
    private JLabel southGrid;
    private JLabel northGridR;
    private JLabel northGridL;
    private JLabel eastGrid;
    private JLabel westHold;
    private JLabel nameLabel;

    //Download Count Object
    private QueueCount qC = new QueueCount();

    //JList &amp; JScrollPane for completed downloads
    private JScrollPane spane;
    private ArrayList<String> display = new ArrayList<String>();
    private String[] bigData = new String[100];
    private JList<String> myList = new JList<String>(bigData);
    private JLabel completed = new JLabel("Completed Downloads Total: " + qC.getCount());
    private JButton open;
    private Dimension openB= new Dimension(30, 5);
    private String[] words;
    private String userFile;
    private String opening;
    private String error;


    //////////////constructor/////////////////////
    /**Three-Argument constructor. Initializes a MainGame object that
     * takes a name, file directory, and file path and passes them to JPanel methods.
     * @param name the name to be used as player's name.
     * @param directory the directory the files are to be stored.
     * @param fullPath the file path to desired directory.
     */
    public MainGUI(String name, String directory, String fullPath) {

        //Call the super class constructor
        super("Document Download Manager");

        //Set the size of the window
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        //Set application to exit when window is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set size of window to be unchangeable
        setResizable(false);

        //Create BorderLayout panels
        makeNorthPanel();
        makeSouthPanel();
        makeWestPanel(name, directory, fullPath);
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
     * house IconImages and will not interact
     * with user.
     */
    public void makeNorthPanel(){
        //Initialize panel &amp; design
        northPanel = new JPanel();

        //Initialize ImageIcons &amp; set to labels
        try {
            gridNorthR = new ImageIcon(ImageIO.read(getClass().getResource(("/img/northPanelR.png"))));
            gridNorthL = new ImageIcon(ImageIO.read(getClass().getResource(("/img/northPanelL.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        northGridR = new JLabel(gridNorthR);
        northGridL = new JLabel(gridNorthL);

        //Set layout &amp; add components
        northPanel.setLayout(new GridLayout(1,1));
        northPanel.add(northGridL);

    }

    /**Method to initialize South Panel. Panel will
     * house space, color &amp; dimension only and
     * will not interact with user.
     */
    public void makeSouthPanel(){
        //Initialize panel &amp; design
        southPanel = new JPanel();

        //Initialize ImageIcons and set to label
        try {
            gridSouth = new ImageIcon(ImageIO.read(getClass().getResource(("/img/southPanel.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        southGrid = new JLabel(gridSouth);
        southPanel.setBackground(dustyColor);
        southPanel.add(southGrid);
    }

    /**Method to initialize East Panel. Panel will
     * house space, color &amp; dimension only and
     * will not interact with user.
     */
    public void makeEastPanel(){
        //Initialize panel &amp; design
        eastPanel = new JPanel();
        eastPanel.setBackground(dustyColor);

        //Initialize ImageIcons and set to label
        try {
            gridEast = new ImageIcon(ImageIO.read(getClass().getResource(("/img/eastPanel.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        eastGrid = new JLabel(gridEast);
        eastPanel.add(eastGrid);
    }

    /**Method to initialize West Panel. Panel will
     * house JButtons and will initiate download thread
     * when user interacts with components.
     * @param name the name to be used as player's name.
     * @param directory the directory the files are to be stored.
     * @param fullPath the file path to desired directory.
     */
    public void makeWestPanel(String name, String directory, String fullPath){
        //Initialize panel &amp; design
        westPanel = new JPanel();
        westPanel.setBackground(backgroundColor);

        //Initialize User's name
        userName = name + System.lineSeparator() + "Save Downloads To Folder: " + directory;
        nameLabel = new JLabel(userName, 0);
        nameLabel.setForeground(metal);
        nameLabel.setSize(200,10);

        //Initialize aesthetic panel &amp; set to JLabel
        try {
            hold1 = new ImageIcon(ImageIO.read(getClass().getResource(("/img/centerHold.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        westHold = new JLabel(hold1);

        //Initialize Download JButton
        startDownload = new JButton();
        try {
            arrow = new ImageIcon(ImageIO.read(getClass().getResource(("/img/arrow.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        startDownload.setIcon(arrow);
        startDownload.setOpaque(true);
        startDownload.setBackground(dustyColor);

        //Initialize "selected" Download image
        try {
            greyArrow = new ImageIcon(ImageIO.read(getClass().getResource(("/img/greyArrow.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //initialize URL space/download thread interaction point
        enterUrl = new JTextField("Enter URL here", 0);
        enterUrl.setHorizontalAlignment(0);
        enterUrl.setForeground(metal);

        //Action listener to grab String html and pass to ThreadManager to download
        startDownload.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {

                        //If URL field is default, display error message
                        if("Enter URL here".equals(enterUrl.getText())){
                            acknowledgeUserM = "Please enter a valid URL to continue";

                        // If URL field is default, display error message
                        }else if("".equals(enterUrl.getText())){
                            acknowledgeUserM = "Please enter a valid URL to continue";

                        //Initialize Downloading threads and process.
                        }else{

                            //Grad html and add job to queue/pass to Thread Manager
                            urlJob = enterUrl.getText();
                            filePath = fullPath;
                            ThreadManager.createUrl(urlJob, filePath);

                            //add total count
                            qC.addCount();

                            //set display message to User
                            acknowledgeUserM = "Download process initiated";
                            completed.setText("Completed Downloads Total: " + qC.getCount());
                            spane.setColumnHeaderView(completed);

                            //make file name and add to completed Arraylist
                            words = urlJob.split("/");
                            userFile = filePath + "/" + words[words.length - 1];
                            display.add(userFile);
                            addDownload();

                            //set back to original text
                            enterUrl.setText("Enter URL here");

                        }
                    }
                });


        //Initialize user messages
        instructions1 = "Copy and paste URL of content you wish to download. You may start as many downloads as you like.";
        //Set restrictions &amp; look for display text
        instructions = new JTextArea(10,8);
        instructions.setText(instructions1);
        instructions.setWrapStyleWord(true);
        instructions.setLineWrap(true);
        instructions.setOpaque(false);
        instructions.setEditable(false);
        instructions.setFocusable(false);
        instructions.setMargin(centered);
        instructions.setForeground(metal);

        //Set restrictions &amp; look for display text
        acknowledgeUserM = "Ready for Task";
        acknowledgeUser = new JTextArea();
        acknowledgeUser.setText(acknowledgeUserM);
        acknowledgeUser.setWrapStyleWord(true);
        acknowledgeUser.setLineWrap(true);
        acknowledgeUser.setOpaque(false);
        acknowledgeUser.setEditable(false);
        acknowledgeUser.setFocusable(false);
        acknowledgeUser.setMargin(centered);
        acknowledgeUser.setForeground(metal);

        //Set layout &amp; add components
        westPanel.setLayout(new GridLayout(6,1));
        westPanel.add(nameLabel);
        westPanel.add(westHold);
        westPanel.add(instructions);
        westPanel.add(enterUrl);
        westPanel.add(startDownload);
        westPanel.add(acknowledgeUser);

    }

    /**Method to initialize Center Panel. Panel will
     * display queue count &amp; queue thread progress.
     * User can choose to open downloaded files by
     * pressing the "Open" button.
     */
    public void makeCenterPanel(){
        //Initialize panel
        centerPanel = new JPanel();

        //Initialize &amp; design queue count display
        open = new JButton("Open");
        open.setBackground(dustyColor);
        open.setOpaque(true);
        open.setBackground(dustyColor);
        open.setSize(new Dimension(0,300) );

        //Action listener to open selected file on user's computer
        open.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        opening = myList.getSelectedValue();
                        File file = new File(opening);
                        //Confirm file exists and attempt to open, otherwise
                        //so error message UI
                        if(file.exists()) {

                            try {
                                //open file
                                Desktop desktop = Desktop.getDesktop();

                                desktop.open(file);

                            } catch (IllegalArgumentException | IOException i) {

                                error = "There was an error opening the requested file";
                                JOptionPane.showMessageDialog(new JFrame(), error, "Dialog",
                                        JOptionPane.ERROR_MESSAGE);
                                i.printStackTrace();
                            }
                        }else{
                            error = "The file doesn't not exist";
                            JOptionPane.showMessageDialog(new JFrame(), error, "Dialog",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });


        //JList Downloaded File Display
        for(int t = 0; t < bigData.length; t++){
            bigData[t] = new String("");
        }

        //Initialize individual thread displays, set to JLabels &amp; JScrollPane &amp; set design
        completed.setHorizontalAlignment(0);
        completed.setForeground(metal);

        //Set look and feel for JList
        myList.setFixedCellHeight(30);
        myList.setBackground(metal);
        myList.setForeground(fontWhite);

        //Put the JList in the JScrollPane
        spane = new JScrollPane(myList);
        spane.createHorizontalScrollBar();
        spane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        spane.setColumnHeaderView(completed);
        spane.setOpaque(true);

        //Set layout &amp; add components to center panel
        centerPanel.setLayout(new GridLayout(2,1));
        centerPanel.add(spane);
        //centerPanel.add(count);
        centerPanel.add(open);
        centerPanel.setBackground(fontWhite);
    }

    //Method to add downloaded file to "completed list"
    public void addDownload(){
        //loop through the ArrayList and add new downloaded task to JList UI view
        for(int i = 0; i < display.size(); i++){
          bigData[i] = display.get(i);
        }
        //repaint UI
        myList.repaint();
        spane.repaint();
    }
}





