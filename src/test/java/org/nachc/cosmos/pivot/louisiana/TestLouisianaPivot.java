package org.nachc.cosmos.pivot.louisiana;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

import org.nachc.cosmos.pivot.util.pivot.legacy.PivotType;
import org.nachc.cosmos.pivot.util.pivot.legacy.PivotUtil;

public class TestLouisianaPivot {

    private final String inputFileName = "test-data-set-001.csv";
    private final String outputFileName = "test-data-set-001";

    @Test
    public void CanWriteLouisianaPivotToCsv() {
    	
    	//Arrange
        String[] headers;
        File srcFile = new File(inputFileName);
        
        //Act
        // demo
        headers = new String[]{"pca", "health_center_name", "patient_id", "sex_at_birth", "age", "date_of_death", "gender_identity", "race", "ethnicity", "language", "insurance_type", "fpl_range", "sdoh_assessment_date", "demo_number"};
        new PivotUtil("-DEMO", "-DEMO_PIVOT", 3, 12, 10, 1, 3, headers).exec(srcFile);
        // dx
        headers = new String[]{"pca", "health_center_name", "patient_id", "dx_date", "dx_code", "dx_number"};
        new PivotUtil("-DX_COVID", "-DX_COVID_PIVOT", 93, 94, 2, 1, 3, headers).exec(srcFile);
        // lab
        headers = new String[]{"pca", "health_center_name", "patient_id", "test_date", "test_result", "test_loinc", "test_description", "test_number"};
        new PivotUtil("-LAB", "-LAB_PIVOT", 35, 50, 4, 4, 3, headers).exec(srcFile);
        // sdoh
        headers = new String[]{"pca", "health_center_name", "patient_id", "sdoh_date", "sdoh_description", "sdoh_value"};
        new PivotUtil("-SDOH", "-SDOH_PIVOT", 12, 34, 23, 1, 3, 4, headers).exec(srcFile, PivotType.USE_HEADERS);
        // vacc-covid
        headers = new String[]{"pca", "health_center_name", "patient_id", "vacc_date", "vacc_cvx", "vacc_manufacturer", "vacc_refused", "vacc_number"};
        new PivotUtil("-VACC_COVID", "-VACC_COVID_PIVOT", 51, 58, 4, 2, 3, headers).exec(srcFile);
        // vacc-flu
        headers = new String[]{"pca", "health_center_name", "patient_id", "vacc_date", "vacc_cvx", "vacc_manufacturer", "vacc_refused", "vacc_number"};
        new PivotUtil("-VACC_FLU", "-VACC_FLU_PIVOT", 104, 107, 4, 1, 3, headers).exec(srcFile);
        
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
        }

        //DX
        try {
        	File testFile = new File(outputFileName + "-DX_COVID.csv");
        	Scanner outputFile = new Scanner(testFile);
        	Assert.assertEquals(outputFile.nextLine(), "PCA,PAT ID,State,Covid Dx,Covid Dx Code");
        	Assert.assertEquals(outputFile.nextLine(), "AKPCA,22F9BB8B-0896-4A9D-8539-7977EE183F26,AK,,");
        	outputFile.close();
        } catch (FileNotFoundException e) {
        	fail("DX File was not generated");
        }

        //LAB
        try {
        	File testFile = new File(outputFileName + "-LAB.csv");
        	Scanner outputFile = new Scanner(testFile);
        	Assert.assertEquals(outputFile.nextLine(), "PCA,PAT ID,State,Covid Test Date 1,Covid Test Result 1,Covid Test LOINC 1,Covid Test Type 1,Covid Test Date 2,Covid Test Result 2,Covid Test LOINC 2,Covid Test Type 2,Covid Test Date 3,Covid Test Result 3,Covid Test LOINC 3,Covid Test Type 3,Covid Test Date 4,Covid Test Result 4,Covid Test LOINC 4,Covid Test Type 4");
        	Assert.assertEquals(outputFile.nextLine(), "AKPCA,22F9BB8B-0896-4A9D-8539-7977EE183F26,AK,9/1/2021,Negative,94500-6,Covid 19 Gene Xpert Xpress (In-house) | Covid 19 Gene Xpert Xpress,8/18/2021,Negative,94500-6,Covid 19 Gene Xpert Xpress (In-house) | Covid 19 Gene Xpert Xpress,8/12/2021,Negative,94534-5,Covid 19 Abbott ID Now (In-house) | Covid 19 Abbott ID Now,8/3/2021,Negative,94500-6,Covid 19 Gene Xpert Xpress (In-house) | Covid 19 Gene Xpert Xpress");
        	outputFile.close();
        } catch (FileNotFoundException e) {
        	fail("LAB File was not generated");
        }

        //SDOH
        try {
        	File testFile = new File(outputFileName + "-SDOH.csv");
        	Scanner outputFile = new Scanner(testFile);
        	Assert.assertEquals(outputFile.nextLine(), "PCA,PAT ID,State,SDOH Assessment Date,Child Care Insecurity,Clothing Insecurity,Education,Employment,Food Insecurity,FPL Value,Homeless,Housing Situation,Incarceration,Medical Care Insecurity,Social Integration,Migrant Status,Phone Insecurity,Refugee,Safety,Stress,Medical Transportation Insecurity,Transportation Insecurity,Utilities Insecurity,Veteran Status,Domestic Violence,Other Material Insecurity");
        	Assert.assertEquals(outputFile.nextLine(), "AKPCA,22F9BB8B-0896-4A9D-8539-7977EE183F26,AK,8/31/2021,N,N,N,N,N,FPL<200%,N,N,N,N,N,N,N,N,N,N,N,N,N,N,N,N");
        	outputFile.close();
        } catch (FileNotFoundException e) {
        	fail("SDOH File was not generated");
        }

        //VACC_COVID
        try {
        	File testFile = new File(outputFileName + "-VACC_COVID.csv");
        	Scanner outputFile = new Scanner(testFile);
        	Assert.assertEquals(outputFile.nextLine(), "PCA,PAT ID,State,Covid Vaccine Date 1,Covid Vaccine CVX 1,Covid Vaccine Manufacturer 1,Covid Vaccine Refused 1,Covid Vaccine Date 2,Covid Vaccine CVX 2,Covid Vaccine Manufacturer 2,Covid Vaccine Refused 2");
        	Assert.assertEquals(outputFile.nextLine(), "AKPCA,22F9BB8B-0896-4A9D-8539-7977EE183F26,AK,,,,,,,,");
        	outputFile.close();
        } catch (FileNotFoundException e) {
        	fail("VACC_COVID File was not generated");
        }

        //VACC_FLU
        try {
        	File testFile = new File(outputFileName + "-VACC_FLU.csv");
        	Scanner outputFile = new Scanner(testFile);
        	Assert.assertEquals(outputFile.nextLine(), "PCA,PAT ID,State,Last Flu Vaccine Date,Flu Vaccine CVX,FluVaccineManufacturer,Flu Vaccine Refused");
        	Assert.assertEquals(outputFile.nextLine(), "AKPCA,22F9BB8B-0896-4A9D-8539-7977EE183F26,AK,,,,");
        	outputFile.close();
        } catch (FileNotFoundException e) {
        	fail("VACC_FLU File was not generated");
        }
    }
}
