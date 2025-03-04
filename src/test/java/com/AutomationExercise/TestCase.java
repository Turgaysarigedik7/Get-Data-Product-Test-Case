package com.AutomationExercise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.*;
import javax.imageio.ImageIO;
import static com.AutomationExercise.JSONHandler.*;

public class TestCase {

    WebDriver driver;
    // Logger objesi oluşturuluyor
    private static final Logger logger = LoggerFactory.getLogger(TestCase.class);
    @Before
    public void SetUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
    }
    @Test
    public void ReadProductTestCase() throws InterruptedException, IOException {
        driver.get("http://automationexercise.com");
        Assert.assertTrue(driver.findElement(By.xpath("//h2[normalize-space()='Features Items']"))
                .isDisplayed());
        driver.findElement(By.xpath("//div[@class='header-middle']//a[contains(text(),'Products')]"))
                .click();
        List<WebElement> allProduct = driver.findElements(By.xpath("//div[@class='product-image-wrapper']"));
        Actions actions = new Actions(driver);
        int index =0;

        JsonArray jsonArrayRead = new JsonArray(); // Yeni bir JsonArray oluştur
        JsonArray jsonArrayUnread = new JsonArray(); // Yeni bir JsonArray oluştur

        for (var product: allProduct) {
            index++;
            Thread.sleep(1000);

            driver.findElement(By.xpath("(//div[@class='product-image-wrapper'])["+index+"]//a[contains(text(),'View Product')]"))
                    .click();
            String url = driver.findElement(By.xpath("//div[@class='view-product']//img"))
                    .getAttribute("src");


            if (!isImageControl(url)) {

                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("url", url);

                jsonArrayUnread.add(jsonObject);
            }
            else {
                ImageData image = new ImageData(url,"jpg",url);
                System.out.println("image: "+url);
                image.EcodeBase64ToImage();

                String productName = driver.findElement(By.xpath("//div[@class='product-information']//h2"))
                        .getText();
                String productLink = driver.getCurrentUrl();
                String productPrice = driver.findElement(By.xpath("//div[@class='product-information']//span[contains(text(),'Rs')]"))
                        .getText();
                String categories = driver.findElement(By.xpath("//div[@class='product-information']//p[contains(text(),'Category')]"))
                        .getText();
                String cleanCategory = categories.replace("Category","").trim();
                String[] parts = cleanCategory.split(">");

                String productCategory = parts[0];
                String productSubCategory = parts[1];


                Product productDetails = new Product(productName,productPrice,productLink,image,productCategory,productSubCategory);


                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("name", productDetails.getName());
                jsonObject.addProperty("category", productDetails.getCategory());
                jsonObject.addProperty("sub category", productDetails.getSubCategory());
                jsonObject.addProperty("link", productDetails.getLink());
                jsonObject.addProperty("price", productDetails.getPrice());

                ImageData base62Image = productDetails.getImage();
                JsonObject imageJson = new JsonObject();
                imageJson.addProperty("url", base62Image.getImageURL());
                imageJson.addProperty("format", base62Image.getFormat());
                imageJson.addProperty("name", base62Image.getName());

                // ImageData'yı Product JSON nesnesine ekliyoruz
                jsonObject.add("image", imageJson);

                // JSON array'ine bu JSON nesnesini ekliyoruz
                jsonArrayRead.add(jsonObject);

            }
            System.out.println();
            driver.navigate().back();
            }

        try {
            writeJSONToFile("unread.json", jsonArrayUnread);
            writeJSONToFile("read.json",jsonArrayRead);
        } catch (IOException e) {
            logger.error("Her iki dosyaya da eklenmedi ", e);
        }
        System.out.println("Okunan Dosya Sayısı: "+readJSONFromFile("read.json").size());
        System.out.println("Okunmayan Sayısı: "+readJSONFromFile("unread.json").size());
        }

        /**
         * Sayfada resim olup olmadığını konrol eder
         * @param url Type: String Info: Resmin olduğu sayfaya ait url adresi
         * @return  Type: Bool Info: Eğer sayfada resim varsa true yoksa false döner
         */
        public Boolean isImageControl(String url) {
          //Url üzerinden resmi indir
          BufferedImage imaged = null;
          try {
              if ((imaged = ImageIO.read(new URL(url))) == null) {
                  return false;
              } else {
                  return true;
              }
          } catch (IOException e) {
              logger.error(url+" Resim yüklenemedi: ", e);
          }
          return false;
      }
    

    @After
    public void TearDown(){
        driver.close();
    }
}
