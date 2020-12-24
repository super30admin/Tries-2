def camelMatch(self, queries: List[str], pattern: str) -> List[bool]:
        
        
        def match(pattern,s):
            i = 0
            for j,c in enumerate(s):
                if i<len(pattern) and pattern[i]==s[j]:
                    i+=1
                elif s[j].isupper():
                    return False
            return i == len(pattern)
                
        
        return [True if match(pattern, s) else False for s in queries]
        
        #tc is o(pattern_len*queries_len)
        #sc is o(1)
