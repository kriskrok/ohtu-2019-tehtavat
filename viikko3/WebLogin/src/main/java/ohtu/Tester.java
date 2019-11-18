package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class Tester {

    public static void main(String[] args) {
        Random r = new Random();
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        
        sleep(2);

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("ninni"+r.nextInt(1000000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("password123");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("password123");

        element = driver.findElement(By.name("signup"));
        element.click();

        sleep(2);

        element = driver.findElement(By.partialLinkText("continue to application"));
        element.click();

        sleep(2);

        element = driver.findElement(By.linkText("logout"));
        element.click();

        //onnistunut kirjautuminen
        element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(2);
        element = driver.findElement(By.linkText("logout"));
        element.click();
        sleep(2);


        //epäonnistunut kirjautuminen, oikea käyttäjätunnus, väärä salasana
        element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("vaarasalasana");
        element = driver.findElement(By.name("login"));

        sleep(2);
        element.submit();

        sleep(2);
        element = driver.findElement(By.linkText("back to home"));
        element.click();

        sleep(3);
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
