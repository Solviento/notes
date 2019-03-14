#!/usr/bin/env python
# coding:utf-8
"""
Usage:
$ python3 driver.py <81-digit-board>
$ python3 driver.py   => this assumes a 'sudokus_start.txt'

Saves output to output.txt
"""
import sys, os
from pprint import pprint
import time
ROW = "ABCDEFGHI"
COL = "123456789"
TIME_LIMIT = 1.  # max seconds per board
out_filename = 'output.txt'
src_filename = 'sudokus_start.txt'
global then
count = 0
solution = 0

def print_board(board):
    """Helper function to print board in a square."""
    print("-----------------")
    for i in ROW:
        row = ''
        for j in COL:
            row += (str(board[i + j]) + " ")
        print(row)

def string_to_board(s):
    """
        Helper function to convert a string to board dictionary.
        Scans board L to R, Up to Down.
    """
    return {ROW[r] + COL[c]: int(s[9 * r + c])
            for r in range(9) for c in range(9)}

def board_to_string(board):
    """Helper function to convert board dictionary to string for writing."""
    ordered_vals = []
    for r in ROW:
        for c in COL:
            ordered_vals.append(str(board[r + c]))
    return ''.join(ordered_vals)


def write_solved(board, f_name=out_filename, mode='w+'):
    global solution
    """
        Solve board and write to desired file, overwriting by default.
        Specify mode='a+' to append.
    """
    result = backtracking(board)
    # Write board to file

    outfile = open(f_name, mode)
    outfile.write(solution)
    outfile.write('\n')
    outfile.close()

    return result

# does recursive backtracking
def backtracking(board):
    global count, solution
    global then
    zeros_list = not_assigned(board)
    now = time.time()
    if (int(now - then) > 60):
        solution = "n/a"
        return True
    else:
        # TODO: implement this
        # we are using the "a1", "a2", notation
        # finds indices of where 0's are located in the board
        if (len(zeros_list) == 0):
            #print("backtracking successful, returning solution")
            solution = board_to_string(board)
            return True
        # checks domain, the remaining variables
        values_dict = check_domain(board)

        # location of minimum remaining values and index of minimum in zeros list
        min_thresh = 1000
        index = -1000
        # for 'aN' in zeros list [a1, a2, ...]
        for key in zeros_list:
            # returns length of remaining values for each empty square
            if (len(values_dict[key]) < min_thresh):
                min_thresh = len(values_dict[key])
                index = key
        # index now contains 'A1', etc.
        # for each value in order_domain_values
        remaining_values = values_dict[index]
        for value in remaining_values:
            rule_check = True
            ROW = "ABCDEFGHI"
            COL = "123456789"
            row_letter = index[0] # 'A'
            col_number = index[1] # '1'
            # check if value violates rules of sudoku
            # first check along the row
            for c in COL:
                # we skip the chosen square itself
                if (c == col_number):
                    continue
                elif (board[row_letter+c] == int(value)):
                    rule_check = False
                    break
            for r in ROW:
                if (r == row_letter):
                    continue
                elif (board[r+col_number] == int(value)):
                    rule_check = False
                    break
            rule_check_for_square = check_square(board, value, index)
            # print(rule_check, rule_check_for_square)
            if (rule_check == True and rule_check_for_square == True):
                # good to go, set value to board
                # index = 'A1'
                board[index] = int(value)
                zeros_list.remove(index)
                tup = (index, value, values_dict, zeros_list)
                ret = fc(tup)
                if backtracking(board):#, zeros_list, values_dict):
                    return True
                for ind in ret:
                		for integer in ret[ind]:
                			values_dict[ind].append(integer)
                board[index] = 0
                zeros_list.append(index)
        return 0
# does forward checking
def fc(tup):
    #board = tup[0]
    index = tup[0]
    value = tup[1]
    values_dict = tup[2]
    zeros = tup[3]
    ROW = "ABCDEFGHI"
    COL = "123456789"
    row_letter = index[0] # 'A'
    col_number = index[1] # '1'
    left = {}
    # check if value violates rules of sudoku
    # first check along the row
    for c in COL:
        # we skip the chosen square itself
        target = row_letter+c
        if target in zeros:
            if c != col_number:
                if value in values_dict[target]:
                    values_dict[target].remove(value)
                    if target not in left:
                        left[target] = []
                    left[target].append(value)

    for r in ROW:
        target = r+col_number
        if target in zeros:
            if r != row_letter:
                if value in values_dict[target]:
                    values_dict[target].remove(value)
                    if target not in left:
                        left[target] = []
                    left[target].append(value)

    abc = ('A', 'B', 'C')
    def_ = ('D', 'E', 'F')
    ghi = ('G', 'H', 'I')
    _123 = ('1', '2', '3')
    _456 = ('4', '5', '6')
    _789 = ('7', '8', '9')
    r = index[0]
    c = index[1]
    if r in abc:
        if c in _123:
            for letter in abc:
                for number in _123:
                    target = letter+number
                    if target in zeros:
                        if r != letter:
                            if c != number:
                                if value in values_dict[target]:
                                    values_dict[target].remove(value)
                                    if target not in left:
                                        left[target] = []
                                    left[target].append(value)
        if c in _456:
            for letter in abc:
                for number in _456:
                    target = letter+number
                    if target in zeros:
                        if r != letter:
                            if c != number:
                                if value in values_dict[target]:
                                    values_dict[target].remove(value)
                                    if target not in left:
                                        left[target] = []
                                    left[target].append(value)
        if c in _789:
            for letter in abc:
                for number in _789:
                    target = letter+number
                    if target in zeros:
                        if r != letter:
                            if c != number:
                                if value in values_dict[target]:
                                    values_dict[target].remove(value)
                                    if target not in left:
                                        left[target] = []
                                    left[target].append(value)
    if r in def_:
        if c in _123:
            for letter in def_:
                for number in _123:
                    target = letter+number
                    if target in zeros:
                        if r != letter:
                            if c != number:
                                if value in values_dict[target]:
                                    values_dict[target].remove(value)
                                    if target not in left:
                                        left[target] = []
                                    left[target].append(value)
        if c in _456:
            for letter in def_:
                for number in _456:
                    target = letter+number
                    if target in zeros:
                        if r != letter:
                            if c != number:
                                if value in values_dict[target]:
                                    values_dict[target].remove(value)
                                    if target not in left:
                                        left[target] = []
                                    left[target].append(value)
        if c in _789:
            for letter in def_:
                for number in _789:
                    target = letter+number
                    if target in zeros:
                        if r != letter:
                            if c != number:
                                if value in values_dict[target]:
                                    values_dict[target].remove(value)
                                    if target not in left:
                                        left[target] = []
                                    left[target].append(value)
    if r in ghi:
        if c in _123:
            for letter in ghi:
                for number in _123:
                    target = letter+number
                    if target in zeros:
                        if r != letter:
                            if c != number:
                                if value in values_dict[target]:
                                    values_dict[target].remove(value)
                                    if target not in left:
                                        left[target] = []
                                    left[target].append(value)
        if c in _456:
            for letter in ghi:
                for number in _456:
                    target = letter+number
                    if target in zeros:
                        if r != letter:
                            if c != number:
                                if value in values_dict[target]:
                                    values_dict[target].remove(value)
                                    if target not in left:
                                        left[target] = []
                                    left[target].append(value)
        if c in _789:
            for letter in ghi:
                for number in _789:
                    target = letter+number
                    if target in zeros:
                        if r != letter:
                            if c != number:
                                if value in values_dict[target]:
                                    values_dict[target].remove(value)
                                    if target not in left:
                                        left[target] = []
                                    left[target].append(value)
    return left

# checks 3x3 square of individual 1x1 square
def check_square(board, value, index):

    abc = ('A', 'B', 'C')
    def_ = ('D', 'E', 'F')
    ghi = ('G', 'H', 'I')
    _123 = ('1', '2', '3')
    _456 = ('4', '5', '6')
    _789 = ('7', '8', '9')

    r = index[0]
    c = index[1]
    if r in abc:
        if c in _123:
            for letter in abc:
                for number in _123:
                    if (board[letter+number] == (value)):
                        return False
        if c in _456:
            for letter in abc:
                for number in _456:
                    if (board[letter+number] == (value)):
                        return False
        if c in _789:
            for letter in abc:
                for number in _789:
                    if (board[letter+number] == (value)):
                        return False
    if r in def_:
        if c in _123:
            for letter in def_:
                for number in _123:
                    if (board[letter+number] == (value)):
                        return False
        if c in _456:
            for letter in def_:
                for number in _456:
                    if (board[letter+number] == (value)):
                        return False
        if c in _789:
            for letter in def_:
                for number in _789:
                    if (board[letter+number] == (value)):
                        return False
    if r in ghi:
        if c in _123:
            for letter in ghi:
                for number in _123:
                    if (board[letter+number] == (value)):
                        return False
        if c in _456:
            for letter in ghi:
                for number in _456:
                    if (board[letter+number] == (value)):
                        return False
        if c in _789:
            for letter in ghi:
                for number in _789:
                    if (board[letter+number] == (value)):
                        return False
    return True
# return list of nonassigned
def not_assigned(board):
    not_assigned = []
    for r in ROW:
        for c in COL:
            if(board[r + c] == 0):
                not_assigned.append(r+c)
    return not_assigned
# checks and returns domain of variables
def check_domain(board):
    dom = {}
    for r in ROW:
        for c in COL:
            if(board[r+c] == 0):
                dom[r+c] = [1, 2, 3, 4, 5, 6, 7, 8, 9]
            if(board[r+c] != 0):
                dom[r+c] = [board[r+c]]
    return dom

if __name__ == '__main__':
    global then

    if len(sys.argv) > 1:  # Run a single board, as done during grading
        board = string_to_board(sys.argv[1])
        write_solved(board)
        print(count)

    else:
         print("Running all from sudokus_start")

         #  Read boards from source.
         try:
             srcfile = open(src_filename, "r")
             sudoku_list = srcfile.read()
         except:
             print("Error reading the sudoku file %s" % src_filename)
             exit()

         # Solve each board using backtracking
         for line in sudoku_list.split("\n"):

             if len(line) < 9:
                 continue

             # Parse boards to dict representation
             board = string_to_board(line)
             #print_board(board)  # TODO: Comment this out when timing runs.

             # Append solved board to output.txt
             then = time.time()

             write_solved(board, mode='a+')

         print("Finished all boards in file.")
