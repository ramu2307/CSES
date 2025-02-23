package CSES_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Monsters {
    
    static char[][] path;
    static StringBuilder sb = new StringBuilder();
        private static int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        static char[] moves = {'R', 'L', 'U', 'D'};
    
    public static boolean bfs(int n, int m, int row, int col, char[][] arr, boolean[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{row, col});
        visited[row][col] = true;
        while(!q.isEmpty()) {

            int[] curr = q.poll();
            int r = curr[0];
            int c = curr[1];

            if(r == 0 || r == n - 1 || c == 0 || c == m - 1) {
                while(true) {
                    char move = path[r][c];
                    sb.append(move);
                    if(move == 'L') c++;
                    if(move == 'R') c--;
                    if(move == 'U') r++;
                    if(move == 'D') r--;

                    if(row == r && c == col) {
                        break;
                    }
                }
                
                return true;
            }

            for(int i = 0; i < 4; i++) {
                int nRow = r + directions[i][0];
                int nCol = c + directions[i][1];
    
                if(nRow >= 0 && nRow < n && nCol >= 0 && nCol < m && arr[nRow][nCol] == '.' && !visited[nRow][nCol]) {
                    q.add(new int[]{nRow, nCol});
                    path[nRow][nCol] = moves[i];
                    visited[nRow][nCol] = true;
                }
            }
        }

        return false;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] arr = new char[n][m];
        boolean[][] visited = new boolean[n][m];
        int x = 0;
        int y = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = br.readLine().toCharArray();
            for(int j = 0; j < m; j++) {
                if(arr[i][j] == 'A') {
                    x = i;
                    y = j;
                }
            }
        }

        path = new char[n][m];
        for(int i = 0; i < n; i++) {
            Arrays.fill(path[i], '\0');
        }
        if(bfs(n, m, x, y, arr, visited)) {
            System.out.println("YES");
            System.out.println(sb.length());
            System.out.println(sb.reverse().toString());
        }
        else {
            System.out.println("NO");
        }
    }
}
