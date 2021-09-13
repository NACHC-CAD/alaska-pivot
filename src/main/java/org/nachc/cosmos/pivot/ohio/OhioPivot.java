package org.nachc.cosmos.pivot.ohio;

import java.io.File;

import org.nachc.cosmos.pivot.util.pivot.PivotType;
import org.nachc.cosmos.pivot.util.pivot.PivotUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OhioPivot {

	public static final String SRC_FILE_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\hiv\\update-2021-08-13-HIV-OACHC\\_ETC\\pivot\\2021_08_13_872 OACHC LL Data July 2021 Azara.csv";

	public static void main(String[] args) {
		log.info("Starting pivots...");
		File srcFile = new File(SRC_FILE_NAME);
		String[] headers;
		
		// preg
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "Pregnancy Episode Start Date", "Pregnancy Episode Indicator", "Pregnancy Episode End Date", "Pregnancy Episode Prenatal Entry", "pos" };
		new PivotUtil("-PREG", "-PREG_PIVOT", 20, 23, 4, 1, 20, headers).exec(srcFile);
		// dx
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "Dx Date", "Dx Code", "Dx Category", "pos" };
		new PivotUtil("-DX", "-DX_PIVOT", 24, 29, 2, 3, 20, headers).exec(srcFile, PivotType.ADD_HEADERS, 1);
		// drug-ass
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "Drug Assessment Date", "Drug Assessment Code", "Drug Assessment Score", "Drug Use Status Date", "Drug Use Status Detail", "pos" };
		new PivotUtil("-DRUGS", "-DRUGS_PIVOT", 30, 34, 5, 1, 20, headers).exec(srcFile);
		// rx1
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "Rx Start Date", "Rx Category", "pos" };
		new PivotUtil("-RX1", "-RX1_PIVOT", 35, 35, 1, 1, 20, headers).exec(srcFile, PivotType.ADD_HEADERS);
		// exp
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "HIV Exposure Risk Date", "HIV Exposure Risk Detail", "pos" };
		new PivotUtil("-EXP", "-EXP_PIVOT", 36, 37, 2, 1, 20, headers).exec(srcFile);
		// demo
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "Sexual Orientation", "Gender Identity", "pos" };
		new PivotUtil("-DEMO", "-DEMO_PIVOT", 38, 39, 2, 1, 20, headers).exec(srcFile);
		// rx1
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "Lab Date", "Lab Result", "Lab Category", "pos" };
		new PivotUtil("-LAB", "-LAB_PIVOT", 40, 51, 2, 6, 20, headers).exec(srcFile, PivotType.ADD_HEADERS);
		// PARAMETERS: flatSuffix, String pivotSuffix, int startFlat, int endFlat, int pivotWidth, int pivotRepeat, int constantsWidth, Object[] pivotHeaders
		// done
		log.info("Done.");
	}

}
