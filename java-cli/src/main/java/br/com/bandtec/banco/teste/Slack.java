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

    private static final HttpClient client = HttpClient.newHttpClient();

    public static void sendMessage(JSONObject content) throws IOException, InterruptedException {
        try {

            String url1 = "https";
            String url2 = "://hooks";
            String url3 = ".slack.com";
            String url4 = "/services";
            String url5 = "/T03DJG";
            String url6 = "8FXFZ/B0";
            String url7 = "3HZU3A";
            String url8 = "B6G/8HoY1";
            String url9 = "Y9xWfVL6";
            String url10 = "fL0XhOQQ";
            String url11 = "2WF";
            String URL = url1 + url2 + url3 + url4 + url5 + url6 + url7 + url8 + url9 + url10 + url11;
            HttpRequest request = HttpRequest.newBuilder(
                    URI.create(URL))
                    .header("accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(content.toString()))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println(String.format("Status: %s", response.statusCode()));
            System.out.println(String.format("Response: %s", response.body()));

            Log log = new Log();
            log.sucesso("Alerta enviado ao Slack");
        } catch (IOException | InterruptedException e) {
            Log log = new Log();
            log.erro(String.format("Não foi possível conectar ao"
                    + " Slack exception %s", e.toString()));
        }
    }

    public Slack() {
    }

    public void alertaDisco(Double disco) throws IOException, InterruptedException {
        String porcentagem = "%";
        if (disco > 80.00) {
            JSONObject json = new JSONObject();
            String alertaDisco = String.format("Alerta Disco está em: %.2f %s", disco, porcentagem);
            json.put("text", alertaDisco);
            Slack.sendMessage(json);
        }

    }

    public void alertaCpu(Double cpu) throws IOException, InterruptedException {
        String porcentagem = "%";
        if (cpu > 70.00 && cpu <= 90.00) {
            JSONObject json = new JSONObject();
            String alertaCpu = String.format("Alerta CPU está em:%.2f %s", cpu, porcentagem);
            json.put("text", alertaCpu);
            Slack.sendMessage(json);
        }
        if (cpu > 90.00) {
            JSONObject json = new JSONObject();
            String alertaCpu = String.format("Alerta critico CPU está: %.2f %s", cpu, porcentagem);
            json.put("text", alertaCpu);
            Slack.sendMessage(json);

        }

    }

}
