package com.a11logic.fbhk2020;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class TravelRestrictions {

    private void runTests() {
        Scanner scanner = new Scanner(System.in);
        int numTests = scanner.nextInt();
        for (int i = 1; i <= numTests; i++) {
            int n = scanner.nextInt();
            //finish this line
            scanner.nextLine();
            String incoming = scanner.nextLine();
            String outgoing = scanner.nextLine();
            runTestCase(i, incoming, outgoing);
        }
    }

    private void runTestCase(int caseNum, String incoming, String outgoing) {
        int len = incoming.length();
        char[][] matrix = new char[len][len];
        for(int i=0; i < len; i++) {
            for(int j=i; j < len; j++) {
                if(i == j) {
                    matrix[i][j] = 'Y';
                    continue;
                }
                if(outgoing.charAt(i) == 'N' || incoming.charAt(j) == 'N') {
                    matrix[i][j] = 'N';
                    continue;
                }
                matrix[i][j] = (outgoing.charAt(j-1) != 'N' && matrix[i][j-1] == 'Y') ?
                        'Y' : 'N';

            }
        }
        for(int i=0; i < len; i++) {
            for(int j=i-1; j >= 0; j--) {
                if(outgoing.charAt(i) == 'N' || incoming.charAt(j) == 'N') {
                    matrix[i][j] = 'N';
                    continue;
                }
                matrix[i][j] = (outgoing.charAt(j+1) != 'N' && matrix[i][j+1] == 'Y') ?
                        'Y' : 'N';

            }
        }
        System.out.println("Case #" + caseNum + ": ");
        for(int i =0; i < len; i++) {
            System.out.println(new String(matrix[i]));
        }
    }

    public static void main(String[] args) {
        TravelRestrictions s = new TravelRestrictions();
        s.runTests();
    }
}
