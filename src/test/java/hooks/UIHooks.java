package hooks;

import io.cucumber.java.After;

import static net.serenitybdd.core.Serenity.getDriver;

public class UIHooks{


    @After("@web")
    public void tearDown() {

        if (getDriver() != null) {
            getDriver().close();
        }

    }

}
