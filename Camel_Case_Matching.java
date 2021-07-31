class Solution {
    //Time O(N(m+n))
    //Space O(1)
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> list = new ArrayList<>();
        if(queries == null || queries.length == 0)
        {
            return list;
        }
        for(String word : queries)
        {
            int i = 0 ,j = 0;
            boolean flag = false;
            for(i = 0 ; i < word.length() ; i++)
            {
                char c = word.charAt(i);
                if( j < pattern.length() && c == pattern.charAt(j))
                {
                    j++;
                }
                else if(Character.isUpperCase(c))
                {
                    break;
                }
            }
            if(j == pattern.length() && i == word.length())
            {
                flag = true;
            }
            list.add(flag);
        }
        return list;
    }
}