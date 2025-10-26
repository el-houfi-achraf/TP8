package TP8.spring.repositories;

import TP8.spring.entities.Compte;
import TP8.spring.entities.TypeCompte;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

@SpringBootApplication
@EntityScan(basePackages = "TP8.spring.entities")
@EnableJpaRepositories(basePackages = "TP8.spring.repositories")
@ComponentScan(basePackages = "TP8.spring")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner start(CompteRepository compteRepository) {
		return args -> {
			// Création de quelques comptes pour tester
			compteRepository.save(new Compte(null, 1500.0, new Date(), TypeCompte.COURANT));
			compteRepository.save(new Compte(null, 3000.0, new Date(), TypeCompte.EPARGNE));
			compteRepository.save(new Compte(null, 2500.0, new Date(), TypeCompte.COURANT));

			// Affichage des comptes
			compteRepository.findAll().forEach(compte -> {
				System.out.println("Compte ID: " + compte.getId() +
						", Solde: " + compte.getSolde() +
						", Type: " + compte.getType());
			});
		};
	}
}
