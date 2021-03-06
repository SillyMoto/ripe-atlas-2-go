/*
 * Copyright (C) 2019 SillyMoto authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.beuth.master.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * check dates of a specific format
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
public class DateParser {
    /**
     * specific date format
     */
    private static final SimpleDateFormat[] dateFormats = {new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.GERMAN),
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'", Locale.GERMAN)};

    public static boolean isValidFormat(String value) {
        Date date = null;
        for (SimpleDateFormat format : dateFormats){
            try {
                date = format.parse(value);
                return true;
            } catch (ParseException ex) {
                // loop
            }
        }
        System.err.println("No known Date format found: " + value);
        return date != null;
    }

    public static String getDate(Date date){
        return dateFormats[0].format(date);
    }

}
