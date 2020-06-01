a = input("Podaj liczbe ")
a = int(a)
for x in range(1, a+1, 1):
    for b in range(0, x,1):
        print("A", end='')
    print()
    if x==10:
        break