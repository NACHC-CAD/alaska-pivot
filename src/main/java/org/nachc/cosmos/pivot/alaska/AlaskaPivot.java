package org.nachc.cosmos.pivot.alaska;

import java.io.File;

import org.nachc.cosmos.pivot.util.pivot.legacy.PivotType;
import org.nachc.cosmos.pivot.util.pivot.legacy.PivotUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlaskaPivot {

//	public static final String SRC_FILE_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\covid\\update-2021-06-22-COVID-APCA\\_ETC\\macro\\APCA_NACHC_C19_202001_202105.csv";
//	public static final String SRC_FILE_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\covid\\update-2021-06-22-COVID-APCA\\_ETC\\macro\\testing\\APCA_NACHC_C19_202001_202105_THUMB-500.csv";
//	public static final String SRC_FILE_NAME = "C:\\Users\\greshje\\Downloads\\Update-2021-09-15-COVID-APCA\\_ETC\\pivot\\2021-09-15_Covid19_APCA_data.csv";
	public static final String SRC_FILE_NAME = "C:\\test\\pivot\\2021-09-15_Covid19_APCA_data.csv";

	public static void main(String[] args) {
		log.info("Starting pivots...");
		File srcFile = new File(SRC_FILE_NAME);
		String[] headers;
		// demo
		headers = new String[] { "pca", "patient_id", "state", "sex_at_birth", "age", "date_of_death", "gender_identity", "race", "ethnicity", "language", "insurance_financial_class", "fpl_range", "sdoh_assessment_date", "demo_number" };
		new PivotUtil("-DEMO", "-DEMO_PIVOT", 2, 12, 11, 1, 2, headers).exec(srcFile);
		// vacc (covid)
		headers = new String[] { "pca", "patient_id", "vacc_date", "covid_cvx", "vacc_manufacturer", "vacc_refused", "vacc_number" };
		new PivotUtil("-VACC_COVID", "-VACC_COVID_PIVOT", 51, 58, 4, 2, 2, headers).exec(srcFile);
		// vacc (flu)
		headers = new String[] { "pca", "patient_id", "vacc_date", "flu_cvx", "vacc_manufacturer", "vacc_refused", "vacc_number" };
		new PivotUtil("-VACC_FLU", "-VACC_FLU_PIVOT", 104, 107, 4, 1, 2, headers).exec(srcFile);
		// lab
		headers = new String[] { "pca", "patient_id", "test_date", "test_result", "test_loinc", "test_description", "test_number" };
		new PivotUtil("-LAB", "-LAB_PIVOT", 35, 50, 4, 4, 2, headers).exec(srcFile);
		// sdoh
		headers = new String[] { "pca", "patient_id", "sdoh_date", "sdoh_description", "sdoh_value" };
		new PivotUtil("-SDOH", "-SDOH_PIVOT", 12, 34, 22, 1, 2, 3, headers).exec(srcFile, PivotType.USE_HEADERS);
		// obs
		headers = new String[] { "pca", "patient_id", "sdoh_date", "sdoh_description", "sdoh_value" };
		new PivotUtil("-SDOH", "-SDOH_PIVOT", 12, 34, 22, 1, 2, 3, headers).exec(srcFile, PivotType.USE_HEADERS);
		// dx1
		headers = new String[] { "pca", "patient_id", "dx_date", "dx_code", "dx_description", "position" };
		new PivotUtil("-DX1", "-DX1_PIVOT", 60, 68, 2, 4, 2, headers).exec(srcFile, PivotType.ADD_HEADERS);
		// dx2
		headers = new String[] { "pca", "patient_id", "dx_date", "dx_code", "dx_description", "position" };
		new PivotUtil("-DX2", "-DX2_PIVOT", 71, 102, 2, 16, 2, headers).exec(srcFile, PivotType.ADD_HEADERS);
		// DONE
		log.info("Done.");
	}

}
