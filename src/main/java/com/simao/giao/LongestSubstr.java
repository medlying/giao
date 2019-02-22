package com.simao.giao;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstr {

    public int lengthOfLongestSubstring(String s) {
        int result = 1;
        if (s.equals("")) {
            return 0;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            int k = 1;
            Map<String, Object> map = new HashMap<>();
            map.put(String.valueOf(s.charAt(i)), 1);
            for (int j = i + 1; j < s.length(); j++) {
                if (map.get(String.valueOf(s.charAt(j))) == null) {
                    map.put(String.valueOf(s.charAt(j)), 1);
                    k++;
                } else {
                    break;
                }
            }
            result = Math.max(result, k);
        }
        return result;
    }

    public static void main(String[] args) {
        LongestSubstr longestSubstr = new LongestSubstr();
        System.out.println(longestSubstr.lengthOfLongestSubstring("pwwkew"));
        System.out.println(longestSubstr.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(longestSubstr.lengthOfLongestSubstring(""));
        System.out.println(longestSubstr.lengthOfLongestSubstring("au"));
    }
}
