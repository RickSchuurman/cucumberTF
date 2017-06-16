package page_objects;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;


import static com.codeborne.selenide.Selenide.*;

public class StartPage {


    public StartPage openBrowser() {
        open("https://google.nl");
        return this;
    }

    public void zoeken(String zoekWoord) {
        $("#lst-ib").sendKeys(zoekWoord);
        sleep(500);
    }


    public String getEersteHit() {
        // Capture Search Auto Suggestions
        ElementsCollection listBox = $$(By.xpath("//div//div//ul[@role='listbox']//li"));
        int listBoxSize = listBox.size();
        System.out.println("The size of the listbox is:" + listBoxSize);
        ArrayList<String> listBoxItems = new ArrayList<String>();
        for (WebElement option : listBox) {
            listBoxItems.add(option.getText());
        }
        return listBoxItems.get(0);
    }
}
