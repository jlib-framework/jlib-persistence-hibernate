package org.jlib.persistence.hibernate.usertype;

import java.util.Properties;

import org.jlib.core.exception.InvalidStateException;
import org.jlib.core.message.Message;

public abstract class InvalidUserTypeParametersException
extends InvalidStateException {

    private static final long serialVersionUID = 109106908837305142L;

    protected InvalidUserTypeParametersException(final Properties parameters, final Message message,
                                                 final Exception cause) {
        super(buildMessage(parameters, message), cause);
    }

    protected InvalidUserTypeParametersException(final Properties parameters, final Message message) {
        super(buildMessage(parameters, message));
    }

    private static Message buildMessage(final Properties parameters, final Message message) {
        return message.with("parameters", parameters);
    }
}
