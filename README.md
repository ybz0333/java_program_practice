# java编程练习
## 2016.02.27
第一次上传，主要是八大排序算法。
位于com.ybz.sort包中，
*Try.java是自己对算法的编写尝试，没有Try的文件是网络上的答案。
#### 经典排序算法的时间复杂度
- 插入排序、选择排序、冒泡排序：O(N^2)
- 归并排序、快速排序、堆排序、希尔排序：O(N*logN)
- 计数排序、基数排序：O(N)

#### 经典排序算法的空间复杂度
- 插入排序、选择排序、冒泡排序、堆排序、希尔排序：O(1)
- 快速排序：O(logN)~O(N)（取决于划分情况）
- 归并排序：O(N)（有方法可以优化到O(1)，但时间复杂度会上升）
- 计数排序、基数排序：O(M)（取决于选择桶的数量）

#### 稳定性的概念：
假定待排序的记录序列中，存在多个具有相同的关键字的记录，若经过排序，这些记录的相对次序保持不变，称这种排序算法是稳定的，否则称为不稳定的。

#### 稳定的排序算法：
冒泡排序、插入排序 归并排序 计数排序、基数排序（桶排序）

#### 不稳定的排序算法：
选择排序 快速排序、希尔排序、堆排序

#### 补充说明：
1. 对于人的年龄排序或者身高排序，因为这种数据范围通常比较小，可以考虑采用计数排序，但是对于均匀分布的证书，计数排序就不合适了。
2. 快速排序之所以叫快速排序，并不代表它比堆排序和归并排序优良。在最好情况下，它的渐进复杂度与堆排序和归并排序是相同的。只是快速排序的常量系数比较小而已。
3. 工程上的排序：
 - 工程上的排序是综合排序
 - 数组较小时，插入排序
 - 数组较大时，快速排序或其它O(N*logN)的排序
