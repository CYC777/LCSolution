Description
For a series given server log file, get peak memory usage on the server
Log file format:

ProgId, startTime, EndTime, Memory used

eg:
1,	6:10,        8:20,       300 mb + 611 301 612 302
2, 	8:20,        9:30,       400 mb
3， 7:31，       7:35,        200mb
4，
5，


610, 709 : 300
7:10, 730: 700,
731, 820: 300

answer: 700 mb

log file

(Time,Mem,flag)

Log to List
List

sort  + , -

(610,300,+)
(710,400,+)
(730,400,- )
(820,300,- )

CurrMem: 300 700 300 0
MaxMem: 300 700 700 700

0000             2400

610--------------------------------- 820

          710 -------------730


```java
class Solution {
    public int maxMemoryUsage(List<int[]> logs) {
        int maxMem = 0;
        int mem = 0;
        List<int[]> list = new ArrayList<>();
        for (int[] log : logs) {
            list.add(new int[]{log[0], log[2], 1});
            list.add(new int[]{log[1], log[2], -1});
        }

//时间点相同的话，先把结束的减了
        list.sort((a, b) -> (a[0] == b[0] ? a[2] - b[2] : a[0] - b[0]));

        for (int[] log : list) {
            mem += log[1] * log[2];
            maxMem = Math.max(mem, maxMem);
        }
        return maxMem;
    }
}
```

思路/notes:
扫描线算法。就是按时间顺序遍历memory变化的节点，只有节点memory才会变化。
