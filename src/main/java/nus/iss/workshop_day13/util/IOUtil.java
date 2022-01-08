package nus.iss.workshop_day13.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(IOUtil.class);
    private static final String Set = null;

    public static void createDir(String path) {
        try {
            File dir = new File(path);
            dir.mkdir();
            String perm = "rwxrwx---";
            Set<PosixFilePermission> permissions = 
        }
    }

}
