package com.zyj.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClssName: StreamListUtil
 * @Description:TODO(list切割工具类 要求jdk1.8)
 * @author: 钟裕京
 * @date: 2021年12月8日 上午9:52:39
 */
public class StreamListUtil {

    //按每20个一组分割
    private static final Integer MAX_NUMBER = 20;

    /**
     * 计算切分次数
     */
    private static Integer countStep(Integer size) {
        return (size + MAX_NUMBER - 1) / MAX_NUMBER;
    }

    /**
     * @return List<List < T>>返回类型
     * @author:钟裕京
     * @date:2021年12月8日上午9:58:18
     * @Description:TODO(使用流遍历操作)
     * @parameter @param list
     * @parameter @return参数说明
     */
    public static <T> List<List<T>> forEachIterate(List<T> list) {

        int limit = countStep(list.size());

        List<List<T>> mglist = new ArrayList<List<T>>();
        Stream.iterate(0, n -> n + 1).limit(limit).forEach(i -> {
            mglist.add(list.stream().skip(i * MAX_NUMBER).limit(MAX_NUMBER).collect(Collectors.toList()));
        });

        return mglist;
    }

    /**
     * @return List<List < T>>返回类型
     * @author:钟裕京
     * @date:2021年12月8日上午10:04:27
     * @Description:TODO(获取分割后的集合)
     * @parameter @param list
     * @parameter @return参数说明
     */
    public static <T> List<List<T>> parallelIterate(List<T> list) {

        int limit = countStep(list.size());

        List<List<T>> splitList = Stream.iterate(0, n -> n + 1).limit(limit).parallel().
                map(a -> list.stream().skip(a * MAX_NUMBER).limit(MAX_NUMBER).parallel().
                        collect(Collectors.toList())).collect(Collectors.toList());

        return splitList;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        //方法一：使用流遍历操作
        List<List<Integer>> mglist = new ArrayList<>();
        mglist = forEachIterate(list);

        System.out.println(mglist);

        //方法二：获取分割后的集合
//	      List<List<Integer>> splitList = Stream.iterate(0, n -> n + 1).limit(limit).parallel().map(a -> list.stream().skip(a * MAX_NUMBER).limit(MAX_NUMBER).parallel().collect(Collectors.toList())).collect(Collectors.toList());
//
//	      System.out.println(splitList);
    }

}
