package br.com.bandtec.banco.teste;

import com.github.britooo.looca.api.core.Looca;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import swing.Login;

public class Maquina {

    private Integer id;
    private String processador;
    private Integer coreProcessador;
    private Double espacoDisco;
    private Double memoriaRam;
    private Integer FK_Usuario;
    Connection config = new Connection();
    JdbcTemplate con = new JdbcTemplate(config.getDatasource());
    ConnectionMysql configMysql = new ConnectionMysql();
    JdbcTemplate conMysql = new JdbcTemplate(configMysql.getDatasource());

    public Maquina() {
    }



    public String getProcessador() {
        Looca looca = new Looca();
        return looca.getProcessador().getNome();
    }

    public Double getMemoriaRamTotal() {
        Looca looca = new Looca();
        Long memoriaTotalByte = looca.getMemoria().getTotal();
        Double memoriaGigaByte = memoriaTotalByte / 1073741824.0;
        return memoriaGigaByte;
    }

    public Double getEspacoDiscoTotal() {
        Looca looca = new Looca();
        Long armazenamentoTotalByte = looca.getGrupoDeDiscos().getTamanhoTotal();
        Double armazenamentoGigaByte = armazenamentoTotalByte / 1073741824.0;
        return armazenamentoGigaByte;
    }

    public Integer getCoreProcessador() {
        Looca looca = new Looca();
        return looca.getProcessador().getNumeroCpusFisicas();
    }

    public void setMaquina(String id) {
        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        //MySQL
        conMysql.update("INSERT INTO maquina (processador, espacoDisco,"
                + " memoriaRam, coreProcessador, FK_Usuario, hostname, registro) "
                + "VALUES(?, ?, ?, ?, ?, ?, now())",
                this.getProcessador(),
                this.getEspacoDiscoTotal(),
                this.getMemoriaRamTotal(),
                this.getCoreProcessador(),
                id, hostname);
        
        //AZURE
        con.update("INSERT INTO maquina (processador, espacoDisco,"
                + " memoriaRam, coreProcessador, FK_Usuario, hostname, registro) "
                + "VALUES(?, ?, ?, ?, ?, ?, GETDATE())",
                this.getProcessador(),
                this.getEspacoDiscoTotal(),
                this.getMemoriaRamTotal(),
                this.getCoreProcessador(),
                id, hostname);
    }

    @Override
    public String toString() {
        return String.format("\nID: %d\nPROCESSADOR: %s\nCORE_PROCESSADOR: %d"
                + "\nESPACO_DISCO: %.2f"
                + "\nMEMORIA_RAM: %.2f\nFK_USUARIO: %d",
                this.id, this.processador, this.coreProcessador, this.espacoDisco,
                this.memoriaRam, this.FK_Usuario);
    }

}
