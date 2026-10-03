package com.songsquare.util;

public class ObjectComparator {
    
    private ObjectComparator() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static boolean isNullOrEmpty(String string) {
        return (string == null || "".equals(string));
    }

    public static boolean isNullOrEmpty(String string, boolean ignoreSpace) {
        String newString = ignoreSpace ? string.trim() : string;

        return (newString == null || "".equals(newString));
    }


}

