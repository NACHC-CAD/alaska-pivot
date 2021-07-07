package org.nachc.cosmos.pivot.louisiana;

import java.io.File;

import org.nachc.cosmos.pivot.util.pivot.PivotUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LouisianaPivot {

	public static final String SRC_FILE_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\covid\\update-2021-07-07-COVID-LPCA\\_ETC\\macro\\LPCA-Thumbnail.csv\\";
	
	public static void main(String[] args) {
		log.info("Starting pivots...");
		File srcFile = new File(SRC_FILE_NAME);
		String[] headers;
		// parameters are start, end, width, repeat, constantsWidth
		// demo
		headers = new String[] { "pca", "health_center_name", "patient_id", "sex_at_birth", "age", "date_of_death", "gender_identity", "race", "ethnicity", "language", "fpl_range", "sdoh_assessment_date", "demo_number" };
		new PivotUtil("-DEMO", "-DEMO_PIVOT", 3, 11, 9, 1, 3, headers).exec(srcFile);
		log.info("Done.");
	}
	
}
