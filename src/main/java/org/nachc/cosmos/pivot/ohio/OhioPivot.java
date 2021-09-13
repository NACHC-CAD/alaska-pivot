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
		// demo
		headers = new String[] {"Zip","Sex at Birth","Race Unscrubbed","Usual Provider","Race","Ethnicity","Language","Housing Situation UDS","Fake Id","Age","Language Record Date","Language Patient Detail","FPL Range","FPL Value","Housing Trigger","Insurance Financial Class","Insurance Primary Payer","Most Recent Encounter Date","Most Recent Encounter Provider","Most Recent Encounter Location","pos"};
		new PivotUtil("-DEMO", "-DEMO_PIVOT", 19, 19, 1, 1, 19, headers).exec(srcFile);
		// String flatSuffix, String pivotSuffix, int startFlat, int endFlat, int pivotWidth, int pivotRepeat, int constantsWidth, Object[] pivotHeaders
		// done
		log.info("Done.");
	}

}
