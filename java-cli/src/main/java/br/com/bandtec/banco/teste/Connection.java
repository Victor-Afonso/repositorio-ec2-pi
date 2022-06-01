package br.com.bandtec.banco.teste;

import org.apache.commons.dbcp2.BasicDataSource;

public class Connection {

    private BasicDataSource datasource;

    Log log = new Log();

    public Connection() {
        try {

            log.sucesso("Conectado com a Azure");

            this.datasource = new BasicDataSource();

            this.datasource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            this.datasource.setUrl("jdbc:sqlserver://svr-monitech.database.windows.net:1433;database=bd-Monitech;user=admin-Monitech@svr-monitech;password=2ads#grupo3;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;");

            this.datasource.setUsername("admin-Monitech");
            this.datasource.setPassword("2ads#grupo3");

        } catch (Exception e) {
            log.erro(String.format("Não foi possível conectar a Azure "
                    + "expection \n %s", e.toString()));
        }

    }

    public BasicDataSource getDatasource() {
        return datasource;
    }
}
