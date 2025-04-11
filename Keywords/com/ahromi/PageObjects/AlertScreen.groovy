package com.ahromi.PageObjects

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.ahromi.Helpers.BaseHelper
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class AlertScreen extends BaseHelper {
	private TestObject lblAlertTitle
	private TestObject imgAlertSuccess
	private TestObject lblAlertSuccessTitle
	private TestObject lblAlertSuccessDesc
	
	public AlertScreen() {
		lblAlertTitle 			= getTestObject("lblAlertTitle", "//*[@class = 'title' and text() = 'Checkout: Complete!']")
		imgAlertSuccess 		= getTestObject("imgAlertSuccess", "pony_express", "class")
		lblAlertSuccessTitle 	= getTestObject("lblAlertSuccessTitle", "complete-header", "class")
		lblAlertSuccessDesc 	= getTestObject("lblAlertSuccessDesc", "complete-text", "class")
	}
	
	public void verifyLandingScreen() {
		verifyLanding(lblAlertTitle, "Alert Screen")
	}
	
	public void verifyOrderSuccess() {
		if (WebUI.verifyElementNotVisible(imgAlertSuccess) && WebUI.verifyElementNotVisible(lblAlertSuccessTitle) && WebUI.verifyElementNotVisible(lblAlertSuccessDesc)) {
			KeywordUtil.markPassed("Success : Order Success!")
		}else {
			KeywordUtil.markFailed("Failed : Order Failed/Object Not Found")
		}
	}
}
