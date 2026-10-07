class Solution {
    public int strStr(String haystack, String needle) {
        int hLen = haystack.length();
        int nLen = needle.length();

        for (int i = 0; i <= hLen - nLen; i++) {
            int p1 = i;
            int p2 = 0;

            while (p2 < nLen && haystack.charAt(p1) == needle.charAt(p2)) {
                p1++;
                p2++;
            }

            if (p2 == nLen) {
                return i;
            }
        }
        return -1;
    }
}