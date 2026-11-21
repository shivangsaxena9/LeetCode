import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] origTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origTree[parent[i]].add(i);
        }

        int[] newParent = new int[n];
        newParent[0] = -1;
        int[] closest = new int[26];
        Arrays.fill(closest, -1);
        dfsAssignParents(0, origTree, parent, s, closest, newParent);
        List<Integer>[] finalTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            finalTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            finalTree[newParent[i]].add(i);
        }

        int[] ans = new int[n];
        dfsSubtreeSize(0, finalTree, ans);
        return ans;
    }

    private void dfsAssignParents(
            int u,
            List<Integer>[] origTree,
            int[] origParent,
            String s,
            int[] closest,
            int[] newParent
    ) {
        int c = s.charAt(u) - 'a';
        newParent[u] = (closest[c] != -1) ? closest[c] : origParent[u];

        int prev = closest[c];
        closest[c] = u;

        for (int v : origTree[u]) {
            dfsAssignParents(v, origTree, origParent, s, closest, newParent);
        }

        closest[c] = prev;
    }

    private int dfsSubtreeSize(int u, List<Integer>[] finalTree, int[] ans) {
        int size = 1;
        for (int v : finalTree[u]) {
            size += dfsSubtreeSize(v, finalTree, ans);
        }
        ans[u] = size;
        return size;
    }
}