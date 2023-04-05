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
    public String nom_produit;
    public String cod_prod;
    public Date last_delivary_date;
    public Date last_sell_date;
    public Integer derniere_qte_livrée;
}
