/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.bandtec.banco.teste;

import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author aluno
 */
public class AppSlack {
    public static void main(String[] args) throws IOException, InterruptedException {
        
        JSONObject json = new JSONObject();
        
        json.put("text", "Olá eu sou o Jonas irei ajudar vocês na monitoração");
        Slack.sendMessage(json);
        Slack slack = new Slack();
        slack.alertaDisco(71.00);
        slack.alertaCpu(81.00);
        slack.alertaCpu(91.00);
        slack.alertaMemoriaRam(5.0);
        
        
        
        
    
        
    }
}
