// Given the preorder and postorder traversals of a binary tree, construct 
// the original binary tree and print its level order traversal.

// Input Format:
// ---------------
// Space separated integers, pre order data
// Space separated integers, post order data

// Output Format:
// -----------------
// Print the level-order data of the tree.


// Sample Input:
// ----------------
// 1 2 4 5 3 6 7
// 4 5 2 6 7 3 1

// Sample Output:
// ----------------
// [[1], [2, 3], [4, 5, 6, 7]]

// Explanation:
// --------------
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7


// Sample Input:
// ----------------
// 1 2 3
// 2 3 1

// Sample Output:
// ----------------
// [[1], [2, 3]]

// Explanation:
// --------------
//     1
//    / \
//   2  3


import java.util.*;

class Day6_Program1_PrePost{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String[] pre = sc.nextLine().split(" ");
        
        String[] post = sc.nextLine().split(" ");
        int[] preorder = new int[pre.length];
        int[] postorder = new int[post.length];
        
        for(int i=0;i<preorder.length;i++){
            preorder[i] = Integer.parseInt(pre[i]);
            postorder[i] = Integer.parseInt(post[i]);
        }
        
        TreeNode root = buildtree(preorder,postorder);
        System.out.println(levelorder(root));
        sc.close();
        
    }
    
    private static TreeNode buildtree(int[] preorder,int[] postorder){
        TreeNode root = new TreeNode(preorder[0]);
        
        Queue<TreeNode> q = new LinkedList<>();
        
        q.offer(root);

        Set<Integer> visited = new HashSet<>();
        HashMap<Integer,Integer> pre = new HashMap<>();
        HashMap<Integer,Integer> post = new HashMap<>();
        for(int i=0;i<preorder.length;i++){
            pre.put(preorder[i],i);
            post.put(postorder[i],i);
        }
        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            visited.add(root.val);
            int postIdx = post.get(temp.val)-1;
            int preIdx = pre.get(temp.val)+1;
            if(postIdx>=0 && preIdx<preorder.length){
                if(!visited.contains(preorder[preIdx])){
                    temp.left = new TreeNode(preorder[preIdx]);
                    visited.add(preorder[preIdx]);
                    q.offer(temp.left);
                }
                if(!visited.contains(postorder[postIdx])){
                    temp.right = new TreeNode(postorder[postIdx]);
                    visited.add(postorder[postIdx]);
                    q.offer(temp.right);
                }
            }
            
            
        }
        return root;
    }
    
    private static List<List<Integer>> levelorder(TreeNode root){
        
        List<List<Integer>> nodes = new ArrayList<>();
        
        
        Queue<TreeNode> q = new LinkedList<>();
        
        q.offer(root);
        int level = 1;
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> levelNodes = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode temp = q.poll();
                levelNodes.add(temp.val);
                if(temp.left!=null){
                    q.offer(temp.left);
                }
                if(temp.right!=null){
                    q.offer(temp.right);
                }
            }
            
            nodes.add(levelNodes);
            
        }
        return nodes;
    }
}

class TreeNode{
    int val;
    TreeNode left,right;
    TreeNode(int val){
        this.val = val;
    }
}