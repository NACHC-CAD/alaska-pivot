package org.nachc.cosmos.pivot.alaska.tables.demo;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.nach.core.util.csv.CsvUtilApache;
import com.nach.core.util.file.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlaskaPivotDemo {

	public static void exec(File srcFile) {
		log.info("Starting pivot for LABS");
		log.info("Getting Files");
		// get files
		File flatFile = FileUtil.createFileWithAppendedName(srcFile, "-DEMO");
		File pivotFile = FileUtil.createFileWithAppendedName(srcFile, "-DEMO_PIVOT");
		log.info("Creating FLAT file");
		writeFlatFileCsv(srcFile, flatFile);
		log.info("Creating PIVOT file");
		writePivotFileCsv(srcFile, pivotFile);
		log.info("Done pivot");
	}

	private static void writeFlatFileCsv(File srcFile, File flatFile) {
		try {
			CSVParser parser = CsvUtilApache.getParser(srcFile);
			CSVPrinter printer = CsvUtilApache.getWriter(flatFile);
			int cnt = 0;
			printer.printRecord("org", "patient_id", "state", "sex_at_birth", "age", "date_of_death", "gender_identity", "race", "ethnicity", "language", "insurance_financial_class", "fpl_range", "sdoh_assessment_date");
			for (CSVRecord record : parser) {
				// skip the headers
				if (cnt == 0) {
					cnt++;
					continue;
				}
				if (cnt % 1000 == 0 && cnt != 0) {
					log.info("Reading row " + cnt);
				}
				ArrayList<String> row = new ArrayList<String>();
				row.add(record.get(0));
				row.add(record.get(1));
				for (int i = 2; i <= 12; i++) {
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

	private static void writePivotFileCsv(File srcFile, File pivotFile) {
		writeFlatFileCsv(srcFile, pivotFile);
	}

}
