package nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

/**
*********************************************************************
* 
* @author poles
* @date 2020/8/27 9:36 上午
*
*********************************************************************
*/
public class LRU {
    LinkedHashMap<Integer, Integer> data = new LinkedHashMap<>();
    private int max;
    private int count = 1;

    /**
     * lru design
     * @param operators int整型二维数组 the ops
     * @param k int整型 the k
     * @return int整型一维数组
     */
    public Integer[] start (int[][] operators, int k) {
        this.max = k;
        List<Integer> list = new ArrayList<>();
        for(int[] group : operators){
            //输入
            if(group[0] == 1){
                set(group[1], group[2]);
            }else{
                list.add(get(group[1]));
            }
        }

        return list.toArray(new Integer[0]);
    }

    private void set(Integer key, Integer value){
        data.put(key, value);
        refreshIndex(key, value);
    }

    private Integer get(Integer key){
        Integer value = data.get(key);
        if(value == null){
            return -1;
        }
        refreshIndex(key, value);
        return data.get(key);
    }

    private void refreshIndex(Integer key, Integer value){
        //删除
        data.remove(key);
        //放在最左边
        data.put(key, value);
        //如果数量超标，删除第一个
        if(data.size() > this.max){
            Integer firstNode = data.keySet().iterator().next();
            data.remove(firstNode);
        }

        System.out.println("第" + (count++) + "次结果：" + data);
    }

    public static void main(String[] args) {
        LRU lru = new LRU();
        int[][] operator = {{1,1,1},{1,2,2},{1,3,2},{2,1},{1,4,4},{2,2}};
        System.out.println(Arrays.toString(lru.start(operator, 3)));
    }
}
