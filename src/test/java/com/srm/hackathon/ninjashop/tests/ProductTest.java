package com.srm.hackathon.ninjashop.tests;

import com.srm.hackathon.ninjashop.base.BaseTest;
import com.srm.hackathon.ninjashop.pages.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    // 🔍 1. Search product
    @Test
    public void testSearchProduct() {

        HomePage home = new HomePage(driver);
        SearchResultsPage search = new SearchResultsPage(driver);

        home.searchProduct("MacBook");

        Assert.assertTrue(
                search.isProductDisplayed(),
                "Products not displayed"
        );
    }

    // 📂 2. Category navigation
    @Test
    public void testCategoryNavigation() {

        HomePage home = new HomePage(driver);
        SearchResultsPage search = new SearchResultsPage(driver);

        home.navigateToMacCategory();

        Assert.assertTrue(
                search.isProductDisplayed(),
                "Category products not displayed"
        );
    }

    // 📄 3. Product detail
    @Test
    public void testProductDetail() {

        HomePage home = new HomePage(driver);
        SearchResultsPage search = new SearchResultsPage(driver);
        ProductPage product = new ProductPage(driver);

        home.searchProduct("MacBook");

        // ✅ FIXED: No driver usage
        search.clickFirstProduct();

        Assert.assertTrue(
                product.isProductNameDisplayed(),
                "Product name not displayed"
        );

        Assert.assertTrue(
                product.isProductPriceDisplayed(),
                "Product price not displayed"
        );
    }

    // ❌ 4. Invalid search
    @Test
    public void testInvalidSearch() {

        HomePage home = new HomePage(driver);
        SearchResultsPage search = new SearchResultsPage(driver);

        home.searchProduct("abcd123xyz");

        Assert.assertTrue(
                search.isNoResultDisplayed(),
                "No result message not displayed"
        );
    }
}