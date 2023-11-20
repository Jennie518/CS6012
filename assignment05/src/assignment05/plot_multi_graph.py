import pandas as pd
import matplotlib.pyplot as plt

# Read CSV files
array_stack_df = pd.read_csv('/Users/zhanyijun/Desktop/CS6012/assignment05/stack_performance_ARRAY_STACK.csv', sep='\t', names=['Size', 'Time'])
linked_list_stack_df = pd.read_csv('/Users/zhanyijun/Desktop/CS6012/assignment05/stack_performance_LINKED_LIST_STACK.csv', sep='\t', names=['Size', 'Time'])

# Plotting
plt.figure(figsize=(10, 6))
plt.plot(array_stack_df['Size'], array_stack_df['Time'], label='ArrayStack')
plt.plot(linked_list_stack_df['Size'], linked_list_stack_df['Time'], label='LinkedListStack')

plt.xlabel('Size of Stack (Number of Elements)')
plt.ylabel('Average Time (ns)')
plt.title('Performance Comparison of Stack Implementations')
plt.xscale('log')  # As the size is in powers of 2, a log scale might be more appropriate
plt.yscale('log')  # If the times vary greatly, a log scale can help in visualization
plt.legend()
plt.grid(True)
plt.show()
