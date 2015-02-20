package org.jlib.persistence.hibernate.usertype;

import java.util.Properties;

import static org.jlib.core.message.MessageUtility.message;

public class MissingUserTypeParameterException
extends InvalidUserTypeParametersException {

    private static final long serialVersionUID = 5471808293209091513L;

    public MissingUserTypeParameterException(final Properties parameters, final String parameterName) {
        super(parameters, message().with("name", parameterName));
    }
}
