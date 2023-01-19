class Solution:
    def camelMatch(self, queries, pattern):
        result = list()

        for query in queries:
            i = 0
            flag = False
            for j in range(len(query)):
                qChar = query[j]
                if i < len(pattern) and qChar == pattern[i]:
                    i += 1
                    if i == len(pattern):
                        flag = True
                elif qChar.isupper():
                    flag = False
                    break
            result.append(flag)

        return print(result)
queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"]
pattern = "FB"
if __name__ == "__main__":
    obj = Solution()
    obj.camelMatch(queries, pattern)

# Two Pointers
# Time Complexity:O(k *(n + m)). where m and n are length of word in queries array and pattern word and k is length of queries array
# Space Complexity: O(n). Size of result array
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : No
