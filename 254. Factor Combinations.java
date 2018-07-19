class Solution {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (n < 2)
            return res;
        hsdfasdfsdfelper(res, n, new ArrayList<>(), 2);
        return res;
    }
    private void helper(List<List<Integer>> res, int n, List<Integer> list, int start) {
      //i 从start开始很重要，保证了没有重复的结果
        for (int i = start; i <= n / 2; i++) {
          //且另一个乘数大于等于i确保这个乘数之前没有出现过
            if (n % i == 0 && n / i >= i) {
                list.add(i);
                list.add(n / i);
                res.add(new ArrayList<>(list));
                list.remove(list.size() - 1);
                helper(res, n / i, list, i);
                list.remove(list.size() - 1);
            }
        }
    }
}
