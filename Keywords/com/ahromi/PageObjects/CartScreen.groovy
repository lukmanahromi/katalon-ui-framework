package com.ahromi.PageObjects

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebElement

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

public class CartScreen extends BaseHelper {
	private TestObject lblCartTitle
	private TestObject lblItemTitle
	private TestObject lblItemDesc
	private TestObject lblPrice
	private TestObject btnRemoveOnCart
	private TestObject btnCheckout

	public CartScreen() {
		lblCartTitle 	= getTestObject("lblCartTitle", "//*[@class = 'title' and text() = 'Your Cart']")
		lblItemTitle	= getTestObject("lblItemTitle", "inventory_item_name", "class")
		lblItemDesc		= getTestObject("lblItemDesc", "inventory_item_desc", "class")
		lblPrice		= getTestObject("lblPrice", "inventory_item_price", "class")
		btnRemoveOnCart	= getTestObject("btnRemoveOnCart", "remove", "id")
		btnCheckout		= getTestObject("btnCheckout", "checkout", "id")
	}

	public void verifyLandingScreen() {
		verifyLanding(lblCartTitle, "Cart Screen")
	}

	public void verifyItemsValue(Map itemsValue) {
		List<WebElement> objList = getListElementByTestObject(getXpath(btnRemoveOnCart))
		KeywordUtil.logInfo("List of Obj: " + objList.size())
		TestObject newlblItemTitle, newlblItemDesc, newlblPrice
		
		for (int i=1; i<=objList.size(); i++) {
			String expectedTitle = itemsValue["title$i"]
			String expectedDesc = itemsValue["desc$i"]
			String expectedPrice = itemsValue["price$i"]

			newlblItemTitle = getTestObject("lblItemTitle_$i", "(${getXpath(lblItemTitle)})[$i]")
			newlblItemDesc = getTestObject("lblItemDesc_$i", "(${getXpath(lblItemDesc)})[$i]")
			newlblPrice = getTestObject("lblPrice_$i", "(${getXpath(lblPrice)})[$i]")

			validateIsEquals(WebUI.getText(newlblItemTitle), expectedTitle)
			validateIsEquals(WebUI.getText(newlblItemDesc), expectedDesc)
			validateIsEquals(WebUI.getText(newlblPrice), expectedPrice)
		}
	}
	
	public void clickCheckout() {
		WebUI.click(btnCheckout)
	}
}
