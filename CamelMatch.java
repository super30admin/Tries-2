class CamelMatch {
    //time: O((m+n)*k) where m = avg length of a word, n=avg length of pattern, k= avg length of input list
    //space: O(1)
    //did it run on leetcode: yes
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        //list for the output
        List<Boolean> res = new ArrayList<>();
        //looping through all the words in list
        for(String query: queries){
            //initializing pointer for pattern
            int j=0;
            //initializing flag to track if current word is in the pattern
            boolean flag = false;
            //looping through current word
            for(int i=0; i<query.length(); i++){
                //fetching current character
                Character c = query.charAt(i);
                //if pattern pointer is within bound and current character of word and pattern matches
                if(j<pattern.length() && c == pattern.charAt(j)){
                    //update pattern pointer
                    j++;
                    //if pattern pointer at the end
                    if(j==pattern.length()){
                        //update flag as pattern found
                        flag = true;
                    }  
                }//if any uppercase character found not in pattern
                else if(Character.isUpperCase(c)){
                    //mark flag as pattern mismatch
                        flag = false;
                        break;
                }
            }
            //add to result
            res.add(flag);
        }
        return res;
    }
}