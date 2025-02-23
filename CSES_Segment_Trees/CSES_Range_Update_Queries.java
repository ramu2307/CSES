import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CSES_Range_Update_Queries {

    public static long query(int idx, int low, int high, int l, int r, long[] segment, long[] lazy) {

        if(lazy[idx] != 0) {
            segment[idx] += lazy[idx];
            if(low != high) {
                lazy[2 * idx + 1] += lazy[idx];
                lazy[2 * idx + 2] += lazy[idx];
            }
            lazy[idx] = 0;
        }

        //no-overlap
        // [low high l r] or [l r low high]
        if(high < l || low > r) {
            return 0;
        }

        //complete overlap case
        //[l low high r]
        if(low >= l && high <= r) {
            return segment[idx];
        }

        int mid = (low + high)/2;
        long left = query(2 * idx + 1, low, mid, l, r, segment, lazy);
        long right = query(2 * idx + 2, mid + 1, high, l, r, segment, lazy);
        return left + right;

    }

    public static void lazyUpdate(int idx, int low, int high, long u, int l, int r,long[] segment, long[] lazy) {

        if(lazy[idx] != 0) {
            segment[idx] += lazy[idx];
            if(low != high) {
                lazy[2 * idx + 1] += lazy[idx];
                lazy[2 * idx + 2] += lazy[idx];
            }
            lazy[idx] = 0;
        }

        //no-overlap
        // [low high l r] or [l r low high]
        if(high < l || low > r) {
            return;
        }

        //complete overlap case
        //[l low high r]
        if(low >= l && high <= r) {
            segment[idx] += u;
            if(low != high) {
                lazy[2 * idx + 1] += u;
                lazy[2 * idx + 2] += u;
            }
            return;
        }

        int mid = (low + high)/2;
        lazyUpdate(2 * idx + 1, low, mid, u, l, r, segment, lazy);
        lazyUpdate(2 * idx + 2, mid + 1, high, u, l, r, segment, lazy);
        segment[idx] = Math.min(segment[2 * idx + 1], segment[2 * idx + 2]);

    }

    public static void build(int idx, int low, int high, long[] segment, long[] arr) {

        if(low == high) {
            segment[idx] = arr[low];
            return;
        }

        int mid = (low + high)/2;
        build(2 * idx + 1, low, mid, segment, arr);
        build(2 * idx + 2, mid + 1, high, segment, arr);

        segment[idx] = Math.min(segment[2 * idx + 1], segment[2 * idx + 2]);
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        long[] arr = readArr(n, br, st);
        long[] segment = new long[4 * n];
        long[] lazy = new long[4 * n];
        build(0, 0, n - 1, segment, arr);
        while(q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());

            if(type == 1) {
                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = Integer.parseInt(st.nextToken()) - 1;
                long u = Long.parseLong(st.nextToken());
                lazyUpdate(0, 0, n - 1, u, l, r, segment, lazy);
            }
            else {
                int l = Integer.parseInt(st.nextToken()) - 1;
                int r = l;
                sb.append(query(0, 0, n - 1, l, r, segment, lazy)).append("\n");
            }
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
