import pandas as pd
import matplotlib.pyplot as plt

# 读取 CSV 文件
data = pd.read_csv("/Users/zhanyijun/Desktop/CS6012/ass4/sorts_timing_data.csv")

# 设置绘图
plt.figure(figsize=(10, 6))

# 为 Mergesort 和 Quicksort 的每种情况绘制一条线
for algorithm in ['Mergesort', 'Quicksort']:
    for list_type in ['Best', 'Average', 'Worst']:
        subset = data[(data['Algorithm'] == algorithm) & (data['ListType'] == list_type)]
        plt.plot(subset['Size'], subset['Time'], label=f'{algorithm} {list_type}')

# 设置图表标题和坐标轴标签
plt.title('Mergesort vs. Quicksort Performance')
plt.xlabel('List Size')
plt.ylabel('Average Time (nanoseconds)')
plt.xscale('log')
plt.yscale('log')
plt.legend()
plt.grid(True)

# 显示图表
plt.show()
