package Prefix_Infix_Postfix;

import java.util.Scanner;
import java.util.Stack;

public class prefix_to_infix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s = in.next();
        int len = s.length();
        Stack<String> st = new Stack<>();
        String[] str = s.split("");
        for(int i=len-1;i>=0;i++){

            char s1 = str[i].charAt(0);
            if((s1 >= 'a' && s1<='z') || (s1>='A' && s1<='Z')){
                st.push(str[i]);
            }

            else{
                String c1 = st.pop();
                String c2 = st.pop();

                String c3 = "(" + c1 + str[i] + c2 + ")";
                st.push(c3);
            }
        }

        System.out.println(st.pop());
    }
}
