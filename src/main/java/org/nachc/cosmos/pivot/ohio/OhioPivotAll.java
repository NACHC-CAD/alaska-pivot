package org.nachc.cosmos.pivot.ohio;

import java.io.File;
import java.util.List;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OhioPivotAll {

	public static final String ROOT_DIR_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\hiv\\_ETC\\ohio-pivot\\csv";
	
	public static void main(String[] args) {
		File rootDir = new File(ROOT_DIR_NAME);
		List<File> dirs = FileUtil.listDirs(rootDir);
		for(File dir : dirs) {
			pivotAllFiles(dir);
		}
		log.info("Done.");
	}
	
	private static void pivotAllFiles(File dir) {
		List<File> files = FileUtil.listFiles(dir, "*.csv");
		for(File file : files) {
			log.info("Doing pivot for: " + file.getName());
			OhioPivot.doPivot(file);
		}
	}
	
}
