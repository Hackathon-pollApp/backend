package poll.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class sendEmailService {

    @Autowired
    private JavaMailSender sender;

    @RequestMapping("/invitation")
    @ResponseBody
    String home() {
        try {
            sendEmail(1, "Marina", "marinacamachovarea@outlook.com" );
            return "Email enviado!";
        }catch(Exception ex) {
            return "Error al enviar el mail: " + ex;
        }
    }

    private void sendEmail(int userId, String name, String email) throws Exception{
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setTo(email);
        helper.setText("Participa en el evento con el tema X. Sigue este <a href='http://127.0.0.1:8080/users/" + userId + "'>enlace</a> para votar.");
        helper.setSubject("Hola " + name);

        sender.send(message);
    }
}
