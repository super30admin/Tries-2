// approch 1 
// Time Complexity : O(n^n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// approch 2 
// Time Complexity : O(n*26^l)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// approch 2
// Time Complexity : O(n*26^l)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

import java.util.*;

class Main {
    private static List<List<String>> result;

    // approch 1 using recursion
    public static List<List<String>> wordSquares1(String[] words) {
        // null case
        if (words == null)
            return new ArrayList<>();
        result = new ArrayList<>();
        // helper function
        helper1(words, new ArrayList<>());
        return result;
    }

    private static void helper1(String[] words, List<String> li) {
        // base case
        if (li.size() == words[0].length()) {
            // System.out.println(li);
            // System.out.println(isValid(li));
            if (isValid(li)) {
                result.add(new ArrayList<>(li));
            }
            return;
        }

        // main logic
        for (int i = 0; i < words.length; i++) {
            // action
            li.add(words[i]);
            // recurse
            helper1(words, li);
            // backtrack
            li.remove(li.size() - 1);

            // }
        }
    }

    // check isValid
    private static boolean isValid(List<String> li) {
        // create another list to store vertically
        List<String> li2 = new ArrayList<>();
        for (int i = 0; i < li.size(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < li.size(); j++) {
                sb.append(li.get(j).charAt(i));
            }
            li2.add(sb.toString());
        }
        return li.equals(li2);
    }

    private static Map<String, List<String>> map;

    // approch 2 using HashMap to add all startsWith
    public static List<List<String>> wordSquares2(String[] words) {
        // null case
        if (words == null)
            return new ArrayList<>();
        // result list
        result = new ArrayList<>();
        int n = words.length;
        map = new HashMap<>();
        // create a hashset of all startswith
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                // prefix map
                String sub = words[i].substring(0, j + 1);
                // check if subString is not there in the map
                // create new list of the and then add it inside the map
                if (!map.containsKey(sub)) {
                    map.put(sub, new ArrayList<>());
                }
                map.get(sub).add(words[i]);
            }
        }

        // System.out.println(map);
        // first loop over all the words inside the
        // words and run helper function to get all the
        // wordSqaures
        List<String> li = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // action
            li.add(words[i]);
            // recurse
            helper2(words, li);
            // backtrak
            li.remove(li.size() - 1);
        }
        return result;
    }

    private static void helper2(String[] words, List<String> li) {
        // base case
        if (li.size() == words[0].length()) {
            result.add(new ArrayList<>(li));
            return;
        }
        // main logic
        // go over all the words with start
        // with prefix
        // first get prefix
        int size = li.size();
        // System.out.println(li);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(li.get(i).charAt(size));
        }
        // get list from map
        List<String> startsWith = map.get(sb.toString());
        if (startsWith != null) {
            // loop over string and check valid or not
            for (String str : startsWith) {
                // action
                li.add(str);
                // recurese
                helper2(words, li);
                // backTrack
                li.remove(li.size() - 1);
            }
        }
    }

    // trieNode
    static class TrieNode {
        TrieNode[] children;
        List<String> startsWith;

        TrieNode() {
            this.children = new TrieNode[26];
            this.startsWith = new ArrayList<>();
        }
    }

    private static TrieNode root;

    private static void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
            curr.startsWith.add(word);
        }
    }

    private static List<String> prefix(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (curr.children[c - 'a'] == null) {
                return new ArrayList<>();
            }
            curr = curr.children[c - 'a'];
        }
        return curr.startsWith;
    }

    // approch 3 using trie to store starts with
    public static List<List<String>> wordSquares(String[] words) {
        // null case
        if (words == null)
            return new ArrayList<>();
        // result list
        result = new ArrayList<>();
        root = new TrieNode();
        int n = words.length;
        for (String word : words) {
            insert(word);
        }
        // first loop over all the words inside the
        // words and run helper function to get all the
        // wordSqaures
        List<String> li = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // action
            li.add(words[i]);
            // recurse
            helper(words, li);
            // backtrak
            li.remove(li.size() - 1);
        }
        return result;
    }

    private static void helper(String[] words, List<String> li) {
        // base case
        if (li.size() == words[0].length()) {
            result.add(new ArrayList<>(li));
            return;
        }
        // main logic
        // go over all the words with start
        // with prefix
        // first get prefix
        int size = li.size();
        // System.out.println(li);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(li.get(i).charAt(size));
        }
        // get list from map
        List<String> startsWith = prefix(sb.toString());
        if (startsWith != null) {
            // loop over string and check valid or not
            for (String str : startsWith) {
                // action
                li.add(str);
                // recurese
                helper(words, li);
                // backTrack
                li.remove(li.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        String[] words = new String[] { "area", "lead", "wall", "lady", "ball" };
        System.out.println(wordSquares1(words));
        System.out.println(wordSquares2(words));
        System.out.println(wordSquares(words));
    }
}