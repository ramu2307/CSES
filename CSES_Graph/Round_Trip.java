package CSES_Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Round_Trip {

    static ArrayList<Integer> cycle;

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
        cycle = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                if(dfs(list, parent, i, -1, visited)) {
                    System.out.println(cycle.size());
                    for(int j = 0; j < cycle.size(); j++) {
                        System.out.print((cycle.get(j) + 1) + " ");
                    } 
                    System.out.println();
                    return;
                }
            }
        }
        System.out.println("IMPOSSIBLE");

    }

    public static boolean dfs(ArrayList<ArrayList<Integer>> list, int[] parent, int node, int par, boolean[] visited) {
         visited[node] = true;
        parent[node] = par;

        for(int neighbour : list.get(node)) {
            if(!visited[neighbour]) {
                if(dfs(list, parent, neighbour, node, visited)) {
                    return true;
                }
            }
            else if(neighbour != par) {
                cycle.add(neighbour);
                int current = node;
                while(current != neighbour) {
                    cycle.add(current);
                    current = parent[current];
                }
                cycle.add(neighbour);
                Collections.reverse(cycle);
                return true;
            }
        }
        return false;
    }

    
}
