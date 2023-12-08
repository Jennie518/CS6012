import pandas as pd
import matplotlib.pyplot as plt

# Read the CSV file into a Pandas DataFrame
data = pd.read_csv('/Users/zhanyijun/Desktop/CS6012/assignment/construction_data.csv', delimiter='\t', header=None, names=['Size', 'Bulk', 'Individual'])

# Plot the data
plt.figure(figsize=(10, 6))
plt.plot(data['Size'], data['Bulk'], label='Bulk Construction', marker='o')
plt.plot(data['Size'], data['Individual'], label='Individual Insertions', marker='x')
plt.xlabel('Dataset Size')
plt.ylabel('Average Construction Time (nanoseconds)')
plt.title('Construction Performance')
plt.legend()
plt.grid(True)
plt.tight_layout()

# Save the plot as an image or display it
plt.savefig('construction_performance.png')
plt.show()
