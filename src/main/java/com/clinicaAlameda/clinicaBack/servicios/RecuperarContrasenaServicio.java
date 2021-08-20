package com.clinicaAlameda.clinicaBack.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.clinicaAlameda.clinicaBack.entidades.Persona;

@Service
public class RecuperarContrasenaServicio {
private JavaMailSender javaMailSender;
	
	@Autowired
	public RecuperarContrasenaServicio(JavaMailSender javaMailSender){
		this.javaMailSender = javaMailSender;
	}
	
	public void sendMail(Persona persona) throws MailException {
		String piedepagina = "Clínica Dental Alameda.\r\n"
				+ "Av Libertador Bernardo O´Higgins 4050\r\n"
				+ "Piso 4, Oficina 418. Estación Central.\r\n"
				+ "Edificio Alameda Oficinas.\r\n"
				+ "Metro San Alberto Hurtado Línea 1\r\n"
				+ "Tel +56 2 32451667 +56989053857";
		// send email
					SimpleMailMessage mail = new SimpleMailMessage();
					mail.setTo(persona.getCorreo());
					mail.setFrom("juanallison3432@gmail.com");
					mail.setSubject("Recupera tu Contraseña!");
					mail.setText("Hola " + persona.getNombre()
					+ ' ' + persona.getApellido() + "\n\n"
					+ "Clinica Alameda ha cambiado tu contraseña\n\n"
					+ "La nueva contraseña es tu numero de identificacion: " + persona.getCedula()
					+ "\n\nPor seguridad acceda con el correo electronico y esta nueva contraseña a la pestaña Actualizar datos, por si desea cambiar la contraseña a una mas segura.\n\n"
					+ piedepagina
							); 
					
					javaMailSender.send(mail);
	}
}
