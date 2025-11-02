import java.util.Scanner;

class UserMainCode{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] arr = new int[n];

        for(int i=0;i<n;i++){
            arr[i] = sc.nextInt();

        }

        int curr = 0;
        for(int i=0;i<k;i++){
            curr += arr[i];
        }
        int max = 0;
        int temp = k -1;
        for(int i=0;i<k;i++){

            curr -= arr[temp--];
            curr += arr[n - i - 1];

            max = Math.max(max, curr);
        }

        System.out.println(max);

    }
}