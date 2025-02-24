// Imagine you are designing a network of secret corridors in an ancient castle. 
// Each chamber in the castle leads to at most two other chambers through 
// hidden passageways, forming a branching layout. 
// The castle’s "longest secret route" is defined as the maximum number of corridors 
// you must traverse to get from one chamber to another (without repeating the corridor). 
// This route may or may not pass through the main entry chamber.

// Your task is to compute the length of longest secret route between 
// two chambers which is represented by the number of corridors between them.

// Example 1
// input=
// 1 2 3 4 5 
// output=
// 3

// Structure:
//        1
//       / \
//      2   3
//     / \
//    4   5

// Explanation:
// The longest secret route from chamber 4 to chamber 3 
// (alternatively, from chamber 5 to chamber 3) along the route:
// 4 → 2 → 1 → 3
// This path has 3 corridors (4–2, 2–1, 1–3), so the length is 3.

// Example 2:
// input=
// 1 -1 2 3 4
// output=
// 2

// Structure:
//    1
//     \
//      2
//     / \
//    3   4

// Explanation:
// The longest secret route from chamber 3 to chamber 4 
// (alternatively, from chamber 1 to chamber 4) along the route:
// 3 → 2 → 4
// This path has 2 corridors (3–2, 2–4), so the length is 2.


import java.util.*;

class Day11_P3_SecretRoute
{
    static int maxLength = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String[] a = sc.nextLine().split(" ");
        int[] tree = new int[a.length];
        for(int i=0;i<a.length;i++){
            tree[i] = Integer.parseInt(a[i]);
        }
        
        TreeNode root = buildTree(tree);
        int r = secretRoute(root);
        System.out.println(maxLength);
        
        
    }
    
    
    private static int secretRoute(TreeNode root){
        if(root==null){
            return 0;
        }
        
        int lheight = secretRoute(root.left);
        int rheight = secretRoute(root.right);
        maxLength = Math.max(maxLength, lheight + rheight);
        return Math.max(lheight, rheight) + 1;
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
    
}

class TreeNode {
    int val;
    TreeNode left, right;
    
    TreeNode(int data){
        val = data;
    }
}