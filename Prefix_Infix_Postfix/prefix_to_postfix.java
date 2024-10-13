package Prefix_Infix_Postfix;

import java.util.Scanner;
import java.util.Stack;

public class prefix_to_postfix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s = in.next();
        int len = s.length();
        Stack<String> st = new Stack<>();
        for (int i=len-1;i>=0;i--){
            char ch = s.charAt(i);
            if((ch >= 'a' && ch<='z') || (ch>='A' && ch<='Z')){
                st.push(Character.toString(ch));
            }
            else{
                String c1 = st.pop();
                String c2 = st.pop();
                String s1 = c1 + c2 + ch;
                st.push(s1);
            }
        }
        System.out.println(st.pop());
    }
}
