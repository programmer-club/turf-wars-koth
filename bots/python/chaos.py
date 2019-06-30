# import sys
# import os
#
# sys.path.insert(0, os.path.dirname(__file__))
# read_board = __import__("adaptor").read_board
import random

a = random.choice([0, 1, 2, 3])
b = random.choice([-1, 0, 1])
c = random.choice([-1, 0, 1])
print(f"{a} {b} {c}")
