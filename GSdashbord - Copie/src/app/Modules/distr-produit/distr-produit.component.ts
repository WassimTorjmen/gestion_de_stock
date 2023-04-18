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


  };
  lineChartDataReady = true;
  public lineChartLabels: Label[] = [];
  public lineChartData: SingleDataSet = [];
  public lineChartType: ChartType = 'line';
  public lineChartColor: Color[] = [
    { backgroundColor: ['#FF0000', '#ffbf3a', '#4e3dc8', '#CD09F8', '#33B67C'] },
  ];

  constructor(private dashboardService: DashboardService, private datePipe: DatePipe) { }
  @Input() datedepart!: string;
  @Input() dateaarret!: string;
  source: string[] = [];
  proddes: string[] = [];
  ngOnInit(): void {
    console.log(this.dateaarret);
    console.log(this.datedepart);



    let response = this.dashboardService.distributionTotal("PORTABLE OPPO A55 (4+128G)", "BEN", this.datedepart, this.dateaarret).subscribe(
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
