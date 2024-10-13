package Prefix_Infix_Postfix;

import java.util.Scanner;
import java.util.Stack;

public class Infix_to_Postfix {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        Stack<Character> st = new Stack<>();

        for(int i=0;i<s.length();i++){

            if((s.charAt(i)>=65 && s.charAt(i)<=90) || (s.charAt(i))>=97 && (s.charAt(i))<=122){
                System.out.print(s.charAt(i));
            }

            else if(s.charAt(i) == '(') st.push(s.charAt(i));

            else if(s.charAt(i) == ')'){
                char ch;
                while(!st.empty() && (ch =  st.pop()) != '('){
                    System.out.print(ch);
                }
            }

            else{
                while(!st.empty() && priority(st.peek()) >= priority(s.charAt(i))){
                    System.out.print(st.pop());
                }
                st.push(s.charAt(i));
            }
        }

        while(!st.empty()){
            System.out.print(st.pop());
        }
    }

    public static int priority(char ch){
        if(ch == '^') return 3;
        else if(ch == '*' || ch == '/') return 2;
        else if(ch == '+' || ch == '-') return 1;
        return -1;
    }
}
