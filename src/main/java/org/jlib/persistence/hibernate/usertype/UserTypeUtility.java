package org.jlib.persistence.hibernate.usertype;

import java.util.Properties;

public final class UserTypeUtility {

    public static void assertValidParametersCount(final Properties parameters, final int expectedParametersCount)
    throws InvalidUserTypeParametersCountException {
        if (parameters.size() != expectedParametersCount)
            throw new InvalidUserTypeParametersCountException(parameters, expectedParametersCount);
    }

    public static String getParameterValue(final Properties parameters, final String parameterName)
    throws MissingUserTypeParameterException {
        final String parameterValue = parameters.getProperty(parameterName);

        if (parameterValue == null)
            throw new MissingUserTypeParameterException(parameters, parameterName);

        return parameterValue;
    }

    private UserTypeUtility() {}
}
