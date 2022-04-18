package br.com.bandtec.banco.teste;

import com.github.britooo.looca.api.core.Looca;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import swing.Validacoes;

public class TestDatabase {

    public static void main(String[] args) {

        Connection config = new Connection();
        JdbcTemplate con = new JdbcTemplate(config.getDatasource());

        // Um jeito de criar longos textos de maneira organizada.
        StringBuilder createStatement1 = new StringBuilder();
        StringBuilder createStatement2 = new StringBuilder();
        StringBuilder createStatement3 = new StringBuilder();
        StringBuilder createStatement4 = new StringBuilder();
        
        
        Looca looca = new Looca();
        
        System.out.println(looca.getProcessador().getUso());
        //h2 drop tabelas
//        con.execute("SET foreign_key_checks = 0");
//        con.execute("DROP TABLE IF EXISTS medida");
//        con.execute("DROP TABLE IF EXISTS inventario");
//        con.execute("DROP TABLE IF EXISTS usuario");
//        con.execute("DROP TABLE IF EXISTS equipe");
//        con.execute("SET foreign_key_checks = 1");
        

        // Criando o comando para criar tabela equipe.
        // o método append funciona como se fosse uma concatenação.
//        createStatement1.append("CREATE TABLE equipe (");
//        createStatement1.append("id INT PRIMARY KEY AUTO_INCREMENT,");
//        createStatement1.append("nome VARCHAR(45),");
//        createStatement1.append("area VARCHAR(45)");
//        createStatement1.append(")");
//
//        // Executando o comando de criação de equipe.
//        con.execute(createStatement1.toString());
//
//        createStatement2.append("CREATE TABLE usuario (");
//        createStatement2.append("id INT PRIMARY KEY AUTO_INCREMENT,");
//        createStatement2.append("nome VARCHAR(45),");
//        createStatement2.append("email VARCHAR(45),");
//        createStatement2.append("senha VARCHAR(45),");
//        createStatement2.append("dataNascimento VARCHAR(45),");
//        createStatement2.append("funcao VARCHAR(40),");
//        createStatement2.append("FK_Gerente INT,");
//        createStatement2.append("FOREIGN KEY (FK_Gerente) REFERENCES usuario(id),");
//        createStatement2.append("FK_Equipe INT,");
//        createStatement2.append("FOREIGN KEY (FK_Equipe) REFERENCES equipe(id)");
//        createStatement2.append(")");
//
//        // Executando o comando de criação de usuario.
//        con.execute(createStatement2.toString());
//
//        createStatement3.append("CREATE TABLE inventario (");
//        createStatement3.append("id INT PRIMARY KEY AUTO_INCREMENT,");
//        createStatement3.append("cpu VARCHAR(45),");
//        createStatement3.append("coreCPU INT,");
//        createStatement3.append("espacoDisco NUMERIC(7,2),");
//        createStatement3.append("memoriaRam NUMERIC(4,2),");
//        createStatement3.append("FK_Usuario INT,");
//        createStatement3.append("FOREIGN KEY (FK_Usuario) REFERENCES usuario(id)");
//        createStatement3.append(")");
//
//        // Executando o comando de criação de inventario.
//        con.execute(createStatement3.toString());
//
//        createStatement4.append("CREATE TABLE medida (");
//        createStatement4.append("id INT PRIMARY KEY AUTO_INCREMENT,");
//        createStatement4.append("porcentagemCPU NUMERIC(5,2),");
//        createStatement4.append("qtdMemoriaRam NUMERIC(4,2),");
//        createStatement4.append("qtdEspacoDisco NUMERIC(7,2),");
//        createStatement4.append("registro VARCHAR(45),");
//        createStatement4.append("FK_Inventario INT,");
//        createStatement4.append("FOREIGN KEY (FK_Inventario) REFERENCES inventario(id)");
//        createStatement4.append(")");
//
//        // Executando o comando de criação de medida.
//        con.execute(createStatement4.toString());
        
        //Executando insert Admin h2
         //con.update("INSERT INTO USUARIO VALUES(null, 'Admin', 'admin@gmail.com', 'Admin123', '10-09-2002', 'Admin', null, null)");

         //String inserirUsuario = "INSERT INTO usuario VALUES(null,?,?,?,?,?,?,?)";
         //con.update(inserirUsuario,"Admin", "admin@gmail.com", "Admin123", "2002-09-10", "Admin", null,null);
         //System.out.println(validar.getEmailAdmin());
         //System.out.println(validar.getSenhaAdmin());
    }
}
