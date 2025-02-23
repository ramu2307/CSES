import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CF_C_Sereja_and_Brackets {

    public class Info {
        int open;
        int close;
        int full;
        public Info(int open, int close, int full) {
            this.open = open;
            this.close = close;
            this.full = full;
        }

        public Info() {
            open = 0;
            close = 0;
            full = 0;
        }
    }

    public Info merge(Info left, Info right) {

        Info ans = new Info();
        ans.full = left.full + right.full + Math.min(left.open, right.close);
        ans.open = left.open + right.open - Math.min(left.open, right.close);
        ans.close = left.close + right.close - Math.min(left.open, right.close);
        return ans;

    }

    public void build(int idx, int low, int high, String s, Info[] info) {

        if(low == high) {
            Info temp = new Info();
            temp.open = (s.charAt(low) == '(') ? 1 : 0;
            temp.close = (s.charAt(low) == ')') ? 1 : 0;
            info[idx] = temp;
            return;
        }

        int mid = (low + high)/2;
        build(2 * idx + 1, low, mid, s, info);
        build(2 * idx + 2, mid + 1, high, s, info);

        info[idx] = merge(info[2 * idx + 1], info[2 * idx + 2]);
    }

    public Info query(int idx, int low, int high, int l, int r, Info[] info) {

        //no-overlap case
        // [low high l r] or [l r low high]
        if(high < l || low > r) {
            return new Info();
        }

        //complete overlap
        //[ l low high r]
        if(low >= l && high <= r) {
            return info[idx];
        }

        int mid = (low + high)/2;
        Info left = query(2 * idx + 1 , low, mid, l, r, info);
        Info right = query(2 * idx + 2, mid + 1, high, l, r, info);
        return merge(left, right);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();
        int n = s.length();
        Info[] info = new Info[4 * n];
        CF_C_Sereja_and_Brackets cf = new CF_C_Sereja_and_Brackets();
        cf.build(0, 0, n - 1, s, info);
        int q = Integer.parseInt(br.readLine());
        while(q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken()) - 1;
            Info ans = cf.query(0, 0, n - 1, l, r, info);
            sb.append(ans.full * 2).append("\n");
        }

        System.out.println(sb.toString());
        br.close();

    }
}
