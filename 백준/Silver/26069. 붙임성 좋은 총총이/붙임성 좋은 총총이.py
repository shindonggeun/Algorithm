import sys; input = sys.stdin.readline
from collections import defaultdict

# 총총이는 처음부터 무지개 댄스를 추고 있다.
dance = defaultdict(bool)
dance['ChongChong'] = True
answer = 1

for _ in range(int(input())):
    # A와 B가 만났다.
    A, B = input().split()
    # 두 사람 중 한 사람만 추고 있다면
    # 추고 있지 않은 사람을 추게 만들면 된다.
    if dance[A]:
        if not dance[B]:
            dance[B] = True
            answer += 1
    elif dance[B]:
        dance[A] = True
        answer += 1

print(answer)