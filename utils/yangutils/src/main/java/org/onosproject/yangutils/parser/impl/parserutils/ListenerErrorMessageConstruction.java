/*
 * Copyright 2016 Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onosproject.yangutils.parser.impl.parserutils;

import org.onosproject.yangutils.parser.ParsableDataType;

import static org.onosproject.yangutils.parser.ParsableDataType.getParsableDataType;
import static org.onosproject.yangutils.parser.impl.parserutils.ListenerErrorLocation.getErrorLocationMessage;
import static org.onosproject.yangutils.parser.impl.parserutils.ListenerErrorType.getErrorType;

/**
 * It's a utility to help construct detailed error message.
 */
public final class ListenerErrorMessageConstruction {

    /**
     * Private constructor.
     */
    private ListenerErrorMessageConstruction() {
    }

    /**
     * Constructs message for error with extended information and returns the
     * same.
     *
     * @param errorType error type needs to be set in error message.
     * @param parsableDataType type of parsable data in which error occurred.
     * @param parsableDataTypeName identifier/string of parsable data type in
     *            which error occurred.
     * @param errorLocation location where error occurred.
     * @param extendedErrorInformation extended error information.
     * @return constructed error message.
     */
    public static String constructExtendedListenerErrorMessage(ListenerErrorType errorType,
                                                               ParsableDataType parsableDataType,
                                                               String parsableDataTypeName,
                                                               ListenerErrorLocation errorLocation,
                                                               String extendedErrorInformation) {
        String newErrorMessage;
        newErrorMessage = constructListenerErrorMessage(errorType, parsableDataType, parsableDataTypeName,
                                                        errorLocation)
                + "\n"
                + "Error Information: "
                + extendedErrorInformation;
        return newErrorMessage;
    }

    /**
     * Constructs message for error during listener based tree walk and returns
     * the same.
     *
     * @param errorType error type needs to be set in error message.
     * @param parsableDataType type of parsable data in which error occurred.
     * @param parsableDataTypeName identifier/string of parsable data type in
     *            which error occurred.
     * @param errorLocation location where error occurred.
     * @return constructed error message.
     */
    public static String constructListenerErrorMessage(ListenerErrorType errorType,
                                                       ParsableDataType parsableDataType,
                                                       String parsableDataTypeName,
                                                       ListenerErrorLocation errorLocation) {

        String errorMessage;

        errorMessage = "Internal parser error detected: " + getErrorType(errorType) + " "
                + getParsableDataType(parsableDataType);

        if (!parsableDataTypeName.isEmpty()) {
            errorMessage = errorMessage + " \"" + parsableDataTypeName + "\" ";
        } else {
            errorMessage = errorMessage + " ";

        }
        errorMessage = errorMessage + getErrorLocationMessage(errorLocation) + " processing.";
        return errorMessage;
    }
}