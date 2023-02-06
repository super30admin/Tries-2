package s30.tries.tries2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;



//Algo: for each query, take pointers and iterate pattern and query with given conditions.

//TC: O(q * l) q-> number of queries, l -> length of each query;
//Sc: O(1);
public class CamelCaseMatching {

    public List<Boolean> camelMatch(String[] queries, String pattern) {

        List<Boolean> res = new ArrayList();
        for(String query : queries){

            res.add(camelMatch(query, pattern));
        }

        return res;
    }

    private boolean camelMatch(String query, String pattern){

        int index = 0;

        int pi=0;


        while(index < query.length()){

            char ch = query.charAt(index);

            if(pi < pattern.length() && ch == pattern.charAt(pi)){
                pi++;
            }
            else if(Character.isUpperCase(ch)) return false;

            index++;
        }


        return pi == pattern.length();
    }


    public static void main(String[] args) {
      String s = "Hello,     my name is      John";

      String[] s1 = s.split(" ");

        Comparator<Integer> c = (a,b) -> b -a;

        System.out.println(s1.length);
    }
}
