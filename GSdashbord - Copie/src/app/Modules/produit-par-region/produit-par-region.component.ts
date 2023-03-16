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
  selector: 'app-produit-par-region',
  templateUrl: './produit-par-region.component.html',
  styleUrls: ['./produit-par-region.component.css']
})
export class ProduitParRegionComponent implements OnInit {

  myControl = new FormControl('');
  filteredOptions: Observable<string[]> | undefined;

  public doughnutChartOptions: ChartOptions = {
    responsive: true,
    legend: {
      display: true,
    },
    cutoutPercentage: 80,
  };
  public doughnutChartLabels: Label[] = [];
  public doughnutChartData: SingleDataSet = [];
  public doughnutChartType: ChartType = 'doughnut';
  public doughnutChartColor: Color[] = [
    { backgroundColor: ['#FF0000', '#ffbf3a', '#4e3dc8', '#CD09F8', '#33B67C', '#33FFDA', '#33D4FF', '#FF33A5', '#042383', '#00FF2E'] },
  ];


  public typeData: Array<Model1> = [];
  adresse: string[] = [];

  constructor(private dashboardService: DashboardService, private datePipe: DatePipe) { }

  ngOnInit() {
    let responses = this.dashboardService.pieChart("TUNIS").subscribe(
      (d) => {
        // console.log(d);
        d.forEach((typeCount: Model1) => {

          this.doughnutChartData.push(typeCount.count);
          this.doughnutChartLabels.push(typeCount.libelle);
          console.log(this.doughnutChartData);
        });
      },
      (error) => {
        console.error(error);
      }
    );
    let response = this.dashboardService.adresse();
    response.subscribe((data) => this.adresse = data);
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );



  }
  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.adresse.filter(option => option.toLowerCase().includes(filterValue));
  }

  onSelFunc(option: any) {
    this.doughnutChartData = [];
    this.doughnutChartLabels = [];
    let response = this.dashboardService.pieChart(option).subscribe(
      (d) => {
        // console.log(d);
        d.forEach((typeCount: Model1) => {

          this.doughnutChartData.push(typeCount.count);
          this.doughnutChartLabels.push(typeCount.libelle);
          console.log(this.doughnutChartData);
        });
      },
      (error) => {
        console.error(error);
      }
    );
  }

}
