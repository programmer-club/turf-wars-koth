import bz2
import typing


def read_board(data: bytes) -> typing.Tuple[int, int, int, int, typing.List[int]]:
    b = bz2.decompress(data)
    ret = [x for x in b]

    return (
        ret[0],
        ret[1],
        ret[2],
        ret[3],
        ret[4:]
    )

