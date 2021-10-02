package problem1;

import java.util.ArrayList;
import java.util.List;

public class TrieNode {
	TrieNode[] children;
	List<String> prefixWords;

	public TrieNode() {
		this.children = new TrieNode[26];
		this.prefixWords = new ArrayList<>();
	}
}
