import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { QteminMax } from 'src/app/interfaces/qtemin-max';
import { DashboardService } from '../dashboard.service';

@Component({
  selector: 'app-dashbordcharts',
  templateUrl: './dashbordcharts.component.html',
  styleUrls: ['./dashbordcharts.component.css']
})
export class DashbordchartsComponent implements OnInit {
  title = 'angular-material-dialog-app';

  @ViewChild('dialogRef')
  dialogRef!: TemplateRef<any>;
  listqtemax: Array<QteminMax> = [];
  listqtemin: Array<QteminMax> = [];
  constructor(public dialog: MatDialog, private dashboardService: DashboardService) { }
  ngOnInit(): void {
    let response = this.dashboardService.qtemax().subscribe(
      (d) => {
        // console.log(d);
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
  openTempDialogMax() {

    const myTempDialog = this.dialog.open(this.dialogRef, { data: this.listqtemax });
    myTempDialog.afterClosed().subscribe((res) => {

      // Data back from dialog
      console.log({ res });
    });
  }
  openTempDialogMin() {

    const myTempDialog = this.dialog.open(this.dialogRef, { data: this.listqtemin });
    myTempDialog.afterClosed().subscribe((res) => {

      // Data back from dialog
      console.log({ res });
    });
  }
  displayedColumns = ['Quantité', 'Produit', 'Boutique'];

}


