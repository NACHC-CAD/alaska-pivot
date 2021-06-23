package org.nachc.cosmos.pivot.alaska;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.nach.core.util.csv.CsvUtilApache;
import com.nach.core.util.string.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

	public static final String SRC_FILE_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\covid\\update-2021-06-22-COVID-APCA\\_ETC\\macro\\APCA_NACHC_C19_202001_202105.csv";

	public static final String LAB_FILE_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\covid\\update-2021-06-22-COVID-APCA\\_ETC\\macro\\APCA_NACHC_C19_202001_202105-LABS.csv";

	public static final String LAB_PIVOT_FILE_NAME = "C:\\_WORKSPACES\\nachc\\_PROJECT\\cosmos\\covid\\update-2021-06-22-COVID-APCA\\_ETC\\macro\\APCA_NACHC_C19_202001_202105-LABS-PIVOT.csv";

	public static void main(String[] args) {
		log.info("Starting pivot...");
		log.info("Getting Files");
		// get files
		File srcFile = new File(SRC_FILE_NAME);
		File labFile = new File(LAB_FILE_NAME);
		File labPivotFile = new File(LAB_PIVOT_FILE_NAME);
		writeLabsCsv(srcFile, labFile);
		writeLabsPivotCsv(labFile, labPivotFile);
		log.info("Done.");
	}

	private static void writeLabsCsv(File srcFile, File labFile) {
		try {
			log.info("Creating labs file");
			CSVParser parser = CsvUtilApache.getParser(srcFile);
			CSVPrinter printer = CsvUtilApache.getWriter(labFile);
			int cnt = 0;
			for (CSVRecord record : parser) {
				if (cnt % 1000 == 0 && cnt != 0) {
					log.info("Reading row " + cnt);
				}
				ArrayList<String> row = new ArrayList<String>();
				row.add(record.get(0));
				row.add(record.get(1));
				for (int i = 35; i <= 50; i++) {
					row.add(record.get(i));
				}
				// print the record
				printer.printRecord(row);
				// finish up
				printer.flush();
				cnt++;
			}
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	private static void writeLabsPivotCsv(File srcFile, File labFile) {
		try {
			CSVParser parser = CsvUtilApache.getParser(srcFile);
			CSVPrinter printer = CsvUtilApache.getWriter(labFile);
			int cnt = 0;
			log.info("Writing lab pivot file");
			printer.printRecord("pca", "patient_id", "test_date", "test_result", "test_loinc", "test_description", "test_number");
			for (CSVRecord record : parser) {
				// skip the headers
				if (cnt == 0) {
					cnt++;
					continue;
				}
				if (cnt % 1000 == 0 && cnt != 0) {
					log.info("Reading row " + cnt);
				}
				ArrayList<String> row;
				int testNumber;
				int start;
				// first test
				row = new ArrayList<String>();
				row.add(record.get(0));
				row.add(record.get(1));
				start = 2;
				testNumber = 1;
				if (record.size() > (start + 3) && StringUtil.isEmpty(record.get(start)) == false) {
					row.add(record.get(start));
					row.add(record.get(start + 1));
					row.add(record.get(start + 2));
					row.add(record.get(start + 3));
					row.add(testNumber + "");
					printer.printRecord(row);
				}
				// second test
				row = new ArrayList<String>();
				row.add(record.get(0));
				row.add(record.get(1));
				start = start + 4;
				testNumber = testNumber + 1;
				if (record.size() > (start + 3) && StringUtil.isEmpty(record.get(start)) == false) {
					row.add(record.get(start));
					row.add(record.get(start + 1));
					row.add(record.get(start + 2));
					row.add(record.get(start + 3));
					row.add(testNumber + "");
					printer.printRecord(row);
				}
				// third test
				row = new ArrayList<String>();
				row.add(record.get(0));
				row.add(record.get(1));
				start = start + 4;
				testNumber = testNumber + 1;
				if (record.size() > (start + 3) && StringUtil.isEmpty(record.get(start)) == false) {
					row.add(record.get(start));
					row.add(record.get(start + 1));
					row.add(record.get(start + 2));
					row.add(record.get(start + 3));
					row.add(testNumber + "");
					printer.printRecord(row);
				}
				// fourth test
				row = new ArrayList<String>();
				row.add(record.get(0));
				row.add(record.get(1));
				start = start + 4;
				testNumber = testNumber + 1;
				if (record.size() > (start + 3) && StringUtil.isEmpty(record.get(start)) == false) {
					row.add(record.get(start));
					row.add(record.get(start + 1));
					row.add(record.get(start + 2));
					row.add(record.get(start + 3));
					row.add(testNumber + "");
					printer.printRecord(row);
				}
				// finish up
				printer.flush();
				cnt++;
			}
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}
