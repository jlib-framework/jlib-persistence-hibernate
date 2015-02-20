package org.jlib.persistence.hibernate.usertype;

import java.io.Serializable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;

public abstract class ImmutableUserType<Value extends Serializable>
implements UserType {

    protected ImmutableUserType() {}

    @Override
    public abstract Class<Value> returnedClass();

    protected abstract Value nullSafeGet(ResultSet resultSet, String[] columnNames)
    throws HibernateException, SQLException;

    protected abstract void nullSafeSet(PreparedStatement preparedStatement, Value value, int index)
    throws HibernateException, SQLException;

    @Override
    public Object replace(final Object original, final Object target, final Object owner) {
        return original;
    }

    @Override
    public int hashCode(final Object value) {
        return value.hashCode();
    }

    @Override
    public Value nullSafeGet(final ResultSet resultSet, final String[] columnNames,
                             final SessionImplementor sessionImplementor, final Object containingEntity)
    throws HibernateException, SQLException {
        return nullSafeGet(resultSet, columnNames);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void nullSafeSet(final PreparedStatement preparedStatement, final Object value, final int columnIndex,
                            final SessionImplementor sessionImplementor)
    throws HibernateException, SQLException {
        nullSafeSet(preparedStatement, (Value) value, columnIndex);
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Object deepCopy(final Object value) {
        return value;
    }

    @Override
    public Object assemble(final Serializable cached, final Object owner) {
        return cached;
    }

    @Override
    public Serializable disassemble(final Object value) {
        return (Serializable) value;
    }

    @Override
    public boolean equals(final Object value1, final Object value2) {
        return value1 == value2 ||
               value1 != null && value2 != null && value1.equals(value2);
    }
}
