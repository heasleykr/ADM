package edu.sdccd.cisc191.heaslykr;

/**
 * Class that keeps total for the download queue. You can get information
 * about the current queue total by requesting the count.
 *
 * @author Katelynn Heasley
 */
public class QueueCount {
    ///////////////////fields/////////////////////
    //Fields for count holders
    private int total = 0;
    private int count = 0;
    private int helper = 0;

    //Fields for display messages
    private String display = new String();
    private String complete = new String();

//////////////constructor/////////////////////
    /**No-Argument constructor. Initializes a Queue Count object.
     * Count initializes to 0 and there's no maximum for total in queue.
     */
    public QueueCount(){
        total = 0;
        count = 0;
    }

////////////////methods///////////////////////

    /**Method to add count to current queue total.
     */
    public void addCount(){
        helper = getCount();
        this.count = helper + 1;
    }

    /**Method to return current queue count.
     * @return count of queue
     */
    public int getCount(){
        return count;
    }

    /**Method to display current queue total
     * @return String sentence of queue count.
     */
    public String displayCount(){
        display = "Total Downloaded: " + getCount() ;
        return display;
    }

    /**Method to return completion method when all
     * items in queue are marked complete.
     * @return String download completion message
     */
    public String downloadComplete(){
        complete = "Download of all items complete.";
        return complete;
    }

}




