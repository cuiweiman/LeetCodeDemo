package com.niuke.bytedance;

import java.util.*;

/**
 * 题目：https://www.nowcoder.com/practice/e3769a5f49894d49b871c09cadd13a61?tpId=117&tqId=37804&companyId=665&rp=1&ru=%2Fcompany%2Fhome%2Fcode%2F665&qru=%2Fta%2Fjob-code-high%2Fquestion-ranking&tab=answerKey
 * 设计LRU缓存结构，该结构在构造时确定大小，假设大小为K，并有如下两个功能：
 * 1. set(key, value)：将记录(key, value)插入该结构
 * 2. get(key)：返回key对应的value值
 * 要求
 * 1. set和get方法的时间复杂度为O(1)
 * 2. 某个key的set或get操作一旦发生，认为这个key的记录成了最常使用的。
 * 3. 当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
 * 4. 若opt=1，接下来两个整数x, y，表示set(x, y)
 * 5. 若opt=2，接下来一个整数x，表示get(x)，若x未出现过或已被移除，则返回-1
 * 6. 对于每个操作2，输出一个答案
 * <p>
 * 示例1
 * 输入
 * [[1,1,1],[1,2,2],[1,3,2],[2,1],[1,4,4],[2,2]],3
 * 返回
 * [1,-1]
 * <p>
 * 说明：
 * 第一次操作后：最常使用的记录为("1", 1)
 * 第二次操作后：最常使用的记录为("2", 2)，("1", 1)变为最不常用的
 * 第三次操作后：最常使用的记录为("3", 2)，("1", 1)还是最不常用的
 * 第四次操作后：最常用的记录为("1", 1)，("2", 2)变为最不常用的
 * 第五次操作后：大小超过了3，所以移除此时最不常使用的记录("2", 2)，加入记录("4", 4)，并且为最常使用的记录，然后("3", 2)变为最不常使用的记录
 *
 * @description: 设计LRU缓存结构
 * @author: wei·man cui
 * @date: 2021/3/18 9:39
 */
public class Nc93 {

    public static void main(String[] args) {
        int[][] operators = {{1, 1, 1}, {1, 2, 2}, {1, 3, 2}, {2, 1}, {1, 4, 4}, {2, 2}};
        int k = 3;
        final int[] result = lru(operators, k);
        Arrays.stream(result).forEach(System.out::println);
    }

    /**
     * lru design
     *
     * @param operators int整型二维数组 the ops
     * @param k         int整型 the k
     * @return int整型一维数组
     */
    public static int[] lru(int[][] operators, int k) {
        // write code here
        LruCache cache = new LruCache(k);
        List<Integer> list = new ArrayList<>();
        for (int[] operator : operators) {
            if (Objects.equals(operator[0], 1)) {
                cache.put(operator[1], operator[2]);
            } else if (Objects.equals(operator[0], 2)) {
                final Integer value = cache.get(operator[1]);
                list.add(value);
            } else {
                throw new RuntimeException("操作符错误");
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

}

class LruCache {
    private final int limit;
    private final LinkedList<Integer> keys = new LinkedList<>();
    private final HashMap<Integer, Integer> cache = new HashMap();

    public LruCache(int limit) {
        this.limit = limit;
    }

    public void put(Integer key, Integer value) {
        Integer k = Optional.ofNullable(key).orElseThrow(() -> new RuntimeException("key不允许为空"));
        Integer v = Optional.ofNullable(value).orElseThrow(() -> new RuntimeException("value不允许为空"));
        if (keys.size() >= limit) {
            final Integer last = keys.removeFirst();
            cache.remove(last);
        }
        keys.add(k);
        cache.put(k, v);
    }

    public Integer get(Integer key) {
        boolean exists = keys.remove(key);
        if (!exists) {
            return -1;
        }
        keys.add(key);
        return cache.get(key);
    }

}




