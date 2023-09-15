package com.example.databindingincludelayout.dataextraction;

public class JsonData {

    String parameter1;
    String parameter2;
    String parameter3;

    public JsonData(String parameter1, String parameter2, String parameter3) {
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
        this.parameter3 = parameter3;
    }

    public String getParameter1() {
        return parameter1;
    }

    public void setParameter1(String parameter1) {
        this.parameter1 = parameter1;
    }

    public String getParameter2() {
        return parameter2;
    }

    public void setParameter2(String parameter2) {
        this.parameter2 = parameter2;
    }

    public String getParameter3() {
        return parameter3;
    }

    public void setParameter3(String parameter3) {
        this.parameter3 = parameter3;
    }

//
//    public void apiWork() {
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://real-time-finance-data.p.rapidapi.com/search?query=Apple&language=en"))
//                .header("X-RapidAPI-Key", "5562fac321mshe2f49aff2685edfp1e6fafjsn008350e3e261")
//                .header("X-RapidAPI-Host", "real-time-finance-data.p.rapidapi.com")
//                .method("GET", HttpRequest.BodyPublishers.noBody())
//                .build();
//        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());
//
//    }


}
