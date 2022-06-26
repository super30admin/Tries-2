import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Time Complexity: O(n.m); where n is number of queries and m is length of a single query.
//Space Complexity: O(1) 
public class CamelcaseMatching { 
	/**Approach: Two pointers**/
	public List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> res = new ArrayList<>();
        for(String query: queries){//O(n)
            boolean flag= false;
            int p1 =0; int p2=0;
            while(p1 < query.length()){//O(m)
                char qChar = query.charAt(p1);
                if(p2<pattern.length() && qChar == pattern.charAt(p2)){
                    flag = true;
                    p2++;
                }else if(Character.isUpperCase(qChar)){
                    flag = false;
                    break;
                }
                p1++;
            }
            if(p2 != pattern.length()) flag = false;
            res.add(flag);
        }
        return res;
    }
	
	/** Driver code to test above **/
	public static void main (String[] args) {			
		CamelcaseMatching ob  = new CamelcaseMatching();	
		String[] queries = {"FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"};
		String pattern = "FoBaT";//"FB";
						
		System.out.println("Queries:"+Arrays.toString(queries));
		System.out.println("Pattern matches to query? "+ ob.camelMatch(queries, pattern));   
	}	
}
