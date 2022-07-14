class Solution {

    //Time Complexity : 0(n(m+k)) where m is the length of pattern, n is the length of queries array and k is the avg length of each string
    //Space Complexity: 0(1)

    public List<Boolean> camelMatch(String[] queries, String pattern) {
        if(queries == null || queries.length == 0){
            return new ArrayList<>();
        }

        List<Boolean> result = new ArrayList<>();   //to return the final result

        for(String query : queries){    //taking each word out of queries and checking it
            int i = 0;  //this is a pointer at my pattern string
            boolean match = false;  //initially, I put the boolean variable as false
            for(int j = 0; j < query.length(); j++){    //I go through each and every character in my word extracted
                if(i < pattern.length() && query.charAt(j) == pattern.charAt(i)){   //I compare them, and also check if my pattern is not out of bounds
                    i++;    //if it is same, i move my pointer in pattern forward, pointer in query automatically moves forward
                    if(i == pattern.length()){  //if pointer in pattern reaches the end of string
                        match = true;   //then I make it as true. But i still traverse the query as I might face a uppercase so I have to return false
                    }
                }
                else if(Character.isUpperCase(query.charAt(j))){    //if the character do not match and if character in query is lowe case, it does not enter here and pointer mover forward. But in mismatch, If character in query is upper case, then I make boolean as false and break out of this loop
                    match = false;
                    break;
                }
            }
            result.add(match);  //i add the result to mu list and move forward
        }
        return result;
    }
}