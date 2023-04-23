/*

We are given a pattern and list of strings, and we have to determine if each string is matching with the pattern , of so then put true in the result array.

We have to put as many(or 0) lower case letter in pattern and at any position to make it match the string

So we can puty only lower case letters in pattern . that means the upper case letters are only limited

We can put two pointers, 

1)if the character at string matches the patter, both pointers move
2) if the character is lower case in the string, then its acceptable, as we have been told lower case letters can be put as many
3) any other condition would be a breach , this includes, Upper case letter in string not matching letter in pattern

Then, there could be possibility that either

1) string ended early. If string ended, and pattern's pointer is not finished, then there is a breach
2) pattern ended early. if pattern ended, then we will check the rest of the string to contain only lower case character, if any upper case character found, there will be a breach

the result array is returned

Time : O ( n * l) , where n is the length of the queries and l is the longest string
Space : O (1)

*/

class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {

        

        Boolean[] result = new Boolean[queries.length];

        for(int i=0;i<queries.length;i++){
            boolean noMatch = false;
            String str = queries[i];
            int ptrP =0;
            int ptrQ =0;
            while(ptrQ<str.length() && ptrP < pattern.length()){
                char q = str.charAt(ptrQ);
                char p = pattern.charAt(ptrP);
                // case 1: when both chars same , then increment both
                if(q == p){
                    ptrP++;
                    ptrQ++;
                }
                // case 2: when q is lowercase 
                else if(Character.isLowerCase(q)){
                    // all lower case characters are accepted, so we can increment only the str
                    ptrQ++;
                }
                // case 3: when q is uppercase and its not matching with the uppercase character in pattern
                else{
                    noMatch = true;
                    break;
                }
                
                
            }
            if(ptrP < pattern.length()) noMatch = true; // if pointer on pattern is not finished, then its no match
            if(noMatch) {
                result[i] = false;
                continue;
            }
            // check if the rest of the characters in the queries are all lower case

            while(ptrQ<str.length() ){
                if(!Character.isLowerCase(str.charAt(ptrQ))){
                    break;
                }
                ptrQ++;
            }

            result[i] = (ptrQ == str.length()); // the pointer on the string should have been finished by now if its valid
        }
        

        return Arrays.asList(result);
    }
}