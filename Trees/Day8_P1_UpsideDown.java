
// VishnuVardan is working with Decision Trees for AI-based predictions.
// To analyze alternative outcomes, Kishore has planned to flip the decision 
// tree horizontally to simulate a reverse processing approach.

// Rules for Flipping the Decision Tree:
// 	- The original root node becomes the new rightmost node.
// 	- The original left child becomes the new root node.
// 	- The original right child becomes the new left child.
// This transformation is applied level by level recursively.

// Note:
// ------
// - Each node in the given tree has either 0 or 2 children.
// - Every right node in the tree has a left sibling sharing the same parent.
// - Every right node has no further children (i.e., they are leaf nodes).

// Your task is to help VishnuVardan flip the Decision Tree while following 
// the given transformation rules.

// Input Format:
// -------------
// Space separated integers, nodes of the tree.

// Output Format:
// --------------
// Print the list of nodes of flipped tree as described below.


// Sample Input-1:
// ---------------
// 4 2 3 5 1

// Sample Output-1:
// ----------------
// 5 1 2 3 4


// Sample Input-2:
// ---------------
// 4 2 5

// Sample Output-2:
// ----------------
// 2 5 4

import java.util.*;
class Day8_P1_UpsideDown{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] a = sc.nextLine().split(" ");
        int[] tree = new int[a.length];
        for(int i=0;i<a.length;i++){
            tree[i] = Integer.parseInt(a[i]);
        }
        
        TreeNode root = buildTree(tree);
        
        TreeNode newRoot = flip(root);
        levelorder(newRoot);
        
        
    }
    
    private static TreeNode buildTree(int[] nodes){
        if(nodes.length==0 || nodes[0] == -1) return null; 
        TreeNode root = new TreeNode(nodes[0]);
        
        Queue<TreeNode> q = new LinkedList<>();
        
        q.offer(root);
        int idx = 1;
        while(!q.isEmpty() && idx<nodes.length){
            TreeNode temp = q.poll();
            
            if(idx<nodes.length && nodes[idx]!=-1){
                temp.left = new TreeNode(nodes[idx]);
                q.offer(temp.left);
                
            }
            idx++;
            if(idx<nodes.length &&nodes[idx]!=-1){
                temp.right = new TreeNode(nodes[idx]);
                q.offer(temp.right);
            }
            idx++;
        }
        
        return root;
    }
    
    private static void levelorder(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> levelNodes = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode temp = q.poll();
                System.out.print(temp.val + " ");
                if(temp.left!=null){
                    q.offer(temp.left);
                }
                if(temp.right!=null){
                    q.offer(temp.right);
                }
            }
            
            
        }
    }
    
    
    private static TreeNode flip(TreeNode root){
        if(root==null || root.left==null){
            return root;
        }
        
        TreeNode newNode = flip(root.left);
        
        root.left.right = root;
        root.left.left = root.right;
        root.left=null;
        root.right=null;
        return newNode;
    }
}

class TreeNode{
    int val;
    TreeNode left,right;
    TreeNode(int val){
        this.val = val;
    }
}