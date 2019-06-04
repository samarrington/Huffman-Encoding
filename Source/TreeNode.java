
package huffmanencoding;

/**
 * TreeNode is a class for the nodes in a Huffman Tree.  Each node has a key and
 * and a frequency associated with that key.  Each node also has a pointer to
 * left child and a pointer to the right child.
 * 
 * @author W. Sam Arrington
 */

public class TreeNode {
    
    // Attributes
    public String key;
    public int freq;
    public TreeNode left;
    public TreeNode right;
    
    /*Constructor:  The key and frequency are set during construction but may be
    altered after construction.  The left and right nodes can be set later.
    */
    
    TreeNode(String key, int freq){
            
        this.key = key;
        this.freq = freq;
        this.left = null;
        this.right = null;
        
    }
    
}
