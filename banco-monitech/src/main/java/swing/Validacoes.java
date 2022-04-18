/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import br.com.bandtec.banco.teste.Connection;
import br.com.bandtec.banco.teste.Usuario;
import java.util.List;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author anton
 */
public class Validacoes {

    Connection config = new Connection();
    JdbcTemplate con = new JdbcTemplate(config.getDatasource());

    public Boolean validarLogin(String email, String senha, String emailBanco, String senhaBanco) {
        Boolean login;
        login = email.equals(emailBanco) && senha.equals(senhaBanco);
        return login;
    }

    public String getEmailAdmin() {
        List email = con.queryForList("SELECT EMAIL FROM usuario WHERE ID=1");
        return email.get(0).toString().replace("{EMAIL=", "").replace("}", "");
    }

    public String getSenhaAdmin() {
        List senha = con.queryForList("SELECT SENHA FROM usuario WHERE ID=1");
        return senha.get(0).toString().replace("{SENHA=", "").replace("}", "");
    }

    public String getIdUsuario(String hostname) {
        List<Usuario> usuarioId = con.query("SELECT ID FROM usuario "
                + "WHERE HOSTNAME =?",
                new BeanPropertyRowMapper<>(Usuario.class), hostname);
        String formatar = usuarioId.toString().replace("Usuario{id=", "")
                .replace("nome=null", "").replace("email=null", "")
                .replace("senha=null", "").replace("funcao=null", "")
                .replace("hostname=null", "").replace("FK_Gerente=null", "")
                .replace("FK_Equipe=null}", "").replaceAll(",", "")
                .replaceAll(" ", "").replace("[", "").replace("]", "");
    return formatar;
    }
}
