package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

     public static boolean clickAndSend(WebElement e, String s)
     {
        e.click();
        e.sendKeys(s);
        return true;
     }

     public static boolean click(WebElement e)
     {
        e.click();
        return true;
     }

     public static String changeWebElementIntoString(WebElement element)
     {
            String ss = element.getText();
            String sss = ss.substring(ss.indexOf("(")+1,ss.indexOf(")"));
            return sss;

     }

     public static int removeCommaChangedToInt(String s)
     {
      int num = 0;
      if(s.contains(","))
      {
         String[] s1 = s.split(",");

         String s2 = s1[0];
         String s3 = s1[1];
         String s4 = s2 + s3;
         num = Integer.parseInt(s4);
      }
      else
      {
         num = Integer.parseInt(s);

      }
     
      return num;

     }
     public static void wrapperSendKeys(WebElement element, String inputText) {
      try{
          element.sendKeys(inputText);
      }
      catch(Exception e){}
  }
}

