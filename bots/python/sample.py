import sys

from python.adaptor import read_board

import sys

def eprint(*args, **kwargs):
    print(*args, file=sys.stderr, **kwargs)

with open("sample.log", "w") as f:
    try:
        x = read_board(sys.stdin.buffer.read())

        width, height, line, turn, board = x

        eprint("w =",width,", h =",height," dividing_line =",line," turn =",turn)

        print("4 0 1")
    except Exception as e:
        f.write(str(e))
        f.close()
