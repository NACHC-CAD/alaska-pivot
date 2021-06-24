package org.nachc.cosmos.pivot.alaska.tables.vacc;

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
public class AlaskaPivotVacc {

	private static final String FLAT_SUFFIX = "-VACC";

	private static final String PIVOT_SUFFIX = "-VACC_PIVOT";

	private static final int START_FLAT = 51;

	private static final int END_FLAT = 58;

	private static final int PIVOT_WIDTH = 4;

	private static final int PIVOT_REPEAT = 2;

	private static final Object[] PIVOT_HEADERS = { "pca", "patient_id", "covid_vacc_date", "covid_vacc_cvx", "covid_vacc_manufacturer", "covid_vacc_refused", "iter"};

	public static void exec(File srcFile) {
		log.info("Starting pivot for LABS");
		log.info("Getting Files");
		// get files
		File flatFile = FileUtil.createFileWithAppendedName(srcFile, FLAT_SUFFIX);
		File pivotFile = FileUtil.createFileWithAppendedName(srcFile, PIVOT_SUFFIX);
		log.info("Creating FLAT file");
		writeFlatFileCsv(srcFile, flatFile);
		log.info("Creating PIVOT file");
		writePivotFileCsv(flatFile, pivotFile);
		log.info("Done pivot");
	}

	private static void writeFlatFileCsv(File srcFile, File flatFile) {
		try {
			CSVParser parser = CsvUtilApache.getParser(srcFile);
			CSVPrinter printer = CsvUtilApache.getWriter(flatFile);
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
			printer.printRecord(PIVOT_HEADERS);
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
				int iterNumber = 0;
				int start = 2;
				// first test
				for (int i = 0; i < PIVOT_REPEAT; i++) {
					if (record.size() > (start + PIVOT_WIDTH - 1) && StringUtil.isEmpty(record.get(start)) == false) {
						row = new ArrayList<String>();
						row.add(record.get(0));
						row.add(record.get(1));
						for (int r = start; r < start + PIVOT_WIDTH; r++) {
							row.add(record.get(r));
						}
						row.add(iterNumber + "");
						printer.printRecord(row);
					}
					start = start + PIVOT_WIDTH;
					iterNumber++;
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
