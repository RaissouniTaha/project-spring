package com.ensate.app.studentapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ensate.app.studentapp.dao.AnnonceRepository;
import com.ensate.app.studentapp.dao.CommentaireRepository;
import com.ensate.app.studentapp.dao.ImageRepository;
import com.ensate.app.studentapp.dao.RoleRepository;
import com.ensate.app.studentapp.dao.UserRepository;
import com.ensate.app.studentapp.entity.AnnonceEntity;
import com.ensate.app.studentapp.entity.CommentaireEntity;
import com.ensate.app.studentapp.entity.ImageEntity;
import com.ensate.app.studentapp.entity.RoleEntity;
import com.ensate.app.studentapp.entity.UserEntity;
import com.ensate.app.studentapp.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final AnnonceRepository annonceRepository;
	private final CommentaireRepository commentaireRepository;
	private final ImageRepository imageRepository;
	
	@Autowired
	public UserServiceImpl(ImageRepository imageRepository, UserRepository userRepository, RoleRepository roleRepository, AnnonceRepository annonceRepository, CommentaireRepository commentaireRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.annonceRepository = annonceRepository;
		this.commentaireRepository = commentaireRepository;
		this.imageRepository = imageRepository;
	}

	@Override
	public UserEntity addNewUser(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	public UserEntity updateUser(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	public UserEntity getUserById(Long userId) {
		return userRepository.findById(userId).get();
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
		
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public RoleEntity addNewRole(RoleEntity role) {
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(Long userId, Long roleId) {

		UserEntity user = userRepository.getById(userId);
		
		if(user == null) {
			throw new RuntimeException("User not found exception");
		}
		
		RoleEntity role = roleRepository.getById(roleId);
		
		if(role == null) {
			throw new RuntimeException("Role not found exception");
		}
		
		user.getRoles().add(role);
			
		
	}

	@Override
	public AnnonceEntity addNewAnnonce(AnnonceEntity annonce) {
		return annonceRepository.save(annonce);
	}

	@Override
	public AnnonceEntity updateAnnonce(AnnonceEntity annonce) {
		return annonceRepository.save(annonce);
	}

	@Override
	public AnnonceEntity getAnnonceById(Long annonceId) {
		return annonceRepository.findById(annonceId).get();
	}

	@Override
	public void deleteAnnonceById(Long annonceId) {
		annonceRepository.deleteById(annonceId);
		
	}

	@Override
	public void addAnnonceToUser(Long userId, Long annonceId) {
		
		UserEntity user = userRepository.getById(userId);
		
		if(user == null) {
			throw new RuntimeException("User not found exception");
		}
		
		AnnonceEntity annonce = annonceRepository.getById(annonceId);
		
		if(annonce == null) {
			throw new RuntimeException("Annonce not found exception");
		}
		
		user.getAnnonces().add(annonce);
		annonce.setUser(user);
		
	}

	@Override
	public List<AnnonceEntity> getAllAnnoncesByUserId(Long userId) {
		return userRepository.findById(userId).get().getAnnonces();
	}

	@Override
	public List<AnnonceEntity> getAllAnnonces() {
		return annonceRepository.findAll();
	}

	@Override
	public CommentaireEntity addNewCommentaire(CommentaireEntity commentaire) {
		return commentaireRepository.save(commentaire);
	}

	@Override
	public CommentaireEntity updateCommentaire(CommentaireEntity commentaire) {
		return commentaireRepository.save(commentaire);
	}

	@Override
	public CommentaireEntity getCommentaireById(Long commentaireId) {
		return commentaireRepository.findById(commentaireId).get();
	}

	@Override
	public void deleteCommentaireById(Long commentaireId) {
		commentaireRepository.deleteById(commentaireId);
		
	}

	@Override
	public List<CommentaireEntity> getAllCommentairesByAnnonceId(Long annonceId) {
		return annonceRepository.findById(annonceId).get().getCommentaires();
	}

	@Override
	public List<CommentaireEntity> getAllCommentaires() {
		return commentaireRepository.findAll();
	}

	@Override
	public void addCommentaireToAnnonce(Long annonceId, Long commentaireId, Long userId) {
		
		UserEntity user = userRepository.getById(userId);
		
		if(user == null) {
			throw new RuntimeException("User not found exception");
		}
		
		AnnonceEntity annonce = annonceRepository.getById(annonceId);
		
		if(annonce == null) {
			throw new RuntimeException("Annonce not found exception");
		}
		
		CommentaireEntity commentaire = commentaireRepository.getById(commentaireId);
		
		if(commentaire == null) {
			throw new RuntimeException("Commentaire not found exception");
		}
		
		user.getAnnonces().add(annonce);
		user.getCommentaires().add(commentaire);
		annonce.getCommentaires().add(commentaire);
		annonce.setUser(user);
		commentaire.setAnnonce(annonce);
		commentaire.setUser(user);
		
	}

	@Override
	public ImageEntity addNewImage(ImageEntity image) {
		return imageRepository.save(image);
	}

	@Override
	public ImageEntity updateImage(ImageEntity image) {
		return imageRepository.save(image);
	}

	@Override
	public ImageEntity getImageById(Long imageId) {
		return imageRepository.findById(imageId).get();
	}

	@Override
	public void deleteImageByID(Long imageId) {
		imageRepository.deleteById(imageId);
		
	}

	@Override
	public List<ImageEntity> getAllImageByAnnonceId(Long annonceId) {
		return annonceRepository.findById(annonceId).get().getImages();
	}

	@Override
	public void addImageToAnnonce(Long imageId, Long annonceId) {
		
		AnnonceEntity annonce = annonceRepository.getById(annonceId);
		
		if(annonce == null) {
			throw new RuntimeException("Annonce not found exception");
		}
		
		ImageEntity image = imageRepository.getById(imageId);
		
		if(image == null) {
			throw new RuntimeException("Image not found exception");
		}
		
		annonce.getImages().add(image);
		image.setAnnonce(annonce);
		
	}

	@Override
	public UserEntity getUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
	}

}
