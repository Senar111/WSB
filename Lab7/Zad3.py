with open("text.txt", "a+") as plik:
    for a in range(10):
        plik.write(str("Test testowego testu "))
        plik.writelines(str(a))

with open("text.txt","r")as plik:
    print(plik.readlines())