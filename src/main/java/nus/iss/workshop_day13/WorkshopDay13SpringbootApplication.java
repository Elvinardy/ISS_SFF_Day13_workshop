package nus.iss.workshop_day13;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class WorkshopDay13SpringbootApplication {
	private static final Logger logger = LoggerFactory.getLogger(WorkshopDay13SpringbootApplication.class);
	public static final String DATA_DIR = "dataDir";
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WorkshopDay13SpringbootApplication.class);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		
		List<String>optsVal = appArgs.getOptionValues("dataDir");
		
		if(optsVal != null) {
			createDir((String)optsVal.get(0));
		} else {
			logger.warn("No data directory is provided!");
			System.exit(1);
		}
		app.run(args);
	}
	private static void createDir(String path) {
		try {
            File dir = new File(path);
            dir.mkdirs();
            String perm = "rwxrwx---";
            Set<PosixFilePermission> permissions
            = PosixFilePermissions.fromString(perm);
            Files.setPosixFilePermissions(dir.toPath(), permissions);
        } catch(IOException e) {
            logger.error("Error creating directory", e);
        }
	}
}

	
/*public void checkFile (String value) {
	String PATH = "/Users/elvinardy_darwin/Documents/data";
	String directoryName = PATH.concat(this.checkFile(value));
	
		File directory = new File(directoryName);
		if(!directory.exists()) {
			directory.mkdir();
		}

		File file = new File (directoryName + "/" + fileName);
	}*/

