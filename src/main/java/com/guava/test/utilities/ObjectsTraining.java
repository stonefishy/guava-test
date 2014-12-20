package com.guava.test.utilities;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.guava.test.GuavaTraining;
import com.sun.org.apache.xml.internal.utils.ObjectStack;

import java.util.Date;

/**
 * Created by yushi on 11/27/14.
 */
public class ObjectsTraining implements GuavaTraining {
    private String stringField;
    private int intField;
    private char charField;
    private Date dateField;

    @Override
    public void test() {
        Objects.equal("a", "a"); // return true;
        Objects.equal(null, "a"); // return false;
        Objects.equal(null, null); // return true;

        Objects.hashCode(this.intField, this.charField, this.stringField, this.dateField); // generate hash code with multi object

        // comparison chain. It's a fluent idiom and much more readable. less prone to accidental typos, and smart enough
        // not to do more work than it must.
        // It only performs comparisons until it finds a non-zero result, after which it ignores further input
        ObjectsTraining objectsTraining = new ObjectsTraining();
        int result = ComparisonChain.start().compare(this.intField, objectsTraining.intField)
                .compare(this.stringField, objectsTraining.stringField)
                .compare(this.charField, objectsTraining.charField)
                .compare(this.dateField, objectsTraining.dateField)
                .result();
    }
}
