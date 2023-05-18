import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { NgApexchartsModule } from "ng-apexcharts";
import { AutocompleteLibModule } from 'angular-ng-autocomplete';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatDividerModule } from '@angular/material/divider';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatCardModule } from '@angular/material/card';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatTableModule } from '@angular/material/table';
import { DashboardService } from 'src/app/Modules/dashboard.service';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ChartsModule, } from "ng2-charts";
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProduitParRegionComponent } from './Modules/produit-par-region/produit-par-region.component';
import { DashbordchartsComponent } from './Modules/dashbordcharts/dashbordcharts.component';
import { HeaderComponent } from './Modules/edges/header/header.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { FooterComponent } from './Modules/edges/footer/footer.component';
import { EtatProduitComponent } from './Modules/etat-produit/etat-produit.component';
import { TopboutiqueEnstockComponent } from './Modules/topboutique-enstock/topboutique-enstock.component';
import { TopboutiqueVenduComponent } from './Modules/topboutique-vendu/topboutique-vendu.component';
import { DistrProduitComponent } from './Modules/distr-produit/distr-produit.component';
import { VentProduitComponent } from './Modules/vent-produit/vent-produit.component';
import { MatDialogModule } from '@angular/material/dialog';
import { VentePardistComponent } from './Modules/vente-pardist/vente-pardist.component';
import { LoginComponent } from './Modules/Auth_Security/login/login.component';
import { AdminBoardComponent } from './Modules/Auth_Security/admin-board/admin-board.component';
import { RegisterComponent } from './Modules/Auth_Security/register/register.component';
import { InterceptorService } from './Modules/Auth_Security/_helpers/interceptor.service';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { GestionUsersComponent } from './Modules/gestion-users/gestion-users.component';
import { EditUserDialogComponent } from './Modules/gestion-users/edit-user-dialog/edit-user-dialog.component';
import { ConfirmDeleteComponent } from './Modules/gestion-users/confirm-delete/confirm-delete.component';
import { AgentBoardComponent } from './Modules/Auth_Security/agent-board/agent-board.component';
const modules = [

  MatFormFieldModule,
  MatAutocompleteModule,
  MatInputModule,
  MatIconModule,
  MatToolbarModule,
  FormsModule,
  ReactiveFormsModule,
  MatDialogModule
];
@NgModule({
  declarations: [
    AppComponent,
    ProduitParRegionComponent,
    DashbordchartsComponent,
    HeaderComponent,
    FooterComponent,
    EtatProduitComponent,
    TopboutiqueEnstockComponent,
    TopboutiqueVenduComponent,
    DistrProduitComponent,
    VentProduitComponent,
    VentePardistComponent,
    LoginComponent,
    AdminBoardComponent,
    RegisterComponent,
    GestionUsersComponent,
    EditUserDialogComponent,
    ConfirmDeleteComponent,
    AdminBoardComponent,
    AgentBoardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    modules,
    NgApexchartsModule,
    CommonModule,
    RouterModule,
    MatSidenavModule,
    MatDividerModule,
    FlexLayoutModule,
    MatCardModule,
    MatPaginatorModule,
    MatTableModule,
    HttpClientModule,
    ChartsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatDialogModule


  ],
  providers:
    [
      DashboardService,
      DatePipe,
      {
        provide: HTTP_INTERCEPTORS,
        useClass: InterceptorService,
        multi: true,
      }
    ],
  bootstrap: [AppComponent],
  exports: [modules,
    ChartsModule,
    ProduitParRegionComponent]

})
export class AppModule { }
