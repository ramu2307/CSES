package CSES_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Counting_Rooms {

    static int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void findRooms(int n, int m, int row, int col, char[][] arr, boolean[][] visited) {

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{row, col});
        visited[row][col] = true;
        while(!q.isEmpty()) {
            int[] curr = q.poll();

            int r = curr[0];
            int c = curr[1];
            for(int[] dir : directions) {
                int nRow = r + dir[0];
                int nCol = c + dir[1];

                if(nRow >= 0 && nRow < n && nCol >=0 && nCol < m && arr[nRow][nCol] == '.' && !visited[nRow][nCol]) {
                    q.add(new int[]{nRow, nCol});
                    visited[nRow][nCol] = true;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] arr = new char[n][m];
        for(int i = 0; i < n; i++) {
            String s = br.readLine();
            arr[i] = s.toCharArray();
        }

        boolean[][] visited = new boolean[n][m];
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(arr[i][j] == '.' && !visited[i][j]) {
                    findRooms(n, m, i, j, arr, visited);
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
