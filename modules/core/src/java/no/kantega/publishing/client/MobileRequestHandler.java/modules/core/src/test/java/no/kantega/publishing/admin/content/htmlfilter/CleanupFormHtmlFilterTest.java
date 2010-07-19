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

package no.kantega.publishing.admin.content.htmlfilter;

import junit.framework.TestCase;
import no.kantega.commons.exception.SystemException;
import no.kantega.commons.xmlfilter.FilterPipeline;

import java.io.StringReader;
import java.io.StringWriter;

/**
 * Date: Apr 14, 2010
 * Time: 12:23:21 PM
 */
public class CleanupFormHtmlFilterTest extends TestCase {

    private FilterPipeline pipeline = SharedPipeline.getFilterPipeline();


    public void testAddTextTypeOnInputElementsWithoutType() throws SystemException {
        pipeline.removeFilters();
        pipeline.addFilter(new CleanupFormHtmlFilter());

        String input="<input name=\"inputName\">";
        String output = "<input name=\"inputName\" type=\"text\">";

        StringWriter sw = new StringWriter();
        pipeline.filter(new StringReader(input), sw);
        assertEquals(output, sw.toString());
    }

    public void testNoModificationOfTextTypeInputElements() {
        pipeline.removeFilters();
        pipeline.addFilter(new CleanupFormHtmlFilter());

        String input="<input name=\"inputName\" type=\"text\">";
        String output = "<input name=\"inputName\" type=\"text\">";

        StringWriter sw = new StringWriter();
        pipeline.filter(new StringReader(input), sw);
        assertEquals(output, sw.toString());
    }

    public void testNoModificationOfRadioTypeInputElements() {
        pipeline.removeFilters();
        pipeline.addFilter(new CleanupFormHtmlFilter());

        String input="<input type=\"radio\" name=\"inputName\">";
        String output = "<input type=\"radio\" name=\"inputName\">";

        StringWriter sw = new StringWriter();
        pipeline.filter(new StringReader(input), sw);
        assertEquals(output, sw.toString());
    }

}
