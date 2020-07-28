package com.a11logic.fbhk2020;

import java.util.Scanner;

public class Alchemy {

    private void runTests() {
        Scanner scanner = new Scanner(System.in);
        int numTests = scanner.nextInt();
        //discard rest of the line
        scanner.nextLine();
        for (int i = 1; i <= numTests; i++) {
//            int n = scanner.nextInt();
            //discard n
            scanner.nextLine();
            String s = scanner.nextLine();
            runTestCase(i, s);
        }
    }

    private void runTestCase(int caseNum, String s) {
        int A = 0;
        int B = 0;
        char result = 'N';
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'A')
                A++;
            else
                B++;
        }
        if(Math.abs(A - B) == 1)
            result = 'Y';

        System.out.println("Case #" + caseNum + ": " + result);
    }

    public static void main(String[] args) {
        Alchemy s = new Alchemy();
        s.runTests();
    }
}
