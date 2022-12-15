package utility;

import POJO.Spartan;
import com.github.javafaker.Faker;

import java.util.LinkedHashMap;
import java.util.Map;

public class SpartanUtil {

    public static Map<String, Object> RandomSpartanPOSTPayload() {


        Faker faker = new Faker();

        Map<String, Object> payLoad = new LinkedHashMap<>();

        payLoad.put("name", faker.name().firstName());
        payLoad.put("gender", faker.demographic().sex());
        payLoad.put("phone", faker.number().numberBetween(5000000000L, 9999999999L));

        return payLoad;
    }


    public static Spartan getRandomSpartanPOJO() {
        Faker faker = new Faker();

        Spartan randomSpartan = new Spartan();

        randomSpartan.setName(faker.name().firstName());
        randomSpartan.setGender(faker.demographic().sex());
        randomSpartan.setPhone(faker.number().numberBetween(5000000000L, 9999999999L));

        return randomSpartan;
    }


}
