//Time Complexity: O(n*m), where n is the length of the queries array and m is the length of the pattern string.
//Space Complexity: O(n), where n is the length of the queries array.
//ran on leetcode: yes
//check if each character in the string matches the pattern character, and if a character in the string is uppercase and doesn't match the pattern, or if the pattern character is uppercase and doesn't match the string character, then returns false. 

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList<>();

        for(String str : queries)
        {
            int i=0 , j = 0;
            while(true)
            {
                if(i==str.length() && j==pattern.length())
                {
                    res.add(true);
                    break;
                }
                if(j==pattern.length())
                {
                    boolean isValid = true;
                    for(int k=i;k<str.length();k++)
                    {
                        if(Character.isUpperCase(str.charAt(k)))
                        {
                            isValid = false;
                            break;
                        }
                    }
                    res.add(isValid);
                    break;
                    
                }
                if(i==str.length())
                {
                    res.add(false);
                    break;
                }
                char a = str.charAt(i);
                char b = pattern.charAt(j);
                if(a==b)
                {
                    i++;
                    j++;
                }
                else if(Character.isUpperCase(a))
                {
                    res.add(false);
                    break;
                }
                else if(Character.isUpperCase(b))
                {
                    i++;
                }
                else
                    i++;
            }
        }
        return res;
    }
}
