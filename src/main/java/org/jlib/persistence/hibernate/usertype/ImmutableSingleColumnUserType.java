package org.jlib.persistence.hibernate.usertype;

import java.io.Serializable;

public abstract class ImmutableSingleColumnUserType<Value extends Serializable>
extends ImmutableUserType<Value> {

    protected ImmutableSingleColumnUserType() {}

    @Override
    public final int[] sqlTypes() {
        return new int[] { getSqlType() };
    }

    protected abstract int getSqlType();
}
