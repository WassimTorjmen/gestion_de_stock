import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProduitParRegionComponent } from './Modules/produit-par-region/produit-par-region.component';
import { DashbordchartsComponent } from './Modules/dashbordcharts/dashbordcharts.component';
import { DistrProduitComponent } from './Modules/distr-produit/distr-produit.component';
import { LoginComponent } from './Modules/Auth_Security/login/login.component';
import { AdminBoardComponent } from './Modules/Auth_Security/admin-board/admin-board.component';
import { RegisterComponent } from './Modules/Auth_Security/register/register.component';
const routes: Routes = [{
  path: 'dash',
  component: DashbordchartsComponent
},
{
  path: 'dist',
  component: DistrProduitComponent
},
{
  path: '',
  component: LoginComponent
},
{
  path: 'login',
  component: LoginComponent
},
{
  path: 'register',
  component: RegisterComponent
},


{
  path: 'admin',
  component: AdminBoardComponent
},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
