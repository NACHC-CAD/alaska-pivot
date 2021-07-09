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

	private int constantsWidth;
	
	private int pivotConstants;

	private Object[] pivotHeaders;
	
	private PivotType pivotType = PivotType.PIVOT;

	public PivotUtil(String flatSuffix, String pivotSuffix, int startFlat, int endFlat, int pivotWidth, int pivotRepeat, int constantsWidth, Object[] pivotHeaders) {
		super();
		this.flatSuffix = flatSuffix;
		this.pivotSuffix = pivotSuffix;
		this.startFlat = startFlat;
		this.endFlat = endFlat;
		this.pivotWidth = pivotWidth;
		this.pivotRepeat = pivotRepeat;
		this.constantsWidth = constantsWidth;
		this.pivotHeaders = pivotHeaders;
	}

	public PivotUtil(String flatSuffix, String pivotSuffix, int startFlat, int endFlat, int pivotWidth, int pivotRepeat, int constantsWidth, int pivotConstants, Object[] pivotHeaders) {
		super();
		this.flatSuffix = flatSuffix;
		this.pivotSuffix = pivotSuffix;
		this.startFlat = startFlat;
		this.endFlat = endFlat;
		this.pivotWidth = pivotWidth;
		this.pivotRepeat = pivotRepeat;
		this.constantsWidth = constantsWidth;
		this.pivotConstants = pivotConstants;
		this.pivotHeaders = pivotHeaders;
	}

	public void exec(File srcFile) {
		exec(srcFile, PivotType.PIVOT);
	}

	public void exec(File srcFile, PivotType pivotType) {
		log.info("Starting pivot for " + pivotSuffix);
		log.info("Getting Files");
		// get files
		File flatFile = FileUtil.createFileWithAppendedName(srcFile, flatSuffix);
		File pivotFile = FileUtil.createFileWithAppendedName(srcFile, pivotSuffix);
		log.info("Creating FLAT file");
		writeFlatFileCsv(srcFile, flatFile);
		log.info("Creating PIVOT file");
		if (pivotType.equals(PivotType.USE_HEADERS)) {
			doPivotWithHeaders(flatFile, pivotFile);
		} else if(pivotType.equals(PivotType.ADD_HEADERS)) {
			doPivot(flatFile, pivotFile, true);
		} else {
			doPivot(flatFile, pivotFile);
		}
		log.info("Done pivot");
	}

	private void writeFlatFileCsv(File srcFile, File flatFile) {
		try {
			CSVParser parser = CsvUtilApache.getParser(srcFile);
			CSVPrinter printer = CsvUtilApache.getWriter(flatFile);
			int cnt = 0;
			for (CSVRecord record : parser) {
				if (cnt % 1000 == 0 && cnt != 0) {
					log.info("  Reading row " + cnt);
				}
				ArrayList<String> row = new ArrayList<String>();
				for (int c = 0; c < constantsWidth; c++) {
					row.add(record.get(c));
				}
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

	private void doPivot(File srcFile, File pivotFile) {
		doPivot(srcFile, pivotFile, false);
	}
	
	private void doPivot(File srcFile, File pivotFile, boolean addHeader) {
		try {
			log.info("Doing pivot");
			CSVParser parser = CsvUtilApache.getParser(srcFile);
			CSVPrinter printer = CsvUtilApache.getWriter(pivotFile);
			int cnt = 0;
			log.info("Writing pivot file");
			printer.printRecord(pivotHeaders);
			ArrayList<String> keys = new ArrayList<String>();
			for (CSVRecord record : parser) {
				// collect headers and move on
				if (cnt == 0) {
					for (int i = 0; i < record.size(); i++) {
						String key = record.get(i);
						keys.add(key);
					}
					cnt++;
					continue;
				}
				if (cnt % 1000 == 0 && cnt != 0) {
					log.info("  Reading row " + cnt);
				}
				ArrayList<String> row;
				int iterNumber = 0;
				// TODO: CHECK THIS IF ALASKA GOES AWRY
				int start = this.constantsWidth;
				int keyCount = constantsWidth;
				// process the record
				for (int i = 0; i < pivotRepeat; i++) {
					if (record.size() > (start + pivotWidth - 1) && StringUtil.isEmpty(record.get(start)) == false) {
						row = new ArrayList<String>();
						// TODO: CHECK THIS IF ALASKA GOES AWRY
						for(int c=0;c<this.constantsWidth;c++) {
							row.add(record.get(c));
						}
						for (int r = start; r < start + pivotWidth; r++) {
							row.add(record.get(r));
						}
						if(addHeader == true) {
							keyCount = (i * pivotWidth) + constantsWidth;
							row.add(keys.get(keyCount));
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

	private void doPivotWithHeaders(File srcFile, File labFile) {
		try {
			log.info("Doing pivot");
			CSVParser parser = CsvUtilApache.getParser(srcFile);
			CSVPrinter printer = CsvUtilApache.getWriter(labFile);
			int cnt = 0;
			log.info("* * * Writing pivot file WITH HEADERS * * *");
			printer.printRecord(pivotHeaders);
			ArrayList<String> keys = new ArrayList<String>();
			for (CSVRecord record : parser) {
				// echo status
				if (cnt % 1000 == 0 && cnt != 0) {
					log.info("  Reading row " + cnt);
				}
				// get the headers
				if (cnt == 0) {
					for (int i = 0; i < record.size(); i++) {
						String key = record.get(i);
						keys.add(key);
					}
				} else {
					ArrayList<String> row;
					// process the record
					if (record.size() >= pivotWidth + 2) {
						for (int r = pivotConstants; r < keys.size(); r++) {
							// TODO: CHECK HERE IF ALASKA GOES AWRY
							row = new ArrayList<String>();
							for (int c = 0; c < pivotConstants; c++) {
								row.add(record.get(c));
							}
							row.add(keys.get(r));
							row.add(record.get(r));
							printer.printRecord(row);
							printer.flush();
						}
					}
				}
				cnt++;
			}
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}
