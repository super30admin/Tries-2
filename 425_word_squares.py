from typing import List


class Solution:
    class TrieNode:
        def __init__(self):
            self.children = [None] * 26
            self.starts_with = []

    def wordSquares(self, words: List[str]) -> List[List[str]]:
        """
            https://leetcode.com/problems/word-squares/
            Time Complexity - O(Exponential)
            Space Complexity - O(NL)
            N is the number of words and L is the length of a single word.
        """
        root = self._build_trie(words)
        self.result = []
        cur_list = []
        length_of_square = len(words[0])
        for word in words:
            # make each word as the master
            # action
            cur_list.append(word)
            # recurse
            self._backtrack(length_of_square, cur_list, root)
            # backtrack
            cur_list.pop(len(cur_list) - 1)
        return self.result

    def _backtrack(self, length_of_square, word_list, root):
        # base
        if len(word_list) == length_of_square:
            self.result.append(list(word_list))
            return
        # logic
        prefix_str = ''
        i = len(word_list)
        for word in word_list:
            prefix_str += word[i]
        prefixes = self._search_words(prefix_str, root)
        for word in prefixes:
            # action
            word_list.append(word)
            # recurse
            self._backtrack(length_of_square, word_list, root)
            # backtrack
            word_list.pop(len(word_list) - 1)

    def _build_trie(self, words):
        root = self.TrieNode()
        for word in words:
            node = root
            for c in word:
                idx = ord(c) - ord('a')
                if not node.children[idx]:
                    node.children[idx] = self.TrieNode()
                node.children[idx].starts_with.append(word)
                node = node.children[idx]
        return root

    def _search_words(self, prefix, root):
        node = root
        for c in prefix:
            idx = ord(c) - ord('a')
            if not node.children[idx]:
                return []
            node = node.children[idx]
        return node.starts_with


if __name__ == '__main__':
    print(Solution().wordSquares(["area", "lead", "wall", "lady", "ball"]))
