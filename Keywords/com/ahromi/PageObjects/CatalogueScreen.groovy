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

public class CatalogueScreen extends BaseHelper {
	private TestObject lblTitle
	private TestObject imgItem
	private TestObject lblItemTitle
	private TestObject lblItemDesc
	private TestObject lblPrice
	private TestObject btnAddToCart
	private TestObject btnRemoveOnCart
	private TestObject icoCart

	public CatalogueScreen() {
		lblTitle		= getTestObject("lblTitle", "app_logo", "class")
		imgItem			= getTestObject("imgItem", "inventory_item_img", "class")
		lblItemTitle	= getTestObject("lblItemTitle", "//*[contains(@class, 'inventory_item_name') and contains(text(), '_ItemName_')]")
		lblItemDesc		= getTestObject("lblItemDesc", "inventory_item_desc", "class")
		lblPrice		= getTestObject("lblPrice", "inventory_item_price", "class")
		btnAddToCart	= getTestObject("btnAddToCart", "btn_inventory", "class")
		btnRemoveOnCart	= getTestObject("btnRemoveOnCart", "//*[text() = 'Remove']")
		icoCart			= getTestObject("icoCart", "shopping_cart_link", "class")
	}

	public void verifyLanding() {
		verifyLanding(lblTitle, "Catalogue Screen")
	}

	public void addItems(String listItem) {
		String[] list = listItem.split(";")
		for (int i=0; i<list.size(); i++) {
			KeywordUtil.logInfo("Add To Cart -> " + list[i])
			String xpath = getXpath(lblItemTitle).replace("_ItemName_", list[i])
			btnAddToCart = getTestObject("btnAddToCart", "$xpath/ancestor::div[@class='inventory_item']//button")
			WebUI.click(btnAddToCart)
		}
	}

	public Map getItemsValue() {
		List<WebElement> objList = getListElementByTestObject(btnRemoveOnCart)
		KeywordUtil.logInfo("List of Obj: " + objList.size())

		Map itemsValue = [:]
		for (int i=0; i<objList.size(); i++) {
			lblItemTitle = getTestObject("lblItemTitle", "(${getXpath(btnRemoveOnCart)}/ancestor::*[@class = 'inventory_item_description']//*[contains(@class, 'inventory_item_name')])[${i+1}]")
			lblItemDesc = getTestObject("lblItemDesc", "(${getXpath(btnRemoveOnCart)}/ancestor::*[@class = 'inventory_item_description']//*[contains(@class, 'inventory_item_desc')])[${i+1}]")
			lblPrice = getTestObject("lblPrice", "(${getXpath(btnRemoveOnCart)}/ancestor::*[@class = 'inventory_item_description']//*[contains(@class, 'inventory_item_price')])[${i+1}]")

			itemsValue["title${i+1}"] = WebUI.getText(lblItemTitle)
			itemsValue["desc${i+1}"] = WebUI.getText(lblItemDesc)
			itemsValue["price${i+1}"] = WebUI.getText(lblPrice)
		}
		KeywordUtil.logInfo(itemsValue.toString())
		return itemsValue
	}

	public void ClickCart() {
		WebUI.click(icoCart)
	}
}
