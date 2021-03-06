/*
 * Copyright 2009 Kantega AS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package no.kantega.publishing.admin.content.htmlfilter;

import no.kantega.publishing.api.multimedia.MultimediaAO;
import no.kantega.publishing.spring.RootContext;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HTMLEditorHelperTest {

    @BeforeClass
    public static void setup(){
        ApplicationContext applicationContext = mock(ApplicationContext.class);
        RootContext.setInstance(applicationContext);
        when(applicationContext.getBean(MultimediaAO.class)).thenReturn(mock(MultimediaAO.class));
    }

    @Test
    public void shouldRemoveEmptySpanTag() {
        String htmlBefore = "<span> </span><p>Test</p>";

        String htmlAfter = HTMLEditorHelper.postEditFilter(htmlBefore);

        assertEquals("<p>Test</p>", htmlAfter.replace("\n", ""));

    }

    @Test
    public void shouldNotRemoveSpanTagWithContent() {
        String htmlBefore = "<span>test</span><p>Test</p>";

        String htmlAfter = HTMLEditorHelper.postEditFilter(htmlBefore);

        assertEquals(htmlBefore.replace("\n", ""), htmlAfter.replace("\n", ""));

    }
}
