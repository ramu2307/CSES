package CSES_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Building_Teams {

    public static boolean bfs(int start, ArrayList<ArrayList<Integer>> list, int[] team) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        team[start] = 1;
        while(!q.isEmpty()) {
            int curr = q.poll();

            for(int neighbour : list.get(curr)) {
                if(team[neighbour] == 0) {
                    team[neighbour] = (team[curr] == 1) ? 2 : 1;
                    q.add(neighbour);
                }
                else if(team[neighbour] == team[curr]) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }
        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            list.get(u).add(v);
            list.get(v).add(u);
        }

        int[] team = new int[n];
        Arrays.fill(team, 0);
        boolean possible = true;
        for(int i = 0; i < n; i++) {
            if(team[i] == 0) {
                if(!bfs(i, list, team)) {
                    possible = false;
                    break;
                }
            }
        }

        if(possible) {
            for(int i = 0; i < n; i++) {
                System.out.print(team[i] + " ");
            }
            System.out.println();
        }
        else {
            System.out.println("IMPOSSIBLE");
        }

    }
}
