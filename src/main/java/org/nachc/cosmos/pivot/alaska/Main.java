package org.nachc.cosmos.pivot.alaska;

import java.io.File;

import org.nachc.cosmos.pivot.util.pivot.PivotUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

//	public static final String SRC_FILE_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\covid\\update-2021-06-22-COVID-APCA\\_ETC\\macro\\APCA_NACHC_C19_202001_202105.csv";

	public static final String SRC_FILE_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\covid\\update-2021-06-22-COVID-APCA\\_ETC\\macro\\testing\\APCA_NACHC_C19_202001_202105_THUMB-500.csv";

	public static void main(String[] args) {
		log.info("Starting pivots...");
		File srcFile = new File(SRC_FILE_NAME);
		String[] headers;
		// demo
		headers = new String[] { "org", "patient_id", "state", "sex_at_birth", "age", "date_of_death", "gender_identity", "race", "ethnicity", "language", "insurance_financial_class", "fpl_range", "sdoh_assessment_date", "demo_number" };
		new PivotUtil("-DEMO", "-DEMO_PIVOT", 2, 12, 11, 1, 2, headers).exec(srcFile);
		// vacc
		headers = new String[] { "pca", "patient_id", "covid_vacc_date", "covid_vacc_cvx", "covid_vacc_manufacturer", "covid_vacc_refused", "vacc_number" };
		new PivotUtil("-VACC", "-VACC_PIVOT", 51, 58, 4, 2, 2, headers).exec(srcFile);
		// lab
		headers = new String[] { "pca", "patient_id", "test_date", "test_result", "test_loinc", "test_description", "test_number" };
		new PivotUtil("-LAB", "-LAB_PIVOT", 35, 50, 4, 4, 2, headers).exec(srcFile);
		// sdoh
		headers = new String[] {"pca","patient_id","sdoh_date", "sdoh_description","sdoh_value"};
		new PivotUtil("-SDOH", "-SDOH_PIVOT", 12, 34, 22, 1, 2, 3, headers).exec(srcFile, true);
		// DONE
		log.info("Done.");
	}

}
