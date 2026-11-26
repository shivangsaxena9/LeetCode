class Solution {
    public boolean canAliceWin(int n) {
        int take = 10;
        boolean alice = true;
        while (n >= take) {
            n -= take--;
            alice = !alice;
        }
        return !alice;
    }
}