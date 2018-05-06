import re

def main():
    type_of_sort = {
        "InsertionSort": {},
        "QuickSort": {},
        "QuickSortR": {},
        "MergeSort": {},
        "HeapSort": {},
    }
    data = {
            "Randomly sorted list": type_of_sort.copy(),
            "Few Uniques": type_of_sort.copy(),
            "Already sorted": type_of_sort.copy(),
            "Reversed sorted": type_of_sort.copy(),
            "20% of items out of place": type_of_sort.copy(),
    }
    o = open("averages.txt", "w+");
    for list_type in data.keys():
        o.write(list_type+"\n")
        for type_of_sort in data[list_type].keys():
            o.write("\t"+type_of_sort+"\n")
            for list_size in range(1000, 100000, 1000):
                running_sum = 0
                with open("output/out"+str(list_size)+".txt", 'r') as f:
                    lines = [line for line in f]
                    for i in range(len(lines)):
                        if list_type in lines[i]:
                            while type_of_sort not in lines[i]:
                                i+=1
                            running_sum += int(lines[i].split()[-2])
                o.write("\t\t" + str(list_size) + " " + str(running_sum/10) + "\n")

if __name__ == "__main__":
    main()
