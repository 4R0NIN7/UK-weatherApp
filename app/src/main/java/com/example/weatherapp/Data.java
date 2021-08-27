package com.example.weatherapp;

/**
 * Klasa z danymi
 */
public class Data {
    /**
     * Dane
     */
    private static final String json = "[" +
            "   {" +
            "      \"city\":\"Warsaw\"," +
            "      \"weather\":\"rainy\"," +
            "      \"hourly_temp\":[" +
            "         { \"temp\":-2, \"hour\":0 }," +
            "         { \"temp\":-2, \"hour\":4 }," +
            "         { \"temp\":0.5, \"hour\":8 }," +
            "         { \"temp\":2, \"hour\":12 }," +
            "         { \"temp\":3, \"hour\":16 }," +
            "         { \"temp\":-1, \"hour\":20 }" +
            "      ]" +
            "   }," +
            "   {" +
            "      \"city\":\"Paris\"," +
            "      \"weather\":\"cloudy\"," +
            "      \"hourly_temp\":[" +
            "         { \"temp\":11, \"hour\":0 }," +
            "         { \"temp\":14, \"hour\":4 }," +
            "         { \"temp\":18, \"hour\":8 }," +
            "         { \"temp\":22, \"hour\":12 }," +
            "         { \"temp\":15, \"hour\":16 }," +
            "         { \"temp\":13, \"hour\":20 }" +
            "      ]" +
            "   }," +
            "   {" +
            "      \"city\":\"Berlin\"," +
            "      \"weather\":\"sunny\"," +
            "      \"hourly_temp\":[" +
            "         { \"temp\":-6, \"hour\":0 }," +
            "         { \"temp\":-4, \"hour\":4 }," +
            "         { \"temp\":2, \"hour\":8 }," +
            "         { \"temp\":4, \"hour\":12 }," +
            "         { \"temp\":5.5, \"hour\":16 }," +
            "         { \"temp\":3, \"hour\":20 }" +
            "      ]" +
            "   }," +
            "   {" +
            "      \"city\":\"New York\"," +
            "      \"weather\":\"cloudy\"," +
            "      \"hourly_temp\":[" +
            "         { \"temp\":12, \"hour\":0 }," +
            "         { \"temp\":13, \"hour\":4 }," +
            "         { \"temp\":12, \"hour\":8 }," +
            "         { \"temp\":15, \"hour\":12 }," +
            "         { \"temp\":16, \"hour\":16 }," +
            "         { \"temp\":14, \"hour\":20 }" +
            "      ]" +
            "   }" +
            "]";

    /**
     * Zwraca dane
     * @return zwracane dane
     */
    public static final String getJson() {
        return json;
    }



}
