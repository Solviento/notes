# 2048 algorithm with heuristic
from BaseAI import BaseAI
import time

INF = float("inf")
NEG_INF = -float("inf")

class PlayerAI(BaseAI): 
    
    global START_TIME
    # montonicity as given from stack overflow page in assignment pdf
    # https://stackoverflow.com/questions/22342854/what-is-the-optimal-algorithm-for-the-game-2048
    def monotonic(self, grid):
        # UP +, UP - and LEFT +, LEFT -
        up_plus, up_minus = 0,0
        left_plus, left_minus, i = 0,0,0
        # "elevation" like algorithm
        while i < 4:
            if grid.map[1][i] <= grid.map[2][i] and grid.map[0][i] <= grid.map[1][i]:
                if grid.map[2][i] <= grid.map[3][i]:
                    up_plus += 1
            if grid.map[0][i] >= grid.map[1][i] and grid.map[1][i] >= grid.map[2][i]:
                if grid.map[2][i] >= grid.map[3][i]:
                    up_minus += 1
            if grid.map[i][1] >= grid.map[i][2] and grid.map[i][0] >= grid.map[i][1]:
                if grid.map[i][2] >= grid.map[i][3]:
                    left_minus += 1
            if grid.map[i][0] <= grid.map[i][1] and grid.map[i][1] <= grid.map[i][2]:
                if grid.map[i][2] <= grid.map[i][3]:
                    left_plus += 1
            i = i + 1
        return (max(up_plus, up_minus) + \
                max(left_minus, left_plus)) / 8.0
    
    # taken from stack overflow tips given on homework pdf, 
    # returns smoothness and merges number based on grid
    def smoothMerge(self, grid):
        maximum = 0
        # smooth operators
        top, down = 0, 0
        # merge
        down_up = 0
        right_left = 0
        # go through entire grid and find max value, base, and corner boost
        for i in range(0,4):
            for j in range(0,4):
                cellValue = grid.map[i][j]
                if cellValue > maximum:
                    maximum = cellValue
                # smoothness
                if grid.map[i][j] != 0:
                    if j+1 < 4:
                        down += 1
                        if grid.map[i][j+1] == grid.map[i][j]:
                            top += 1
                    if i+1 < 4:
                        down += 1
                        if grid.map[i+1][j] == grid.map[i][j]:
                            top += 1
                # merge
                if grid.map[i][j] == 0:
                    j = j + 1
                    continue
                if (i+1) < 4 and grid.map[i][j] == grid.map[i+1][j]:
                    right_left = right_left + 1
                if (j+1) < 4 and grid.map[i][j] == grid.map[i][j+1]:
                    down_up = down_up + 1
        # empty tiles
        emptyTiles = (len(grid.getAvailableCells()))/15.0;
        # smoothness
        ret_merge = 0.02*max(down_up, right_left)
        if top == 0:
            return 0, ret_merge, emptyTiles
        
        ret_smooth = 0.30*(float(top)/down)
        return ret_smooth, ret_merge, emptyTiles
    
    # bool of whether max number is located in a corner (good heuristic)
    def cornerMaximum(self, grid):
        maximum = 0
        # go through entire grid and find max value, base, and corner boost
        for i in range(0,4):
            for j in range(0,4):
                #cellValue = grid.map[i][j]
                if grid.map[i][j] > maximum:
                    maximum = grid.map[i][j]
                    
        if (grid.map[0][0] == maximum):
            return True
        
        elif (grid.map[0][3] == maximum):
            return True
        
        elif (grid.map[3][3] == maximum):
            return True
        
        elif (grid.map[3][0] == maximum):
            return True
        
        else:
            return False
    
    def heuristic(self, grid):
        MONO, EMPTY, CORNER = 0.25, 0.38, 0.05
        smooth, merge, empty = self.smoothMerge(grid)
        return  merge + smooth + EMPTY*empty + \
                MONO*self.monotonic(grid) + \
                CORNER*self.cornerMaximum(grid) #9.85*self.cornerMaximum(grid)

    # based on lecture slides for alpha beta minimax algorithm
    def expectimaximin_n(self, grid, alpha, beta, depth):
        if self.timer() == False:
            #print("minimize, before for child")
            minChildMove = None
            minUtility = INF    # good
            actions, availableChildren = self.availableChildren(grid)
            
            for child in availableChildren:
                _, utility = self.expectimaximin_x(child, alpha, beta, depth+1)
                #print("\nUtility: " + str(utility))
                if utility < minUtility:
                    minChildMove, minUtility = \
                        actions[availableChildren.index(child)], utility
                    
                if minUtility <= alpha:
                    break
                if minUtility < beta:
                    beta = minUtility
            
            return minChildMove, minUtility
        #print("minimize, return heuristic")
        if self.timer() == True:
            #print(grid)
            return None, self.heuristic(grid)
        if depth >= 10:
            return None, self.heuristic(grid)
        
    # based on lecture slides for alpha beta minimax algorithm
    def expectimaximin_x(self, grid, alpha, beta, depth):

        if self.timer() == False:
            #print("maximize, before for child")
            maxChildMove = None
            maxUtility = NEG_INF   # good
            actions, availableChildren = self.availableChildren(grid)
            
            for child in availableChildren:
                _, utility = self.expectimaximin_n(child, alpha, beta, depth+1)
                #print("\nUtility: " + str(utility))
                if utility > maxUtility:
                    #print("being called, maxchildmove is no longer none")
                    maxChildMove, maxUtility = \
                        actions[availableChildren.index(child)], utility
                    
                if maxUtility >= beta:
                    break
                
                if maxUtility > alpha:
                    alpha = maxUtility
            #print("i should be the last thing called")
            return maxChildMove, maxUtility
        
        if self.timer() == True:
            #print("maximize, return heuristic")
            return None, self.heuristic(grid)
        
        if depth >= 10:
            return None, self.heuristic(grid)
    
    # from lecture notes, return children nodes of current grid state
    def availableChildren(self, grid):
        actions = grid.getAvailableMoves()
        availableChildren = []
        for i in range(len(actions)):
            if actions[i] == False:         # 0
                gridCopy = grid.clone()
                gridCopy.moveUD(False)
                availableChildren.append(gridCopy)
            if actions[i] == True:          # 1
                gridCopy = grid.clone()
                gridCopy.moveUD(True)
                availableChildren.append(gridCopy)
            if actions[i] == 2:
                gridCopy = grid.clone()
                gridCopy.moveLR(False)
                availableChildren.append(gridCopy)
            if actions[i] == 3:
                gridCopy = grid.clone()
                gridCopy.moveLR(True)
                availableChildren.append(gridCopy)
        
        return actions, availableChildren
    
    # determines total amount of time left needed for move calculation
    def timer(self):
        global START_TIME
        #print(type(START_TIME))
        if abs(time.clock() - START_TIME) >= 0.12:
            return True
        else:
            return False
    
    # taken from "decision(state)" method from lecture slides
    def getMove(self, grid): 
        global START_TIME 
        START_TIME = time.clock()
        alpha, beta = NEG_INF, INF
        depth = 0
        childMove, _ = self.expectimaximin_x(grid, alpha, beta, depth)
        #print(type(childMove))
        return childMove