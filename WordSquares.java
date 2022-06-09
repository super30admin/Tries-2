//Time Complexity O(N ^ N)
//Space Complexity O(n^2) Hashmap
//Leetcode tested

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WordSquares {
    HashMap<String,List<String>> map = new HashMap<>();
    public List<List<String>> wordSquares(String[] words){
        List<List<String>> result = new ArrayList<>();
        createPrefixMap(words);
        for (int i = 0; i < words.length; i++) {
            LinkedList<String> list = new LinkedList<>();
            list.add(words[i]);
            backtrack(1,list,result,words[i].length());
        }
        return result;
    }
    private void createPrefixMap(String[] words){
        for (String word: words) {
            for (int i = 0; i < words.length; i++) {
                String prefix = word.substring(0,i);
                map.putIfAbsent(prefix,new ArrayList<>());
                map.get(prefix).add(word);
            }
        }
    }
    public void backtrack(int step,LinkedList<String> list,List<List<String>> result, int n){
        if(list.size() == n){
             result.add(new ArrayList<>(list));
             return;
        }
        StringBuilder sb = new StringBuilder();
        for (String word:list) {
            sb.append(word.charAt(step));
        }
        String prefix = sb.toString();
        List<String> wordList = map.getOrDefault(prefix,new ArrayList<>());
        for (String word:wordList) {
            list.add(word);
            backtrack(step+1,list,result,n);
            list.removeLast();
        }
    }
}
