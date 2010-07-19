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

package no.kantega.publishing.common.exception;

/**
 * User: Anders Skar, Kantega AS
 * Date: Apr 29, 2008
 * Time: 3:57:38 PM
 */
public class MultipleEditorInstancesException  extends no.kantega.commons.exception.KantegaException {
    public MultipleEditorInstancesException() {
        super("Brukeren har flere vinduer med redigering �pne", "", null);
    }
}
