package com.ensate.app.studentapp.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ensate.app.studentapp.dao.RoleRepository;
import com.ensate.app.studentapp.entity.AnnonceEntity;
import com.ensate.app.studentapp.entity.CommentaireEntity;
import com.ensate.app.studentapp.entity.ImageEntity;
import com.ensate.app.studentapp.entity.RoleEntity;
import com.ensate.app.studentapp.entity.UserEntity;
import com.ensate.app.studentapp.entity.request.UserRequestModel;
import com.ensate.app.studentapp.entity.request.UserUpdateRequestModel;
import com.ensate.app.studentapp.entity.rest.UserRest;
import com.ensate.app.studentapp.service.UserService;

@RestController
public class UserController {

	private final UserService userService;
	private final RoleRepository roleRepository;

	@Autowired
	public UserController(UserService userService, RoleRepository roleRepository) {
		this.userService = userService;
		this.roleRepository = roleRepository;
	}
	
	
	@PostMapping("/users")
	public UserRest addUser(@RequestBody UserRequestModel user) {
		
		UserEntity userEntity = new UserEntity();
		
		UserRest returnedValue = new UserRest();
		
		BeanUtils.copyProperties(user, userEntity);
		
		//userEntity.setEncryptedPassword(user.getPassword()+"increpted");
		
		if(userService.getUserByUsername(user.getUsername()) != null) {
			throw new RuntimeException("Username already exist. Please choise another one");
		}
		
		UserEntity savedUser = userService.addNewUser(userEntity);
		
		RoleEntity role = roleRepository.findByRoleName(user.getRole());
		
		userService.addRoleToUser(savedUser.getId(), role.getId());
		
		BeanUtils.copyProperties(userService.getUserById(savedUser.getId()), returnedValue);
		
		returnedValue.setRole(role.getRoleName());
		
		return returnedValue;
		
		
	}
	
	@PutMapping("/users")
	public UserRest updateUser(@RequestBody UserUpdateRequestModel user) {
		
		UserRest returnedValue = new UserRest();
		
		UserEntity savedUser = userService.getUserByUsername(user.getUsername());
		
		savedUser.setFirstName(user.getFirstName());
		savedUser.setLastName(user.getLastName());
		savedUser.setEmail(user.getEmail());
		savedUser.setPhoneNumber(user.getPhoneNumber());
		savedUser.setImageUrl(user.getImageUrl());
		
//		RoleEntity role = roleRepository.findByRoleName(user.getRole());
//		
//		userService.addRoleToUser(savedUser.getId(), role.getId());
		
		
		
		 UserEntity updateUser = userService.updateUser(savedUser);
		 
		 BeanUtils.copyProperties(updateUser, returnedValue);
		 
		 return returnedValue;
		 
	}
	
	@DeleteMapping("/users/{userId}")
	public void deleteUserById(@PathVariable Long userId) {
		userService.deleteUser(userId);
	}
	
	@GetMapping("/users/{userId}")///////////////////////
	public UserRest getUserById(@PathVariable Long userId) {
		UserRest returnedValue = new UserRest();
		UserEntity user = userService.getUserById(userId);
		BeanUtils.copyProperties(user, returnedValue);
		for(RoleEntity role : user.getRoles()) {
			returnedValue.setRole(role.getRoleName());
		}
		return returnedValue;
	}
	
	@GetMapping("/users")
	public List<UserEntity> getAllUsers() {
		return userService.getAllUsers();
	}
	
	//-------------------------------------------------------------------------------------------------------------//
	
	@PostMapping("/users/{userId}/annonces")
	public AnnonceEntity addAnnonce(@RequestBody AnnonceEntity annonce, @PathVariable Long userId) {
		
		AnnonceEntity insertedAnnonce = userService.addNewAnnonce(annonce);
		userService.addAnnonceToUser(userId, insertedAnnonce.getId());
		return userService.getAnnonceById(insertedAnnonce.getId());
		
	}
	
	@PutMapping("/annonces")
	public AnnonceEntity updateAnnonce(@RequestBody AnnonceEntity annonce) {
		return userService.updateAnnonce(annonce);
	}
	
	@DeleteMapping("/annonces/{annonceId}")
	public void deleteAnnonceById(@PathVariable Long annonceId) {
		userService.deleteAnnonceById(annonceId);
	}
	
	@GetMapping("/annonces/{annonceId}")
	public AnnonceEntity getAnnonceById(@PathVariable Long annonceId) {
		return userService.getAnnonceById(annonceId);
	}
	
	@GetMapping("/users/{userId}/annonces")
	public List<AnnonceEntity> getAllAnnoncesByUserId(@PathVariable Long userId){
		return userService.getAllAnnoncesByUserId(userId);
	}
	@GetMapping("/annonces")
	public List<AnnonceEntity> getAllAnnonces(){
		return userService.getAllAnnonces();
	}
	
	//----------------------------------------------------------------------------------------------------------//
	
	@PostMapping("/annonces/{annonceId}/images")
	public ImageEntity AddNewImage(@RequestBody ImageEntity image, @PathVariable Long annonceId){
		
		ImageEntity insertedImage = userService.addNewImage(image);
		userService.addImageToAnnonce(insertedImage.getId(), annonceId);
		
		return userService.getImageById(insertedImage.getId());
	}
	
	@PutMapping("/annonces/{annonceId}/images")
	public ImageEntity updateImage(@RequestBody ImageEntity image) {
		return userService.updateImage(image);
	}
	
	@DeleteMapping("/images/{imageId}")
	public void deleteImageById(@PathVariable Long imageId) {
		userService.deleteImageByID(imageId);
	}
	
	@GetMapping("/images/{imageId}")
	public ImageEntity getImageById(@PathVariable Long imageId) {
		 return userService.getImageById(imageId);
	}
	
	@GetMapping("/annonces/{annonceId}/images")
	public List<ImageEntity> getAllImagesByAnnonceId(@PathVariable Long annonceId){
		return userService.getAllImageByAnnonceId(annonceId);
	}
	
	//-------------------------------------------------------------------------------------------------------//
	
	@PostMapping(path = "/users/{userId}/annonces/{annonceId}/commentaires")
	public CommentaireEntity AddNewCommentaire(@RequestBody CommentaireEntity commentaire, @PathVariable Long annonceId, @PathVariable Long userId){
		
		CommentaireEntity insertedCommentaire = userService.addNewCommentaire(commentaire);
		userService.addCommentaireToAnnonce(annonceId, insertedCommentaire.getId(), userId);
		
		return userService.getCommentaireById(insertedCommentaire.getId());
	}
	
	@PutMapping(path = "/annonces/{annonceId}/commentaires")
	public CommentaireEntity updateCommentaire(@RequestBody CommentaireEntity commentaire) {
		return userService.updateCommentaire(commentaire);
	}
	
	@DeleteMapping("/commentaires/{commentaireId}")
	public void deleteCommentaireById(@PathVariable Long commentaireId) {
		userService.deleteCommentaireById(commentaireId);
	}
	
	@GetMapping("/commentaires/{commentaireId}")
	public CommentaireEntity getCommentaireById(@PathVariable Long commentaireId) {
		 return userService.getCommentaireById(commentaireId);
	}
	
	@GetMapping("/annonces/{annonceId}/commentaires")
	public List<CommentaireEntity> getAllCommentaireByAnnonceId(@PathVariable Long annonceId){
		return userService.getAllCommentairesByAnnonceId(annonceId);
	}
	
}
