import { Component, OnInit, ViewChild } from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label, SingleDataSet } from 'ng2-charts';
import { DatePipe } from '@angular/common';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { Model1 } from 'src/app/interfaces/model-1';


@Component({
  selector: 'app-etat-produit',
  templateUrl: './etat-produit.component.html',
  styleUrls: ['./etat-produit.component.css']
})
export class EtatProduitComponent implements OnInit {
  myControlville = new FormControl('');
  myControlprod = new FormControl('');
  myControldists = new FormControl('');
  filtereddists: Observable<string[]> | undefined;
  filteredprods: Observable<string[]> | undefined;
  filteredvilles: Observable<string[]> | undefined;
  public typeData: Array<Model1> = [];
  adresse: string[] = [];
  ville: string[] = [];
  nomdist: string[] = [];
  proddes: string[] = [];
  source: any;
  summaryboardJson: any;
  public barChartOptions = {
    scales: {

      xAxes: [{

        scaleLabel: {
          display: true,
          labelString: 'Etat(s)',
        }
      }],
      yAxes: [{
        type: 'linear',
        ticks: {
          beginAtZero: true,
          stepSize: 1
        },

        scaleLabel: {
          display: true,
          labelString: 'Quantité'
        }
      }]
    },


    responsive: true,
    legend: {
      display: false,

    },
    cutout: 20,
  };
  public barChartLabels: Label[] = [];
  public barChartData: SingleDataSet = [];
  public barChartType: ChartType = 'bar';
  public barChartColor: Color[] = [
    { backgroundColor: ['#FF0000', '#ffbf3a', '#4e3dc8', '#CD09F8', '#33B67C', '#33FFDA', '#33D4FF', '#FF33A5', '#042383', '#00FF2E'] },
  ];
  constructor(private dashboardService: DashboardService, private datePipe: DatePipe) { }

  ngOnInit(): void {

    let response = this.dashboardService.ville();
    response.subscribe((data) => this.ville = data);
    this.filteredvilles = this.myControlville.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );

    let responseprod = this.dashboardService.proddes();
    responseprod.subscribe((data) => this.proddes = data);
    this.filteredprods = this.myControlprod.valueChanges.pipe(
      startWith(''),
      map(value => this._filterr(value || '')),
    );
  }

  //lowercase ville
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.ville.filter(option => option.toLowerCase().includes(filterValue));
  }
  //lowercase proddes

  private _filterr(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.proddes.filter(option => option.toLowerCase().includes(filterValue));
  }
  /**/

  selectville(ville: any) {
    let responsenomdist = this.dashboardService.nomdist(ville);
    responsenomdist.subscribe((data) => this.nomdist = data);
    this.filtereddists = this.myControldists.valueChanges.pipe(
      startWith(''),
      map(value => this._filterdist(value || '')),
    );

  }

  private _filterdist(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.nomdist.filter(option => option.toLowerCase().includes(filterValue));
  }


  selectChangeHandlerprod(prod: any, ville: any, nomdist: any) {
    this.barChartData = [];
    this.barChartLabels = [];
    let response = this.dashboardService.baretat(prod, ville, nomdist).subscribe(
      (d) => {

        d.forEach((typeCountbar: Model1) => {
          this.barChartData.push(typeCountbar.count);
          this.barChartLabels.push(typeCountbar.libelle);
        });
      },
      (error) => {
        console.error(error);
      }
    );

  }

}
