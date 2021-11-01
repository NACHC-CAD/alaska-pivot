package org.nachc.cosmos.pivot.alaska;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import org.nachc.cosmos.pivot.util.pivot.legacy.PivotType;
import org.nachc.cosmos.pivot.util.pivot.legacy.PivotUtil;

public class TestAlaskaPivot {

    private final String inputFileName = "test-data-set-001.csv";
    private final String outputFileName = "test-data-set-001";

    @Test
    public void CanWriteAlaskaPivotToCsv() {
    	
        //Arrange
        String[] headers;
        File srcFile = new File(inputFileName);
        
        //Act
		// demo
		headers = new String[] { "pca", "patient_id", "state", "sex_at_birth", "age", "date_of_death", "gender_identity", "race", "ethnicity", "language", "insurance_financial_class", "fpl_range", "sdoh_assessment_date", "demo_number" };
		new PivotUtil("-DEMO", "-DEMO_PIVOT", 2, 12, 11, 1, 2, headers).exec(srcFile);
		// vacc (covid)
		headers = new String[] { "pca", "patient_id", "vacc_date", "covid_cvx", "vacc_manufacturer", "vacc_refused", "vacc_number" };
		new PivotUtil("-VACC_COVID", "-VACC_COVID_PIVOT", 51, 58, 4, 2, 2, headers).exec(srcFile);
		// vacc (flu)
		headers = new String[] { "pca", "patient_id", "vacc_date", "flu_cvx", "vacc_manufacturer", "vacc_refused", "vacc_number" };
		new PivotUtil("-VACC_FLU", "-VACC_FLU_PIVOT", 104, 107, 4, 1, 2, headers).exec(srcFile);
		// lab
		headers = new String[] { "pca", "patient_id", "test_date", "test_result", "test_loinc", "test_description", "test_number" };
		new PivotUtil("-LAB", "-LAB_PIVOT", 35, 50, 4, 4, 2, headers).exec(srcFile);
		// sdoh
		headers = new String[] { "pca", "patient_id", "sdoh_date", "sdoh_description", "sdoh_value" };
		new PivotUtil("-SDOH", "-SDOH_PIVOT", 12, 34, 22, 1, 2, 3, headers).exec(srcFile, PivotType.USE_HEADERS);
		// obs
		headers = new String[] { "pca", "patient_id", "sdoh_date", "sdoh_description", "sdoh_value" };
		new PivotUtil("-SDOH", "-SDOH_PIVOT", 12, 34, 22, 1, 2, 3, headers).exec(srcFile, PivotType.USE_HEADERS);
		// dx1
		headers = new String[] { "pca", "patient_id", "dx_date", "dx_code", "dx_description", "position" };
		new PivotUtil("-DX1", "-DX1_PIVOT", 60, 68, 2, 4, 2, headers).exec(srcFile, PivotType.ADD_HEADERS);
		// dx2
		headers = new String[] { "pca", "patient_id", "dx_date", "dx_code", "dx_description", "position" };
		new PivotUtil("-DX2", "-DX2_PIVOT", 71, 102, 2, 16, 2, headers).exec(srcFile, PivotType.ADD_HEADERS);
		
		
        //Assert
		//DEMO
        try {
        	File testFile = new File(outputFileName + "-DEMO.csv");
        	Scanner outputFile = new Scanner(testFile);
        	Assert.assertEquals(outputFile.nextLine(), "PCA,PAT ID,State,Sex at Birth,Age,Date of Death,Gender Identity,Race,Ethnicity,Language,Insurance Financial Class,FPL Range,SDOH Assessment Date");
        	Assert.assertEquals(outputFile.nextLine(), "AKPCA,22F9BB8B-0896-4A9D-8539-7977EE183F26,AK,F,29,,Female,Pacific Islander,Non-Hispanic/Latino,English,Private Insurance,151 - 200%,8/31/2021");
        	outputFile.close();
        } catch (FileNotFoundException e) {
        	fail("DEMO File was not generated");
        	return;
        }

        //DX1
        try {
        	File testFile = new File(outputFileName + "-DX1.csv");
        	Scanner outputFile = new Scanner(testFile);
        	Assert.assertEquals(outputFile.nextLine(), "PCA,PAT ID,Asthma Unspecified/Mild,Asthma - Unspecified/Mild Code,Asthma - Moderate,Asthma - Moderate Code,Asthma - Severe,Asthma - Severe Code,Diabetes,Diabetes Code,Immunosuppressant Medication Date");
        	Assert.assertEquals(outputFile.nextLine(), "AKPCA,22F9BB8B-0896-4A9D-8539-7977EE183F26,,,,,,,,,");
        	outputFile.close();
        } catch (FileNotFoundException e) {
        	System.out.println("DEMO File was not generated");
        	return;
        }

        //DX2
        try {
        	File testFile = new File(outputFileName + "-DX2.csv");
        	Scanner outputFile = new Scanner(testFile);
        	Assert.assertEquals(outputFile.nextLine(), "PCA,PAT ID,COPD,COPD Code,Obesity,Obesity Code,Immunocompromised,Immunocompromised Code,CHF,CHF Code,ESRD,ESRD Code,CKD Stg 5,CKD Code,HIV Stg 3,HIV Code,Cirrhosis,Cirrhosis Code,Acute Resp Disease,Acute Resp Code,Acute Upper Resp Inf,Acute Upper Resp Inf Code,Pneumonia,Pneumonia Code,Covid Dx,Covid Dx Code,DM Complication,DM Sx Code,SOB,Sob Sx Code,Cough,Cough Sx Code,Fever,Fever Sx Code");
        	Assert.assertEquals(outputFile.nextLine(), "AKPCA,22F9BB8B-0896-4A9D-8539-7977EE183F26,,,9/27/2018,E66.8,,,,,,,,,,,,,,,,,,,,,,,,,,,,");
        	outputFile.close();
        } catch (FileNotFoundException e) {
        	fail("DEMO File was not generated");
        }

        //LAB
        try {
        	File testFile = new File(outputFileName + "-LAB.csv");
        	Scanner outputFile = new Scanner(testFile);
        	Assert.assertEquals(outputFile.nextLine(), "PCA,PAT ID,Covid Test Date 1,Covid Test Result 1,Covid Test LOINC 1,Covid Test Type 1,Covid Test Date 2,Covid Test Result 2,Covid Test LOINC 2,Covid Test Type 2,Covid Test Date 3,Covid Test Result 3,Covid Test LOINC 3,Covid Test Type 3,Covid Test Date 4,Covid Test Result 4,Covid Test LOINC 4,Covid Test Type 4");
        	Assert.assertEquals(outputFile.nextLine(), "AKPCA,22F9BB8B-0896-4A9D-8539-7977EE183F26,9/1/2021,Negative,94500-6,Covid 19 Gene Xpert Xpress (In-house) | Covid 19 Gene Xpert Xpress,8/18/2021,Negative,94500-6,Covid 19 Gene Xpert Xpress (In-house) | Covid 19 Gene Xpert Xpress,8/12/2021,Negative,94534-5,Covid 19 Abbott ID Now (In-house) | Covid 19 Abbott ID Now,8/3/2021,Negative,94500-6,Covid 19 Gene Xpert Xpress (In-house) | Covid 19 Gene Xpert Xpress");
        	outputFile.close();
        } catch (FileNotFoundException e) {
        	fail("LAB File was not generated");
        }

        //SDOH
        try {
        	File testFile = new File(outputFileName + "-SDOH.csv");
        	Scanner outputFile = new Scanner(testFile);
        	Assert.assertEquals(outputFile.nextLine(), "PCA,PAT ID,SDOH Assessment Date,Child Care Insecurity,Clothing Insecurity,Education,Employment,Food Insecurity,FPL Value,Homeless,Housing Situation,Incarceration,Medical Care Insecurity,Social Integration,Migrant Status,Phone Insecurity,Refugee,Safety,Stress,Medical Transportation Insecurity,Transportation Insecurity,Utilities Insecurity,Veteran Status,Domestic Violence,Other Material Insecurity");
        	Assert.assertEquals(outputFile.nextLine(), "AKPCA,22F9BB8B-0896-4A9D-8539-7977EE183F26,8/31/2021,N,N,N,N,N,FPL<200%,N,N,N,N,N,N,N,N,N,N,N,N,N,N,N,N");
        	outputFile.close();
        } catch (FileNotFoundException e) {
        	fail("SDOH File was not generated");
        }

        //VACC_COVID
        try {
        	File testFile = new File(outputFileName + "-VACC_COVID.csv");
        	Scanner outputFile = new Scanner(testFile);
        	Assert.assertEquals(outputFile.nextLine(), "PCA,PAT ID,Covid Vaccine Date 1,Covid Vaccine CVX 1,Covid Vaccine Manufacturer 1,Covid Vaccine Refused 1,Covid Vaccine Date 2,Covid Vaccine CVX 2,Covid Vaccine Manufacturer 2,Covid Vaccine Refused 2");
        	Assert.assertEquals(outputFile.nextLine(), "AKPCA,22F9BB8B-0896-4A9D-8539-7977EE183F26,,,,,,,,");
        	outputFile.close();
        } catch (FileNotFoundException e) {
        	fail("VACC_COVID File was not generated");
        }

        //VACC_FLU
        try {
        	File testFile = new File(outputFileName + "-VACC_FLU.csv");
        	Scanner outputFile = new Scanner(testFile);
        	Assert.assertEquals(outputFile.nextLine(), "PCA,PAT ID,Last Flu Vaccine Date,Flu Vaccine CVX,FluVaccineManufacturer,Flu Vaccine Refused");
        	Assert.assertEquals(outputFile.nextLine(), "AKPCA,22F9BB8B-0896-4A9D-8539-7977EE183F26,,,,");
        	outputFile.close();
        } catch (FileNotFoundException e) {
        	fail("VACC_FLU File was not generated");
        }
    }

}
