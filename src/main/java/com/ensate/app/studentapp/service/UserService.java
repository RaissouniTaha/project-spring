package com.ensate.app.studentapp.service;

import java.util.List;

import com.ensate.app.studentapp.entity.AnnonceEntity;
import com.ensate.app.studentapp.entity.CommentaireEntity;
import com.ensate.app.studentapp.entity.ImageEntity;
import com.ensate.app.studentapp.entity.RoleEntity;
import com.ensate.app.studentapp.entity.UserEntity;

public interface UserService {

	UserEntity addNewUser(UserEntity user);
	
	UserEntity updateUser(UserEntity user);
	
	UserEntity getUserByUsername(String username);
	
	UserEntity getUserById(Long userId);
	
    void deleteUser(Long userId);
	
	List<UserEntity> getAllUsers();
	
	
	
	AnnonceEntity addNewAnnonce(AnnonceEntity annonce);
	
	AnnonceEntity updateAnnonce(AnnonceEntity annonce);
	
	AnnonceEntity getAnnonceById(Long annonceId);
	
	void deleteAnnonceById(Long annonceId);
	
	void addAnnonceToUser(Long userId, Long annonceId);
	
	List<AnnonceEntity> getAllAnnoncesByUserId(Long userId);
	
	List<AnnonceEntity> getAllAnnonces();
	
	
	
	CommentaireEntity addNewCommentaire(CommentaireEntity commentaire);
	
	CommentaireEntity updateCommentaire(CommentaireEntity commentaire);
	
	CommentaireEntity getCommentaireById(Long commentaireId);
	
	void deleteCommentaireById(Long commentaireId);
	
	List<CommentaireEntity> getAllCommentairesByAnnonceId(Long annonceId);
	
	List<CommentaireEntity> getAllCommentaires();
	
	void addCommentaireToAnnonce(Long annonceId, Long commentaireId, Long userId);
	
	
	
	ImageEntity addNewImage(ImageEntity image);
	
	ImageEntity updateImage(ImageEntity image);
	
	ImageEntity getImageById(Long imageId);
	
	void deleteImageByID(Long imageId);
	
	List<ImageEntity> getAllImageByAnnonceId(Long annonceId);
	
	void addImageToAnnonce(Long imageId, Long annonceId);
	
	
	
	
	RoleEntity addNewRole(RoleEntity role);
	
	void addRoleToUser(Long userId, Long roleId);
	
	
	
}
