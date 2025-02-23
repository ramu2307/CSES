import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CSES_Static_Range_Sum_Queries {

    public static void build(int idx, int low , int high, long[] arr, long[] segment) {

        if(low == high) {
            segment[idx] = arr[low];
            return;
        }

        int mid = (low + high)/2;
        build(2 * idx + 1, low, mid, arr, segment);
        build(2 * idx + 2, mid + 1, high, arr, segment);
        segment[idx] = (segment[2 * idx + 1] + segment[2 * idx + 2]);
    }

    public static long query(int idx, int low, int high, int l, int r, long[] segment) {

        //no-overlap case
        //[low high l r] or [l r low high]
        if(high < l || low > r) {
            return 0;
        }

        //complete overlap case
        //[l low high r]
        if(low >= l && high <= r) {
            return segment[idx];
        }

        int mid = (low + high)/2;
        long left = query(2 * idx + 1, low, mid, l, r, segment);
        long right = query(2 * idx + 2, mid + 1, high, l, r, segment);
        return (left + right);
    }

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        long[] arr = readArr(n, br, st);
        long[] segment = new long[4 * n];
        build(0, 0, n - 1, arr, segment);

        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            sb.append(query(0, 0, n - 1, l, r, segment)).append("\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    public static long[] readArr(int n, BufferedReader br, StringTokenizer st) throws IOException{
        st = new StringTokenizer(br.readLine());
        long[] arr = new long[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        return arr;
    }
}
