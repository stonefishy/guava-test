package com.guava.test.utilities;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import com.guava.test.GuavaTraining;
import com.sun.istack.internal.Nullable;

import java.util.List;

/**
 * Created by yushi on 12/12/14.
 */
public class OrderingTraining implements GuavaTraining {
    private static List<String> locations = Lists.newArrayList(
            "Guang zhou",
            "Cheng du",
            "",
            "Shang hai"
    );

    private static List<Foo> fooList = Lists.newArrayList(
            new Foo(null, "abc"),
            new Foo(23, "abc"),
            new Foo(10, "abc")
    );

    private static Ordering<String> getCustomzieStringOrder() {
        return new Ordering<String>() {
            @Override
            public int compare(String first, String second) {
                return Ints.compare(first.length(), second.length());
            }
        };
    }

    private static Ordering<Foo> getNaturalFooOrder() {
        return Ordering.natural().nullsFirst().onResultOf(new Function<Foo, Integer>() {

            @Override
            public Integer apply(@Nullable Foo foo) {
                return foo.getSortedBy();
            }
        });
    }

    @Override
    public void test() {
        Ordering<String> byLengthOrdering = getCustomzieStringOrder();
        byLengthOrdering.isOrdered(locations);
    }


    public static void main(String[] args) {
        Ordering<String> byLengthOrdering = getCustomzieStringOrder();

        // use natural comparator, such as a -> z
        // so the result is: [, Cheng du, Guang zhou, Shang hai]
        System.out.println(Ordering.natural().sortedCopy(locations));

        // use customize comparator to sorted array.
        // the result is: [, Cheng du, Shang hai, Guang zhou]
        System.out.println(byLengthOrdering.sortedCopy(locations));

        // will return false. because the locations array is not sorted.
        System.out.println(byLengthOrdering.isOrdered(locations));

        // reverse customize comparator.
        // so the result is: [Guang zhou, Shang hai, Cheng du, ]
        System.out.println(byLengthOrdering.reverse().sortedCopy(locations));

        // add null object to the locations array.
        locations.add(null);

        // null obejct will at first.
        // so the result is : [null, , Cheng du, Shang hai, Guang zhou]
        System.out.println(byLengthOrdering.nullsFirst().sortedCopy(locations));

        // null object will at last.
        // the result is : [, Cheng du, Shang hai, Guang zhou, null]
        System.out.println(byLengthOrdering.nullsLast().sortedCopy(locations));

        // will throw NullPointerException, because the location contains null object.
        // and here doesn't have nullsFirst() or nullsLast() to deal with null object.
        //byLengthOrdering.sortedCopy(locations);

        // remove the null object.
        locations.remove(null);

        // the "AAAAAAAA" length is equal to "Cheng du" length. (8 characters)
        // and it will be appended at last.
        locations.add("AAAAAAAA");

        // use natrual ordering with second comparator to deal with equal situation.
        Ordering<String> compoundOrdering = byLengthOrdering.compound(Ordering.natural());

        // the result will be : [, AAAAAAAA, Cheng du, Shang hai, Guang zhou]
        System.out.println(compoundOrdering.sortedCopy(locations));


        Ordering<Foo> fooOrdering = getNaturalFooOrder();
        // the result will be [null+abc, 10+abc, 23+abc]
        System.out.println(fooOrdering.sortedCopy(fooList));

        // the result will be [23+abc]
        System.out.println(fooOrdering.greatestOf(fooList, 1));

        // the result will be [23+abc, 10+abc]
        System.out.println(fooOrdering.greatestOf(fooList, 2));

        // the result will be [null+abc, 10+abc]
        System.out.println(fooOrdering.leastOf(fooList, 2));

        // the result will be null+abc
        System.out.println(fooOrdering.min(fooList));

        // the result will be 23+abc
        System.out.println(fooOrdering.max(fooList));
    }

    private static class Foo {
        @Nullable
        private Integer sortedBy;

        private String nonSortedBy;

        public Foo(Integer sortedBy, String nonSortedBy) {
            this.sortedBy = sortedBy;
            this.nonSortedBy = nonSortedBy;
        }

        public String getNonSortedBy() {
            return nonSortedBy;
        }

        public void setNonSortedBy(String nonSortedBy) {
            this.nonSortedBy = nonSortedBy;
        }

        public Integer getSortedBy() {
            return sortedBy;
        }

        public void setSortedBy(int sortedBy) {
            this.sortedBy = sortedBy;
        }

        @Override
        public String toString() {
            return String.format("%s+%s", this.sortedBy, this.nonSortedBy);
        }
    }
}
