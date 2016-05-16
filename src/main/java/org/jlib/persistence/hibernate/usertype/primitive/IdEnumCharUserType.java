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

package org.jlib.persistence.hibernate.usertype.primitive;

import java.util.Properties;

import java.io.Serializable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import static org.hibernate.type.StandardBasicTypes.CHARACTER;
import org.hibernate.usertype.ParameterizedType;
import org.jlib.persistence.IdEnum;
import org.jlib.persistence.hibernate.usertype.ImmutableOptionalSingleColumnUserType;
import org.jlib.persistence.hibernate.usertype.InvalidUserTypeParameterValueException;
import org.jlib.persistence.hibernate.usertype.InvalidUserTypeParametersException;
import static org.jlib.persistence.hibernate.usertype.UserTypeUtility.assertValidParametersCount;
import static org.jlib.persistence.hibernate.usertype.UserTypeUtility.getParameterValue;
import org.jlib.reflect.languageelement.ProgramElementException;
import static org.jlib.reflect.reflector.defaults.Reflectors.useClass;

public class IdEnumCharUserType<EnumValue extends Enum<EnumValue> & IdEnum<Id>, Id extends Serializable>
    extends ImmutableOptionalSingleColumnUserType<EnumValue>
    implements ParameterizedType {

    public static final String PARAMETERNAME_ENUM_CLASS_NAME = "enumClassName";
    public static final String PARAMETERNAME_ENUM_VALUE_METHOD_NAME = "enumValueIdentifierMethodName";

    private Properties parameters;
    private Class<EnumValue> enumClass;
    private String enumValueIdentifierMethodName;

    @Override
    protected int getSqlType() {
        return CHARACTER.sqlType();
    }

    @Override
    public Class<EnumValue> returnedClass() {
        return enumClass;
    }

    @Override
    protected EnumValue get(final ResultSet resultSet, final String columnName)
        throws HibernateException, SQLException {
        try {
            // TODO: verify correct use
            final Object enumValueId = resultSet.getObject(columnName);
            return useClass(enumClass).useStaticMethod(enumValueIdentifierMethodName)
                                      .withReturnType(enumClass)
                                      .withParameterTypes(Object.class)
                                      .invoke(enumValueId)
                                      .get();
        }
        catch (final ProgramElementException exception) {
            throw new InvalidUserTypeParameterValueException(parameters, PARAMETERNAME_ENUM_VALUE_METHOD_NAME,
                                                             exception);
        }
    }

    @Override
    protected void set(final PreparedStatement preparedStatement, final EnumValue enumValue, final int index)
        throws SQLException {
        preparedStatement.setString(index, enumValue.getId().toString());
    }

    @Override
    public void setParameterValues(final Properties parameters)
        throws InvalidUserTypeParametersException {
        assertValidParametersCount(parameters, 2);
        this.parameters = parameters;
        retrieveEnumValueIdentifierMethodNameAndClass();
    }

    @SuppressWarnings("unchecked")
    private void retrieveEnumValueIdentifierMethodNameAndClass()
        throws InvalidUserTypeParameterValueException {
        try {
            final String enumClassName = getParameterValue(parameters, PARAMETERNAME_ENUM_CLASS_NAME);
            enumClass = useClass(enumClassName).withType(IdEnum.class).withSupertypes(Enum.class).downcast();
            enumValueIdentifierMethodName = getParameterValue(parameters, PARAMETERNAME_ENUM_VALUE_METHOD_NAME);
        }
        catch (final ProgramElementException exception) {
            throw new InvalidUserTypeParameterValueException(parameters, PARAMETERNAME_ENUM_CLASS_NAME, exception);
        }
    }
}
