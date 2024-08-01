# 72. Edit Distance

Medium
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character

Example 1:

Input: word1 = "horse", word2 = "ros"\
Output: 3\
Explanation:\
horse -> rorse (replace 'h' with 'r')\
rorse -> rose (remove 'r')\
rose -> ros (remove 'e')\
Example 2:\

Input: word1 = "intention", word2 = "execution"\
Output: 5\
Explanation:\
intention -> inention (remove 't')\
inention -> enention (replace 'i' with 'e')\
enention -> exention (replace 'n' with 'x')\
exention -> exection (replace 'n' with 'c')\
exection -> execution (insert 'u')\

Constraints:

0 <= word1.length, word2.length <= 500\
word1 and word2 consist of lowercase English letters.

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()][word2.length()];
        for(int[] arr: dp)
            Arrays.fill(arr, -1);

        return helper(word1, word2, 0, 0, dp);
    }
    public int helper(String word1, String word2, int index1, int index2, int[][] dp) {
        if(index1 == word1.length())
            return word2.length() - index2; // insert

        if(index2 == word2.length())
            return word1.length() - index1; // replace

        if(dp[index1][index2] != -1)
            return dp[index1][index2];

        if(word1.charAt(index1) == word2.charAt(index2))
            return dp[index1][index2] = helper(word1, word2, index1+1, index2+1, dp);

        int remove = helper(word1, word2, index1+1, index2, dp); // remove
        int replace = helper(word1, word2, index1+1, index2+1, dp); // replace
        int insert = helper(word1, word2, index1, index2+1, dp); // insert

        int count = 1 + Math.min(remove, Math.min(replace, insert));
        return dp[index1][index2] = count;
    }
}
// problems
// Base Cases: The base cases should handle the scenario where one of the strings is exhausted. If one string is exhausted, the remaining characters of the other string need to be inserted or deleted.
// Infinity Issue: When one string is exhausted, returning Integer.MAX_VALUE is inappropriate because it misrepresents the number of operations needed.
// While Loop Logic: The while loop inside the helper method might skip essential character comparisons and cause infinite recursion in some cases.



      // if(word1.length() == index1 && word2.length() == index2)
        //     return 0;

        // if(word1.length() <= index1 || word2.length() <= index2)
        //     return Integer.MAX_VALUE;

        // while(word1.length() < index1 && word2.length() < index2 &&
        //     word1.charAt(index1) == word2.charAt(index2)){
        //     index1++;
        //     index2++;
        // }

        // int remove = helper(word1, word2, index1+1, index2); // remove
        // int replace = helper(word1, word2, index1+1, index2+1); // replace
        // int insert = helper(word1, word2, index1, index2+1); // insert

        // int count = 1 + Math.min(remove, Math.min(replace, insert));
        // return count;
```
