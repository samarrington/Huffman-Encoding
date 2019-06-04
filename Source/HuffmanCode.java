
package huffmanencoding;

/**
 *
 * @author W. Sam Arrington
 */
public final class HuffmanCode {
    
    /**
     * String array with key being in the first column and code
     * in the second
     */
    public String[][] encoding;

    /**
     * String containing the preorder traversal of the Huffman tree.
     */
    public String trav;
    
    /**
     * String containing the encoding of each key in form 'A = 19'
     */
    public String encode;
    /**
     * The Huffman tree associated with the code.
     */
    public BinaryTree huffTree;
    
    
    /**
     * HuffmanCode generates an array containing the encoding, a string containing
     * the preorder traversal of the Huffman tree, and a string containing the encoding
     * 
     * @param HuffmanTree Huffman tree used to generate encoding
     */
    HuffmanCode(BinaryTree HuffmanTree){
        
        huffTree = HuffmanTree;
        
        StringBuffer encodeBuff = new StringBuffer();
        StringBuffer travBuff = new StringBuffer();
        MakeHuffmanCode(HuffmanTree, "", encodeBuff, travBuff);
        
        trav = travBuff.toString();
        encode = encodeBuff.toString();

        // Splits encode string and forms array encoding array and sorts by length of code
        String[] lines = encode.split(System.getProperty("line.separator"));
        encoding = new String[lines.length][2];
        for (int i = 0; i < encoding.length; i++){
            String[] codeEntry = lines[i].split(" = ");
            encoding[i] = codeEntry;          
        }
        sort();
    }
    
    /**
     * Recursive function that traverses Huffman tree and generates encoding
     * @param tree Huffman tree used to generate encoding
     * @param code keeps track of encoding for a leaf after recursive calls
     * @param encode string containing encoding
     * @param trav string containing preorder traversal of tree
     */
    private void MakeHuffmanCode(BinaryTree tree, String code,
            StringBuffer encode, StringBuffer trav){
        
        String newLine = System.getProperty("line.separator");
        trav.append(tree.root.key + ": " + Integer.toString(tree.root.freq) + newLine);
        
        // Checks to see if leaf, if so it adds encoding to string
        if (tree.root.left == null && tree.root.right == null){
            encode.append(tree.root.key + " = " + code + newLine);
            return;
        }
        
        // Recursively calls with left child as root and adds '0' to code for next leaf
        MakeHuffmanCode(new BinaryTree(tree.root.left), code + "0", encode, trav);
        
        // Recursiveily calls with right child as root and adds '1' to code for next leaf
        MakeHuffmanCode(new BinaryTree(tree.root.right), code + "1", encode, trav);
    }
    
    /**
     * Sorts the array containing the code by length.  Shorter length codes corresponding
     * to more frequently used characters so sorting allows for you to search for
     * key/code quickly
     */
    public void sort(){
        
        for (int i = 0; i < encoding.length - 1 ;  i++){
            int exch = 0;
            for(int j = 0; j < encoding.length - 1 - i; j++ ){
                if(encoding[j][1].length() > encoding[j+1][1].length()){
                    String[] temp = encoding[j];
                    encoding[j] = encoding[j+1];
                    encoding[j+1] = temp;
                    exch++;
                }    
            }
            
            // if no exchanges occur, don't make another pass
            if (exch == 0){
                break;
            }
        }
    }
    
    /**
     * Searches for key in encoding array and returns the encoding if found.
     * @param key the character that needs to be encoded.
     * @return the encoding for the desired key
     */
    private String getCode(char key){
        
        for (int i = 0; i < encoding.length; i++){
            if (encoding[i][0].charAt(0) == key){
                return encoding[i][1];
            }
        }
        System.err.println("Character not found. Character invalid.");
        return null;
        
    }
    
    /**
     * Takes a plain text and encodes it
     * @param clearLine String of plain text
     * @return encoded version of input text
     */
    public String encodeLine(String clearLine){
        
        String cleanLine = clearLine.replaceAll("[^a-zA-Z]","").toUpperCase();
        StringBuffer encodeText = new StringBuffer();
        
        for (int i = 0; i < cleanLine.length(); i++){
            String letterCode = getCode(cleanLine.charAt(i));
            encodeText.append(letterCode);
        }
        
        return(encodeText.toString());
    }
    
    /**
     * Decodes a line of encoded text
     * @param code encoded text
     * @return decoded text
     */
    public String decodeLine(String code){
        
        StringBuffer clearText = new StringBuffer();
        
        TreeNode topRoot = huffTree.root;
        TreeNode check = huffTree.root;
        
        for (int i = 0; i < code.length(); i++){
  
            if (code.charAt(i) == '0'){
                    check = check.left;
            }else if (code.charAt(i) == '1'){
                    check = check.right;
            }else{
                return("Not a valid code.  Only 1s or 0s allowed.");
            }
            
            if (check.key.length() == 1){
                clearText.append(check.key);
                check = topRoot;
            }
        }
        
        return (clearText.toString());
    }
}
