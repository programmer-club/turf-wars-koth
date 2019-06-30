import bz2
import typing


def chunks(l, n):
    """Yield successive n-sized chunks from l."""
    for i in range(0, len(l), n):
        yield l[i:i + n]


def read_board(data: bytes) -> typing.Tuple[int, int, int, int, typing.List[int]]:
    b = bz2.decompress(data)
    ret = list(map(lambda b: int.from_bytes(b, "big", signed=True), chunks(b, 4)))

    return (
        ret[0],
        ret[1],
        ret[2],
        ret[3],
        ret[4:]
    )

