package java1.algorithms.dynamicProgramming;
import java.util.*;

//TC: O(n*2 *m) SC:O(n)
public class WordBreak {
    private static boolean wordBreak(String str, List<String> wordDict) {
            boolean[] dp = new boolean[str.length()+1];
            dp[str.length()] = true;
            for(int i = str.length()-1; i>=0; i--) {
                for(String word: wordDict) {
                    if((i+ word.length() <= str.length()) && str.startsWith(word, i)) {
                        dp[i] = dp[i+ word.length()];
                    }
                    if(dp[i]) break;
                }
            }
            return dp[0];
    }

    public static void main(String[] args) {
        String str = "applepenapple";
        List<String> wordDict = Arrays.asList("pen", "apple");
        String str1 = "catsandog";
        List<String> wordDict1 = Arrays.asList("cats", "dog", "and", "cat");
        System.out.println(wordBreak(str, wordDict));
        System.out.println(wordBreak(str1, wordDict1));
    }
}
