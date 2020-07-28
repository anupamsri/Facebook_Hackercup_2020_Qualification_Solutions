import sys
from os import path
if(path.exists('travel_restrictions_input.txt')):
    sys.stdin = open("travel_restrictions_input.txt","r")
    sys.stdout = open("Aout.txt","w")

t = int(input())
for _ in range(1, t + 1):
    print("Case #", _, ":",sep = "")
    n = int(input())
    qwe = input()
    asd = input()
    ans = ["" for i in range(n)]
    for i in range(n):
        for j in range(n):
            if i == j:
                ans[i] += "Y"
            elif i < j:
                if asd[i]=="Y" and qwe[j]=="Y" and ans[i][j-1]=="Y" and asd[j-1]=="Y":
                    ans[i] += "Y"
                else:
                    ans[i] += "N"
            else:
                if asd[i]=="Y" and qwe[i - 1] == "Y" and ans[i - 1][j] == "Y":
                    ans[i] += "Y"
                else:
                    ans[i] += "N"
        print(ans[i])
