import { Component, OnInit, ViewChild } from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { venteParDist } from 'src/app/interfaces/vente-pardist';
import { HttpClient } from '@angular/common/http';



@Component({
  selector: 'app-vente-pardist',
  templateUrl: './vente-pardist.component.html',
  styleUrls: ['./vente-pardist.component.css']
})
export class VentePardistComponent {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  displayedColumns: String[] = ['destination', 'adresse', 'nom_dist', 'nom_produit', 'cod_prod', 'last_delivary_date', 'last_sell_date', 'derniere_qte_livree'];
  dataSource!: MatTableDataSource<venteParDist>;
  listventeParDist: any;

  constructor(private dashboardService: DashboardService, private http: HttpClient) {
    this.dashboardService.venteParDist().subscribe(data => {
      this.listventeParDist = data;
      this.dataSource = new MatTableDataSource<venteParDist>(this.listventeParDist);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });

  }


  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }




}
