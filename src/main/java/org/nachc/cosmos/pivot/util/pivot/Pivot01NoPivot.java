package org.nachc.cosmos.pivot.util.pivot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

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

	InputStream src;

	OutputStream flat;

	OutputStream pivot;

	String[] colNames;

	Integer[] cols;

	//
	// constructors and init
	//

	public Pivot01NoPivot(File srcFile, File flatFile, File pivotFile, String[] colNames, Integer[] cols) {
		try {
			InputStream src = new FileInputStream(srcFile);
			OutputStream flat = new FileOutputStream(flatFile);
			OutputStream pivot = new FileOutputStream(pivotFile);
			init(src, flat, pivot, colNames, cols);
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	public Pivot01NoPivot(InputStream src, OutputStream flat, OutputStream pivot, String[] colNames, Integer[] cols) {
		init(src, flat, pivot, colNames, cols);
	}

	private void init(InputStream src, OutputStream flat, OutputStream pivot, String[] colNames, Integer[] cols) {
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
		this.src = src;
		this.flat = flat;
		this.pivot = pivot;
		this.colNames = colNames;
		this.cols = cols;
		log.info("Done creating pivot tool");
	}

	public void exec() {
		log.info("Writing flat file...");
		writeFlatFile();
		log.info("Done writing flat file.");
	}

	private void writeFlatFile() {
		try {
			CSVParser parser = CsvUtilApache.getParser(src);
			CSVPrinter printer = CsvUtilApache.getWriter(flat);
			// write the header
			printer.printRecord(colNames);
			printer.flush();
		} catch (Exception exp) {
			throw new RuntimeException(exp);
		}
	}

	private void writePivotFile(InputStream src, OutputStream flat, OutputStream pivot, List<String> colNames, List<Integer> cols) {

	}

}
