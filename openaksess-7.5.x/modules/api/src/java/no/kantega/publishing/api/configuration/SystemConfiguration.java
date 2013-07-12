/*
 * Copyright 2010 Kantega AS
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

package no.kantega.publishing.api.configuration;

import java.util.Locale;

public interface SystemConfiguration {

    public String getString(String name);

    public String getString(String name, String defaultValue);

    public String[] getStrings(String name);

    public String[] getStrings(String name, String defaultValue);

    public boolean getBoolean(String name, boolean defaultValue);

    public long getLong(String name, long defaultValue);

    public int getInt(String name, int defaultValue);

    public Locale getDefaultAdminLocale();

    public String getDefaultDateFormat();

    public String getDefaultDatetimeFormat();

}
