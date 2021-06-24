package org.nachc.cosmos.pivot.alaska.tables.labs;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.nach.core.util.csv.CsvUtilApache;
import com.nach.core.util.file.FileUtil;
import com.nach.core.util.string.StringUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlaskaPivotLabs {

	private static final String FLAT_SUFFIX = "-LAB";
	
	private static final String PIVOT_SUFFIX = "-LAB_PIVOT";
	
	private static final int START_FLAT = 35;
			
	private static final int END_FLAT = 50;

	public static void exec(File srcFile) {
		log.info("Starting pivot...");
		log.info("Getting Files");
		File labFile = FileUtil.createFileWithAppendedName(srcFile, FLAT_SUFFIX);
		File labPivotFile = FileUtil.createFileWithAppendedName(srcFile, PIVOT_SUFFIX);
		writeFlatFileCsv(srcFile, labFile);
		writePivotFileCsv(labFile, labPivotFile);
		log.info("Done.");
	}

	private static void writeFlatFileCsv(File srcFile, File labFile) {
		try {
			log.info("Creating flat file");
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
				for (int i = START_FLAT; i <= END_FLAT; i++) {
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

	private static void writePivotFileCsv(File srcFile, File labFile) {
		try {
			CSVParser parser = CsvUtilApache.getParser(srcFile);
			CSVPrinter printer = CsvUtilApache.getWriter(labFile);
			int cnt = 0;
			log.info("Writing pivot file");
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
