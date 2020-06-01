import java.net.URL;
import java.io.*;


/*
 * The manager for Program ADM.  This class manages all GUI classes and
 * download thread operations. All objects are initialized within.
 *
 * @author Katelynn Heasley
 */
public class ThreadManager {
    /**
     * Main method to initialize ADM program
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //Initialize Intro GUI. Grab User name & directory, pass to MainGUI
        IntroGUI ig = new IntroGUI();
    }

    /*Static class to initialize a Runnable each time a
    *url is request for download
     */
    private static class DownloadTask implements Runnable {

        String url;
        String directory;

    /*Two-Argument Constructor for Runnable
     * @param urlTask the String to be downloaded.
     * @param userDirectory the file path to desired directory where file is to be written.
     */
        DownloadTask(String urlTask, String userDirectory) {
            this.url = urlTask;
            this.directory = userDirectory;
        }
        public void run() {
            try {
                //convert String to Url Object
                URL url1 = new URL(url);

                //add ending URL path name to saved file path
                String test = url1.getFile();

                //Parse url split into string array
                String[] words = test.split("/");
                String userFile = directory + "/" + words[words.length - 1] + ".xml";

                //Open an input stream from Url Object
                BufferedInputStream reader = new BufferedInputStream(url1.openStream());

                //Open Output stream to write to file
                FileOutputStream writer = new FileOutputStream(userFile);

                //Intialize a byte array to handle stream
                byte[] buffer = new byte[1024];
                int count = 0;

                //Read the incoming bytes and write to desired file until complete
                while ((count = reader.read(buffer, 0, 1024)) != -1) {
                    writer.write(buffer, 0, count);
                }

                //Flush the output stream and close both reader and writer
                writer.flush();
                reader.close();
                writer.close();

             //Catch if String cannot be converted to Url Object
            } catch (IOException e) {
                e.printStackTrace();

            }
        }

    }

    /**
     * Method to initiate Runnable and start a thread for each download request
     * @param url the name to be used as player's name.
     * @param filePath the file path to desired directory.
     */
    public static void createUrl(String url, String filePath) {

        String taskUrl = url;

        //Initialize Runnable Object
        DownloadTask add = new DownloadTask(taskUrl, filePath);

        //Start thread to handle task
        Thread th = new Thread(add);
        th.start();
    }
}