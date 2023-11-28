import pandas as pd
import matplotlib.pyplot as plt

# Read the CSV file
data = pd.read_csv('/Users/zhanyijun/Desktop/CS6012/ass4/bst_experiment_results.csv')

# Plotting
plt.figure(figsize=(10, 6))
plt.plot(data['N'], data['TimeSorted'], label='Sorted Order')
plt.plot(data['N'], data['TimeRandom'], label='Random Order')
plt.xlabel('Number of Items (N)')
plt.ylabel('Time (ms)')
plt.title('BST Performance: Sorted vs Random Order')
plt.legend()
plt.grid(True)
plt.show()
