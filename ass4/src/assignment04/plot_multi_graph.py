# import matplotlib.pyplot as plt
#
# strategies = ["FIRST_ELEMENT", "LAST_ELEMENT", "RANDOM_ELEMENT"]
#
# for strategy in strategies:
#     filename = f'/Users/zhanyijun/Desktop/CS6012/ass4/quicksort_pivot_{strategy}.csv'
#     with open(filename, 'r') as file:
#         lines = file.readlines()
#
#     sizes, times = [], []
#     for line in lines:
#         size, time = map(float, line.split())
#         sizes.append(size)
#         times.append(time)
#
#     if sizes and times:
#         plt.plot(sizes, times, marker='o', label=f'Strategy {strategy}')
#
# plt.title('Quicksort Pivot Strategy Running Time vs. Size')
# plt.xlabel('Size')
# plt.ylabel('Average Time (nanoseconds)')
# plt.legend()
# plt.show()
import matplotlib.pyplot as plt

# 阈值列表，包括模拟完全使用归并排序的情况
thresholds = [5, 10, 15, 20, float('inf')]

for threshold in thresholds:
    # 生成文件名，特别处理模拟完全归并排序的情况
    threshold_label = "FullMergeSort" if threshold == float('inf') else str(threshold)
    filename = f'/Users/zhanyijun/Desktop/CS6012/ass4/mergesort_threshold_{threshold_label}.csv'

    sizes, times = [], []

    try:
        with open(filename, 'r') as file:
            lines = file.readlines()

        for line in lines:
            line = line.strip()  # 移除行首行尾的空白字符
            if not line:
                continue  # 跳过空行
            size, time = map(float, line.split('\t'))  # 使用制表符分隔字段
            sizes.append(size)
            times.append(time)
    except FileNotFoundError:
        print(f"File not found: {filename}")
        continue

    if sizes and times:
        label = 'Full MergeSort' if threshold == float('inf') else f'Threshold {threshold}'
        plt.plot(sizes, times, marker='o', label=label)

plt.title('MergeSort Threshold vs. Running Time vs. Size')
plt.xlabel('Size')
plt.ylabel('Average Time (nanoseconds)')
plt.legend()
plt.show()
