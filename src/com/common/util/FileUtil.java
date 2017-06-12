package com.common.util;

import java.io.File;

/**
 * @author pascalstammer
 * @version 11.06.17.
 */
public class FileUtil {

    public static boolean fileExists(final String path) {
        return new File(path).exists();
    }
}
