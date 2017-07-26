package com.jemmm.sort;

import java.util.Arrays;

import static com.jemmm.utils.PrintfUtils.printf;

/**
 * Created by BIG-JIAN on 2017/7/8.
 */
/*基数排序已经不再是一种常规的排序方式，它更多地像一种排序方法的应用，基数排序必须依赖于另外的排序方法。基数排序的总体思路就是将待排序数据拆分成多个关键字进行排序，也就是说，基数排序的实质是多关键字排序。
多关键字排序的思路是将待排数据里德排序关键字拆分成多个排序关键字；第1个排序关键字，第2个排序关键字，第3个排序关键字......然后，根据子关键字对待排序数据进行排序。
多关键字排序时有两种解决方案：
最高位优先法（MSD）(Most Significant Digit first)
最低位优先法（LSD）(Least Significant Digit first)
例如，对如下数据序列进行排序。
192,221,12,23
可以观察到它的每个数据至多只有3位，因此可以将每个数据拆分成3个关键字：百位（高位）、十位、个位（低位）。
如果按照习惯思维，会先比较百位，百位大的数据大，百位相同的再比较十位，十位大的数据大；最后再比较个位。人得习惯思维是最高位优先方式。
如果按照人得思维方式，计算机实现起来有一定的困难，当开始比较十位时，程序还需要判断它们的百位是否相同--这就认为地增加了难度，计算机通常会选择最低位优先法。
基数排序方法对任一子关键字排序时必须借助于另一种排序方法，而且这种排序方法必须是稳定的。
对于多关键字拆分出来的子关键字，它们一定位于0-9这个可枚举的范围内，这个范围不大，因此用桶式排序效率非常好。
对于多关键字排序来说，程序将待排数据拆分成多个子关键字后，对子关键字排序既可以使用桶式排序，也可以使用任何一种稳定的排序方法。*/

public class MultiKeyRadixSort {
    public static void main(String[] args) {
        int[] data = new int[] { 1100, 191, 226, 12, 12, 23 };
        printf(data);
        radixSort(data, 10, 4);
        System.out.println("排序后的数组：");
        printf(data);
    }

    public static void radixSort(int[] data, int radix, int d) {
        // 缓存数组
        int[] tmp = new int[data.length];
        // buckets用于记录待排序元素的信息
        // buckets数组定义了max-min个桶
        int[] buckets = new int[radix];

        for (int i = 0, rate = 1; i < d; i++) {

            // 重置count数组，开始统计下一个关键字
            Arrays.fill(buckets, 0);
            // 将data中的元素完全复制到tmp数组中
            System.arraycopy(data, 0, tmp, 0, data.length);

            // 计算每个待排序数据的子关键字
            for (int j = 0; j < data.length; j++) {
                int subKey = (tmp[j] / rate) % radix;
                buckets[subKey]++;
            }

            for (int j = 1; j < radix; j++) {
                buckets[j] = buckets[j] + buckets[j - 1];
            }
            // 按子关键字对指定的数据进行排序
            for (int m = data.length - 1; m >= 0; m--) {
                int subKey = (tmp[m] / rate) % radix;
                data[--buckets[subKey]] = tmp[m];
            }
            rate *= radix;

        }

    }
}