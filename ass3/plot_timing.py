import matplotlib.pyplot as plt

X = 0
Y = 1
def main():
    data = readFile("/Users/zhanyijun/Desktop/CS6012/lab03/data.csv")
    plt.figure(1)
    x = []
    y = []
    counter = 0
    for key in data:
        x.append(data[key][X])
        y.append([data[key][X],data[key][Y]])

    arr_x = [float(sublist[0][0]) for sublist in y]  # Convert strings to floats
    arr_y = [float(sublist[1][0]) for sublist in y]


# arr_x = np.array(x)
    # arr_y = np.array(y)
    # print(arr_y)
    plt.plot(arr_x, arr_y,'ro-')
    plt.savefig('python_example1.png')
    plt.show()
    plt.close()



def readFile(filename):
    data = None
    delim = "\t"
    plot = {}
    with open(filename, 'r') as f:
        data = [line.split(delim) for line in f]
        # print(data)
    for datum in data[0:]:
        # print(datum)
        key = datum[0]
        # print(key)
        if(key not in plot):
            plot[key] = ([],[])
        plot[key][X].append(datum[0])
        plot[key][Y].append(datum[1].split("\n")[0])

    return plot


if __name__ == '__main__':
    main()
