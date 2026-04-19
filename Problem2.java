class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        //l is the avg length of queries
        //n is the number of queries
        //Time Complexity : O(ln)
        //Space complexity: O(1) 
        List<Boolean> result = new ArrayList<>();
        for(int i = 0; i < queries.length; i++){
            String word = queries[i];
            int pq = 0;
            int pp = 0;
            while(pq < word.length() && pp < pattern.length()){
                char wChar = word.charAt(pq);
                char pChar = pattern.charAt(pp);
                System.out.println(wChar + " "+ pChar);
                if(wChar == pChar){
                    pp++;
                    pq++;
                }else if(wChar >= 'A' && wChar <= 'Z'){
                    break;
                }else{
                    pq++;
                }
            }
            while(pq < word.length() && word.charAt(pq) >= 
        'a' && word.charAt(pq) <= 'z'){
                pq++;
            }
            result.add(pq == word.length() && pp == pattern.length());
        }
        return result;
    }
}