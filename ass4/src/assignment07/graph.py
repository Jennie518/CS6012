import pandas as pd
import matplotlib.pyplot as plt

# 读取CSV文件
data = pd.read_csv('/Users/zhanyijun/Desktop/CS6012/ass4/hash_function_add_performance.csv')

# # 创建第一个图表（碰撞次数）
# plt.figure(figsize=(10, 6))
# for functor in data['Hash Function'].unique():
#     subset = data[data['Hash Function'] == functor]
#     plt.plot(subset['Size'], subset['Collisions'], label=f'{functor} Collisions', marker='o')
# plt.xlabel('Data Size')
# plt.ylabel('Collisions')
# plt.title('Hash Function Collisions Comparison')
# plt.legend()
# plt.show()

# 创建第二个图表（持续时间）
plt.figure(figsize=(10, 6))
for functor in data['Hash Function'].unique():
    subset = data[data['Hash Function'] == functor]
    plt.plot(subset['Size'], subset['Duration (ns)'], label=f'{functor} Duration', marker='x')
plt.xlabel('Data Size')
plt.ylabel('Duration (ns)')
plt.title('Hash Function Duration Comparison')
plt.legend()
plt.show()
