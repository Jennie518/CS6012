import matplotlib.pyplot as plt
import pandas as pd

# 数据文件的路径
data_file = '/Users/zhanyijun/Desktop/CS6012/assignment/construction_data.csv'  # 根据您的文件路径进行替换

# 从 CSV 文件中读取数据
data = pd.read_csv(data_file)
data.columns = ['Size', 'AvgTimeBulk', 'AvgTimeIndividual']

# 绘制数据
plt.figure(figsize=(10, 6))
plt.plot(data['Size'], data['AvgTimeBulk'], label='Bulk Construction Time', marker='o')
plt.plot(data['Size'], data['AvgTimeIndividual'], label='Individual Insertion Time', marker='x')

# 添加标签和标题
plt.xlabel('Size of BSPTree (Number of Segments)')
plt.ylabel('Average Time (nanoseconds)')
plt.title('Performance Comparison of BSPTree Construction Methods')
plt.legend()
plt.xscale('log')  # 使用对数尺度来处理指数级变化的大小
plt.yscale('log')  # 使用对数尺度来更好地可视化时间差异

# 显示绘图
plt.show()
