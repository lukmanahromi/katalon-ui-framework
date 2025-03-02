import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.ahromi.BaseHelper
import com.ahromi.LoginPage.LoginScreen
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

LoginScreen login = new LoginScreen()

Map dataRow = BaseHelper.getTestDataByScenario("ahromiSheet", GlobalVariable.TEST_DATA_LOCATION + 'Web_SauceDemo_Test_Data.xlsx', "P_002_NegativeScenarios")

'Step 1 : Verify Landing on Login Screen'
login.verifyLandingOnLoginScreen()

'Step 2 : Input Username'
login.inputUsername(dataRow.UserName)

'Step 3 : Input Password'
login.inputPassword(dataRow.Password)

'Step 4 : Click Login'
login.clickLogin()

'Step 5 : Verify Landing on Next page'
login.verifyInvalidInput()
