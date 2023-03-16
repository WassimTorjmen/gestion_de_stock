import { Component, OnInit, ViewChild } from '@angular/core';
import { DashboardService } from '../dashboard.service';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { Color, Label, SingleDataSet } from 'ng2-charts';
import { DatePipe } from '@angular/common';
import { FormControl } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';
import { Model1 } from 'src/app/interfaces/model-1'
@Component({
  selector: 'app-topboutique-vendu',
  templateUrl: './topboutique-vendu.component.html',
  styleUrls: ['./topboutique-vendu.component.css']
})
export class TopboutiqueVenduComponent implements OnInit {
  myControl = new FormControl('');
  filteredOptions: Observable<string[]> | undefined;
  public doughnut2ChartOptions: ChartOptions = {
    responsive: true,
    legend: {
      display: true,
    },
    cutoutPercentage: 80,
  };
  public doughnut2ChartLabels: Label[] = [];
  public doughnut2ChartData: SingleDataSet = [];
  public doughnut2ChartType: ChartType = 'doughnut';
  public doughnut2ChartColor: Color[] = [
    { backgroundColor: ['#FF0000', '#ffbf3a', '#4e3dc8', '#CD09F8', '#33B67C'] },
  ];


  public typeData: Array<Model1> = [];
  prod: string[] = [];
  constructor(private dashboardService: DashboardService, private datePipe: DatePipe) { }

  ngOnInit(): void {

    let responsestock = this.dashboardService.vendu("smartphone Type").subscribe(
      (d) => {
        console.log(d);
        d.forEach((typeCount: Model1) => {
          this.doughnut2ChartData.push(typeCount.count);
          this.doughnut2ChartLabels.push(typeCount.libelle);
        });
      },
      (error) => {
        console.error(error);
      }
    );

    let response = this.dashboardService.proddes();
    response.subscribe((data) => this.prod = data);
    this.filteredOptions = this.myControl.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );

  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.prod.filter(option => option.toLowerCase().includes(filterValue));
  }
  onSelFunc(option: any) {
    this.doughnut2ChartData = [];
    this.doughnut2ChartLabels = [];
    let response = this.dashboardService.vendu(option).subscribe(
      (d) => {
        // console.log(d);
        d.forEach((typeCount: Model1) => {
          this.doughnut2ChartData.push(typeCount.count);
          this.doughnut2ChartLabels.push(typeCount.libelle);
        });
      },
      (error) => {
        console.error(error);
      }
    );
  }
}
