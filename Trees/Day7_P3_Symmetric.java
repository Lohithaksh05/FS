// Mr. Rakesh is interested in working with Data Structures.

// He has constructed a Binary Tree (BT) and asked his friend 
// Anil to check whether the BT is a self-mirror tree or not.

// Can you help Rakesh determine whether the given BT is a self-mirror tree?
// Return true if it is a self-mirror tree; otherwise, return false.

// Note:
// ------
// In the tree, '-1' indicates an empty (null) node.

// Input Format:
// -------------
// A single line of space separated integers, values at the treenode

// Output Format:
// --------------
// Print a boolean value.


// Sample Input-1:
// ---------------
// 2 1 1 2 3 3 2

// Sample Output-1:
// ----------------
// true


// Sample Input-2:
// ---------------
// 2 1 1 -1 3 -1 3

// Sample Output-2:
// ----------------
// false



import java.util.*;

class Day7_P3_Symmetric{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String[] a = sc.nextLine().split(" ");
        int[] tree = new int[a.length];
        for(int i=0;i<a.length;i++){
            tree[i] = Integer.parseInt(a[i]);
        }
        
        TreeNode root = buildTree(tree);
        System.out.println(symmetric(root.left,root.right));
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
    
    private static boolean symmetric(TreeNode a,TreeNode b){
        if(a==null && b==null) return true;
        if(a==null || b==null){
            return false;
        }
        
        if(a.val!=b.val){
            return false;
        }
        if(a.val==b.val){
            return (symmetric(a.left,b.right)&&symmetric(a.right,b.left));
        }

        return false;
        
        
    }
    
    
}

class TreeNode{
    int val;
    TreeNode left,right;
    TreeNode(int val){
        this.val = val;
    }
}
