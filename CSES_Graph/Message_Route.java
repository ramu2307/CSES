package CSES_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Message_Route {
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

        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        visited[0] = true;

        boolean found = false;

        while(!q.isEmpty()) {
            int node = q.poll();
            if(node == n - 1) {
                found = true;
                break;
            }

            for(int neighbour : list.get(node)) {
                if(!visited[neighbour]) {
                    visited[neighbour] = true;
                    parent[neighbour] = node;
                    q.add(neighbour);
                }
            }
        }

        if(!found) {
            System.out.println("IMPOSSIBLE");
        }
        else {
            ArrayList<Integer> path = new ArrayList<>();
            for(int at = n - 1; at != -1; at = parent[at]) {
                path.add(at + 1);
            }
            Collections.reverse(path);
            System.out.println(path.size());
            for(int node : path) {
                System.out.print(node + " ");
            }
            System.out.println();
        }
    }
}
