package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import dev.failsafe.internal.util.Durations;

public class TestCases {
    static ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
    @Test
    public void testCase01() throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.flipkart.com");
        Actions action = new Actions(driver);
        WebElement searchElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@title='Search for Products, Brands and More']")));
        action.moveToElement(searchElement);
        Wrappers.clickAndSend(searchElement, "Washing Machine");
        action.build().perform();
        WebElement searchIcon = driver.findElement(By.xpath("//button[@aria-label='Search for Products, Brands and More']"));
        Wrappers.click(searchIcon);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Popularity']")));
        WebElement sortPopularity = driver.findElement(By.xpath("//div[text()='Popularity']"));
        action.moveToElement(sortPopularity);
        Wrappers.click(sortPopularity);
        action.build().perform();
        Thread.sleep(2000);
        List<String> sorrtedList = new ArrayList<>();
        List<WebElement> ratings = driver.findElements(By.className("XQDdHH"));
        
        for(WebElement rating : ratings)
        {
           String ratingText = rating.getText();
           //System.out.println("Rating vlaue" + ratingText);
           double ratingValue = Double.valueOf(ratingText);
           
           int num = (int) Math.round(ratingValue);
           
           if(num <= 4)
           {
            String sortedRating = Integer.toString(num); 
            
            sorrtedList.add(sortedRating);
           }
          
        }
        System.out.println(sorrtedList.size());
       





    }

    @Test
    public void testCase02() throws InterruptedException
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://www.flipkart.com");
        Actions action = new Actions(driver);
        WebElement searchElement = driver.findElement(By.xpath("//input[@class='Pke_EE']"));
        action.moveToElement(searchElement);
        Thread.sleep(2000);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='Pke_EE']']")));
        Wrappers.clickAndSend(searchElement, "iPhone");
        action.sendKeys(searchElement,Keys.ENTER).perform();
        action.build().perform();
        Thread.sleep(2000);
       // action.build().perform();
        //WebElement searchIcon = driver.findElement(By.xpath("//button[@aria-label='Search for Products, Brands and More']"));
        //Wrappers.click(searchIcon);
        List<WebElement> titles = driver.findElements(By.xpath("//div[@class='KzDlHZ']"));
        List<WebElement> discounts = driver.findElements(By.xpath("//div[@class='UkUFwK']"));
       
        for(WebElement title : titles)
        {
            if(title.getText().contains("iphone"))
            {
                for(WebElement discount : discounts)
                {
                    String d = discount.getText();
                    String newD = d.substring(0, 2);
                    int dis = Integer.parseInt(newD);
                    if(dis > 17)
                    {
                        System.out.println("The Title and Price of iphone is : " + title.getText() +" "+ dis+"%");
                    }
                }
            }
        }

    }
// Class to hold Product details (Title, Image URL, Reviews)
static class ProductDetails {
    private String title;
    private String imageUrl;
    private int reviews;

    public ProductDetails(String title, String imageUrl, int reviews) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.reviews = reviews;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getReviews() {
        return reviews;
    }
}
     @Test
    public void testCase03() throws InterruptedException
    {
        

            System.out.println("Start the testCase03");
    
            driver.get("https://www.flipkart.com");
    
            WebElement searchElement = driver
                    .findElement(By.xpath("//input[@title ='Search for Products, Brands and More']"));
            // searchElement.sendKeys("Coffee Mug");
            Wrappers.wrapperSendKeys(searchElement, "Coffee Mug");
           
            Thread.sleep(5000);
    
            Actions action = new Actions(driver);
            action.sendKeys(searchElement, Keys.ENTER).perform();
            Thread.sleep(5000);
    
            WebElement fourStarcheckboxElement = driver
                    .findElement(By.xpath("(//div[@class='XqNaEv'])[1]"));
    
                    Thread.sleep(3000);
    
            fourStarcheckboxElement.click();
    
            Thread.sleep(3000);
    
            List<WebElement> coffeeMugsElement = driver.findElements(By.xpath("//div[@class='slAVV4']"));
    
            // Wait<WebDriver> wait = new WebDriverWait(driver, null);
    
            // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='slAVV4']")));
    
            // Create a list to store product details
            List<ProductDetails> products = new ArrayList<>();
    
            // Iterate through each product element and extract details (Title, Image URL,
            // Reviews)
            for (WebElement productElement : coffeeMugsElement) {
    
                // Extract title
                WebElement titleElement = productElement.findElement(By.xpath(".//a[@class='wjcEIp']"));
                Thread.sleep(3000);
                String title = titleElement.getText().trim();
    
                // Extract image URL (you may need to adjust the XPath for actual
                // implementation)
                WebElement imageElement = productElement.findElement(By.xpath(".//img[@class='DByuf4']"));
                Thread.sleep(2000);
                String imageUrl = imageElement.getAttribute("src");
    
                // Extract number of reviews
                WebElement reviewElement = productElement
                        .findElement(By.xpath(".//span[@class='Wphh3N']"));
                String reviewText = reviewElement.getText().replaceAll("[^0-9]", ""); // Remove non-numeric characters
                int reviews = Integer.parseInt(reviewText);
    
                // Create ProductDetails object and add to the list
                ProductDetails product = new ProductDetails(title, imageUrl, reviews);
                products.add(product);
    
                // Sort products based on number of reviews (descending order)
                Collections.sort(products, (p1, p2) -> p2.getReviews() - p1.getReviews());
    
    
                // Print the Title and Image URL of the top 5 products with highest number of
                // reviews
                int count = 0;
                for (ProductDetails product1 : products) {
                    if (count<5) {
                        System.out.println("Title: " + product1.getTitle());
                        System.out.println("Image URL: " + product1.getImageUrl());
                        System.out.println("------------------------");
                        count++;
                    } else {
                        break;
                    }
                }
            }
    
        }
    


     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}