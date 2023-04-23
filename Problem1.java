/*
Lets understand the question first : 

We are given an array of unique words, which will all be of same length, from 1 to 4.
We need to find as many word square from the array and return them

-------------------------------------------------------------------------------------------------------------------------------------------------
What is word square? 
Look at the editorial for better example. But , imagine below

H E A R T
E M B E R
A B U S E
R E S I N
T R E N D


if you read the first row , it will be heart, and if you read the first column , it will be heart as well
now keep on increasing row and column and you will read the same word vertically and horizontally

A sequence of strings forms a valid word square if the kth row and column read the same string, where 0 <= k < max(numRows, numColumns).
-------------------------------------------------------------------------------------------------------------------------------------------------

Algorithm:

0) We will use trie to store the words, one important trick is that each character of the trie , would store a list of words that it can form till now as a prefix, since we will be creating prefix and seeing how many words could be formed, this could give us a liner time lookup

1) Pick a word and add them to a candidate list, this list would potentially be a list containing wordSquares , if not then we will keep removing elements,
2) Start index from 1 (and increment subsequently)
3) form a prefix from each character at that index in the list
4) seach the prefix and get the list of strings containing that prefixes. if no list , then return back and explore other options
5) each string would be a potential candidate to be the next element in the list of word square
6) explore it further  and recursively build the list
7) when the idx is greather than the length of the average word ( all words will have the same length) , the the list would contain a word square
8) add them to the final list


Intuition:

We will use Trie and Bactracking to build the word square.
We will pick each word , then recursively backtrack on it to get the next possible word
We will remove the words from the list if it is not in the solution


Time:  O (n^k) // backtracking solution is always expoential

When k is the average length of the word

n is the length of the words aray

Space : O (n * k) // coming from the Trie 

*/
class Solution {

    class TrieNode{
        TrieNode[] children;
        List<String> prefList;
        TrieNode(){
            children = new TrieNode[26];
            prefList = new ArrayList<String>();
        }
    }

    TrieNode trie;
    // insert a string into a trie
    private void insert(String word){
        TrieNode head = trie;

        for(int i=0;i<word.length();i++){
            char a = word.charAt(i);
            if(head.children[a-97] ==null){ // null check
            head.children[a-97] = new TrieNode();
            }
            head = head.children[a-97];
            head.prefList.add(word);
        }

    }

    private List<String> get(String prefix){
        TrieNode head = trie;

        for(int i=0;i<prefix.length();i++){
            char a = prefix.charAt(i);
            if(head.children[a-97] == null){
                return null;
            }
            head = head.children[a-97];
        }
        return head.prefList;
    }

    List<List<String>> result;


    
    public List<List<String>> wordSquares(String[] words) {
        trie = new TrieNode();
        result = new ArrayList<List<String>>();

        for(int i=0;i<words.length;i++){
            insert(words[i]);
        }

        List<String> temp = new ArrayList<String>();
        for(int i=0;i<words.length;i++){
            temp.add(words[i]);

            backtrack(1,temp);

            temp.remove(temp.size()-1); // removing the last element
        }

        return result;
    }

    private void backtrack(int idx, List<String> li){

        // base case
        // when teh idx goes past the last index of the word
        if(idx >= li.get(0).length()){
            result.add(new ArrayList(li)); // creating a new list and adding it to the result list, as current list is going to be mutated
            return;
        }

        
        // create prefix and look for the next word
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<li.size();i++){ // at max it would run 4  , so its O(1)
            sb.append(li.get(i).charAt(idx));
        }

        List<String> found = get(sb.toString()); // O (1)
        if(found!=null)
        for(String s : found){ // recurse on each returned string as a possible solution
            li.add(s);

            backtrack(idx+1,li);

            li.remove(li.size()-1); // removing the last element
        }
        

    }
}