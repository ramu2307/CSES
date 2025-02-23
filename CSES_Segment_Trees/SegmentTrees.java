import java.util.Scanner;

public class SegmentTrees {

    public static void build(int idx, int low, int high, int[] arr, int[] segment) {

        if(low == high) {
            segment[idx] = arr[low];
            return;
        }

        int mid = (low + high)/2;
        build(2 * idx + 1, low , mid, arr, segment);
        build(2 * idx + 2, mid + 1, high, arr, segment);
        segment[idx] = Math.min(segment[2 * idx + 1], segment[2 * idx + 2]);
    }

    public static int query(int idx, int low, int high, int l, int r, int[] segment) {

        //no-overlap case
        // [low high l r] or [l r low high]
        if(l > high || r < low) {
            return Integer.MAX_VALUE;
        }

        //Complete overlap case
        //[l low high r]

        if(l <= low && r >= high) {
            return segment[idx];
        }

        int mid = (low + high) / 2;
        int left = query(2 * idx + 1, low, mid, l, r, segment);
        int right = query(2 * idx + 2, mid + 1, high, l, r, segment);
        return Math.min(left, right);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arr[] = new int[n];
        int segment[] = new int[4 * n];
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        build(0, 0, n - 1, arr, segment);
        int q = sc.nextInt();
        while(q-- > 0) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            System.out.println(query(0, 0, n - 1, l, r, segment));
        }
        sc.close();
    }
}