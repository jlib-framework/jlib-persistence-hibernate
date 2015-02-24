package org.jlib.persistence.hibernate.usertype.primitive;

import java.util.Properties;

import java.io.Serializable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.hibernate.HibernateException;
import static org.hibernate.type.StandardBasicTypes.CHARACTER;
import org.hibernate.usertype.ParameterizedType;
import org.jlib.persistence.hibernate.usertype.ImmutableOptionalSingleColumnUserType;
import org.jlib.persistence.hibernate.usertype.InvalidUserTypeParameterValueException;
import org.jlib.persistence.hibernate.usertype.InvalidUserTypeParametersException;
import static org.jlib.persistence.hibernate.usertype.UserTypeUtility.assertValidParametersCount;
import static org.jlib.persistence.hibernate.usertype.UserTypeUtility.getParameterValue;
import org.jlib.persistence.jpa.IdEnum;
import org.jlib.reflect.ClassInstanceException;
import org.jlib.reflect.InvalidMethodException;
import org.jlib.reflect.InvalidValueException;
import static org.jlib.reflect.Reflectors.useClass;
import org.jlib.reflect.WrongTypedInstanceException;

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
                                      .withArgumentTypes(Object.class)
                                      .invoke(enumValueId)
                                      .get();
        }
        catch (final InvalidValueException | InvalidMethodException | WrongTypedInstanceException exception) {
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
            enumClass = useClass(enumClassName).withType(IdEnum.class).assertSubtypeOf(Enum.class).getParametrized();
            enumValueIdentifierMethodName = getParameterValue(parameters, PARAMETERNAME_ENUM_VALUE_METHOD_NAME);
        }
        catch (final ClassInstanceException exception) {
            throw new InvalidUserTypeParameterValueException(parameters, PARAMETERNAME_ENUM_CLASS_NAME, exception);
        }
    }
}
