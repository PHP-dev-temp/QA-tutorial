package data;

import org.testng.annotations.DataProvider;

import utilities.ExcelUtils;

public class DataProviderSelfTesting {
	
	@DataProvider(name = "signup")
	public static Object[][] pages() {
		Object[][] testObjArray = new Object[][]{};
		try {
			testObjArray = ExcelUtils.getTableArray(".//src//test//resources//TestData.xls", "Sheet1");
			//testObjArray = ExcelUtils.getTableArray(".//src//test//resources//TestData.xlsx", "Sheet1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (testObjArray);
	}
}


/* data provider without excel

import org.testng.annotations.DataProvider;

public class DataSignUp {
	
	@DataProvider(name = "signup")
	public static Object[][] pages() {
		return new Object[][] {
			{	"First Name", 
				"Last Name", 
				"Email Mobile", 
				"Repeat Email Mobile", 
				"Password", 
				"31", 
				"12", 
				"2000"},	
			
			{	"1234", 
				"5678", 
				"9090", 
				"1234567", 
				"7654321", 
				"1",
				"1", 
				"2001"},
			
			{	"", 
				"@#$%",
				"aaaaa", 
				"aaaaa", 
				"", 
				"11", 
				"11", 
				"1999"},	
			};
	}

}


*/