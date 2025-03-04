package com.example.QuestionMicroService.Model;

public class Response {
    private Integer slno;
    private String response;

    public Response() {
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getSlno() {
        return slno;
    }

    public void setSlno(Integer slno) {
        this.slno = slno;
    }

    public Response(String response, Integer slno) {
        this.response = response;
        this.slno = slno;
    }
}
