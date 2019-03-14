"""
Jason Perez - jp3476
driver.py will solve an 8 puzzle board using breadth, depth first, a-star
search algorithms.
Executed as follows:
    $ python driver.py <method> <board>
    method = bfs, dfs, ast
    board = starting configuration of n-puzzle board
    ex: python driver.py bfs 0, 1, 8, 7, 5, 4, 3, 2, 6
"""
from sys import argv
import sys, time
from collections import deque
import heapq

global ram          # use to store ram statistic

start_state = []
goal_state=['0','1','2','3','4','5','6','7','8']
test00 = ['1','2','3','5','0','6','7','4','8']
test01 = ['3','1','2','0','4','5','6','7','8']
test02 = ['1','2','3','4','0','5','6','7','8']
test03 = ['1','2','3','4','8','5','6','7','0']
test04 = ['1','2','5','3','4','0','6','7','8']
test05 = ['3','1','2','0','4','5','6','7','8']
test06 = ['1','4','2','0','5','8','3','6','7']
test10 = ['6','1','8','4','0','2','7','3','5']
test11 = ['8','6','4','2','1','3','5','7','0']
testeasy=['3','1','2','6','4','5','0','7','8']
easy =   ['3','1','2','0','4','5','6','7','8']

class Node(object):
    # initialize node
    def __init__(self, board, parent, action, nodes_expanded, search_depth, cost, max_search_depth):
        self.board = board # state
        self.parent = parent
        self.action = action
        self.nodes_expanded = nodes_expanded
        self.search_depth = search_depth
        self.cost = cost
        self.max_search_depth = max_search_depth
    # add child node to parent
    def add_child(self, obj):
        self.children.append(obj)
        
    def __cmp__(self, other):
        if other == None:
            #print("none")
            return 1
        return cmp(self.cost, other.cost)

def main():
    # verify correct command line arguments
    if len(argv) != 5:
        print("not 5 arguments")
    if len(argv) == 5:
        # check length of string
        if (len(argv[4])) != 17:    # valid for 8-puzzle board only
            print("Incorrect configuration of game board")
        # obtain starting board state
        else:
            i = 0
            while i <= len(argv[4]):
                start_state.append((argv[4][i]))
                i += 2
    # instantiate starting node 
    start_node = Node(start_state, None, 'Start', 0, 0, 0, 0)
    
    if argv[3] == 'bfs':
        run_time = time.time()
        goal = bfs(start_node)
        total_time = time.time() - run_time
        out(goal, total_time)
        
    if argv[3] == 'dfs':
        run_time = time.time()
        goal = dfs(start_node)
        total_time = time.time() - run_time
        out(goal, total_time)

    if argv[3] == 'ast':
        run_time = time.time()
        goal = ast(start_node)
        total_time = time.time() - run_time
        out(goal, total_time)
    
""" write output accordingly to homework description """
def out(goal, time):
    global ram
    
    f = open("output.txt","w")
    
    path = find_solution_path(goal)
    goal.search_depth = len(path)
    
    f.write("path_to_goal: " + str(path))
    f.write("\ncost_of_path: " + str(len(path))) #str(goal.cost))
    f.write("\nnodes_expanded: " + str(goal.nodes_expanded))
    f.write("\nsearch_depth: " + str(goal.search_depth))
    f.write("\nmax_search_depth: " + str(goal.max_search_depth))
    f.write("\nrunning_time: " + str(time))
    f.write("\nmax_ram_usage: " + (ram))
    
    f.close()

""" simple goal test check """
def goal_test(state):
    if state == goal_state:
        return True
    if state == ('0', '1', '2', '3', '4', '5', '6' , '7', '8'):
        return True
    else:
        return False

""" returns solution path from solution node """
def find_solution_path(node):
    path = []
    
    if node != None:
        #print("!= none")
        path.append(node.action)
    else:
        return path

    while True:
        parent = node.parent
        #print (node.parent.board)
        if parent == None:
            #print("solution path")
            break
        else:
            path.append(parent.action)
            node = parent
    # reverse path and pop first element
    path.reverse()
    path.pop(0)
    return path

# returns solution node
def bfs(node):
    # if node is goal state configuration, return
    if (goal_test(node.board)):
        return node
    
    frontier = deque()
    frontier.append(node)
    # keep track of explored, seen node states
    explored = set()
    seen = set()
    
    seen.add(tuple(node.board))
    nodes_expanded = 0
    max_search_depth = 0
    
    while frontier:
        shallowest_node = frontier.popleft()
        shallowest_board = tuple(shallowest_node.board)
        explored.add(shallowest_board)
        
        children_nodes = available_nodes(shallowest_node) # list of possible states

        for child_node in children_nodes:
            if (child_node.cost > max_search_depth):
                max_search_depth = child_node.cost
                
            child_board = tuple(child_node.board)
            if (child_board not in explored) and (child_board not in seen):
                nodes_expanded += 1
                if (goal_test(child_node.board)):
                    child_node.max_search_depth = max_search_depth + 1
                    child_node.nodes_expanded = nodes_expanded
                    return child_node
                else:
                    frontier.append(child_node)
                    seen.add(child_board)
    return None

def dfs(node):
    # if node is goal state configuration, return
    if (goal_test(node.board)):
        return node
    
    frontier = deque()
    frontier.append(node)
    # keep track of explored, seen node states
    explored = set()
    seen = set()
    
    seen.add(tuple(node.board))
    
    nodes_expanded = 0
    max_search_depth = 0

    while frontier:
        shallowest_node = frontier.pop()
        shallowest_board = tuple(shallowest_node.board)
        explored.add(shallowest_board)
        
        children_nodes = available_nodes(shallowest_node) # list of possible states
        nodes_expanded += 1
        children_nodes.reverse()
        
        for child_node in children_nodes:
            if (child_node.cost > max_search_depth):
                max_search_depth = child_node.cost
                
            child_board = tuple(child_node.board)
            
            if (child_board not in explored) and (child_board not in seen):
                if (goal_test(child_node.board)):
                    child_node.nodes_expanded = nodes_expanded
                    child_node.max_search_depth = max_search_depth
                    return child_node
                else:
                    frontier.append(child_node)
                    seen.add(child_board)
    return None

def ast(node):
    if (goal_test(node.board)):
        return node
    # use priority queue + manhattan distance heuristic
    # searching priority queue gives this result: 
    # https://docs.python.org/2/library/heapq.html
    # psuedocode taken from chapter 3 in textbook
    else:
        frontier = [node]
        # keep sets of all explored and seen node states
        explored = set()
        seen = set()
        
        seen.add(tuple(node.board))
        
        nodes_expanded = 0
        max_search_depth = 0
        
        while frontier:
            shallow_node = heapq.heappop(frontier)
            explored.add(tuple(shallow_node.board))
            
            nodes_expanded += 1
            children_nodes = available_nodes(shallow_node)
    
            for child_node in children_nodes:
                if (child_node.search_depth > max_search_depth):
                    #print(child_node.search_depth)
                    max_search_depth = child_node.search_depth - 1
                    
                current_state = tuple(child_node.board)
    
                if goal_test(tuple(shallow_node.board)) is True:
                    shallow_node.nodes_expanded = nodes_expanded
                    shallow_node.max_search_depth = max_search_depth
                    return shallow_node
                
                if current_state in explored:
                    continue
                else:
                    # heuristic function
                    h = heuristic(current_state)
                    total_cost = h + (shallow_node.cost - heuristic(shallow_node.board))
                    # add in the new state with cost
                    if current_state not in seen:
                        child_node.cost = (total_cost+1)
                        heapq.heappush(frontier, child_node)
                        seen.add(current_state)
                    # udapte the cost
                    if (total_cost+1) < child_node.cost:
                        frontier.remove(child_node)
                        heapq.heappush(frontier, child_node)
        return None

# returns heuristic of current state in relation to goal
def heuristic(current_state):
    # total manhattan distance (sum of horiz + vert distances)
    man_dist = 0
    for tile in current_state:
        #print("tile: " + tile)
        current_index = current_state.index(tile)
        if tile == '0':
            goal_index = 0
            man_dist += mandistance(current_index, goal_index)
        if tile == '1':
            goal_index = 1
            man_dist += mandistance(current_index, goal_index)
        if tile == '2':
            goal_index = 2
            man_dist += mandistance(current_index, goal_index)
        if tile == '3':
            goal_index = 3
            man_dist += mandistance(current_index, goal_index)
        if tile == '4':
            goal_index = 4
            man_dist += mandistance(current_index, goal_index)
        if tile == '5':
            goal_index = 5
            man_dist += mandistance(current_index, goal_index)
        if tile == '6':
            goal_index = 6
            man_dist += mandistance(current_index, goal_index)
        if tile == '7':
            goal_index = 7
            man_dist += mandistance(current_index, goal_index)
        if tile == '8':
            goal_index = 8
            man_dist += mandistance(current_index, goal_index)
            
    return man_dist

# return column number, row number of given index (8-puzzle only)
def colrow(index):
    if index == 0:
        return 1, 1
    if index == 1:
        return 2, 1
    if index == 2:
        return 3, 1
    if index == 3:
        return 1, 2
    if index == 4:
        return 2, 2
    if index == 5:
        return 3, 2
    if index == 6:
        return 1, 3
    if index == 7:
        return 2, 3
    if index == 8:
        return 3, 3

# computes manhattan distance of single tile
def mandistance(current_index, goal_index):
    current_col, current_row = colrow(current_index)
    goal_col, goal_row = colrow(goal_index)
    
    col_dist = goal_col - current_col
    row_dist = goal_row - current_row
    #print( abs(col_dist) + abs(row_dist))
    return abs(col_dist) + abs(row_dist)

def swapTile(current_state, index, operation):
    # create copy of node's current state configuration
    copy_state = list(current_state)
    # swap elements and return configuration
    if operation == 'Up':
        swap_index = index - 3
        temp_variable = current_state[swap_index]
        copy_state[swap_index] = '0'
        copy_state[index] = temp_variable
        return tuple(copy_state)
    
    if operation == 'Down':
        swap_index = index + 3
        #print("Down: ")
        #print(swap_index)
        #print(current_state)
        temp_variable = current_state[swap_index]
        copy_state[swap_index] = '0'
        copy_state[index] = temp_variable
        return tuple(copy_state)
    
    if operation == 'Left':
        swap_index = index - 1
        temp_variable = current_state[swap_index]
        copy_state[swap_index] = '0'
        copy_state[index] = temp_variable
        return tuple(copy_state)
    
    if operation == 'Right':
        swap_index = index + 1
        temp_variable = current_state[swap_index]
        copy_state[swap_index] = '0'
        copy_state[index] = temp_variable
        return tuple(copy_state)

# given a node, generate next possible states, return as a list of nodes
def available_nodes(node):
    child_nodes = []
    current_state = node.board
    blank_index = current_state.index('0')
    # move tile up
    if blank_index <= 8 and blank_index >= 3:
        up_childstate = swapTile(current_state, blank_index, 'Up')
        tmp_child_node = Node(up_childstate, node, 'Up', 0, node.search_depth +1, (node.cost + 1), 0)
        child_nodes.append(tmp_child_node)
        # add to number of total nodes expanded on search
        node.nodes_expanded += 1
        
    # move tile right
    if blank_index >= 0 and blank_index <= 5:
        down_childstate = swapTile(current_state, blank_index, 'Down')
        tmp_child_node = Node(down_childstate, node, 'Down', 0, node.search_depth +1, (node.cost + 1), 0)
        child_nodes.append(tmp_child_node)
        # add to number of total nodes expanded on search
        node.nodes_expanded += 1
        
    # move tile left
    if (blank_index == 1 or blank_index == 2 or blank_index == 4 or \
            blank_index == 5 or blank_index == 7 or blank_index == 8):
        left_childstate = swapTile(current_state, blank_index, 'Left')
        tmp_child_node = Node(left_childstate, node, 'Left', 0, node.search_depth +1, (node.cost + 1), 0)
        child_nodes.append(tmp_child_node)
        # add to number of total nodes expanded on search
        node.nodes_expanded += 1
        
    # move tile right
    if blank_index == 0 or blank_index == 1 or blank_index == 3 or \
            blank_index == 4 or blank_index == 6 or blank_index == 7:
        right_childstate = swapTile(current_state, blank_index, 'Right')
        tmp_child_node = Node(right_childstate, node, 'Right', 0, node.search_depth +1, (node.cost + 1), 0)
        child_nodes.append(tmp_child_node)
        # add to number of total nodes expanded on search
        node.nodes_expanded += 1
        
    return child_nodes

if sys.platform == "win32": 
    import psutil
    #print("psutil", psutil.Process().memory_info().rss)
    ram = str(psutil.Process().memory_info().rss)
else:
    import resource 
    #print("resource", resource.getrusage(resource.RUSAGE_SELF).ru_maxrss)
    ram = str(resource.getrusage(resource.RUSAGE_SELF).ru_maxrss)

if __name__ == "__main__":
    main()