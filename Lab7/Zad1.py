C=[x for x in range (100) if x%4 == 0]
plik=open("dane.txt","w+")
plik.writelines(str(C))
plik.close