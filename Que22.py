
TP = 90  
FP = 140 
FN = 210 
TN = 9560 
Total = 10000 


accuracy = (TP + TN) / Total

error_rate = (FP + FN) / Total

precision = TP / (TP + FP)

recall = TP / (TP + FN)

print(f"Accuracy: {accuracy:.4f}")
print(f"Error Rate: {error_rate:.4f}")
print(f"Precision: {precision:.4f}")
print(f"Recall: {recall:.4f}")