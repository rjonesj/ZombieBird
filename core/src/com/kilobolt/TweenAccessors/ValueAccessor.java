package com.kilobolt.TweenAccessors;

import aurelienribon.tweenengine.TweenAccessor;

/**
 * Created by jone1 on 5/2/2016.
 */
public class ValueAccessor implements TweenAccessor<Value> {

    @Override
    public int getValues(Value target, int tweenType, float[] returnValues) {
        returnValues[0] = target.getValue();
        return 1;
    }

    @Override
    public void setValues(Value target, int tweenType, float[] newValues) {
        target.setValue(newValues[0]);
    }

}
