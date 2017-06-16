/*Setting an system property "key - value" *, this system property will be used for loading the correct property file (local or external)*/

import cucumber.api.junit.Cucumber;
import org.junit.runners.model.InitializationError;

import java.io.IOException;

public class ExternalEnvironment extends Cucumber {


 static {
     System.setProperty("cucumber-env","-external");
 }

 public ExternalEnvironment(final Class<?> clazz) throws InitializationError, IOException{
     super(clazz);
 }

}
