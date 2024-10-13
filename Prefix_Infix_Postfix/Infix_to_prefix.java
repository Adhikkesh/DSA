package Prefix_Infix_Postfix;

import java.util.Scanner;
import java.util.Stack;

public class Infix_to_prefix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();

        int len = s.length();

        char[] s1 = new char[len];
        int j=0;
        for(int i=len-1;i>=0;i--){
            if(s.charAt(i) == '('){
                s1[j] = ')';
            }
            else if(s.charAt(i) == ')'){
                s1[j] = '(';
            }
            else{
                s1[j] = s.charAt(i);
            }
            j++;
        }

        String s2 = "";

        Stack<Character> st = new Stack<>();
        for(int k=0;k<len;k++){
            char ch = s1[k];

            if((ch>=65 && ch<=90) || (ch>=97 && ch<=122)){
                s2 += ch;
            }

            else if(ch == '(') st.push(ch);
            else if(ch == ')'){
                while(!st.empty() && st.peek() != '('){
                    s2 += st.pop();
                }
                st.pop();
            }

            else{
                if(ch == '^'){
                    while(!st.empty() && priority(ch) <= priority(st.peek())){
                        s2 += st.pop();
                    }
                }
                else {
                    while (!st.empty() && priority(ch) < priority(st.peek())) {
                        s2 += st.pop();
                    }
                }
                st.push(ch);
            }
        }

        while(!st.empty()){
            s2 += st.pop();
        }
        System.out.println(s2);

        s1 = s2.toCharArray();
        len = s1.length;

        int i=0;
        int h = len-1;
        while(i<=h){
            char temp = s1[i];
            s1[i] = s1[h];
            s1[h] = temp;
            i++;
            h--;
        }

        for(char c:s1){
            System.out.print(c);
        }


    }

    public static int priority(char ch){
        if(ch == '^') return 3;
        else if(ch == '*' || ch == '/') return 2;
        else if(ch == '+' || ch == '-') return 1;
        return -1;
    }
}
