package com.clinicaAlameda.clinicaBack.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.clinicaAlameda.clinicaBack.entidades.Cita;

@Service
public class EmailServicio {
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public EmailServicio(JavaMailSender javaMailSender){
		this.javaMailSender = javaMailSender;
	}
	
	public void sendMail(Cita cita) throws MailException {
		String piedepagina = "Clínica Dental Alameda.\r\n"
				+ "Av Libertador Bernardo O´Higgins 4050\r\n"
				+ "Piso 4, Oficina 418. Estación Central.\r\n"
				+ "Edificio Alameda Oficinas.\r\n"
				+ "Metro San Alberto Hurtado Línea 1\r\n"
				+ "Tel +56 2 32451667 +56989053857";
		int hora = Integer.parseInt(cita.getHora().substring(0, 2));
		String horaFinal = "";
		if(hora < 13) {
			horaFinal = " a.m.";
		} else {
			horaFinal = " p.m.";
		}
		if(cita.getEstado().equals("confirmado")) {
			// send email
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(cita.getPaciente().getCorreo());
			mail.setFrom("securesally@gmail.com");
			mail.setSubject("Clinica Alameda te confirma tu cita!");
			mail.setText("Hola" + cita.getPaciente().getNombre() 
					+ "\n\nSu cita para " + cita.getProcedimiento().getTipo() 
					+ ", fue programada de forma exitosa. "
					+ "A continuaciòn, te entregamos toda la informaciòn"
					+ " que necesitas para tu atenciòn:"
					+ "\n\nNombre: " + cita.getPaciente().getNombre()
					+ " " + cita.getPaciente().getApellido() 
					+ "\nFecha de la cita: " + cita.getFecha_cita().substring(0, 10)
					+ "\nHora de la cita: " + cita.getHora() + horaFinal
					+ "\nOdontologo asignado: " + cita.getOdontologo().getNombre() + " " + cita.getOdontologo().getApellido()
					+ "\n\n Que tengas un buen dìa. \n\n" + piedepagina);
			
			javaMailSender.send(mail);
		} else {
			// send email
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(cita.getPaciente().getCorreo());
			mail.setFrom("securesally@gmail.com");
			mail.setSubject("Clinica Alameda te cancela tu cita!");
			mail.setText("Hola" + cita.getPaciente().getNombre() 
					+ "\n\nSu cita en la fecha: " + cita.getFecha_cita().substring(0, 10)
					+ " ha sido cancelada. Para mayor informaciòn contactase con nosotros: "
					+ "\n\nTelefono: +56 2 32451667"
					+ "\nMóvil-WhatsApp : +56 9 8905 3857"
					+ "\n\n Que tengas un buen dìa. \n\n" + piedepagina);
			
			javaMailSender.send(mail);
		}
	}
}
