package com.softserve.edu.opencart.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class CSVReader implements IFileReader {
    
	private final String PATH_SEPARATOR = "/";
	private final String CSV_SPLIT_BY = ";";
	private String filename;
	private String path;
	
	public CSVReader(String filename) {
	    this.filename = filename;
	    this.path = this.getClass().getResource(PATH_SEPARATOR + filename).getPath().substring(1);
	    //System.out.println("***PATH = " + path);
	}
	
	public String getFilename() {
        return this.filename;
    }

	public String getPath() {
        return this.path;
    }

	public List<List<String>> getAllCells() {
	    return getAllCells(path);
	}

    public List<List<String>> getAllCells(String path) {
		List<List<String>> allCells = new ArrayList<List<String>>();
		String row;
		// List<String> row;
		//
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(path));
			while ((row = bufferedReader.readLine()) != null) {
				allCells.add(Arrays.asList(row.split(CSV_SPLIT_BY)));
			}
		} catch (FileNotFoundException e) {
			// TODO Develop Custom Exceptions
			throw new RuntimeException(String.format(FILE_NOT_FOUND_EXCEPTION, path), e);
		} catch (IOException e) {
			throw new RuntimeException(String.format(FILE_NOT_READ_EXCEPTION, path), e);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					throw new RuntimeException(String.format(FILE_NOT_CLOSE_EXCEPTION, path), e);
				}
			}
		}
		//
		// try with resources
		/*
		try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
			while ((row = bufferedReader.readLine()) != null) {
				allCells.add(Arrays.asList(row.split(CSV_SPLIT_BY)));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//
		return allCells;
	}

}
