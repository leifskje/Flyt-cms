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

package no.kantega.commons.util;

import no.kantega.commons.log.Log;

import java.util.*;

/**
 *
 */

public class LocaleLabels {
    static public String DEFAULT_BUNDLE = "TextLabels";

    private static Map bundles = new HashMap();

    private static PropertyResourceBundle getBundle(String bundleName, String locale) {
        PropertyResourceBundle bundle = null;
        synchronized (bundles) {
            bundle = (PropertyResourceBundle)bundles.get(bundleName + "_" + locale);
                if (bundle == null) {
                String[] locArr = locale.split("_");
                try {
                    if (locArr.length > 2) {
                        bundle = (PropertyResourceBundle)ResourceBundle.getBundle(bundleName, new Locale(locArr[0], locArr[1], locArr[2]));
                    } else {
                        bundle = (PropertyResourceBundle)ResourceBundle.getBundle(bundleName, new Locale(locArr[0], locArr[1]));
                    }
                    bundles.put(bundleName + "_" + locale, bundle);
                } catch (MissingResourceException e) {
                    Log.error("LocaleLabels", e, null, null);
                }
            }
        }
        return bundle;
    }

    public static String getLabel(String key, String bundleName, String locale) {
        PropertyResourceBundle bundle = getBundle(bundleName, locale);
        if (bundle == null) {
            return key;
        }

        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return key;
        }
    }

    public static String getLabel(String key, String locale) {
        return getLabel(key, DEFAULT_BUNDLE, locale);
    }

    public static String getLabel(String key, String bundleName, Locale locale) {
        String loc = locale.getLanguage() + "_" + locale.getCountry();
        if (locale.getVariant() != null) {
            loc += "_" + locale.getVariant();
        }
        return getLabel(key, bundleName, loc);
    }

    public static String getLabel(String key, Locale locale) {
        String loc = locale.getLanguage() + "_" + locale.getCountry();
        if (locale.getVariant() != null) {
            loc += "_" + locale.getVariant();
        }
        return getLabel(key, DEFAULT_BUNDLE, loc);
    }


    public static Enumeration getKeys(String bundleName, String locale) {
        PropertyResourceBundle bundle = getBundle(bundleName, locale);
        if (bundle == null) {
            return null;
        }
        return bundle.getKeys();
    }

    public static Enumeration getKeys(String bundleName, Locale locale) {
        String loc = locale.getLanguage() + "_" + locale.getCountry();
        if (locale.getVariant() != null) {
            loc += "_" + locale.getVariant();
        }
        return getKeys(bundleName, loc);
    }

}