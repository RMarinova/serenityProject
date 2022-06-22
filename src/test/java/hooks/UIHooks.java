package hooks;

import UI.starter.stepsLibrary.UIStepsLibrary;
import io.cucumber.java.After;
import net.thucydides.core.annotations.Steps;

import static net.serenitybdd.core.Serenity.getDriver;

public class UIHooks{


    @After("@web")
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().close();
        }
    }


}
