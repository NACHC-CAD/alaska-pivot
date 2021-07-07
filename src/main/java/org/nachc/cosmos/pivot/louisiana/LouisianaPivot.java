package org.nachc.cosmos.pivot.louisiana;

import java.io.File;

import org.nachc.cosmos.pivot.util.pivot.PivotType;
import org.nachc.cosmos.pivot.util.pivot.PivotUtil;

import lombok.extern.slf4j.Slf4j;

// parameters are start, end, width, repeat, constantsWidth, pivotConstantsWidth

@Slf4j
public class LouisianaPivot {

	public static final String SRC_FILE_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\covid\\update-2021-07-07-COVID-LPCA\\_ETC\\macro\\LPCA-Thumbnail.csv\\";
	
	public static void main(String[] args) {
		log.info("Starting pivots...");
		File srcFile = new File(SRC_FILE_NAME);
		String[] headers;
		// demo
		headers = new String[] { "pca", "health_center_name", "patient_id", "sex_at_birth", "age", "date_of_death", "gender_identity", "race", "ethnicity", "language", "fpl_range", "sdoh_assessment_date", "demo_number" };
		new PivotUtil("-DEMO", "-DEMO_PIVOT", 3, 11, 9, 1, 3, headers).exec(srcFile);
		// sdoh
		headers = new String[] { "pca", "health_center_name", "patient_id", "sdoh_date", "sdoh_description", "sdoh_value" };
		new PivotUtil("-SDOH", "-SDOH_PIVOT", 11, 33, 23, 1, 3, 4, headers).exec(srcFile, PivotType.USE_HEADERS);
		log.info("Done.");
	}
	
}
