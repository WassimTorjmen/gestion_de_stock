import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label, SingleDataSet } from 'ng2-charts';
import { DatePipe } from '@angular/common';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { Model1 } from 'src/app/interfaces/model-1';

@Component({
  selector: 'app-vent-produit',
  templateUrl: './vent-produit.component.html',
  styleUrls: ['./vent-produit.component.css']
})
export class VentProduitComponent implements OnInit {
  myControlsource = new FormControl('');
  myControlprod = new FormControl('');
  filteredprods: Observable<string[]> | undefined;
  filteredsource: Observable<string[]> | undefined;
  public lineChart2Options: ChartOptions = {
    scales: {

      xAxes: [{

        scaleLabel: {
          display: true,
          labelString: 'Mois',
        }
      }],
      yAxes: [{

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


  };
  lineChart2DataReady = true;
  public lineChart2Labels: Label[] = [];
  public lineChart2Data: SingleDataSet = [];
  public lineChart2Type: ChartType = 'line';
  public lineChart2Color: Color[] = [
    { backgroundColor: ['#ffbf3a', '#4e3dc8'] },
  ];


  constructor(private dashboardService: DashboardService, private datePipe: DatePipe) { }
  @Input() datedepart!: string;
  @Input() dateaarret!: string;
  source: string[] = [];
  proddes: string[] = [];
  ngOnInit(): void {
    console.log(this.dateaarret);
    console.log(this.datedepart);


    this.lineChart2Data = [];
    this.lineChart2Labels = [];
    let response = this.dashboardService.ventpardate("PORTABLE OPPO A55 (4+128G)", "BEN", this.datedepart, this.dateaarret).subscribe((d) => {

      d.forEach((typeCountbar: Model1) => {
        this.lineChart2Data.push(typeCountbar.count);

        this.lineChart2Labels.push(typeCountbar.libelle);
      });
    },
      (error) => {
        console.error(error);
      }
    );


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


    this.lineChart2Data = [];
    this.lineChart2Labels = [];
    let response = this.dashboardService.ventpardate(prod, source, this.datedepart, this.dateaarret).subscribe(
      (d) => {

        d.forEach((typeCountbar: Model1) => {
          this.lineChart2Data.push(typeCountbar.count);

          this.lineChart2Labels.push(typeCountbar.libelle);
        });
      },
      (error) => {
        console.error(error);
      }
    );

  }


}
