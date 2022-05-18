/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.bandtec.banco.teste;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Victor
 */
public class Log {

    public Log() {

    }

    public void sucesso(String texto) {
        File log = new File("Log.txt");
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String tempo = dateTime.format(formatter);
        try {
            if (!log.exists()) {
                System.out.println("Criando um arquivo para armazenar o log");
                log.createNewFile();
            }
            FileWriter arq = new FileWriter(log, true);

            try (BufferedWriter arqGravar = new BufferedWriter(arq)) {
                String formatador = String.format("\n[%s] [SUCESSO] %s", tempo, texto);
                arqGravar.write(formatador);
            }

        } catch (IOException e) {
            System.out.println("Na hora de fazer o log deu erro!!!");
        }
    }

    public void erro(String texto) {
        File log = new File("Log.txt");
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String tempo = dateTime.format(formatter);
        try {
            if (!log.exists()) {
                System.out.println("Criando um arquivo para armazenar o log");
                log.createNewFile();
            }
            FileWriter arq = new FileWriter(log, true);

            try (BufferedWriter arqGravar = new BufferedWriter(arq)) {
                String formatador = String.format("\n[%s] [ERRO] %s", tempo, texto);
                arqGravar.write(formatador);
            }

        } catch (IOException e) {
            System.out.println("Na hora de fazer o log deu erro!!!");
        }
    }
}
