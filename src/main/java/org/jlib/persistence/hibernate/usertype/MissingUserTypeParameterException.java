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

import static org.jlib.message.MessageUtility.message;

public class MissingUserTypeParameterException
extends InvalidUserTypeParametersException {

    private static final long serialVersionUID = 5471808293209091513L;

    public MissingUserTypeParameterException(final Properties parameters, final String parameterName) {
        super(parameters, message().with("name", parameterName));
    }
}
