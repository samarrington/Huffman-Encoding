
package huffmanencoding;

/**
 * The class BinaryTree is a binary tree with a pointer to the root of the tree.
 * New nodes can be linked to tree with addLeft and addRight, or by editing 
 * the left and right attributes of a node.
 * 
 * @author W. Sam Arrington
 */
public class BinaryTree {
    
    TreeNode root;
    
    public BinaryTree(){
       root = null;
    }
    
    public BinaryTree(TreeNode root){
        this.root = root;
    }
    
    public void addLeft(TreeNode parent, TreeNode child){
        parent.left = child;
    }
    
    public void addRight(TreeNode parent, TreeNode child){
        parent.right = child;
    }
    
    
    public boolean TreeEmpty(){
        return(root == null);
    }
    
}
