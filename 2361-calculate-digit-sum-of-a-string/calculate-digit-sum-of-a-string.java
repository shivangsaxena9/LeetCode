class Solution {
    public String digitSum(String s, int k) {
        char[] chars = s.toCharArray();
        int len = chars.length;

        while (len > k) {
            int writeIdx = 0;

            for (int i = 0; i < len; i += k) {
                int end = Math.min(i + k, len);
                int sum = 0;

                for (int j = i; j < end; j++) {
                    sum += chars[j] - '0';
                }

                if (sum == 0) {
                    chars[writeIdx++] = '0';
                } else {
                    int start = writeIdx;
                    while (sum > 0) {
                        chars[writeIdx++] = (char) ('0' + (sum % 10));
                        sum /= 10;
                    }
                    int left = start, right = writeIdx - 1;
                    while (left < right) {
                        char temp = chars[left];
                        chars[left++] = chars[right];
                        chars[right--] = temp;
                    }
                }
            }

            len = writeIdx;
        }

        return new String(chars, 0, len);
    }
}