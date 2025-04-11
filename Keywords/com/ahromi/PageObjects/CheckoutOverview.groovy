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

public class CheckoutOverview extends BaseHelper {
	private TestObject lblOverviewTitle
	private TestObject lblItemTitle
	private TestObject lblItemDesc
	private TestObject lblPrice
	private TestObject txtTotalItemPrice
	private TestObject txtTax
	private TestObject txtTotalPrice
	
	public CheckoutOverview() {
		lblOverviewTitle	= getTestObject("lblOverviewTitle", "//*[@class = 'title' and text() = 'Checkout: Overview']")
		lblItemTitle		= getTestObject("lblItemTitle", "inventory_item_name", "class")
		lblItemDesc			= getTestObject("lblItemDesc", "inventory_item_desc", "class")
		lblPrice			= getTestObject("lblPrice", "inventory_item_price", "class")
		txtTotalItemPrice	= getTestObject("txtTotalItemPrice", "summary_subtotal_label", "class")
		txtTax				= getTestObject("txtTax", "summary_tax_label", "class")
		txtTotalPrice		= getTestObject("txtTotalPrice", "summary_total_label", "class")
	}
	
	public void VerifyLandingScreen() {
		verifyLanding(lblOverviewTitle, "Checkout Overview")
	}
}