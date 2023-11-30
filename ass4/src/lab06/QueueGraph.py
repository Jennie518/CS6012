import pandas as pd
import matplotlib.pyplot as plt

# 读取 CSV 文件
df = pd.read_csv("/Users/zhanyijun/Desktop/CS6012/ass4/timing_results.csv")

# 设置图表的大小和风格
plt.figure(figsize=(10, 6))
plt.style.use('ggplot')

# 对每种数据结构和操作组合绘图
for (data_structure, operation), group in df.groupby(['Data Structure', 'Operation']):
    # 绘制每种数据结构和操作的性能曲线
    plt.plot(group['Size'], group['Time (ns)'], label=f'{data_structure} - {operation}')

# 设置图表标题和轴标签
plt.title('Performance Comparison of Priority Queues')
plt.xlabel('Size of Data Structure')
plt.ylabel('Time (ns)')
plt.xscale('log')  # 使用对数刻度，如果数据范围很大
plt.yscale('log')  # 使用对数刻度，如果数据范围很大
plt.legend()  # 显示图例
plt.grid(True)  # 显示网格
plt.tight_layout()  # 调整布局

# 显示图表
plt.show()
