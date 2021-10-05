# Tries-2

## Problem1: List of word squares(https://leetcode.com/problems/word-squares/)

// Time Complexity : O(N*L*(26^L))
// Space Complexity : O(N\*L)

class Solution {

    class TrieNode {
        TrieNode[] children;
        List<String> prefixWords;

        public TrieNode() {
            children = new TrieNode[26];
            prefixWords = new ArrayList<>();
        }
    }

    private void insert(String word) {
        TrieNode current = root;
        for(int i = 0 ; i < word.length(); i++) {
            char c = word.charAt(i);
            if(current.children[c - 'a'] == null) {
                current.children[c-'a'] = new TrieNode();
            }
            current = current.children[c-'a'];
            current.prefixWords.add(word);
        }
    }
    private List<String> startsWith(String prefix) {
        TrieNode current = root;
        for(int i = 0 ; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(current.children[c-'a'] == null) {
                return new ArrayList<>();
            }
            current = current.children[c-'a'];
        }
        return current.prefixWords;
    }

    List<List<String>> result;
    TrieNode root;
    public List<List<String>> wordSquares(String[] words) {
        result = new ArrayList<>();
        if(words == null || words.length == 0) {
            return result;
        }
        root = new TrieNode();
        for(String word : words) {
            insert(word);
        }
        int n = words[0].length();
        List<String> list = new ArrayList<>();
        for(String word : words) {
            //action
            list.add(word);
            //recurse
            backtrack(list,n);
            //backtrack
            list.remove(list.size()-1);

        }
        return result;
    }

    public void backtrack(List<String> list, int len) {
        //base
        if(len == list.size()) {
            result.add(new ArrayList<>(list));
            return;
        }
        //logic
        StringBuilder sb = new StringBuilder();
        int index = list.size();

        for(String word : list) {
            sb.append(word.charAt(index));
        }
        List<String> words = startsWith(sb.toString());
        for(String word : words) {
             //action
            list.add(word);
            //recurse
            backtrack(list, len);
            //backtrack
            list.remove(list.size()-1);
        }
    }

}

## Problem2: Match Camelcases(https://leetcode.com/problems/camelcase-matching/)

// Time Complexity : O(N(L + K))
// Space Complexity : O(1)

class Solution {
public List<Boolean> camelMatch(String[] queries, String pattern) {
if(queries == null || queries.length == 0) {
return new ArrayList<>();
}
List<Boolean> result = new ArrayList<>();

        for(String query : queries) {
            int i = 0;
            boolean match = false;

            for(int j = 0 ; j < query.length(); j++) {
                char queryChar = query.charAt(j);

                if(i < pattern.length() && queryChar == pattern.charAt(i)) {
                    i++;
                    if(i == pattern.length()) {
                        match = true;
                    }
                } else if(Character.isUpperCase(queryChar)) {
                    match = false;
                    break;
                }
            }
            result.add(match);

        }
        return result;
    }

}

## Problem3: Top K Frequently Repeating Elements(https://leetcode.com/problems/top-k-frequent-elements/)

// Time Complexity : O(N)
// Space Complexity : O(N)

class Solution {
public int[] topKFrequent(int[] nums, int k) {
if(nums == null || nums.length == 0) {
return new int[0];
}
int[] result = new int[k];

        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(Integer i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            max = Math.max(max, map.get(i));
        }

        List[] buckets = new List[max + 1];

        for(Integer key : map.keySet()) {
            int freq = map.get(key);
            if(buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(key);
        }

        for(int i = buckets.length - 1; i >= 0 && k > 0; i--) {
            List<Integer> bucket = buckets[i];
            if(bucket != null) {
                for(int j = bucket.size()-1; j >= 0 && k > 0; j--) {
                    result[--k] = bucket.get(j);
                }
            }
        }


        return result;
    }

}
