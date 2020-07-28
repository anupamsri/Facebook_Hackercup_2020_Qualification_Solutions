package com.a11logic.fbhk2020;

import org.apache.commons.collections4.*;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

import java.util.ArrayList;
import java.util.*;

public class Timber {

    private void runTests() {
        Scanner scanner = new Scanner(System.in);
        int numTests = scanner.nextInt();
        for (int i = 1; i <= numTests; i++) {
            int n = scanner.nextInt();
            ArrayList<TimberTree> treeList = new ArrayList<>(n);
            ArrayListValuedHashMap<Integer, TimberTree> treePositions = new ArrayListValuedHashMap();
//            ArrayList<TimberTree> maxRight = new ArrayList<>(3 * n);
            for(int j = 0; j < n; j++) {
                int pos = scanner.nextInt();
                int h = scanner.nextInt();
               TimberTree t = new TimberTree(j, pos, h);
               treeList.add(t);
//               TimberTree fallenTreeLeft = new TimberTree(j, pos - h, h);
//               TimberTree fallenTreeRight = new TimberTree(j, pos + h, h);
//               maxRight.add(fallenTreeLeft);
//               maxRight.add(t);
//               maxRight.add(fallenTreeRight);
               //fallen tree right
               //TODO just maintain the maxPosL instead
               treePositions.put(pos - h, t);
               //treePositions.put(pos + h, t);
               treePositions.put(pos, t);
            }
            Collections.sort(treeList, new Comparator<TimberTree>() {
                @Override
                public int compare(TimberTree o1, TimberTree o2) {
                    return o1.pos - o2.pos;
                }
            });
            runTestCase(i, treePositions, treeList);
        }
    }

    private void runTestCase(int caseNum,
                             ArrayListValuedHashMap<Integer, TimberTree> treePositions,
                             ArrayList<TimberTree> treeList) {
        int max =0;
        //special case
        if(treeList.size() == 1)
            max = treeList.get(treeList.size() -1).h;
        else { //at least 2 trees
            TimberTree rightMostTree = treeList.get(treeList.size() - 1);
            rightMostTree.maxPosR = rightMostTree.pos + rightMostTree.h;
            rightMostTree.maxPosL = rightMostTree.pos;
            //iterate over other trees
            for (int i = treeList.size() - 2; i >= 0; i--) {
                TimberTree tree = treeList.get(i);
                //if the tree falls right
                int pos = tree.pos + tree.h;
                tree.maxPosR = pos; //starting value
                //get all the other trees that connect at this point
                List<TimberTree> connectingTrees =  treePositions.get(pos);
                if(connectingTrees != null) {
                    for (TimberTree ct : connectingTrees) {
                        if(pos == ct.pos) {
                            //ct is fallen right
                            if(ct.maxPosR > tree.maxPosR)
                                tree.maxPosR = ct.maxPosR;
                        } else {
                            //fallen left
                            if(ct.maxPosL > tree.maxPosR)
                                tree.maxPosR = ct.maxPosL;
                        }
                    }
                }
                //if the tree falls left
                pos = tree.pos;
                tree.maxPosL = pos;
                //get all the other trees that connect at this point
                connectingTrees =  treePositions.get(pos);
                if(connectingTrees != null) {
                    for (TimberTree ct : connectingTrees) {
                        //only check for left fallen trees
                        if(pos != ct.pos) {
                        //fallen left
                            if(ct.maxPosL > tree.maxPosL)
                                tree.maxPosL = ct.maxPosL;
                        }
                    }
                }
            }

            //iterate over all the trees.
            // Check the max distance that can be reached from this tree, if this was the first tree in the solution
            //update max, if higher than before.
            for(TimberTree tree: treeList) {
                int maxdistanceL = tree.maxPosL - tree.pos + tree.h;
                if(maxdistanceL > max)
                    max = maxdistanceL;
                int maxdistanceR = tree.maxPosR - tree.pos;
                if(maxdistanceR > max)
                max = maxdistanceR;
            }
        }

        System.out.println("Case #" + caseNum + ": " + max);
    }

    public static void main(String[] args) {
        Timber s = new Timber();
        s.runTests();
    }

    private class TimberTree {
        int index;
        int pos;
        int h;
        int maxPosR;
        int maxPosL;

        public TimberTree(int index, int pos, int h) {
            this.index = index;
            this.pos = pos;
            this.h = h;
        }
    }
}
