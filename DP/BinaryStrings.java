import java.util.*;
public class BinaryStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        int p = sc.nextInt();
        int q = sc.nextInt();

        int[] counts = new int[arr.length];
        int idx = 0;
        for(String s: arr){
            int count = 0;
            for(char c: s.toCharArray()){
                if(c=='1') count++;
            }
            counts[idx++] = count;
        }

        System.err.println(solve(arr,p,q,counts,0));
    }

    private static int solve(String[] arr, int p, int q, int[] counts, int idx){
        if(idx >= arr.length) return 0;

        int take = 0;
        if(counts[idx] <= p && arr[idx].length()-counts[idx] <= q){
            take = 1 + solve(arr, p - counts[idx], q - (arr[idx].length()-counts[idx]),counts,idx+1);
        }
        int notake = solve(arr, p, q, counts, idx+1);

        return Math.max(take, notake);
    }
}
