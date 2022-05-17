/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.bandtec.banco.teste;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 *
 * @author aluno
 */
public class Slack {

    private static HttpClient client = HttpClient.newHttpClient();
    private static final String URL = "https://hooks.slack.com/services/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";

    public static void sendMessage(JSONObject content) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder(
                URI.create(URL))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(content.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(String.format("Status: %s", response.statusCode()));
        System.out.println(String.format("Response: %s", response.body()));
    }

    public Slack() {
    }

    public void alertaDisco(Double disco) throws IOException, InterruptedException {
        if (disco > 80.00) {
            JSONObject json = new JSONObject();
            String alertaDisco = String.format("Alerta Disco está: %.2f ", disco);
            json.put("text", alertaDisco);
            Slack.sendMessage(json);
        }

    }

    public void alertaCpu(Double cpu) throws IOException, InterruptedException {
        String porcentagem = "%"; 
        if (cpu > 70.00 && cpu <= 90.00) {
            JSONObject json = new JSONObject();
            String alertaCpu = String.format("Alerta CPU está: %.2f %s" , cpu, porcentagem);
            json.put("text", alertaCpu);
            Slack.sendMessage(json);
        }
        if (cpu > 90.00) {
            JSONObject json = new JSONObject();
            String alertaCpu = String.format("Alerta de criticidade alto CPU está: %.2f %s", cpu, porcentagem);
            json.put("text", alertaCpu);
            Slack.sendMessage(json);

        }

    }

    public void alertaMemoriaRam(Double ram) throws IOException, InterruptedException {
        if (ram > 4.0) {
            JSONObject json = new JSONObject();
            String alertaRam = String.format("Alerta de memoria RAM está: %.2f GB ", ram);
            json.put("text", alertaRam);
            Slack.sendMessage(json);
        }

    }

}
