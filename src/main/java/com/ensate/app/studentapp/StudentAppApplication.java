package com.ensate.app.studentapp;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ensate.app.studentapp.constant.FileConstant;
import com.ensate.app.studentapp.entity.AnnonceEntity;
import com.ensate.app.studentapp.entity.CommentaireEntity;
import com.ensate.app.studentapp.entity.ImageEntity;
import com.ensate.app.studentapp.entity.RoleEntity;
import com.ensate.app.studentapp.entity.UserEntity;
import com.ensate.app.studentapp.service.UserService;

@SpringBootApplication
public class StudentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentAppApplication.class, args);
		new File(FileConstant.USER_FOLDER).mkdirs();
	}
 
	@Bean
	
	CommandLineRunner start(UserService userService) {
		
		return args -> {
			
			RoleEntity roleStudent = userService.addNewRole(new RoleEntity(null, "STUDENT"));
			RoleEntity roleLocataire = userService.addNewRole(new RoleEntity(null, "LOCATAIRE"));
			RoleEntity roleAdmin = userService.addNewRole(new RoleEntity(null, "ADMIN"));
			
			UserEntity user1 = userService.addNewUser(new UserEntity(null, "user1", "RAISSOUNI", "Taha", "raissouni.taha@gmail.com", "0631543666", null, null, new ArrayList<>(), new ArrayList<>(), null));
			UserEntity user2 = userService.addNewUser(new UserEntity(null, "user2", "ACHAHBAR", "Naoufal", "achahbar.naoufal@gmail.com", "0631543888", null, null, new ArrayList<>(), new ArrayList<>(), null));
			UserEntity user3 = userService.addNewUser(new UserEntity(null, "user3", "ABID", "Ismail", "abid.ismail@gmail.com", "0631589764", null, null, new ArrayList<>(), new ArrayList<>(), null));
			
			AnnonceEntity annonce1 = userService.addNewAnnonce(new AnnonceEntity(null, "title 1", "description 1", 4, 250, 2, true, true, false, 2, new Date(), new Date(System.currentTimeMillis() + 1000*60*60*24*2), null, null, null, null, null));
			AnnonceEntity annonce2 = userService.addNewAnnonce(new AnnonceEntity(null, "title 2", "description 2", 3, 200, 1, false, true, true, 1, new Date(), new Date(System.currentTimeMillis() + 1000*60*60*24*4), null, null, null, null, null));
			AnnonceEntity annonce3 = userService.addNewAnnonce(new AnnonceEntity(null, "title 3", "description 3", 2, 450, 4, false, false, false, 3, new Date(), new Date(System.currentTimeMillis() + 1000*60*60*24*1), null, null, null, null, null));
			AnnonceEntity annonce4 = userService.addNewAnnonce(new AnnonceEntity(null, "title 4", "description 4", 1, 500, 3, true, true, false, 4, new Date(), new Date(System.currentTimeMillis() + 1000*60*60*24*6), null, null, null, null, null));

			CommentaireEntity commentaire1 = userService.addNewCommentaire(new CommentaireEntity(null, "Commentaire 1", null, null));
			CommentaireEntity commentaire2 = userService.addNewCommentaire(new CommentaireEntity(null, "Commentaire 2", null, null));
			CommentaireEntity commentaire3 = userService.addNewCommentaire(new CommentaireEntity(null, "Commentaire 3", null, null));
			CommentaireEntity commentaire4 = userService.addNewCommentaire(new CommentaireEntity(null, "Commentaire 4", null, null));
			CommentaireEntity commentaire5 = userService.addNewCommentaire(new CommentaireEntity(null, "Commentaire 5", null, null));
			
			ImageEntity image1 = userService.addNewImage(new ImageEntity(null, "image1.png", null));
			ImageEntity image2 = userService.addNewImage(new ImageEntity(null, "image2.png", null));
			ImageEntity image3 = userService.addNewImage(new ImageEntity(null, "image3.png", null));
			ImageEntity image4 = userService.addNewImage(new ImageEntity(null, "image4.png", null));
			ImageEntity image5 = userService.addNewImage(new ImageEntity(null, "image5.png", null));
			
			userService.addRoleToUser(user1.getId(), roleStudent.getId());
			userService.addRoleToUser(user2.getId(), roleLocataire.getId());
			userService.addRoleToUser(user3.getId(), roleAdmin.getId());
			
			userService.addAnnonceToUser(user1.getId(), annonce1.getId());
			userService.addAnnonceToUser(user1.getId(), annonce2.getId());
			userService.addAnnonceToUser(user2.getId(), annonce3.getId());
			userService.addAnnonceToUser(user3.getId(), annonce4.getId());
			
			userService.addCommentaireToAnnonce(annonce1.getId(), commentaire1.getId(), user1.getId());
			userService.addCommentaireToAnnonce(annonce2.getId(), commentaire2.getId(), user3.getId());
			userService.addCommentaireToAnnonce(annonce3.getId(), commentaire3.getId(), user3.getId());
			userService.addCommentaireToAnnonce(annonce4.getId(), commentaire4.getId(), user2.getId());
			userService.addCommentaireToAnnonce(annonce1.getId(), commentaire5.getId(), user1.getId());

			userService.addImageToAnnonce(image1.getId(), annonce1.getId());
			userService.addImageToAnnonce(image2.getId(), annonce1.getId());
			userService.addImageToAnnonce(image3.getId(), annonce2.getId());
			userService.addImageToAnnonce(image4.getId(), annonce3.getId());
			userService.addImageToAnnonce(image5.getId(), annonce4.getId());
			
		};
		
	}
	
	
}
