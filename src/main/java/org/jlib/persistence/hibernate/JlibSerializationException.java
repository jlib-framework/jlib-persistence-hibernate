package org.jlib.persistence.hibernate;

import org.jlib.message.Message;

import org.hibernate.type.SerializationException;

public class JlibSerializationException
extends SerializationException {

    private static final long serialVersionUID = - 2018860009702692536L;

    public JlibSerializationException(final Message message, final Exception root) {
        super(message.toString(), root);
    }
}
