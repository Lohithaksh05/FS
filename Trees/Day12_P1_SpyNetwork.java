// Imagine you’re decoding a secret message that outlines the hierarchical structure 
// of a covert spy network. The message is a string composed of numbers and parentheses. 
// Here’s how the code works:

// - The string always starts with an agent’s identification number, this is the 
//   leader of the network.
// - After the leader’s ID, there can be zero, one, or two segments enclosed in 
//   parentheses. Each segment represents the complete structure of one subordinate 
//   network.
// - If two subordinate networks are present, the one enclosed in the first (leftmost) 
//   pair of parentheses represents the left branch, and the second (rightmost) 
//   represents the right branch.

// Your mission is to reconstruct the entire spy network hierarchy based on this 
// coded message.

// Example 1:
// Input: 4(2(3)(1))(6(5))
// Output: [4, 2, 6, 3, 1, 5]

// Spy network:
//         4
//        / \
//       2   6
//      / \  /
//     3   1 5

// Explanation:
// Agent 4 is the leader.
// Agent 2 (with its own subordinates 3 and 1) is the left branch.
// Agent 6 (with subordinate 5) is the right branch.

// Example 2:
// Input: 4(2(3)(1))(6(5)(7))
// Output: [4, 2, 6, 3, 1, 5, 7]

// Spy network:
//          4
//        /   \
//       2     6
//      / \   / \
//     3   1 5   7

// Explanation:
// Agent 4 leads the network.
// Agent 2 with subordinates 3 and 1 forms the left branch.
// Agent 6 with subordinates 5 and 7 forms the right branch.






import java.util.*;

class Day12_P1_SpyNetwork
{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();

        
        TreeNode root = buildTree(a);
        System.out.println(levelOrder(root));
        
    }
    
    private static int[] strToInt(String s, int i){
        int neg = 1;
        if(s.charAt(i)=='-'){
            neg = -1;
            i++;
        }
        String temp = "";
        while(Character.isDigit(s.charAt(i))){
            temp=temp + (""+s.charAt(i));
            i++;
        }
        
        return new int[]{neg * Integer.parseInt(temp),i};
        
    }
    
    private static TreeNode buildTree(String s){
        Stack<TreeNode> st = new Stack<>();
        int idx = 0;
        s+=")";
        
        int[] a = strToInt(s,idx);
        idx = a[1];
        
        TreeNode root = new TreeNode(a[0]);
        st.push(root);
        
        while(!st.isEmpty()){
            
            if(s.charAt(idx)=='('){
                a = strToInt(s,idx+1);
                idx = a[1];
                TreeNode node = new TreeNode(a[0]);
                TreeNode temp = st.peek();
                if(temp.left==null) {
                    temp.left = node;
                }
                else if(temp.right==null){  
                    temp.right = node;
                }
                st.push(node);
            }else if(s.charAt(idx)==')'){
                st.pop();
                idx++;
            }
        }
        return root;
    }
    
    
    
    private static List<Integer> levelOrder(TreeNode root){
        
        Queue<TreeNode> q = new LinkedList<>();
        List<Integer> al = new ArrayList<>();
        q.offer(root);
        while(!q.isEmpty()){
            TreeNode temp = q.poll();
            al.add(temp.val);
            if(temp.left!=null) q.offer(temp.left);
            if(temp.right!=null) q.offer(temp.right);
        }
        
        return al;
    }
}

class TreeNode {
    int val;
    TreeNode left, right;
    
    TreeNode(int data){
        val = data;
    }
}

