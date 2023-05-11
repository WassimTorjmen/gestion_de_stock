import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { QteminMax } from 'src/app/interfaces/qtemin-max';
import { DashboardService } from '../dashboard.service';
import { MatDatepickerInputEvent, MatDatepickerModule } from '@angular/material/datepicker';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-dashbordcharts',
  templateUrl: './dashbordcharts.component.html',
  styleUrls: ['./dashbordcharts.component.css']
})
export class DashbordchartsComponent implements OnInit {
  title = 'angular-material-dialog-app';
  formattedDate: any;
  @ViewChild('dialogRef')
  dialogRef!: TemplateRef<any>;
  listqtemax: Array<QteminMax> = [];
  listqtemin: Array<QteminMax> = [];
  constructor(public dialog: MatDialog, private dashboardService: DashboardService, private datePipe: DatePipe) { }
  ngOnInit(): void {

    let response = this.dashboardService.qtemax().subscribe(
      (d) => {

        d.forEach((typeCount: QteminMax) => {
          this.listqtemax.push(typeCount)
        });
      },
      (error) => {
        console.error(error);
      }
    );


    let responses = this.dashboardService.qtemin().subscribe(
      (d) => {
        // console.log(d);
        d.forEach((typeCount: QteminMax) => {
          this.listqtemin.push(typeCount)
        });
      },
      (error) => {
        console.error(error);
      }
    );
  }
  onDateSelected(dateDep: MatDatepickerInputEvent<Date>): string {
    const selectedDate = dateDep.value;
    this.formattedDate = this.datePipe.transform(selectedDate, 'yyyy-MM-dd');
    console.log(this.formattedDate);
    return this.formattedDate;

  }

  openTempDialogMax() {

    const myTempDialog = this.dialog.open(this.dialogRef, { data: this.listqtemax });
    myTempDialog.afterClosed().subscribe((res) => {

    });
  }
  openTempDialogMin() {

    const myTempDialog = this.dialog.open(this.dialogRef, { data: this.listqtemin });
    myTempDialog.afterClosed().subscribe((res) => {

    });
  }
  displayedColumns = ['Quantité', 'Produit', 'Boutique'];

}


