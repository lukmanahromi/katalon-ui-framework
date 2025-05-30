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
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class CheckoutScreen extends BaseHelper{
	private TestObject lblCheckoutTitle
	private TestObject txfFirstName
	private TestObject txfLastName
	private TestObject txfPostalCode
	private TestObject btnContinue
	
	public CheckoutScreen() {
		lblCheckoutTitle = getTestObject("lblCheckoutTitle", "//*[@class = 'title' and text() = 'Checkout: Your Information']")
		txfFirstName	 = getTestObject("txfFirstName", "first-name", "id")
		txfLastName		 = getTestObject("txfLastName", "last-name", "id")
		txfPostalCode	 = getTestObject("txfPostalCode", "postal-code", "id")
		btnContinue	 	 = getTestObject("btnContinue", "continue", "id")
	}
	
	public void verifyLandingScreen() {
		verifyLanding(lblCheckoutTitle, "Checkout Screen")
	}
	
	public void inputForm(String firstName, String lastName, String postalCode) {
		WebUI.setText(txfFirstName, firstName)
		WebUI.setText(txfLastName, lastName)
		WebUI.setText(txfPostalCode, postalCode)
	}
	
	public void clickContinue() {
		WebUI.click(btnContinue)
	}
	
}
