package org.nachc.cosmos.pivot.ohio;

import java.io.File;

import org.nachc.cosmos.pivot.util.pivot.PivotType;
import org.nachc.cosmos.pivot.util.pivot.PivotUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OhioPivot {

	public static final String SRC_FILE_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\hiv\\update-2021-08-13-HIV-OACHC\\_ETC\\pivot\\2021_08_13_872 OACHC LL Data July 2021 Azara.csv";

	public static void main(String[] args) {
		doPivot(new File(SRC_FILE_NAME));
	}
	
	public static void doPivot(File srcFile) {
		log.info("Starting pivots...");
		String[] headers;
		// preg
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "Pregnancy Episode Start Date", "Pregnancy Episode Indicator", "Pregnancy Episode End Date", "Pregnancy Episode Prenatal Entry", "pos" };
		new PivotUtil("-PREG", "-PREG_PIVOT", 20, 23, 4, 1, 20, headers).exec(srcFile);
		// dx
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "Dx Date", "Dx Code", "Dx Category", "pos" };
		new PivotUtil("-DX1", "-DX1_PIVOT", 24, 29, 2, 3, 20, headers).exec(srcFile, PivotType.ADD_HEADERS, 1);
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
		// lab
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "Lab Date", "Lab Result", "Lab Category", "pos" };
		new PivotUtil("-LAB1", "-LAB1_PIVOT", 40, 51, 2, 6, 20, headers).exec(srcFile, PivotType.ADD_HEADERS);
		// rx2
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "Rx Start Date", "Rx Stop Date", "Rx Name", "Rx Category", "pos" };
		new PivotUtil("-RX2", "-RX2_PIVOT", 52, 54, 3, 1, 20, headers).exec(srcFile, PivotType.ADD_HEADERS, 2);
		// lab2
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "Lab Date", "Lab Result", "Lab Category", "pos" };
		new PivotUtil("-LAB2", "-LAB2_PIVOT", 55, 56, 2, 1, 20, headers).exec(srcFile, PivotType.ADD_HEADERS, 1);
		// enc
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "Primary Care Encounter Date", "Primary Care Encounter Provider", "Primary Care Encounter Location", "Primary Care Encounter Count", "pos" };
		new PivotUtil("-ENC", "-ENC_PIVOT", 57, 60, 4, 1, 20, headers).exec(srcFile);
		// lab3
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "Lab Date", "Lab Code", "Lab Result", "Lab Category", "pos" };
		new PivotUtil("-LAB3", "-LAB3_PIVOT", 61, 63, 3, 1, 20, headers).exec(srcFile, PivotType.ADD_HEADERS, 2);
		// hx
		headers = new String[] { "Zip", "Sex at Birth", "Race Unscrubbed", "Usual Provider", "Race", "Ethnicity", "Language", "Housing Situation UDS", "Fake Id", "Age", "Language Record Date", "Language Patient Detail", "FPL Range", "FPL Value", "Housing Trigger", "Insurance Financial Class", "Insurance Primary Payer", "Most Recent Encounter Date", "Most Recent Encounter Provider", "Most Recent Encounter Location", "Hx Date", "Hx Detail", "Hx Category", "pos" };
		new PivotUtil("-HX", "-HX_PIVOT", 64, 77, 2, 7, 20, headers).exec(srcFile, PivotType.ADD_HEADERS, 1);
		// PARAMETERS: flatSuffix, String pivotSuffix, int startFlat, int endFlat, int pivotWidth, int pivotRepeat, int constantsWidth, Object[] pivotHeaders
		// done
		log.info("Done.");
	}

}
