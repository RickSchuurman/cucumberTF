package step_definitions;

import cucumber.api.java.nl.Als;
import cucumber.api.java.nl.Dan;
import cucumber.api.java.nl.Gegeven;
import page_objects.StartPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class MyStepdefs {

    private StartPage startPage = new StartPage();

    @Gegeven("^open browser$")
    public void openBrowser(){
        startPage.openBrowser();
    }

    @Als("^de gebruiker google opent en zoekt op \"([^\"]*)\"$")
    public void deGebruikerGoogleOpentEnZoektOp(String zoekWoord){
        startPage.zoeken(zoekWoord);
    }

    @Dan("^is de eerst hit \"([^\"]*)\"$")
    public void isDeEerstHit(String hit) {
        assertThat(startPage.getEersteHit(), is(hit));

    }

}
