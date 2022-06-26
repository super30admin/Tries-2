import java.util.ArrayList;
import java.util.List;

//Time Complexity: O(n.m.26^m); where n is number of words and m is length of a single word.
//Space Complexity: O(n.m) 
public class WordSquares { 
	/**Approach: Backtracking + Trie**/
	class TrieNode{
        TrieNode[] children;
        List<String> startsWith;
        public TrieNode(){
            children = new TrieNode[26];
            startsWith = new ArrayList<>();
        }
    }
    TrieNode root;
    private void insert(String word){
        TrieNode curr = root;
        for(char c: word.toCharArray()){
            if(curr.children[c-'a'] == null){
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c-'a'];
            curr.startsWith.add(word);
        }        
    }
    private List<String> getStartsWith(String prefix){
        TrieNode curr = root;
        for(char c: prefix.toCharArray()){
            if(curr.children[c-'a'] == null) return new ArrayList<>();
            curr = curr.children[c-'a'];            
        }
        return curr.startsWith;        
    }
    
    List<List<String>> res;
    public List<List<String>> wordSquares(String[] words) {
        res= new ArrayList<>();
        if(words == null || words.length ==0) return res;        
        root= new TrieNode();
        for(String word: words) insert(word);//Insert all words in Trie     
        List<String> temp = new ArrayList<>();
        for(String word: words){ //O(n)
            temp.add(word);//action
            backtrack(temp, word.length());//recurse
            temp.remove(temp.size()-1);//backtrack
        }        
        return res;
    }
    private void backtrack(List<String> path, int len){//O(m.26^m)
        //base
        if(path.size() == len){
            res.add(new ArrayList<>(path));
            return;
        }
        //logic
        StringBuilder prefix= new StringBuilder();
        for(String word: path){
            prefix.append(word.charAt(path.size()));
        }
        List<String> words = getStartsWith(prefix.toString());       
        for(String word: words){
            path.add(word);//action
            backtrack(path, len);//recurse
            path.remove(path.size()-1);//backtrack
        }
    }
	
	/** Driver code to test above **/
	public static void main (String[] args) {			
		WordSquares ob  = new WordSquares();	
		String[] words = {"area","lead","wall","lady","ball"};
						
		System.out.println("Word sqaures are: "+ ob.wordSquares(words));   
	}	
}
