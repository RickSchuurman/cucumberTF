/*Setting an system property "property - value" *, this system property will be used for loading the correct property file (local or external)*/

import cucumber.api.junit.Cucumber;
import org.junit.runners.model.InitializationError;
import java.io.IOException;


public class LocalCucumber extends Cucumber {


 static {
     System.setProperty("cucumber-env","-local");
 }

 public LocalCucumber(final Class<?> clazz) throws InitializationError, IOException{
     super(clazz);
 }

}
