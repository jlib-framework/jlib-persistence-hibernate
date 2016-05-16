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

import static org.jlib.message.Messages.message;

public class InvalidUserTypeParameterValueException
    extends InvalidUserTypeParametersException {

    private static final long serialVersionUID = - 8900643107183979776L;

    public InvalidUserTypeParameterValueException(final Properties parameters, final String parameterName,
                                                  final Exception cause) {
        super(parameters, message().with("name", parameterName)
                                   .with("value", parameters.getProperty(parameterName)), cause);
    }
}
