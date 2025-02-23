import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CSES_Hotel_Queries {

    public static int query(int idx, int low, int high, long u, long[] segment) {
        if(segment[idx] < u) {
            return -1;
        }

        if(low == high) {
            return low;
        }

        int mid = (low + high)/2;
        if(segment[2 * idx + 1] >= u) {
            return query(2 * idx + 1, low, mid, u, segment);
        }
        else {
            return query(2 * idx + 2, mid + 1, high, u, segment);
        }
    }

    public static void update(int idx, int low, int high, int i, long u, long[] segment) {

        if(low == high) {
            segment[idx] -= u;
            return;
        }

        int mid = (low + high)/2;
        if(i <= mid) {
            update(2 * idx + 1, low, mid, i, u, segment);
        }
        else {
            update(2 * idx + 2, mid + 1, high, i, u, segment);
        }

        segment[idx] = Math.max(segment[2 * idx + 1], segment[2 * idx + 2]);

    }

    public static void build(int idx, int low, int high, long[] arr, long[] segment) {

        if(low == high) {
            segment[idx] = arr[low];
            return;
        }

        int mid = (low + high)/2;
        build(2 * idx + 1, low, mid, arr, segment);
        build(2 * idx + 2, mid + 1, high, arr, segment);

        segment[idx] = Math.max(segment[2 * idx + 1], segment[2 * idx + 2]);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        long[] arr = readArr(n, br, st);
        long[] segment = new long[4 * n];
        build(0, 0, n - 1, arr, segment);
        long[] que = readArr(q, br, st);
        for(int i = 0; i < q; i++) {
            int ans = query(0, 0, n - 1, que[i], segment);
            if(ans != -1) {
                update(0, 0, n - 1, ans, que[i], segment);   
            }
            sb.append(ans + 1).append(" ");
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
