import sys
import os

sys.path.insert(0, os.path.dirname(__file__))
read_board = __import__("adaptor").read_board


def eprint(*args, **kwargs):
    print(*args, file=sys.stderr, **kwargs)


with open("sample.log", "w") as f:
    try:
        # import time
        # t = time.time()
        x = read_board(sys.stdin.buffer.read())
        # print(f"{(time.time() - t) * 1000} ms")

        width, height, line, turn, board = x

        eprint("w =", width, ", h =", height, " dividing_line =", line, " turn =", turn)

        print("4 0 1")
    except Exception as e:
        f.write(str(e))
        f.close()
