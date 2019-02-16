```java
/*
Description:
Unit Exchange:
1 A = 2 B
1 B = 3 C
1 B = 7 J
1 J = 2 I
1 C = 4 D

Question:  3 J = ? C
*/

/*
过程：
A: [(2,B)]
B: [(½,A), (3,C), J]
C: (⅓,B), D
J:(1/7B), (½,I)
D:C
 3 J = ? C
 */

class Solution {
    class Pair{
        float num;
        char unit;
        private Pair(float numIn, char unitIn) {
            num = numIn;
            unit = unitIn;
        }
    }
    public int findDest(int sourceNum, char sourceUnit, char destUnit, List<Equation> list) {
        HashMap<Character, List<Pair>> map = new HashMap<>();
        consturctGraph(map, list);

        // bfs
        Set<Character> visited = new HashSet<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair((float)sourceNum, sourceUnit));
        while (!queue.isEmpty()) {
            Pair node = queue.poll();
            List<Pair> pairs = map.get(node.unit);
            for (Pair pair : pairs) {
                //检查visited
                if (visited.contains(pair.unit)) continue;
                visited.add(pair.unit);

                //更新点的信息
                pair.num *= node.num;
                //检查validation（无)
                //检查是否是答案
                if (pair.unit == destUnit) {
                    return (int)pair.num;
                }
                //入queue
                queue.add(pair);
            }
        }
        return 0;
    }

    private void consturctGraph (HashMap <Character, List <Pair>> map, List<Equation> list){
        for (Equation equ : list) {
            char sourceUnit = equ.sourceUnit;
            char des = equ.destUnit;
            int sourceNum = equ.sourceNum;
            int destNum = equ.destNum;
            if (!map.containsKey(sourceUnit)) {
                map.put(sourceUnit, new ArrayList<>());
            }
            map.get(sourceUnit).add(new Pair((float)destNum / sourceNum, des));
            if (!map.containsKey(des)) {
                map.put(des, new ArrayList<>());
            }
            map.get(des).add(new Pair((float)sourceNum / destNum, sourceUnit));
        }
    }
}
```
思路：
BFS，系数可以一路换算下去。
