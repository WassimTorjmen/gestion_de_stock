import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { DashboardService } from '../../dashboard.service';
import { MatDialog } from '@angular/material/dialog';
import { DatePipe } from '@angular/common';
import { QteminMax } from 'src/app/interfaces/qtemin-max';
import { MatDatepickerInputEvent } from '@angular/material/datepicker';

@Component({
  selector: 'app-agent-board',
  templateUrl: './agent-board.component.html',
  styleUrls: ['./agent-board.component.css']
})
export class AgentBoardComponent implements OnInit {

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
