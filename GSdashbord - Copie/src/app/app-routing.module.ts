import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProduitParRegionComponent } from './Modules/produit-par-region/produit-par-region.component';
import { DashbordchartsComponent } from './Modules/dashbordcharts/dashbordcharts.component';
import { DistrProduitComponent } from './Modules/distr-produit/distr-produit.component';
const routes: Routes = [{
  path: 'dash',
  component: DashbordchartsComponent
},
{
  path: 'dist',
  component: DistrProduitComponent
},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
