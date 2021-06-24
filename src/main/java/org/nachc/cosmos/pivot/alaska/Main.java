package org.nachc.cosmos.pivot.alaska;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.nachc.cosmos.pivot.alaska.tables.demo.AlaskaPivotDemo;
import org.nachc.cosmos.pivot.alaska.tables.labs.AlaskaPivotLabs;
import org.nachc.cosmos.pivot.alaska.tables.vacc.AlaskaPivotVacc;

import com.nach.core.util.csv.CsvUtilApache;
import com.nach.core.util.string.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

//	public static final String SRC_FILE_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\covid\\update-2021-06-22-COVID-APCA\\_ETC\\macro\\APCA_NACHC_C19_202001_202105.csv";

	public static final String SRC_FILE_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\covid\\update-2021-06-22-COVID-APCA\\_ETC\\macro\\testing\\APCA_NACHC_C19_202001_202105_THUMB-500.csv";

	public static void main(String[] args) {
		log.info("Starting pivots...");
		File srcFile = new File(SRC_FILE_NAME);
		AlaskaPivotDemo.exec(srcFile);
		AlaskaPivotLabs.exec(srcFile);
		AlaskaPivotVacc.exec(srcFile);
		log.info("Done.");
	}
	
}
