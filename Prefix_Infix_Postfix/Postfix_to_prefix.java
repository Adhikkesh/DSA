package Prefix_Infix_Postfix;

import java.util.Scanner;
import java.util.Stack;

public class Postfix_to_prefix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s = in.next();
        Stack<String> st = new Stack<>();

        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if((ch >= 'a' && ch<='z') || (ch>='A' && ch<='Z')){
                st.push(Character.toString(ch));
            }
            else{
                String c1 = st.pop();
                String c2 = st.pop();
                String s1 = ch + c2 + c1;
                st.push(s1);
                System.out.println(s1);
            }
        }
        System.out.println(st.pop());
    }
}
