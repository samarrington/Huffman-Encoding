
package huffmanencoding;
import java.io.*;

/**
 * HuffmanEncoding class contains the necessary tools to Huffman encode plain text and 
 * decode Huffman encoded text given a text file with a frequency table. The inputs
 * necessary are a file containing a frequency table for characters, a file containing
 * a message to encode, and a file containing a message to decode.  The outputs are
 * a file containing the preorder traversal of the Huffman tree and the encoding,
 * a file containing the plain text message encoded, and a file containing the
 * encoded message decoded.  Running the main method will take the frequency table
 * file and create a priority queue necessary to build a Huffman Tree.  A Huffman
 * tree will be built from this queue.  From this tree, the Huffman encoding will
 * be generated and outputted to a desired *.txt file along with the preorder
 * traversal of the tree.  Then, it will take the plain text and encode, outputting
 * the result to a desired *.txt file.  Then, it will take inputted encoding text,
 * decode it, and output the result to the desired *.txt file. 
 * 
 * @author W. Sam Arrington
 */
public class HuffmanEncoding {
    
    /**
     * Makes Huffman Tree creates a HuffmanTree given an initial HuffmanQueue(frequency table).
     * 
     * @param que
     * @return
     */
    public static BinaryTree MakeHuffmanTree(HuffmanQueue que){
        
        BinaryTree HuffTree = new BinaryTree();
        
        
        while(que.size > 1){
            
            // First minimum frequency
            TreeNode temp1 = que.remove();
            
            // Second minimum frequency
            TreeNode temp2 = que.remove();
            
            // New node which is sum of first two nodes
            String tempCkey = StringSort(temp1.key + temp2.key);
            int tempCfreq = temp1.freq + temp2.freq;
            TreeNode tempC = new TreeNode(tempCkey, tempCfreq);
            
            /* Setting first min and second min as children of summed node and
            root to summed node
            */
            tempC.left = temp1;
            tempC.right = temp2;
            HuffTree.root = tempC;
            
            // Adding summed node to Huffman Queue
            que.add(tempC);                  
        }
        
        
        return HuffTree;
    }
    /**
     * Sorts a string of letters with no spaces alphabetically with bubble sort.  
     * @param input string to be sorted
     * @return sorted string
     */
    private static String StringSort(String input){
        
        // Breaks string into array of values
        int length = input.length();
        String[] inArray = input.split("");
        
        // Sorts array of values
        for (int i = 0; i < length - 1; i++){
            for (int j = 0; j < length - i - 1; j++){
                if (inArray[j].charAt(0) > inArray[j+1].charAt(0)){
                    String temp = inArray[j];
                    inArray[j] = inArray[j+1];
                    inArray[j+1] = temp;
                }
            }
        }
        
        // Rejoins array into string
        String output = String.join("", inArray);
        return output;
    }
       
    /**
     * 
     * @param args 1st is *.txt input file with frequency table in form 'A - 19'.  All
     * entries must be on separate rows.  Only uppercase letters can be used.
     * 2nd is output *.txt file name for preorder traversal of Huffman tree and the encoding list.
     * 3rd is an *.txt input file containing clear text to be encoded.  All 
     * punctuation and spaces will be ignored.  Lowercase letters will be treated
     * as uppercase.  
     * 4th is a *.txt output file name for the encoding of the inputted clear text.
     * 5th is an input *.txt file containing a message to be decoded.
     * 6th is a *.txt output file name for the decoded message.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // Creates original HuffmanQueue from frequency table input file
        String ftFileName = args[0];
        HuffmanQueue fTable = new HuffmanQueue(ftFileName);
        
        // Creates HuffmanTree based on frequency table input file using HuffmanQueue
        BinaryTree HuffmanTree = MakeHuffmanTree(fTable);
        
        // Creates a file for preorder tree traversal and encoding
        File outputFileTE = new File(args[1]);
        FileWriter fwTE = new FileWriter(outputFileTE);
        PrintWriter outputTE = new PrintWriter(fwTE);
        
        // Writes tree traversal and encoding to file
        HuffmanCode Encoding = new HuffmanCode(HuffmanTree);
        outputTE.println("The tree in preorder is:");
        outputTE.println(Encoding.trav);
        outputTE.println("The Huffman encoding as they appear in preorder is:");        
        outputTE.println(Encoding.encode);
        outputTE.close();
        
        // Reads input clear text file
        String ctFileName = args[2];
        FileInputStream inCT = new FileInputStream(ctFileName);
        BufferedReader brCT = new BufferedReader(new InputStreamReader(inCT));
        
        // Creates file to output encoding message from input clear text file
        File outputFileENC = new File(args[3]);
        FileWriter fwENC = new FileWriter(outputFileENC);
        PrintWriter outputENC = new PrintWriter(fwENC);
        outputENC.println("The encoding from the input clear text"
                + " file with non-letters ommitted goes as follows:");
        outputENC.println();
        
        /* Reads lines of input plain text file and writes encoding to file
        after removing unwanted characters
        */
        while (brCT.ready()){
            
            String clearLine = brCT.readLine();
            // Removes unprinted characters
            clearLine = clearLine.replaceAll("[^\\x20-\\x7e]", "");
            clearLine = clearLine.trim();
            if (clearLine.length() > 0){
                String encodeLine = Encoding.encodeLine(clearLine);
                outputENC.println(clearLine + ": " + encodeLine);
                outputENC.println();
            }
        }
        outputENC.close();
        
        
        // Creates reader for input file with encoded text
        String enFileName = args[4];
        FileInputStream inEN = new FileInputStream(enFileName);
        BufferedReader brEN = new BufferedReader(new InputStreamReader(inEN));
        
        // Creates file to output dencoded message from input encoded text file
        File outputFileDC = new File(args[5]);
        FileWriter fwDC = new FileWriter(outputFileDC);
        PrintWriter outputDC = new PrintWriter(fwDC);
        outputDC.println("The decoding from the input encoded text file with "
                + "non-letters ommitted goes as follow:");
        outputDC.println();
        
        /* Reads lines of input encoded text file and writes decoded text to file
        after removing unwanted characters
        */
        while (brEN.ready()){
            String codeLine = brEN.readLine();
            codeLine = codeLine.replaceAll("[^\\x20-\\x7e]", "");
            codeLine = codeLine.trim();
            if (codeLine.length() > 0){
                String clearLine = Encoding.decodeLine(codeLine);
                outputDC.println(codeLine + ": " + clearLine);
                outputDC.println();
            }
        }
        
        outputDC.close();        
        
        
    }
}
