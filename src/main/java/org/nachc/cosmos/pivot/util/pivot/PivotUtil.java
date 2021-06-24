package org.nachc.cosmos.pivot.util.pivot;

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
public class PivotUtil {

	private String flatSuffix;

	private String pivotSuffix;

	private int startFlat;

	private int endFlat;

	private int pivotWidth;

	private int pivotRepeat;

	private Object[] pivotHeaders = { "pca", "patient_id", "covid_vacc_date", "covid_vacc_cvx", "covid_vacc_manufacturer", "covid_vacc_refused", "vacc_number" };

	public PivotUtil(String flatSuffix, String pivotSuffix, int startFlat, int endFlat, int pivotWidth, int pivotRepeat, Object[] pivotHeaders) {
		super();
		this.flatSuffix = flatSuffix;
		this.pivotSuffix = pivotSuffix;
		this.startFlat = startFlat;
		this.endFlat = endFlat;
		this.pivotWidth = pivotWidth;
		this.pivotRepeat = pivotRepeat;
		this.pivotHeaders = pivotHeaders;
	}

	public void exec(File srcFile) {
		log.info("Starting pivot for LABS");
		log.info("Getting Files");
		// get files
		File flatFile = FileUtil.createFileWithAppendedName(srcFile, flatSuffix);
		File pivotFile = FileUtil.createFileWithAppendedName(srcFile, pivotSuffix);
		log.info("Creating FLAT file");
		writeFlatFileCsv(srcFile, flatFile);
		log.info("Creating PIVOT file");
		writePivotFileCsv(flatFile, pivotFile);
		log.info("Done pivot");
	}

	private void writeFlatFileCsv(File srcFile, File flatFile) {
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
				for (int i = startFlat; i <= endFlat; i++) {
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

	private void writePivotFileCsv(File srcFile, File labFile) {
		try {
			CSVParser parser = CsvUtilApache.getParser(srcFile);
			CSVPrinter printer = CsvUtilApache.getWriter(labFile);
			int cnt = 0;
			log.info("Writing pivot file");
			printer.printRecord(pivotHeaders);
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
				for (int i = 0; i < pivotRepeat; i++) {
					if (record.size() > (start + pivotWidth - 1) && StringUtil.isEmpty(record.get(start)) == false) {
						row = new ArrayList<String>();
						row.add(record.get(0));
						row.add(record.get(1));
						for (int r = start; r < start + pivotWidth; r++) {
							row.add(record.get(r));
						}
						row.add((iterNumber + 1) + "");
						printer.printRecord(row);
					}
					start = start + pivotWidth;
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
