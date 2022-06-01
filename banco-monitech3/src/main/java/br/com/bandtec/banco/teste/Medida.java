package br.com.bandtec.banco.teste;

import com.github.britooo.looca.api.core.Looca;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.cglib.core.Local;
import org.springframework.jdbc.core.JdbcTemplate;

public class Medida {

    private Integer id;
    private Double porcentagemCpu;
    private Double qtdMemoriaRam;
    private Double qtdEspacoDisco;
    private String registro;
    private Integer FK_Maquina;

    public Medida() {
    }
    Looca looca = new Looca();
    Connection config = new Connection();
    JdbcTemplate con = new JdbcTemplate(config.getDatasource());
    ConnectionMysql configMysql = new ConnectionMysql();
    JdbcTemplate conMysql = new JdbcTemplate(configMysql.getDatasource());
    Slack slack = new Slack();

    public Double getPorcentagemCPU() {
        return looca.getProcessador().getUso();
    }

    public Double getQtdMemoriaRam() {
        Long memoriaByte = looca.getMemoria().getEmUso();
        Double memoriaGigaByte = memoriaByte / 1073741824.0;

        return memoriaGigaByte;
    }

    public Double getQtdEspacoDisco() {
        Long discoByte = looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel();
        Double discoGigaByte = discoByte / 1073741824.0;

        return discoGigaByte;
    }

    Timer timer1 = new Timer();

    Maquina maquina = new Maquina();

    public void setMedida(String id) {
        int delay = 5000;
        int interval = 5000;

        timer1.scheduleAtFixedRate(new TimerTask() {

            //Azure
            @Override
            public void run() {
                Double discoTotal = maquina.getEspacoDiscoTotal();
                Double cpu = getPorcentagemCPU();
                Double disco = getQtdEspacoDisco();
                Double discoPorcentagem = ((disco * 100) / discoTotal) - 100.0;
                //SqlServer
                con.update("INSERT INTO medida "
                        + "(porcentagemCPU, qtdMemoriaRam, "
                        + "qtdEspacoDisco, registro, FK_Maquina) "
                        + "VALUES(?, ?, ?, GETDATE(), ?)",
                        cpu,
                        getQtdMemoriaRam(),
                        disco,
                        id);
                //mysql
                conMysql.update("INSERT INTO medida "
                        + "(porcentagemCPU, qtdMemoriaRam, "
                        + "qtdEspacoDisco, registro, FK_Maquina) "
                        + "VALUES(?, ?, ?, now(), ?)",
                        cpu,
                        getQtdMemoriaRam(),
                        disco,
                        id);
                
                try {
                    slack.alertaCpu(cpu);
                } catch (IOException ex) {
                    Logger.getLogger(Medida.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Medida.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    slack.alertaDisco(discoPorcentagem);
                } catch (IOException ex) {
                    Logger.getLogger(Medida.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Medida.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, delay, interval);
    }

    @Override
    public String toString() {
        return "Medida{" + "id=" + id + ", porcentagemCpu="
                + porcentagemCpu + ", qtdMemoriaRam=" + qtdMemoriaRam
                + ", qtdEspacoDisco=" + qtdEspacoDisco + ", registro="
                + registro + ", FK_Maquina=" + FK_Maquina + '}';
    }

}
