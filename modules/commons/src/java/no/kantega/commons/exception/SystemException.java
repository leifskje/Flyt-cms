/*
 * Copyright 2009 Kantega AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.kantega.commons.exception;

import no.kantega.commons.log.Log;
import org.apache.log4j.Logger;

/**
 *
 */
public class SystemException extends RuntimeException {

    public SystemException(String message, String source, Throwable original) {
        super(message, original);
        if (original != null) {
            Logger.getLogger(source).error(message, original);
        } else {
            Logger.getLogger(source).error(message);
        }
    }
}
