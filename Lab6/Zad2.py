import random
matryca = [[random.randint(0,100)for a in range(4)]for b in range(4)]
[print(matryca[b]) for b in range(4)]
print([matryca[x][x] for x in range(4)])