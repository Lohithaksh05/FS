// Given the in-order and post-order traversals of a binary tree, construct 
// the original binary tree. For the given Q number of queries, 
// each query consists of a lower level and an upper level. 
// The output should list the nodes in the order they appear in a level-wise.

// Input Format:
// -------------
// An integer N representing the number nodes.
// A space-separated list of N integers representing the similar to in-order traversal.
// A space-separated list of N integers representing the similar to post-order traversal.
// An integer Q representing the number of queries.
// Q pairs of integers, each representing a query in the form:
// Lower level (L)
// Upper level (U)

// Output Format:
// For each query, print the nodes in order within the given depth range

// Example
// Input:
// 7
// 4 2 5 1 6 3 7
// 4 5 2 6 7 3 1
// 2
// 1 2
// 2 3
// Output:
// [1, 2, 3]
// [2, 3, 4, 5, 6, 7]

// Explanation:
//         1
//        / \
//       2   3
//      / \  / \
//     4   5 6  7
// Query 1 (Levels 1 to 2): 1 2 3
// Query 2 (Levels 2 to 3): 2 3 4 5 6 7
     
import java.util.*;

class Day5_P1_BinaryTree{
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] inorder = new int[n];
        int[] postorder = new int[n];
        
        for(int i=0;i<n;i++) inorder[i] = sc.nextInt();
        for(int i=0;i<n;i++) postorder[i] = sc.nextInt();
        int q = sc.nextInt();
        int[][] queries = new int[q][2];
        
        for(int i=0;i<q;i++){
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }
        
        TreeNode root = buildTree(inorder,postorder);
        processQueries(queries,root);
        sc.close();
    }
    static int postIdx;
    private static TreeNode buildTree(int[] inorder, int[] postorder){
        HashMap<Integer,Integer> inorderMap = new HashMap<>();
        postIdx = postorder.length-1;
        
        for(int i=0;i<inorder.length;i++){
            inorderMap.put(inorder[i],i);
        }
        
        return construct(postorder, 0,inorder.length-1,inorderMap);
    }
    
    private static TreeNode construct(int[] postorder, int inStart, int inEnd,HashMap<Integer,Integer> inorderMap){
        
        if(inStart>inEnd) return null;
        int rootval = postorder[postIdx--];

        TreeNode root = new TreeNode(rootval);
        int inidx = inorderMap.get(rootval);
        root.right = construct(postorder,inidx+1,inEnd,inorderMap);
        root.left = construct(postorder,inStart,inidx-1,inorderMap);
        
        return root;
        
    }
    
    private static void processQueries(int[][] queries, TreeNode root){
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
        
        for(int[] query: queries){
            int l = query[0];
            int u = query[1];
            
            List<Integer> res = new ArrayList<>();
            for(int i = l; i<=u;i++){
                if(levels.containsKey(i)){
                    res.addAll(levels.get(i));
                }
            }
            System.out.println(res);
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


