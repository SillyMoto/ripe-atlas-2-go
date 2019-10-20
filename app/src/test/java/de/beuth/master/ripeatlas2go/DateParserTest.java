package de.beuth.master.ripeatlas2go;

import org.junit.Test;

import de.beuth.master.services.DateParser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateParserTest {
    @Test
    public void checkDateFormat1(){
        assertTrue(DateParser.isValidFormat("2019-10-19T13:30Z"));
    }

    @Test
    public void checkDateFormat2(){
        assertTrue(DateParser.isValidFormat("2019-10-19T13:30:45Z"));
    }

    @Test
    public void checkDateFormatFalse(){
        assertFalse(DateParser.isValidFormat("2019-10-1913:30:45"));
    }

}
