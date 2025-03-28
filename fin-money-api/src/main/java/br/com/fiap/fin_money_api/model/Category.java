package br.com.fiap.fin_money_api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Random;

@Entity
@Data
//@Table(name="tbl_categorias")
public class Category {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
//	@Column(name = "nome")
	private String name;
//	@Column(name = "icone")
	private String icon;
}
