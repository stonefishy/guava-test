package com.guava.test.utilities;

import com.google.common.base.Preconditions;
import com.guava.test.GuavaTraining;

/**
 * Created by yushi on 11/21/14.
 */
public class PreconditionsTraining implements GuavaTraining {
    public static void main(String[] args) {
        PreconditionsTraining test = new PreconditionsTraining();
        test.test();
    }

    @Override
    public void test() {
        int i = 3;
        Preconditions.checkArgument(i > 5, "Argument %s is not larger than 5", i);
        // will throw IllegalArgumentException with "Argument 3 is not larger than 5"

        Preconditions.checkNotNull(i); // return 3;
        Preconditions.checkNotNull(null); // throw NullPointerException;

        Preconditions.checkElementIndex(i,4); // return 3
        Preconditions.checkElementIndex(i,3); // throw IndexOutOfBoundsException

        Preconditions.checkPositionIndex(i, 3); // return 3
        Preconditions.checkPositionIndex(i, 2); // throw IndexOutOfBoundsException
    }
}
