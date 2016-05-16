/*
 * jlib - Open Source Java Library
 *
 *     www.jlib.org
 *
 *
 *     Copyright 2005-2015 Igor Akkerman
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package org.jlib.persistence.hibernate.usertype;

import java.io.Serializable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;

public abstract class ImmutableOptionalSingleColumnUserType<Value extends Serializable>
    extends ImmutableSingleColumnUserType<Value> {

    protected ImmutableOptionalSingleColumnUserType() {}

    @Override
    protected final Value nullSafeGet(final ResultSet resultSet, final String[] columnNames)
        throws HibernateException, SQLException {
        resultSet.getObject(columnNames[0]);
        if (resultSet.wasNull())
            return null;

        return get(resultSet, columnNames[0]);
    }

    protected abstract Value get(ResultSet resultSet, String columnName)
        throws HibernateException, SQLException;

    @Override
    protected final void nullSafeSet(final PreparedStatement preparedStatement, final Value value,
                                     final int columnIndex)
        throws HibernateException, SQLException {
        if (value == null) {
            preparedStatement.setNull(columnIndex, getSqlType());
            return;
        }

        set(preparedStatement, value, columnIndex);
    }

    protected abstract void set(PreparedStatement preparedStatement, Value value, int index)
        throws HibernateException, SQLException;
}
