# TIME COMPLEXITY: Exponential
# SPACE COMPLEXITY: O(m*n) number of words * length
# Define TrieNode and Trie Class
class TrieNode:
    # Every Trie Node consists of an array of trie nodes and a list that hols all the words starting with the prefix
    def __init__(self):
        self.children = [None]*26
        self.words = []


class Trie:
    def __init__(self):
        self.root = TrieNode()

    # Function to add words to trie
    def add_word(self, word):
        curr = self.root
        # For every character in the word
        for c in word:
            # If the corresponding character in not in children, create a new TrieNode
            if curr.children[ord(c) - ord('a')] is None:
                curr.children[ord(c) - ord('a')] = TrieNode()
            # Add the word to the words list for every prefix of the word
            curr.words.append(word)
            curr = curr.children[ord(c) - ord('a')]

    # Function to build a trie out of the list of words
    def build_trie(self, words):
        for word in words:
            self.add_word(word)

    # Function that return the list of words starting with a given prefix
    def get_words_starting_with_prefix(self, prefix):
        curr = self.root
        for c in prefix:
            if curr.children[ord(c) - ord('a')] is None:
                return []
            curr = curr.children[ord(c) - ord('a')]
        return curr.words


class Solution(object):
    def wordSquares(self, words):
        """
        :type words: List[str]
        :rtype: List[List[str]]
        """
        # Edge case
        if not words:
            return []
        self.result = []

        # Initialize and build a new trie from the words list
        trie = Trie()
        trie.build_trie(words)
        temp = []

        # Try to form a word square with every word
        for word in words:
            # Action - append word to temp list
            temp.append(word)
            # Delegate the task of building the word square for the current word to the backtracking function
            self.backtrack(trie, temp, len(word))
            # Undo Action - backtrack
            temp.pop()
        return self.result

    def backtrack(self, trie, temp, length):
        # Base Case - If the length of temp list equals length of word, a valid square is formed, append it to the result
        if len(temp) == length:
            self.result.append(temp[:])
            return

        prefix = []
        size = len(temp)
        # Get all the words starting at a particular prefix of the first word
        # EG: if the first word is:
        # b a* l l
        # The next row must have words starting with a . . .- prefix -> a
        # b a l* l
        # a r e* a
        # third row must have words starting with l e . . - prefix -> le
        # b a l l*
        # a r e a*
        # l e a d*
        # fourth row must have words starting with l a d . - prefix -> lad
        for word in temp:
            prefix.append(word[size])
        # get all words starting with prefix
        all_words_starting_with = trie.get_words_starting_with_prefix(
            "".join(prefix))
        # try to backtrack with every word
        for word in all_words_starting_with:
            # Action
            temp.append(word)
            # Recurse
            self.backtrack(trie, temp, len(word))
            # Backtrack
            temp.pop()
