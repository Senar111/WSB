a=None
while True:
    try:
        a=float(input("Podaj Liczbę "))
        print(a)
    except ValueError:
        print("Miała być liczba")
        break
    