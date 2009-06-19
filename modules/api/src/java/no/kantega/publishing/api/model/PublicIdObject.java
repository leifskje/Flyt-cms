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

package no.kantega.publishing.api.model;

import java.util.List;

/**
 * User: Anders Skar, Kantega AS
 * Date: Feb 11, 2009
 * Time: 11:05:12 AM
 *
 * Base class for templates, documenttypes and associationcategories which are defined in templateconfiguration
 * 
 */
public interface PublicIdObject {
    public int getId();

    public void setId(int id);

    public String getPublicId();

    public void setPublicId(String publicId);
}
