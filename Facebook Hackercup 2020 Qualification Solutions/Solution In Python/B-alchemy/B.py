import sys
from os import path
if(path.exists('alchemy_input.txt')):
    sys.stdin = open("alchemy_input.txt","r")
    sys.stdout = open("BOut.txt","w")

t = int(input())
for _ in range(1, t + 1):
    print("Case #", _, ": ", sep = "", end = "")
    n = int(input())
    s = input()
    a = 0
    b = 0
    for i in s:
        if i == 'A':
            a += 1
        else:
            b += 1
    temp = abs(a - b)
    if(temp == 1):
        print('Y')
    else:
        print('N')