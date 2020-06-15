import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CategoriesComponent } from './components/categories/categories.component';
import { CatalogueComponent } from './components/catalogue/catalogue.component';
import { CartComponent } from './components/cart/cart.component';
import { PricetableComponent } from './components/pricetable/pricetable.component';
import { SucursalesComponent } from './components/sucursales/sucursales.component';
import { HealthComponent } from './components/health/health.component';
import { PricetableplateComponent } from './components/pricetableplate/pricetableplate.component';
import { SearchbarComponent } from './components/searchbar/searchbar.component';
// import { CategoriasResolverService, ProductosResolverService } from './services/resolver.service';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    component: SearchbarComponent /*,
    resolve: {
      categorias : CategoriasResolverService,
      productos : ProductosResolverService
     }*/
  },
  {
    path: 'catalogue',
    component: CatalogueComponent,
    pathMatch: 'full'
  },
  {
    path: 'cart',
    component: CartComponent,
    pathMatch: 'full'
  },
  {
    path: 'prices',
    component: PricetableComponent,
    pathMatch: 'full'
  },
  {
    path: 'sucursales',
    component: SucursalesComponent,
    pathMatch: 'full'
  },
  {
    path: 'health',
    component: HealthComponent,
    pathMatch: 'full'
  },
  {
    path: 'pricesplate',
    component: PricetableplateComponent,
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }
