import numpy as np
import sys
import csv

export_data = []

def main():
    # python p2.py input2.cv output2.csv ~ 4 args
    if (len(sys.argv) < 3):
        print("not enough arguments")

    input_csv = sys.argv[1]
    output_csv = sys.argv[2]
    # make two functions, one for input, second for output
    parse_input(input_csv)

def parse_input(input_data):
    filename = input_data
    #data = []
    with open(filename, 'rb') as csvfile:
        csv_reader = csv.reader(csvfile, delimiter=',')
        list_csvreader = list(csv_reader)
        # csv data in numpy form
        np_csv = np.array(list_csvreader, dtype=float)
        # take first two columns of input data
        x_points = np_csv[:,0:2]
        # must parse through lists
        y_lists = np_csv[:,2:3]
        y_markers = []
        for list_ in y_lists:
            y_markers.append(list_[0])
        # DO PREPARATION OF DATA
        y_markers = np.array(y_markers)
        # x_points, y_markers are good to go. implement x_scaled function
        x_scaled = (x_points-np.mean(x_points, axis=0))/np.std(x_points, axis=0)
        # add a vector column for the matrix
        vector_col = np.ones(len(y_markers))
        vector_col = (vector_col.reshape(-1,1))
        points = np.hstack((vector_col, x_scaled))
        # changed from for to while loop
        alphas = [0.001, 0.005, 0.01, 0.05, 0.1, 0.5, 1, 5, 10]
        #print(y_markers)
        while len(alphas) > 0:
            a = alphas.pop(0)
            b, l = parse_gradient(points, y_markers, a)
            # write alpha and beta to output csv"""
        #print(export_data)
        export_output(export_data)

def export_output(data):
    out_file = sys.argv[2]
    with open(out_file, 'wb') as csvfile:
        csv_writer = csv.writer(csvfile, delimiter=',')
        alphas = [0.001, 0.005, 0.01, 0.05, 0.1, 0.5, 1, 5, 10]
        for row in data:
            a = alphas.pop(0)
            ra = '%.4f'%(row[0])
            rb = '%.4f'%(row[1])
            rc = '%.4f'%(row[2])
            csv_writer.writerow((a, 100, ra, rb, rc))

# points = 3 col array, y = array
def parse_gradient(points, y, a):
    # initialize to 0's
    b = np.zeros(len(points[0]))
    iter, l = 0, 0
    while iter < 100:
        # gradient formula implementation
        fx = np.dot(points, b) - y
        fx_t = fx * points.T
        sum = np.sum(fx_t.T, 0)
        b_ = a*sum/len(y)
        # final beta val
        b = b - b_
        # compute loss
        l = loss(points, b, y)
        # continue iteration
        iter += 1
    #print the loss?
    export_data.append(b)
    return 0,1

# returns loss, implements risk function
def loss(points, b, y):
    loss = np.dot(points, b) - y
    loss = np.sum(np.square(loss))/(len(y)*2)
    return loss

if __name__ == "__main__":
    main()
