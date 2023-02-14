import sys

input = sys.stdin.readline


def matching_key_generator(word):
    key = word
    
    if len(word) > 2:
        key = "-".join([word[0], word[-1], "".join(sorted(word[1:-1]))])
    elif len(word) == 2:
        key = "-".join(list(word))
        
    return key


def word_matching_dict(word_list):
    matching_dict = {}
    
    for word in word_list:
        key = matching_key_generator(word=word)
        matching_dict[key] = word
        
    return matching_dict


def word_superiority_effect(word_list, S):
    answer = []
    
    word_S_list = S.split()
    
    matching_dict = word_matching_dict(
        word_list=word_list
    )
    
    for word_idx, word in enumerate(word_S_list):
        key = matching_key_generator(word=word)
        answer.append(matching_dict[key] if key in matching_dict else word)
            
    return " ".join(answer)
    

if __name__ == "__main__":
    word_list = []
    
    N = int(input().rstrip())
    
    for _ in range(N):
        word_list.append(input().rstrip())
        
    M = int(input().rstrip())
    S = input().rstrip()
    
    print(word_superiority_effect(word_list=word_list, S=S))