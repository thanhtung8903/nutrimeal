package com.nutrimeal.nutrimeal.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallRequest {
    private From from = new From();
    private To[] to = new To[]{new To()};
    private String answer_url = "https://example.com/answerurl";
    private Action[] actions = new Action[]{new Action()};
    // getters and setters

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class From {
        private String type = "external";
        private String number = "842471055742";
        private String alias = "Nutri Meal";

        // getters and setters
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class To {
        private String type = "external";
        private String number;
        private String alias;

        // getters and setters
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Action {
        private String action = "talk";
        private String text;

        // getters and setters
    }
}