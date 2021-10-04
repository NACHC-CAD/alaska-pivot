package org.nachc.cosmos.pivot.louisiana;

import java.io.File;

import org.nachc.cosmos.pivot.util.pivot.PivotType;
import org.nachc.cosmos.pivot.util.pivot.PivotUtil;

import lombok.extern.slf4j.Slf4j;

// parameters are start, end, width, repeat, constantsWidth, pivotConstantsWidth

@Slf4j
public class LouisianaPivot {

//	public static final String SRC_FILE_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\covid\\update-2021-07-07-COVID-LPCA\\_ETC\\macro\\LPCA-Thumbnail.csv\\";
	
	public static final String SRC_FILE_NAME = "C:\\Users\\greshje\\Downloads\\_ETC\\LPCA_AE_2021.09.27_v4.csv";
	
	public static void main(String[] args) {
		log.info("Starting pivots...");
		File srcFile = new File(SRC_FILE_NAME);
		String[] headers;
		// demo
		headers = new String[] { "pca", "health_center_name", "patient_id", "sex_at_birth", "age", "date_of_death", "gender_identity", "race", "ethnicity", "language", "insurance_type", "fpl_range", "sdoh_assessment_date", "demo_number" };
		new PivotUtil("-DEMO", "-DEMO_PIVOT", 3, 12, 10, 1, 3, headers).exec(srcFile);
		// sdoh
		headers = new String[] { "pca", "health_center_name", "patient_id", "sdoh_date", "sdoh_description", "sdoh_value" };
		new PivotUtil("-SDOH", "-SDOH_PIVOT", 12, 34, 23, 1, 3, 4, headers).exec(srcFile, PivotType.USE_HEADERS);
		// lab
		headers = new String[] { "pca", "health_center_name", "patient_id", "test_date", "test_result", "test_loinc", "test_description", "test_number" };
		new PivotUtil("-LAB", "-LAB_PIVOT", 35, 50, 4, 4, 3, headers).exec(srcFile);
		// vacc-covid
		headers = new String[] { "pca", "health_center_name", "patient_id", "vacc_date", "vacc_cvx", "vacc_manufacturer", "vacc_refused", "vacc_number" };
		new PivotUtil("-VACC_COVID", "-VACC_COVID_PIVOT", 51, 58, 4, 2, 3, headers).exec(srcFile);
		// vacc-flu
		headers = new String[] { "pca", "health_center_name", "patient_id", "vacc_date", "vacc_cvx", "vacc_manufacturer", "vacc_refused", "vacc_number" };
		new PivotUtil("-VACC_FLU", "-VACC_FLU_PIVOT", 104, 107, 4, 1, 3, headers).exec(srcFile);
		// dx
		headers = new String[] { "pca", "health_center_name", "patient_id", "dx_date", "dx_code", "dx_number" };
		new PivotUtil("-DX_COVID", "-DX_COVID_PIVOT", 93, 94, 2, 1, 3, headers).exec(srcFile);
		// PARAMETERS: flatSuffix, String pivotSuffix, int startFlat, int endFlat, int pivotWidth, int pivotRepeat, int constantsWidth, Object[] pivotHeaders
		log.info("Done.");
	}
	
}
