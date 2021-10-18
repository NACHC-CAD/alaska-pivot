package org.nachc.cosmos.pivot.util.pivot;

import java.io.File;

import org.junit.Test;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Pivot01NoPivotIntegrationTest {

	private static final String SRC_FILE_NAME = "/alaska/test-data-set-001.csv";

	@Test
	public void shouldWriteOutputFile() {
		log.info("Starting test...");
		File srcFile = FileUtil.getFile(SRC_FILE_NAME);
		File flatFile = FileUtil.createFileWithAppendedName(srcFile, "_FLAT");
		File pivotFile = FileUtil.createFileWithAppendedName(srcFile, "_PIVOT");
		String[] colNames = { "pca", "patient_id", "state", "sex_at_birth", "ethnicity" };
		Integer[] cols = { 1, 2, 3, 4, 9 };
		Pivot01NoPivot pivotTool = new Pivot01NoPivot(srcFile, flatFile, pivotFile, colNames, cols);
		pivotTool.exec();
		log.info("Done.");
	}

}
