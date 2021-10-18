package org.nachc.cosmos.pivot.util.pivot.legacy;

import java.io.File;

import org.junit.Test;

import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PivotIntegrationTest {

	@Test
	public void shouldDoPivot()  {
		log.info("Starting test...");
		String srcFileName = "/alaska/test-data-set-001.csv";
		File srcFile = FileUtil.getFile(srcFileName);
		String[] headers = null;
		headers = new String[] { "pca", "patient_id", "state", "sex_at_birth", "age", "date_of_death", "gender_identity", "race", "ethnicity", "language", "insurance_financial_class", "fpl_range", "sdoh_assessment_date", "demo_number" };
		new PivotUtil("-DEMO", "-DEMO_PIVOT", 2, 12, 11, 1, 2, headers).exec(srcFile);
		log.info("Done.");
	}
	
	
}
