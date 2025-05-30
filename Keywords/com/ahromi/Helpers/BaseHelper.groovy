package com.ahromi.Helpers
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.openqa.selenium.By

import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.SelectorMethod
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class BaseHelper {
	public TestObject getTestObject(String objName, String value, String property="xpath") {
		/*
		 * property: xpath, id, text etc
		 * value : based on what property used, if u use xpath => "//*[text() = 'Login']", if u use id just write only id
		 */
		TestObject to = new TestObject(objName)
		to.addProperty(property, ConditionType.CONTAINS, value)
		//		to.selectorMethod = SelectorMethod.BASIC
		return to
	}

	public String getXpath(TestObject to) {
		def prop = to.getProperties().find()

		if (prop == null) {
			return null
		}

		String name = prop.getName().toLowerCase()
		String value = prop.getValue()

		switch (name) {
			case "xpath":
				return value // already a valid xpath
			case "id":
				return "//*[contains(@id, '${value}')]"
			case "class":
				return "//*[contains(@class, '${value}')]"
			case "text":
				return "//*[contains(text(), '${value}')]"
			case "name":
				return "//*[contains(@name, '${value}')]"
			default:
			// fallback to generic contains
				return "//*[contains(@${name}, '${value}')]"
		}
	}

	public List<WebElement> getListElementByTestObject(TestObject to) {
		def driver = DriverFactory.getWebDriver()

		// Extract selector method & value
		String xpath = null
		def prop = to.getProperties().find { it.getName().equalsIgnoreCase("xpath") }

		if (prop) {
			xpath = prop.getValue()
		} else {
			// If not using xpath, convert other properties into xpath
			xpath = getXpath(to) // <- from your previous method!
		}

		// Return list of WebElements matching the XPath
		return driver.findElements(By.xpath(xpath))
	}

	public List<WebElement> getListElementByTestObject(String xpath) {
		def driver = DriverFactory.getWebDriver()
		return driver.findElements(By.xpath(xpath))
	}


	public void verifyLanding(TestObject to, String screenName) {
		if (WebUI.waitForElementPresent(to, 5)) {
			KeywordUtil.markPassed("Success : Landing to $screenName")
		}else {
			KeywordUtil.markFailed("Failed : Landing to $screenName")
		}
	}


	static Map<String, String> getTestDataByScenario(String sheetName, String filePath, String scenarioID) {
		FileInputStream fis = new FileInputStream(filePath)
		Workbook workbook = new XSSFWorkbook(fis)
		Sheet sheet = workbook.getSheet(sheetName)

		Map<String, String> dataMap = [:]

		int headerRow = 0
		int scenarioColumn = -1

		// Get Header Row
		Row header = sheet.getRow(headerRow)
		for (int i = 0; i < header.getLastCellNum(); i++) {
			if (header.getCell(i).getStringCellValue() == "ScenarioId") {
				scenarioColumn = i
				break
			}
		}

		if (scenarioColumn == -1) {
			KeywordUtil.markFailed("❌ Column 'ScenarioId' not found")
			return null
		}

		// Loop through rows
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i)
			if (row.getCell(scenarioColumn)?.getStringCellValue() == scenarioID) {
				KeywordUtil.markPassed("✅ Scenario Found: ${scenarioID}")
				for (int j = 0; j < header.getLastCellNum(); j++) {
					String key = header.getCell(j).getStringCellValue()
					String value = row.getCell(j)?.getStringCellValue()
					dataMap[key] = value
				}
				break
			}
		}

		if (dataMap.isEmpty()) {
			KeywordUtil.markFailed("❌ No Data Found for Scenario ID: ${scenarioID}")
			return null
		}

		KeywordUtil.logInfo("Scenario Data: ${dataMap}")

		GlobalVariable.TEST_DATA = dataMap
		return dataMap
	}

	static void validateIsEquals(String actual, String expected) {
		if (actual.equalsIgnoreCase(expected)) {
			KeywordUtil.markPassed("Actual : $actual, is equals with Expected : $expected")
		}else {
			KeywordUtil.markFailed("Actual : $actual, is not equals with Expected : $expected")
		}
	}
}