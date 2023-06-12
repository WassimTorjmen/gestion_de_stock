import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/interfaces/users';
import { AuthService } from '../Auth_Security/_services/auth.service';
import { MatDialog } from '@angular/material/dialog';
import { EditUserDialogComponent } from './edit-user-dialog/edit-user-dialog.component';
import { ConfirmDeleteComponent } from './confirm-delete/confirm-delete.component';

@Component({
  selector: 'app-gestion-users',
  templateUrl: './gestion-users.component.html',
  styleUrls: ['./gestion-users.component.css']
})
export class GestionUsersComponent implements OnInit {
  displayedColumns: string[] = [ 'username', 'email', 'tel', 'region', 'actions'];
  dataSource: User[] = [];

  constructor(private authService: AuthService, public dialog: MatDialog) { }

  ngOnInit(): void {
    this.authService.getAllUsers().subscribe((users) => {
      this.dataSource = users;
    })
  }
  
  openDialog(user: User): void {
    const dialogRef = this.dialog.open(EditUserDialogComponent, {
      width: '300px',
      data: { ...user } // Copy of the user object
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.authService.updateUser(result).subscribe(updatedUser => {
          const index = this.dataSource.findIndex(u => u.id === updatedUser.id);
          this.dataSource[index] = updatedUser;
          this.refreshTableData(); // Refresh the table data after saving
        });
      }
    });
  }
  refreshTableData(): void {
    this.authService.getAllUsers().subscribe((users) => {
      this.dataSource = users;
    });
  }
  deleteUser(user: User): void {
    const dialogRef = this.dialog.open(ConfirmDeleteComponent, {
      width: '350px',
      data: 'Vous etes sur?'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.authService.deleteUser(user.id).subscribe(() => {
          this.refreshTableData();
        });
      }
    });
  }



}
