package page_objects;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class StartPage {


    public void openBrowser() {
        open("https://google.nl");
    }

    public void zoeken(String zoekWoord) {
        $("#lst-ib").sendKeys(zoekWoord);
        sleep(500);
    }


    public String getEersteHit() {
        // Capture Search Auto Suggestions
        ElementsCollection listBox = Selenide.$$(By.xpath("//*[@class='sbqs_c']"));
        int listBoxSize = listBox.size();
        System.out.println("The size of the listbox is:" + listBoxSize);
        ArrayList<String> listBoxItems = new ArrayList<String>();
        for (WebElement option : listBox) {
            listBoxItems.add(option.getText());
        }
        return listBoxItems.get(0);
    }
}
