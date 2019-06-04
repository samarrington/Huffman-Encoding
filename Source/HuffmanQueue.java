
package huffmanencoding;
import java.io.*;

/**
 * HuffmanQueue is an array implementation of a priority queue that contains tree nodes. 
 * The nodes are sorted first by their frequency attribute, second by length of 
 * the key, and third alphabetically.
 * 
 * @author W. Sam Arrington
 */
public class HuffmanQueue {
    
    /**
     * Contains the queue of tree nodes.
     */
    public TreeNode[] array;

    /**
     * Maintains the number of nodes left in queue
     */
    public int size;

    /**
     * Contains the number of keys initially in Huffman queue
     */
    public final int totalKeys;
    
    private int top;
    
    
    /**
     * This constructor creates a Huffman Queue from a file that contains letters
     * and their frequencies on separate lines in the following form: A - 16.
     * Lowercase and uppercase letters will be treated the same.  
     * @param fileName the name of the file containing a frequency table
     * @throws FileNotFoundException
     * @throws IOException 
     */
    HuffmanQueue(String fileName) throws FileNotFoundException, IOException{
        
        // Creates reader for input file
        FileInputStream in = new FileInputStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        
        // Counts lines in file
        br.mark(3000);
        int countLines = 0;
        while(br.readLine().length() > 0){
            countLines++;
        }
        br.reset();
        
        /* Sets the number of keys, the initial size of queue, intializes array,
        and sets the top of queue to the first value in array
        */
        totalKeys = countLines;
        size = countLines;
        array = new TreeNode[size];
        top = 0;
        
        // Reads each line, creates tree node and adds it to queue in correct format
        for (int i = 0; i < totalKeys; i++ ){
            
            String line = br.readLine();
            
            String freqEntry[] = line.split("-");
            // Error if not split properly
            if (freqEntry.length != 2){
                System.err.println("Not a valid format.  Key and frequency must"
                        + "be separated by '-'");
                System.exit(1);
            }
            
            // Removes unprintable characters and spaces
            for (int j = 0; j < freqEntry.length; j++){
                freqEntry[j] = freqEntry[j].replaceAll("[^\\x20-\\x7e]", "");
                freqEntry[j] = freqEntry[j].trim();
            }
            
            
            freqEntry[0] = freqEntry[0].toUpperCase();
            
            // Error if key is greater than one or not a letter
            if (!Character.isLetter(freqEntry[0].charAt(0)) 
                    || freqEntry[0].length() > 1){
                System.err.println("Not a valid format for frequency table.  "
                        + "Keys must be single letters.");
                System.exit(1);
            }
            
            // Checks to see if frequency is a number
            for (int j = 0; j < freqEntry[1].length(); j++){
                if (!Character.isDigit(freqEntry[1].charAt(j))){
                    System.err.println("Not a valid format for frequency table.  Frequencies"
                            + " must be numbers.");
                    System.exit(1);
                }
            }
            
            array[i] = new TreeNode(freqEntry[0], Integer.valueOf(freqEntry[1]));
        }
        
        sort();
    }
    
    /**
     * Sorts the Huffman Queue first by frequency, then by key length, then
     * alphabetically by key.  The sort method used is a bubble sort.
     */
    private void sort(){
    
        for (int i = top; i < totalKeys - 1; i++){
            int exch = 0;
            for (int j = top; j < totalKeys - 1 - i + top; j++){
                if (array[j].freq > array[j+1].freq){
                    TreeNode temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    exch++;
                }else if (array[j].freq == (array[j+1].freq)){
                    if(array[j].key.length() > array[j+1].key.length()){
                        TreeNode temp = array[j];
                        array[j] = array[j+1];
                        array[j+1] = temp;
                        exch++;
                    }else if (array[j].key.length() == array[j+1].key.length()){
                        if (array[j].key.charAt(0) > array[j+1].key.charAt(0)){
                            TreeNode temp = array[j];
                            array[j] = array[j+1];
                            array[j+1] = temp;
                            exch++;
                        }
                    }
                }
            }
            // Break if no exchanges occur
            if (exch == 0){
                break;
            }
        }
    }
    
    /**
     * Adds new item to queue and sorts
     * @param item TreeNode - new node to be added to queue
     */
    public void add(TreeNode item){
        
        if (top == 0){
            System.err.println("Cannot insert queue full");
        }else{
            top--;
            size++;
            array[top] = item;
        }
        
        sort();
    }
    
    /**
     * Removes the first item in HuffmanQueue and returns it
     * @return TreeNode - item at the top of the queue
     */
    public TreeNode remove(){
        
        TreeNode item = array[top];
        top++;
        size--;
        return item;
    }
    
    /**
     * Checks to see if the queue is empty.
     * @return true if empty, false if not
     */
    public boolean isEmpty(){
        return size == 0;
    }
    
    
}
    
