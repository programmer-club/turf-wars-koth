import sys
import os
sys.path.insert(0, os.path.dirname(__file__))
read_board = __import__("adaptor").read_board

with open("sample.log", "w") as f:
    try:
        # import time
        # t = time.time()
        x = read_board(sys.stdin.buffer.read())
        # print(f"{(time.time() - t) * 1000} ms")

        width, height, line, turn, board = x

        print("2 0 1")
    except Exception as e:
        f.write(str(e))
        f.close()
