import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CSES_Salary_Queries {

    public static void update(int idx, int low, int high, int i, long u, ArrayList<Long>[] segment, long[] arr) {

        if(low == high) {
            segment[idx].clear();
            segment[idx].add(u);
            return;
        }

        int mid = (low + high)/2;
        if(i <= mid) {
            update(2 * idx + 1, low, mid, i, u, segment, arr);
        } else {
            update(2 * idx + 2, mid + 1, high, i, u, segment, arr);
        }

        segment[idx] = merge(segment[2 * idx + 1], segment[2 * idx + 2]);
    }

     public static int query(int idx, int low, int high, long l, long r, ArrayList<Long>[] segment) {

        // no-overlap case
        if(r < segment[idx].get(0) || l > segment[idx].get(segment[idx].size() - 1)) {
            return 0;
        }

        // complete overlap case
        if(segment[idx].get(0) >= l && segment[idx].get(segment[idx].size() - 1) <= r) {
            return segment[idx].size();
        }

        int mid = (low + high) / 2;
        int left = query(2 * idx + 1, low, mid, l, r, segment);
        int right = query(2 * idx + 2, mid + 1, high, l, r, segment);
        return left + right;
    }

    public static void build(int idx, int low, int high, long[] arr, ArrayList<Long>[] segment) {

        if(low == high) {
            segment[idx].add(arr[low]);
            return;
        }

        int mid = (low + high)/2;
        build(2 * idx + 1, low, mid, arr, segment);
        build(2 * idx + 2, mid + 1, high, arr, segment);
        segment[idx] = merge(segment[2 * idx + 1], segment[2 * idx + 2]);
    }

    public static ArrayList<Long> merge(ArrayList<Long> left, ArrayList<Long> right) {
        ArrayList<Long> ans = new ArrayList<>();
        int i = 0, j = 0;
        while(i < left.size() && j < right.size()) {
            if(left.get(i) < right.get(j)) {
                ans.add(left.get(i++));
            }
            else {
                ans.add(right.get(j++));
            }
        }

        while(i < left.size()) {
            ans.add(left.get(i++));
        }
        while(j < right.size()) {
            ans.add(right.get(j++));
        }

        return ans;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        long[] arr = readArr(n, br, st);
        ArrayList<Long>[] segment = createSegmentTree(4 * n);
        build(0, 0, n - 1, arr, segment);
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            String type = st.nextToken();
            if (type.equals("?")) {
                long a = Long.parseLong(st.nextToken());
                long b = Long.parseLong(st.nextToken());
                sb.append(query(0, 0, n - 1, a, b, segment)).append("\n");
            } else {
                int l = Integer.parseInt(st.nextToken()) - 1; // Adjusted index for 0-based array
                long u = Long.parseLong(st.nextToken());
                update(0, 0, n - 1, l, u, segment, arr);
            }
        }

        System.out.println(sb.toString());
        br.close();
    }

    public static long[] readArr(int n, BufferedReader br, StringTokenizer st) throws IOException {
        st = new StringTokenizer(br.readLine());
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        return arr;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Long>[] createSegmentTree(int size) {
        ArrayList<Long>[] segment = new ArrayList[size];
        for (int i = 0; i < size; i++) {
            segment[i] = new ArrayList<>();
        }
        return segment;
    }
}
