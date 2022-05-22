/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaCli;

import br.com.bandtec.banco.teste.Log;
import br.com.bandtec.banco.teste.Maquina;
import br.com.bandtec.banco.teste.Medida;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor
 */
public class LoginCli {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        Validacoes validar = new Validacoes();
        Boolean adminLogado = false;
        Maquina maquina = new Maquina();
        Medida medida = new Medida();
        Log log = new Log();
        Boolean cadastroLogado = false;

        do {
            System.out.println("\nBem vindo ao Monitech: "
                    + "\n Insira seu usuário administrador:");
            String usuario = leitor.nextLine();

            System.out.println("Insira sua senha:");
            String senha = leitor.nextLine();

            if (validar.validarLogin(usuario, senha, validar.getEmailAdmin(), validar.getSenhaAdmin())) {
                System.out.println("Bem vindo administrador");
                adminLogado = true;
            } else {
                System.out.println("Usuário ou senha inválidos");
            }
        } while (!adminLogado);

        do {
            System.out.println("\nInforme o email a a ser cadastrado:");
            String email = leitor.nextLine();

            String idUsuario = validar.getIdUsuarioEmail(email);

            if (idUsuario.isEmpty()) {
                System.out.println("Email informado está incorreto");
                log.erro("Ao configurar a máquina");
            } else {

                maquina.setMaquina(idUsuario);

                String hostname = null;
                try {
                    hostname = InetAddress.getLocalHost().getHostName();
                } catch (UnknownHostException ex) {
                    Logger.getLogger(LoginCli.class.getName()).log(Level.SEVERE, null, ex);
                }
                String idHostname = validar.getIdHostname(hostname);

                if (idHostname.isEmpty()) {
                    System.out.println("Erro ao configurar a máquina não foi "
                            + "possível adquirir o hostname");
                    log.erro("Ao configurar a máquina");
                } else {
                    System.out.println("Sucesso ao configurar a máquina");
                    log.sucesso("Ao configurar a máquina");
                    medida.setMedida(idHostname);
                    cadastroLogado = true;
                }
            }
        } while (!cadastroLogado);

    }
}
