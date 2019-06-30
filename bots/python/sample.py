import sys

from python.adaptor import read_board

with open("sample.log", "w") as f:
    try:
        x = read_board(sys.stdin.buffer.read())

        width, height, line, turn, board = x

        print("2 0 1")
    except Exception as e:
        f.write(str(e))
        f.close()
