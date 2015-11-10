package com.guava.test.utilities;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.guava.test.GuavaTraining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yushi on 2/13/15.
 */
public class ImmutableCollectionsTraining implements GuavaTraining {
    public static void main(String[] args) {
        ImmutableCollectionsTraining ict = new ImmutableCollectionsTraining();
        ict.test();
    }

    //define a mutable list
    private final List<String> mutableList = Arrays.asList("a", "b", "c");

    //define a immutable list
    private final ImmutableList<String> immutableList = ImmutableList.of("a", "b", "c");

    @Override
    public void test() {
        //modify the first item in the mutable list
        //will print [aa, b, c]
        mutableList.set(0, "aa");
        System.out.println(mutableList);

        //can not modify the first item in the immutable list, any change item operation is unsuppproted.
        //it will throw UnsupportedOperationException
        immutableList.set(0, "aa");
        System.out.println(immutableList);

        //get second item. print b
        System.out.println(immutableList.asList().get(1));
    }

    public ImmutableList<String> createImmutableCollectionsMethod() {
        ImmutableList<String> result = null;

        //first way
        result = ImmutableList.copyOf(mutableList);

        //second way
        result = ImmutableList.of("a", "b", "c");

        //third way
        result = ImmutableList.<String>builder()
                .addAll(mutableList)
                .build();

        return result;
    }


}
