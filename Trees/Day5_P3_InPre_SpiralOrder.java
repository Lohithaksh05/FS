// Construct the binary tree from the given In-order and Pre-order. 
// Find Nodes Between Two Levels in Spiral Order.
// The spiral order is as follows:
// -> Odd levels → Left to Right.
// -> Even levels → Right to Left.

// Input Format:
// --------------
// An integer N representing the number of nodes.
// A space-separated list of N integers representing the in-order traversal.
// A space-separated list of N integers representing the pre-order traversal.
// Two integers:
// Lower Level (L)
// Upper Level (U)

// Output Format:
// Print all nodes within the specified levels, but in spiral order.

// Example:
// Input:
// 7
// 4 2 5 1 6 3 7
// 1 2 4 5 3 6 7
// 2 3

// Output:
// 3 2 4 5 6 7

// Explanation:
// Binary tree structure:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7

// Levels 2 to 3 in Regular Order:
// Level 2 → 2 3
// Level 3 → 4 5 6 7

// Spiral Order:
// Level 2 (Even) → 3 2 (Right to Left)
// Level 3 (Odd) → 4 5 6 7 (Left to Right)

import java.util.*;

class Day5_P3_InPre_SpiralOrder{
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] inorder = new int[n];
        int[] preorder = new int[n];
        
        for(int i=0;i<n;i++) inorder[i] = sc.nextInt();
        for(int i=0;i<n;i++) preorder[i] = sc.nextInt();
        int l = sc.nextInt();
        int u = sc.nextInt();
        
        TreeNode root = buildTree(inorder,preorder);
        display(l,u,root);
        sc.close();
        
    }
    static int preIdx;
    private static TreeNode buildTree(int[] inorder, int[] preorder){
        HashMap<Integer,Integer> inorderMap = new HashMap<>();
        preIdx = 0;
        
        for(int i=0;i<inorder.length;i++){
            inorderMap.put(inorder[i],i);
        }
        
        return construct(preorder, 0,inorder.length-1,inorderMap);
    }
    
    private static TreeNode construct(int[] preorder, int inStart, int inEnd,HashMap<Integer,Integer> inorderMap){
        
        if(inStart>inEnd) return null;
        int rootval = preorder[preIdx++];

        
        TreeNode root = new TreeNode(rootval);
        int inidx = inorderMap.get(rootval);
        root.left = construct(preorder,inStart,inidx-1,inorderMap);
        root.right = construct(preorder,inidx+1,inEnd,inorderMap);
        
        return root;
        
    }
    
    private static void display(int l ,int u, TreeNode root){
        HashMap<Integer,List<Integer>> levels = new HashMap<>();
        if(root==null) return;
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
            
            levels.put(level,levelNodes);
            level++;
            
        }
        
        
            List<Integer> res = new ArrayList<>();
            for(int i = l; i<=u;i++){
                if(levels.containsKey(i)){
                    List<Integer> temp = levels.get(i);
                    if(i%2==0){
                        Collections.reverse(temp);
                    }
                    res.addAll(temp);
                }
            }
            
            for(int i=0;i<res.size();i++){
                System.out.print(res.get(i) + " ");
            }
        }
        
        
        
    
}


class TreeNode{
    int val;
    TreeNode left,right;
    TreeNode(int val){
        this.val = val;
        left = null;
        right = null;
    }
}


