
package br.com.bandtec.banco.teste;

import org.apache.commons.dbcp2.BasicDataSource;


public class ConnectionMysql {
     private BasicDataSource datasource;

    Log log = new Log();

    public ConnectionMysql() {
        try {

            log.sucesso("Conectado com o Docker Mysql");

            this.datasource = new BasicDataSource();

            this.datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");

            this.datasource.setUrl("jdbc:mysql://localhost:3306/monitech?useTimezone=true&serverTimezone=UTC");

            this.datasource.setUsername("aluno");
            this.datasource.setPassword("sptech");

        } catch (Exception e) {
            log.erro(String.format("Não foi possível conectar ao Docker Mysql "
                    + "expection \n %s", e.toString()));
        }

    }

    public BasicDataSource getDatasource() {
        return datasource;
    }
    
}
