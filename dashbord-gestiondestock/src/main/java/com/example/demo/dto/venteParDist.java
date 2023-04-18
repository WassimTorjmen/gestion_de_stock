package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class venteParDist {
    public String destination;
    public String adresse;
    public String nom_dist;
    public String nom_produit;
    public String cod_prod;
    public String last_delivary_date;
    public String last_sell_date;
    public Integer derniere_qte_livree;
}
