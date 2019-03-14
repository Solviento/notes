# problem1.py
import csv, sys
import numpy as np

# citation: https://docs.python.org/2/library/csv.html
def main():
    # check for arguments
    if len(sys.argv) < 3:
        print "incorrect number of arguments"
        exit()
    # infile
    filename = sys.argv[1]
    weight_lists = []
    with open(filename, 'rb') as csvfile:
        # read in csv file as zeros, array
        csv_reader = csv.reader(csvfile, delimiter=',')
        list_csvreader = list(csv_reader)
        weights = np.zeros(len(list_csvreader[0]))
        np_csv = np.array(list_csvreader, dtype=float)

        bool_np = 1
        while bool_np:
            bool_np = 0
            for row in np_csv:
                # iterate and add in features depending on label, dot product
                t = np.dot(np.insert(row[:-1], 2, 1), weights)*row[-1]
                if (t <= 0):
                    bool_np = 1
                    weights += row[-1] * np.insert(row[:-1], 2, 1)
            weight_lists.append(list(weights))
    outfile(weight_lists)
    #print(weight_lists)

# write output to csv file
def outfile(lists):
    outfilename = sys.argv[2]
    with open(outfilename, 'wb') as csvfile:
        csv_writer = csv.writer(csvfile, delimiter=',')
        for row in lists:
            csv_writer.writerow(row)

if __name__ == "__main__":
    main()
