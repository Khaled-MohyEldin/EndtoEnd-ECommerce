package OLDProj;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

public class DE_Serialization {


    @Test
    public void serlization() throws JsonProcessingException {

        //POJO----Serialize----> (JSONObject) ----desrialize----> POJO
        POJOPostRequest pojo = new POJOPostRequest();
        pojo.setPassword("password");
        pojo.setEmail("one@example.com");

        // convert Java Object to JSON from Jackson DataBind Class
        ObjectMapper mapper = new ObjectMapper();
        String jsonData = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(pojo);
        System.out.println("JSON=> "+ jsonData);

        //now lets covert that json data again to Java Object
        POJOPostRequest pojo2 = mapper.readValue( jsonData, POJOPostRequest.class);

        String email = pojo2.getEmail();
        String pass =  pojo2.getPassword();
        System.out.println("email & passwrod => " + email + pass);
    }
}
