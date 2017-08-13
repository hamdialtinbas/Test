package com.mycompany.myapp.web.rest.util;

import org.springframework.http.HttpStatus;

/**
 * Simple static methods to be called at the start of your own methods to verify correct arguments and state. If the Precondition fails, an {@link HttpStatus} code is thrown
 */
public final class RestPreconditions {

    private RestPreconditions() {
        throw new AssertionError();
    }

    // API

    public static void checkFound(final boolean expression) throws Exception {
        if (!expression) {
            throw new Exception();
        }
    }


    public static <T> T checkFound(final T resource) throws Exception {
        if (resource == null) {
            throw new Exception();
        }

        return resource;
    }

}
