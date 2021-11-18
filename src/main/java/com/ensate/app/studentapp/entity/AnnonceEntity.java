package com.ensate.app.studentapp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "annonces")
@Data
@NoArgsConstructor @AllArgsConstructor
public class AnnonceEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titre;
	
	private String description;
	
	private double rating;
	
	private double price;
	
	private int numberOfRooms;
	
	private boolean hasAirConditioner;
	
	private boolean hasWaterHeater;
	
	private boolean hasWifi;
	
	private int etage;
	
	private Date startAt;
	
	private Date endAt;
	
	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;
	
	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private UserEntity user;
	
	@OneToMany(mappedBy = "annonce",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	public List<CommentaireEntity> commentaires = new ArrayList<CommentaireEntity>();
	
	@OneToMany(mappedBy = "annonce",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	public List<ImageEntity> images = new ArrayList<ImageEntity>();
	
	
}
