package org.nachc.cosmos.pivot.util.pivot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import com.nach.core.util.csv.CsvUtilApache;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This class doesn't actually pivot any data. It simply copies the included
 * columns into the new flat and pivoted files.
 *
 */
@Slf4j
public class Pivot01NoPivot {

	//
	// instance variables
	//

	InputStream flatSrc;
	
	InputStream pivotSrc;

	OutputStream flat;

	OutputStream pivot;

	String[] colNames;

	Integer[] cols;

	boolean includeHeaderRow = false;

	//
	// trivial getters and setters
	//
	
	public boolean getIncludeHeaderRow() {
		return includeHeaderRow;
	}

	public void setIncludeHeaderRow(boolean includeHeaderRow) {
		this.includeHeaderRow = includeHeaderRow;
	}
	
	//
	// constructors and init
	//

	public Pivot01NoPivot(File srcFile, File flatFile, File pivotFile, String[] colNames, Integer[] cols) {
		try {
			InputStream flatSrc = new FileInputStream(srcFile);
			InputStream pivotSrc = new FileInputStream(srcFile);
			OutputStream flat = new FileOutputStream(flatFile);
			OutputStream pivot = new FileOutputStream(pivotFile);
			init(flatSrc, pivotSrc, flat, pivot, colNames, cols);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	private void init(InputStream flatSrc, InputStream pivotSrc, OutputStream flat, OutputStream pivot, String[] colNames, Integer[] cols) {
		log.info("Creating pivot tool");
		log.info("colNames: " + colNames);
		log.info("cols: " + cols);
		if (colNames == null || cols == null) {
			throw new RuntimeException("colNames and cols must both be non-null");
		}
		log.info("colNames: " + colNames.length);
		log.info("cols: " + cols.length);
		if (colNames.length != cols.length) {
			throw new RuntimeException("colNames and cols must be the same length");
		}
		this.flatSrc = flatSrc;
		this.pivotSrc = pivotSrc;
		this.flat = flat;
		this.pivot = pivot;
		this.colNames = colNames;
		this.cols = cols;
		log.info("Done creating pivot tool");
	}

	public void exec() {
		log.info("Writing flat file...");
		writeFlatFile();
		writePivotFile();
		log.info("Done writing flat file.");
	}

	private void writeFlatFile() {
		writeFile(flatSrc, flat);
	}

	private void writePivotFile() {
		writeFile(pivotSrc, pivot);
	}

	private void writeFile(InputStream src, OutputStream out) {
		try {
			int writeCnt = 0;
			CSVParser parser = CsvUtilApache.getParser(src);
			CSVPrinter printer = CsvUtilApache.getWriter(out);
			// write the header
			printer.printRecord(colNames);
			writeCnt++;
			printer.flush();
			boolean isFirst = true;
			for (CSVRecord record : parser) {
				// skip the header row
				if(includeHeaderRow == false && isFirst == true) {
					isFirst = false;
					continue;
				}
				writeCnt++;
				ArrayList<String> row = new ArrayList<String>();
				for(int col : cols) {
					String str = record.get(col);
					row.add(str);
				}
				printer.printRecord(row);
				if(writeCnt % 1000 == 0) {
					log.info("Writing row " + writeCnt);
				}
			}
			log.info("Writing row " + writeCnt);
			printer.flush();
			log.info("Done writing flat file");
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

}
