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

package no.kantega.publishing.search.extraction.impl;

import no.kantega.publishing.search.extraction.TextExtractor;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 */
public class PowerPointExtractor implements TextExtractor {
    private Logger log = Logger.getLogger(getClass());

    public String extractText(InputStream is) {
        try {
            org.apache.poi.hslf.extractor.PowerPointExtractor extractor = new org.apache.poi.hslf.extractor.PowerPointExtractor(is);
            return extractor.getText();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }
}
