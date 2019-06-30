import bz2
import typing


def read_board(data: bytes) -> typing.Tuple[int, int, int, int, typing.List[int]]:
    ret = [x for x in bz2.decompress(data)]

    return (
        ret[0],
        ret[1],
        ret[2],
        ret[3],
        ret[4:]
    )

