'''
1023 Camelcase Matching
https://leetcode.com/problems/camelcase-matching/description/

Given an array of strings queries and a string pattern, return a boolean array answer where answer[i] is true if queries[i] matches pattern, and false otherwise.

A query word queries[i] matches pattern if you can insert lowercase English letters into the pattern so that it equals the query. You may insert a character at any position in pattern or you may choose not to insert any characters at all.


Example 1:
Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FB"
Output: [true,false,true,true,false]
Explanation: "FooBar" can be generated like this "F" + "oo" + "B" + "ar".
"FootBall" can be generated like this "F" + "oot" + "B" + "all".
"FrameBuffer" can be generated like this "F" + "rame" + "B" + "uffer".

Example 2:
Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBa"
Output: [true,false,true,false,false]
Explanation: "FooBar" can be generated like this "Fo" + "o" + "Ba" + "r".
"FootBall" can be generated like this "Fo" + "ot" + "Ba" + "ll".

Example 3:
Input: queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], pattern = "FoBaT"
Output: [false,true,false,false,false]
Explanation: "FooBarTest" can be generated like this "Fo" + "o" + "Ba" + "r" + "T" + "est".

Constraints:
1 <= pattern.length, queries.length <= 100
1 <= queries[i].length <= 100
queries[i] and pattern consist of English letters.

Solution:
1. Linear traversal of string

Let P = len(pattern),
k = 0 # index of pattern string
i = 0 # index of query string

We read the current char q in query (q = query[i]) and current char p in pattern (p = pattern[k]).

Two cases:
Case 1. If we have not exhausted all the characters in pattern, then k must be
        0<=k<=K-1. Case 1 has two sub-cases.

        If p == q, then:
            we advance both i and k by 1.

        If p != q, then:
            a) it could be that q is a lowercase char and not present in pattern. This is excuseable. We advance i by 1 but not advance k (since p has not been matched yet)
            b) it could be that q is an uppercase char and not present in pattern. This is not excuseable. This is a mismatch since every uppercase char in query must be present in the pattern. Thus, we set match=False and break

Case 2: If we have exhausted all the characters in the pattern,
        then k must be >= K.
        If there are characters remaining in the query, we check if all those characters are all lowercase. If any char is uppercase, it is a mismatch since every uppercase char in query must be present in the pattern. We set match=False and break.

https://youtu.be/nF2IWTtdKjQ?t=3293

Time: O(Nm), Space: O(1), N = no. of queries, m = avg len of a query
'''
from typing import List
def camelMatch_1(queries: List[str], pattern: str) -> List[bool]:
    ''' Time: O(NK), Space: O(1), K = len of pattern '''
    if not queries:
        return []
    K = len(pattern)
    result = []
    for query in queries: # O(N)
        k = 0 # pointer in pattern
        match = True
        for i in range(len(query)): # O(m)
            if k < K: # Case 1
                p = pattern[k]
                q = query[i]
                if p == q:
                    k += 1
                else:
                    #p ! = q and if q is uppercase, then pattern failed to match
                    # the query. eg. query = "ForceFeedBack", pattern = "FoBa"
                    if q.isupper():
                        match = False
                        break
                    else:
                        pass # k doesn't advance, i advances by 1
            elif query[i].isupper(): # Case 2
                match = False
                break
        if k == K and match:
            result.append(True)
        else:
            result.append(False)

    return result

def camelMatch_2(queries: List[str], pattern: str) -> List[bool]:
    ''' This is identical in logic to camelMatch_1 but slightly tricky to understand'''
    def isMatch(query, pattern):
        i = 0  # query
        j = 0  # pattern

        M = len(query)
        N = len(pattern)

        while i < M:
            if j < N and query[i] == pattern[j]:
                i += 1
                j += 1
            elif query[i].isupper():
                return False
            else:
                i += 1

        return j == N

    result = []
    for query in queries:
        result.append(isMatch(query, pattern))
    return result



def run_camelMatch():
    tests = [(["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], "FB", [True,False,True,True,False]),
    (["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], "FoBa", [True,False,True,False,False]),
    (["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"], "FoBaT", [False,True,False,False,False]),
    ]

    for test in tests:
        queries, pattern, ans = test[0], test[1], test[2]
        print(f"\nqueries = {queries}")
        print(f"pattern = {pattern}")
        for method in [1, 2]:
            if method == 1:
                result = camelMatch_1(queries, pattern)
            elif method == 2:
                result = camelMatch_2(queries, pattern)
            print(f"Method {method}: result = {result}")
            success = (ans == result)
            print(f"Pass: {success}")
            if not success:
                print("Failed")
                return

run_camelMatch()