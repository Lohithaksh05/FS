// Bubloo is working with computer networks, where servers are connected 
// in a hierarchical structure, represented as a Binary Tree. Each server (node) 
// is uniquely identified by an integer value.

// Bubloo has been assigned an important task: find the shortest communication 
// path (in terms of network hops) between two specific servers in the network.

// Network Structure:
// ------------------
// The network of servers follows a binary tree topology.
// Each server (node) has a unique identifier (integer).
// If a server does not exist at a certain position, it is represented as '-1' (NULL).

// Given the root of the network tree, and two specific server IDs (E1 & E2), 
// determine the minimum number of network hops (edges) required to 
// communicate between these two servers.

// Input Format:
// -------------
// Space separated integers, elements of the tree.

// Output Format:
// --------------
// Print an integer value.


// Sample Input-1:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 4 8

       
// Sample Output-1:
// ----------------
// 4

// Explanation:
// ------------
// The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


// Sample Input-2:
// ---------------
// 1 2 4 3 5 6 7 8 9 10 11 12
// 6 6

// Sample Output-2:
// ----------------
// 0

// Explanation:
// ------------
// No edegs between 6 and 6.


import java.util.*;

class Day7_P1_FindDistance{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        
        String[] a = sc.nextLine().split(" ");
        int start = sc.nextInt();
        int end = sc.nextInt();
        int[] tree = new int[a.length];
        for(int i=0;i<a.length;i++){
            tree[i] = Integer.parseInt(a[i]);
        }
        
        TreeNode root = buildTree(tree);
        System.out.println(distance(root,start,end));
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
    
    
    private static int distance(TreeNode root, int start, int end){
        if(start==end) return 0;
        List<Integer> s1 = path(root,start);
        List<Integer> s2 = path(root,end);
        
        int count = 0;
        int i= s1.size()-1, j = s2.size()-1;
        while(i>=0 && j>=0){
            if(s1.get(i--)==s2.get(j--)){
                count++;
            }else{
                break;
            }
         
   
        }
        
        return s1.size() + s2.size() - 2*count;
    }
    
    private static List<Integer> path(TreeNode root,int target){
        List<Integer> p = new ArrayList<>();
        
        preorder(root,p,target);
        return p;
    }
    
    private static boolean preorder(TreeNode root, List<Integer> p, int target){
        if(root==null || root.val==-1 ){
            return false;
        }
        if(root.val==target){
            p.add(root.val);
            return true;
        }
        
        
        if(preorder(root.left,p,target)||preorder(root.right,p,target)){
            p.add(root.val);
            return true;
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