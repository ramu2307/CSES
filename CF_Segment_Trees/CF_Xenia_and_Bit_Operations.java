import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CF_Xenia_and_Bit_Operations {

     static boolean flag = true;

    public static void build(int idx, int low, int high, long[] arr, long[] segment) {

        if(low == high) {
            segment[idx] = arr[low];
            flag = true;
            return;
        }

        int mid = (low + high)/2;
        build(2 * idx + 1, low, mid, arr, segment);
        build(2 * idx + 2, mid + 1, high, arr, segment);
        if(flag) {
            segment[idx] = (segment[2 * idx + 1] | segment[2 * idx + 2]);
            flag = false;
        }
        else {
            segment[idx] = (segment[2 * idx + 1] ^ segment[2 * idx + 2]);
            flag = true;
        }
        
    }

    public static void update(int idx, int low, int high, int i, long u, long[] segment) {

        if(low == high) {
            segment[idx] = u;
            flag = true;
            return;
        }

        int mid = (low + high)/2;
        if(i <= mid) {
            update(2 * idx + 1, low, mid, i, u, segment);
        }
        else {
            update(2 * idx + 2, mid + 1, high, i, u, segment);
        }

        if(flag) {
            segment[idx] = (segment[2 * idx + 1] | segment[2 * idx + 2]);
            flag = false;
        }
        else {
            segment[idx] = (segment[2 * idx + 1] ^ segment[2 * idx + 2]);
            flag = true;
        }

    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        n = 1 << n;
        long[] arr = readArr(n, br, st);
        long[] segment = new long[4 * n];
        flag = true;
        build(0, 0, n - 1, arr, segment);
        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()) - 1;
            long u = Long.parseLong(st.nextToken());
            flag = true;
            update(0, 0, n - 1, k, u, segment);
            sb.append(segment[0]).append("\n");
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