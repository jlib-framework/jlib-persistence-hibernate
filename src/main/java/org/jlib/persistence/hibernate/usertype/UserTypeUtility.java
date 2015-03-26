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
