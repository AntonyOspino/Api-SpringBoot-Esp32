package com.miprojecto.esp32.api.conexionesp32;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Conexionesp32Application {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.out.println("DB_URL: " + dotenv.get("DB_URL")); // Imprimir variable para verificar
		System.out.println("DB_USERNAME: " + dotenv.get("DB_USERNAME")); // Imprimir variable para verificar
		System.out.println("DB_PASSWORD: " + dotenv.get("DB_PASSWORD")); // Imprimir variable para verificar
		SpringApplication.run(Conexionesp32Application.class, args);
	}

}
