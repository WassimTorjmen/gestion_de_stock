import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { Chart, ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label, SingleDataSet } from 'ng2-charts';
import { DatePipe } from '@angular/common';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { Model1 } from 'src/app/interfaces/model-1';
@Component({
  selector: 'app-distr-produit',
  templateUrl: './distr-produit.component.html',
  styleUrls: ['./distr-produit.component.css']
})
export class DistrProduitComponent implements OnInit {
  myControlsource = new FormControl('');
  myControlprod = new FormControl('');
  filteredprods: Observable<string[]> | undefined;
  filteredsource: Observable<string[]> | undefined;

  public lineChartOptions: ChartOptions = {
    responsive: true,
    legend: {
      display: false,

    },

    scales: {
      xAxes: [{
        scaleLabel: {
          display: true,
          labelString: 'Mois'
        },
        gridLines: {
          display: true,
          color: 'rgba(0, 0, 0, 0.1)'
        },
        ticks: {
          fontColor: 'rgba(0, 0, 0, 0.8)'
        }
      }],
      yAxes: [{
        scaleLabel: {
          display: true,
          labelString: 'Quantité'
        },
        gridLines: {
          display: true,
          color: 'rgba(0, 0, 0, 0.1)'
        },
        ticks: {
          beginAtZero: true,
          fontColor: 'rgba(0, 0, 0, 0.8)'
        }
      }]
    },
    tooltips: {
      enabled: true,
      mode: 'index',
      intersect: false
    },
    animation: {
      duration: 2500,
      easing: 'linear'
    },
    elements: {
      line: {
        tension: 0.4,
        borderColor: 'rgba(0, 0, 0, 0.5)',
        borderWidth: 2
      },
      point: {
        radius: 5,
        backgroundColor: 'white',
        borderColor: 'rgba(0, 0, 0, 0.5)',
        borderWidth: 2,
        hoverRadius: 8,
      }

    }



  };

  lineChartDataReady = true;
  public lineChartLabels: Label[] = [];
  public lineChartData: SingleDataSet = [];
  public lineChartType: ChartType = 'line';
  public lineChartColor: Color[] = [
    { backgroundColor: ['#ffbf3a', '#4e3dc8', '#CD09F8', '#33B67C'] },
  ];

  constructor(private dashboardService: DashboardService, private datePipe: DatePipe) { }
  @Input() datedepart!: string;
  @Input() dateaarret!: string;
  source: string[] = [];
  proddes: string[] = [];
  ngOnInit(): void {

    console.log(this.dateaarret);
    console.log(this.datedepart);




    let responseprod = this.dashboardService.proddes();
    responseprod.subscribe((data) => this.proddes = data);
    this.filteredprods = this.myControlprod.valueChanges.pipe(
      startWith(''),
      map(value => this._filterr(value || '')),
    );

    let responsesource = this.dashboardService.source();
    responsesource.subscribe((data) => this.source = data);
    this.filteredsource = this.myControlsource.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );
  }

  private _filterr(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.proddes.filter(option => option.toLowerCase().includes(filterValue));
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.source.filter(option => option.toLowerCase().includes(filterValue));
  }

  selectChangeHandlersource(prod: any, source: any) {


    this.lineChartData = [];
    this.lineChartLabels = [];
    let response = this.dashboardService.distributionTotal(prod, source, this.datedepart, this.dateaarret).subscribe(
      (d) => {

        d.forEach((typeCountbar: Model1) => {
          this.lineChartData.push(typeCountbar.count);

          this.lineChartLabels.push(typeCountbar.libelle);
        });
      },
      (error) => {
        console.error(error);
      }
    );


  }
}
