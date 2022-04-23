package br.com.bandtec.banco.teste;

import com.github.britooo.looca.api.core.Looca;
import java.util.Timer;
import java.util.TimerTask;
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

    public void setMedida(String id) {
        int delay = 5000;
        int interval = 15000;

        timer1.scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                con.update("INSERT INTO medida "
//                        + "(porcentagemCPU, qtdMemoriaRam, "
//                        + "qtdEspacoDisco, registro, FK_Maquina) "
//                        + "VALUES(?, ?, ?, now(), ?)",
//                        getPorcentagemCPU(),
//                        getQtdMemoriaRam(),
//                        getQtdEspacoDisco(),
//                        id);
//            }

            //Azure
            @Override
            public void run() {
                con.update("INSERT INTO medida "
                        + "(porcentagemCPU, qtdMemoriaRam, "
                        + "qtdEspacoDisco, registro, FK_Maquina) "
                        + "VALUES(?, ?, ?, GETDATE(), ?)",
                        getPorcentagemCPU(),
                        getQtdMemoriaRam(),
                        getQtdEspacoDisco(),
                        id);
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
