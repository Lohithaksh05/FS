// Balbir Singh is working with Binary Trees.
// The elements of the tree are given in level-order format.

// Balbir is observing the tree from the right side, meaning he 
// can only see the rightmost nodes (one node per level).

// You are given the root of a binary tree. Your task is to determine 
// the nodes visible from the right side and return them in top-to-bottom order.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// A list of integers representing the node values visible from the right side


// Sample Input-1:
// ---------------
// 1 2 3 5 -1 -1 5

// Sample Output-1:
// ----------------
// [1, 3, 5]



// Sample Input-2:
// ---------------
// 3 2 4 3 2

// Sample Output-2:
// ----------------
// [3, 4, 2]



import java.util.*;

class Day6_P2_RightView {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String[] a = sc.nextLine().split(" ");
        
        int[] tree = new int[a.length];
        for(int i=0;i<a.length;i++){
            tree[i] = Integer.parseInt(a[i]);
        }
        
        TreeNode root = buildTree(tree);
        System.out.println(rightview(root));
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
    
    private static List<Integer> rightview(TreeNode root){
        List<Integer> rightnodes = new ArrayList<>();
        if(root==null) return rightnodes;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size = q.size();
            TreeNode lastNode = null;
            for(int i=0;i<size;i++){
                TreeNode temp = q.poll();
                lastNode = temp;
                if(temp.left!=null){
                    q.offer(temp.left);
                }
                if(temp.right!=null){
                    q.offer(temp.right);
                }
            }
            if(lastNode!=null)
            rightnodes.add(lastNode.val);
            
        }
        return rightnodes;
    }
}

class TreeNode{
    int val;
    TreeNode left,right;
    TreeNode(int val){
        this.val = val;
    }
}