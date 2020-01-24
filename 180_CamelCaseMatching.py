class Solution:
    '''
    2 pointer solution
    Accepted on leetcode(1023)
    time - O(N * L) , N - len of queries , L - len of largest query
    space - O(N)
    '''

    def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        retList = []

        # iterate through all queries
        for query in queries:
            i = 0  # secong pointer for pattern
            flag = False  # added for each return list item

            for j in range(len(query)):  # iterate for length of word
                ch = query[j]

                # case 1: if ith char in pattern matches 'ch' of query then move to next char in pattern(increment i).
                if i < len(pattern) and pattern[i] == ch:
                    i += 1
                    # case 2: if i exits the pattern length change flag to true
                    if i >= len(pattern):
                        flag = True
                # case 3: after exiting pattern if there are still upper case pending in query chnage flag to False again and break
                elif ch.isupper():
                    flag = False
                    break;
            # after iteration of each query append flag value to retList and reset fag to false
            retList.append(flag)

        return retList