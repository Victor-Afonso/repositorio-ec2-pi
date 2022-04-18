package br.com.bandtec.banco.teste;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

public class Inventario {

    private Integer id;
    private String cpu;
    private Integer coreCpu;
    private Double espacoDisco;
    private Double memoriaRam;
    private Integer FK_Usuario;

    public Inventario() {
    }

    Connection config = new Connection();
    JdbcTemplate con = new JdbcTemplate(config.getDatasource());

    public String getProcessador() {
        Looca looca = new Looca();
        return looca.getProcessador().getNome();
    }

    public Double getMemoriaRamTotal() {
        Looca looca = new Looca();
        Long memoriaTotalByte = looca.getMemoria().getTotal();
        Double memoriaGigaByte = memoriaTotalByte / 1073741824.0;
        String formatar = String.format("%.2f", memoriaGigaByte);
        return memoriaGigaByte;
    }

    public Double getEspacoDiscoTotal() {
        Looca looca = new Looca();
        Long armazenamentoTotalByte = looca.getGrupoDeDiscos().getTamanhoTotal();
        Double armazenamentoGigaByte = armazenamentoTotalByte / 1073741824.0;
        String formatar = String.format("%.2f", armazenamentoGigaByte);
        return armazenamentoGigaByte;
    }

    public Integer getCoreProcessador() {
        Looca looca = new Looca();
        return looca.getProcessador().getNumeroCpusFisicas();
    }

    public void setInventario(String id) {
        con.update("INSERT INTO inventario (processador, espacoDisco,"
                + " memoriaRam, coreProcessador, FK_Usuario) "
                + "VALUES(?, ?, ?, ?, ?)",
                this.getProcessador(),
                this.getEspacoDiscoTotal(),
                this.getMemoriaRamTotal(),
                this.getCoreProcessador(),
                id);
    }

    @Override
    public String toString() {
        return String.format("\nID: %d\nCPU: %s\nCORE_CPU: %d"
                + "\nESPACO_DISCO: %.2f"
                + "\nMEMORIA_RAM: %.2f\nFK_USUARIO: %d",
                this.id, this.cpu, this.coreCpu, this.espacoDisco,
                this.memoriaRam, this.FK_Usuario);
    }

}
