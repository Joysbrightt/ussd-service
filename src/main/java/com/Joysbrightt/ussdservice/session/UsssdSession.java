package com.Joysbrightt.ussdservice.session;
import java.util.*;
import java.util.stream.*;
import static spark.Spark.*;

    public class App {

        public static void main(String[] args) {

            post("/ussd", (req, res) -> {
                // Read the variables sent via POST from our API
                Map<String, String> body = Arrays
                        .asList(req.body().split("&"))
                        .stream()
                        .map(entry -> entry.split("="))
                        .collect(Collectors.toMap(entry -> entry[0], entry -> entry.length == 2 ? entry[1] : ""));

                String sessionId = body.get("sessionId");
                String serviceCode = body.get("serviceCode");
                String phoneNumber = body.get("phoneNumber");
                String text = body.get("text");

                StringBuilder response = new StringBuilder("");

                if (text.isEmpty()) {
                    // This is the first request. Note how we start the response with CON

                    response.append("CON What would you like to check\n1. My account\2. My phone number");

                } else if (text.contentEquals("1")) {
                    // Business logic for first level response

                    response.append("CON Choose account information you want to view\n1. Account number");

                } else if (text.contentEquals("2")) {
                    // Business logic for first level response

                    // This is a terminal request. Note how we start the response with END
                    response.append("END Your phone number is ");
                    response.append(phoneNumber);

                } else if (text.contentEquals("1*1")) {
                    // This is a second level response where the user selected 1 in the first instance

                    String accountNumber = "ACC100101";
                    response.append("END Your account number is "); // This is a terminal request. Note how we start the response with END
                    response.append(phoneNumber);

                }

                return response.toString();
            });
        }
    }
}
