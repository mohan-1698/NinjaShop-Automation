package com.srm.hackathon.ninjashop.tests;

import com.srm.hackathon.ninjashop.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthTest extends BaseTest {

    @Test
    public void sampleTest() {
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Your Store"));
    }
}