// Time Complexity : O(N*K)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// We make use of 2 pointers to find all queries that are matching.

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
      List<Boolean> list = new ArrayList<>();

      for (var q : queries) {
         int index = 0;
         boolean flag = true;
         for (var c : q.toCharArray()) {
            if(index < pattern.length() && c == pattern.charAt(index)){
               index++;
               continue;
            }
            if(c >= 'A' && c <= 'Z'){
               if(index >= pattern.length() || c != pattern.charAt(index)){
                  flag = false;
                  break;
               }
            }
         }
         flag = flag && index == pattern.length();
         list.add(flag);
      }
      return list;
    }
}