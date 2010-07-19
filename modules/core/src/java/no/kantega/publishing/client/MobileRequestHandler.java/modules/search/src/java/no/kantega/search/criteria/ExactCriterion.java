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
package no.kantega.search.criteria;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

/**
 * Date: May 6, 2010
 * Time: 1:04:36 PM
 *
 * @author Tarje Killingberg
 */
public class ExactCriterion extends AbstractCriterion {

    private String fieldname;
    private String text;


    public ExactCriterion(String fieldname, String text) {
        this.fieldname = fieldname;
        this.text = text;
    }

    /**
     * {@inheritDoc}
     */
    public Query getQuery() {
        Term term = new Term(fieldname, text);
        return new TermQuery(term);
    }

}
