package com.guava.test.utilities;

import com.google.common.base.Optional;
import com.guava.test.GuavaTraining;

/**
 * Created by yushi on 11/21/14.
 */
public class OptionalTraining implements GuavaTraining {

    public static void main(String[] args) {
        OptionalTraining ot = new OptionalTraining();
        ot.test();
    }

    @Override
    public void test() {
        Optional<Integer> numberOptional = Optional.of(100);
        numberOptional.isPresent(); //return true
        numberOptional.get(); //return 100

        numberOptional = Optional.absent();
        numberOptional.isPresent(); // return false
        numberOptional.or(0); //return 0;
        numberOptional.orNull(); //return null;

        numberOptional = Optional.fromNullable(1);
        numberOptional.get(); //return 1;

        numberOptional = Optional.fromNullable(null);
        numberOptional.isPresent(); //return false;
        numberOptional.orNull(); //return null;
    }
}
