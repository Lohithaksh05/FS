// Balbir Singh is working with Binary Trees.
// The elements of the tree is given in the level order format.
// Balbir has a task to split the tree into two parts by removing only one edge
// in the tree, such that the product of sums of both the splitted-trees should be maximum.

// You will be given the root of the binary tree.
// Your task is to help the Balbir Singh to split the binary tree as specified.
// print the product value, as the product may be large, print the (product % 1000000007)
	
// NOTE: 
// Please do consider the node with data as '-1' as null in the given trees.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6

// Sample Output-1:
// ----------------
// 110

// Explanation:
// ------------
// if you split the tree by removing edge between 1 and 4, 
// then the sums of two trees are 11 and 10. So, the max product is 110.


// Sample Input-2:
// ---------------
// 3 2 4 3 2 -1 6

// Sample Output-2:
// ----------------
// 100


import java.util.*;
class Day9_P2_MaxProduct{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] a = sc.nextLine().split(" ");
        int[] tree = new int[a.length];
        for(int i=0;i<a.length;i++){
            tree[i] = Integer.parseInt(a[i]);
        }
        
        TreeNode root = buildTree(tree);
        System.out.println(maxProduct(root));
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


    public static int maxProduct(TreeNode root) {
       
        long res[] = new long[1];
        long total = helper(root);
        traverse(root,total,res);
        return (int)(res %1000000007);
    }

    private void traverse(TreeNode root,long total,long[] res){
        if(root==null) return;
        res[0] = Math.max(root.val*(total-root.val),res[0]);
        traverse(root.left,total,res);
        traverse(root.right,total,res);
    }
    
    private static int helper(TreeNode root){
        if(root==null) return 0;
        
        return root.val+= helper(root.left) + helper(root.right);
        
        
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
    
    
    
    
}

class TreeNode{
    int val;
    TreeNode left,right;
    TreeNode(int val){
        this.val = val;
    }
}