/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package swing;

import br.com.bandtec.banco.teste.Connection;
import br.com.bandtec.banco.teste.Log;
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
    Log log = new Log();

    public Boolean validarLogin(String email, String senha, String emailBanco, String senhaBanco) {
        Boolean login;
        login = email.equals(emailBanco) && senha.equals(senhaBanco);
        if (login) {
            log.sucesso(String.format("Ao realizar "
                    + "login com o email %s", email));
        } else {
            log.erro(String.format("Ao realizar "
                    + "login com o email %s", email));
        }
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

    public String getIdHostname(String hostname) {
        List<Usuario> usuarioId = con.query("SELECT TOP 1 ID FROM maquina "
                + "WHERE HOSTNAME =? order by registro desc",
                new BeanPropertyRowMapper<>(Usuario.class), hostname);
        String formatar = usuarioId.toString().replace("Usuario{id=", "")
                .replace("nome=null", "").replace("email=null", "")
                .replace("senha=null", "").replace("cargo=null", "")
                .replace("hostname=null", "").replace("FK_Gerente=null", "")
                .replace("FK_Equipe=null}", "").replaceAll(",", "")
                .replaceAll(" ", "").replace("[", "").replace("]", "");
        return formatar;
    }

    public String getIdUsuarioEmail(String email) {
        List<Usuario> usuarioId = con.query("SELECT ID FROM usuario "
                + "WHERE EMAIL =?",
                new BeanPropertyRowMapper<>(Usuario.class), email);
        String formatar = usuarioId.toString().replace("Usuario{id=", "")
                .replace("nome=null", "").replace("email=null", "")
                .replace("senha=null", "").replace("cargo=null", "")
                .replace("FK_Gerente=null", "")
                .replace("FK_Equipe=null}", "").replaceAll(",", "")
                .replaceAll(" ", "").replace("[", "").replace("]", "");
        if (formatar.isEmpty()) {
            log.erro(String.format("Não "
                    + "conseguiu encontrar "
                    + "o id do email %s", email));
        } else {
            log.sucesso(String.format(""
                    + "Conseguiu encontrar "
                    + "o id do email %s", email));
        }
        return formatar;
    }
}
