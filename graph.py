import plotly
from plotly.graph_objs import Scatter, Layout


def main():
    list_sizes = [i for i in range(1000, 101000, 1000)]
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
            "Reverse sorted": type_of_sort.copy(),
            "20% of items out of place": type_of_sort.copy(),
    }

    for list_type in data.keys():
        print(list_type)
        for type_of_sort in data[list_type].keys():
            print("\t"+type_of_sort)
            for list_size in range(1000, 101000, 1000):
                running_sum = 0
                with open("output/out"+str(list_size)+".txt", 'r') as f:
                    lines = [line for line in f]
                    for i in range(len(lines)):
                        if list_type in lines[i]:
                            while type_of_sort not in lines[i]:
                                i+=1
                            running_sum += int(lines[i].split()[-2])
                data[list_type][type_of_sort][list_size] = running_sum/10
                print("\t\t"+str(list_size)+" "+str(data[list_type][type_of_sort][list_size]))

    #Plot data
    for type_of_list in data.keys():
        #Write a trace for each sort
        traces = []
        for type_of_sort in data[type_of_list].keys():
            times = []
            for i in range(1000, 101000, 1000):
                times.append(data[type_of_list][type_of_sort][i])
            traces.append(Scatter(
                        x=list_sizes,
                        y=times,
                        mode="lines",
                        name=type_of_sort
            ))
        #Layout of graph
        layout = dict(title=type_of_list,
                      xaxis=dict(title="List Size"),
                      yaxis=dict(title="Sorting Time")
        )
        #Graph it!
        fig = dict(data=traces, layout=layout)
        plotly.offline.plot(fig, filename=type_of_list+".html")


if __name__ == "__main__":
    main()
