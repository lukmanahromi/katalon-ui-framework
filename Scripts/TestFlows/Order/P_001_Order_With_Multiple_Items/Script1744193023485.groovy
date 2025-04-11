import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.ahromi.Helpers.BaseHelper
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
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

Map testData = BaseHelper.getTestDataByScenario("Order", GlobalVariable.TEST_DATA_LOCATION + 'Web_SauceDemo_Test_Data.xlsx', GlobalVariable.TEST_CASE_NAME) // TEST_CASE_NAME is assigned in the @BeforeTestCase method of the ExecutionListener

WebUI.callTestCase(findTestCase('Test Cases/TestSteps/Login/DoLogin'), testData)
testData["ItemsValue"] = [:]
WebUI.callTestCase(findTestCase('Test Cases/TestSteps/Catalogue/AddItemsToCart'), testData)
WebUI.callTestCase(findTestCase('Test Cases/TestSteps/Cart/VerifyCartSummary'), testData)
WebUI.callTestCase(findTestCase('Test Cases/TestSteps/Checkout/InputInfoForm'), testData)
WebUI.callTestCase(findTestCase('Test Cases/TestSteps/CheckoutOverview/VerifyCheckoutSummary'), testData)
WebUI.callTestCase(findTestCase('Test Cases/TestSteps/Alert/VerifyOrderSuccess'), testData)