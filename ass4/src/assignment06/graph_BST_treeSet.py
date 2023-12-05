import pandas as pd
import matplotlib.pyplot as plt

# Assuming the CSV file 'bst_performance.csv' is in the current working directory.
# If it's in another directory, provide the full path to the file.
csv_file = '/Users/zhanyijun/Desktop/CS6012/ass4/bst_performance.csv'

# Read the CSV file into a DataFrame
df = pd.read_csv(csv_file)

# Plotting the performance
plt.figure(figsize=(14, 7))

# Add performance plot
plt.subplot(1, 2, 1)
plt.plot(df['N'], df['TimeTreeSetAdd'], label='TreeSet Add', marker='o')
plt.plot(df['N'], df['TimeBSTAdd'], label='BST Add', marker='x')
plt.xlabel('Number of Items (N)')
plt.ylabel('Time (ms)')
plt.title('Add Operation Performance')
plt.legend()
plt.grid(True)

# Contains performance plot
plt.subplot(1, 2, 2)
plt.plot(df['N'], df['TimeTreeSetContains'], label='TreeSet Contains', marker='o')
plt.plot(df['N'], df['TimeBSTContains'], label='BST Contains', marker='x')
plt.xlabel('Number of Items (N)')
plt.ylabel('Time (ms)')
plt.title('Contains Operation Performance')
plt.legend()
plt.grid(True)

# Show the plot
plt.tight_layout()
plt.show()

