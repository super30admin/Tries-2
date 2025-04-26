class TrieNode:
    def __init__(self):
        self.children = {}
        self.startsWith = []


class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        node = self.root
        for ch in word:
            if ch not in node.children:
                node.children[ch] = TrieNode()
            node = node.children[ch]
            node.startsWith.append(word)

    def find_words_with_prefix(self, prefix):
        node = self.root
        for ch in prefix:
            if ch not in node.children:
                return []
            node = node.children[ch]
        return node.startsWith


class Solution:
    # T: O(n * 26 ** len(word)), S: O(n * l)
    def wordSquares(self, words: List[str]) -> List[List[str]]:

        trie = Trie()
        for word in words:
            trie.insert(word)

        word_len = len(words[0])
        results = []

        def backtrack(step, square):
            if step == word_len:
                results.append(square[:])
                return
            prefix = "".join([word[step] for word in square])
            for candidate in trie.find_words_with_prefix(prefix):
                square.append(candidate)
                backtrack(step + 1, square)
                square.pop()

        for word in words:
            backtrack(1, [word])

        return results
