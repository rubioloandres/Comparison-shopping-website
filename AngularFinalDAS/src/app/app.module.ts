import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { MatMenuModule } from '@angular/material/menu';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { ReactiveFormsModule } from '@angular/forms';
import { NgMatSearchBarModule } from 'ng-mat-search-bar';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatCardModule } from '@angular/material/card';
import { SearchbarComponent } from './components/searchbar/searchbar.component';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { CatalogueComponent } from './components/catalogue/catalogue.component';
import { CartComponent } from './components/cart/cart.component';
import { MatTableModule } from '@angular/material/table';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { PricetableComponent  } from './components/pricetable/pricetable.component';
import { DialogInfoSucursalComponent } from './components/info/info.component';
import { MatDividerModule } from '@angular/material/divider';
import { MatSelectModule } from '@angular/material/select';
import { CategoriasService } from './services/indec/categorias.service';
import { ProductosService } from './services/indec/productos.service';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatProgressSpinnerModule } from '@angular/material';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { CarouselModule } from 'ngx-owl-carousel-o';
import { HttpClientModule } from '@angular/common/http';
import { CategoriesComponent } from './components/categories/categories.component';
import { DataSharingService } from './services/datasharing.service';
import { SearchfilterComponent } from './components/searchfilter/searchfilter.component';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { LOCALE_ID, Inject } from '@angular/core';
import { registerLocaleData } from '@angular/common';
import localeEs from '@angular/common/locales/es-419';
import localeEn from '@angular/common/locales/en';
import { ProvinciasService } from './services/indec/provincias.service';
import { LocalidadesService } from './services/indec/localidades.service';
import { MapComponent } from './components/map/map.component';
import { SucursalesService } from './services/indec/sucursales.service';
import { CadenasService } from './services/indec/cadenas.service';
import { MatDialogModule } from '@angular/material/dialog';
import { ErrorManager } from './services/handleError.service';
import { DialogLocationComponent } from './components/location/location.component';
import { DialogPlatoComponent } from './components/plato/plato.component';
import { SucursalesComponent } from './components/sucursales/sucursales.component';
import { HealthComponent } from './components/health/health.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatExpansionModule } from '@angular/material/expansion';
import { MenuService } from './services/indec/menu.service';
import { PricetableplateComponent } from './components/pricetableplate/pricetableplate.component';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatRadioModule } from '@angular/material/radio';
import { FlexLayoutModule } from '@angular/flex-layout';
import { FooterComponent } from './components/footer/footer.component';
import { MatListModule } from '@angular/material/list';


@NgModule({
  declarations: [
    AppComponent,
    SearchbarComponent,
    CatalogueComponent,
    CartComponent,
    PricetableComponent,
    CategoriesComponent,
    SearchfilterComponent,
    MapComponent,
    DialogInfoSucursalComponent,
    DialogLocationComponent,
    SucursalesComponent,
    HealthComponent,
    PricetableplateComponent,
    FooterComponent,
    DialogPlatoComponent
  ],
  imports: [
    BrowserModule,
    MatMenuModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    NgMatSearchBarModule,
    MatTabsModule,
    MatToolbarModule,
    MatGridListModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatSelectModule,
    MatTableModule,
    MatButtonToggleModule,
    HttpClientModule,
    RouterModule,
    AppRoutingModule,
    MatProgressSpinnerModule,
    MatDividerModule,
    MatFormFieldModule,
    MatSelectModule,
    MatSlideToggleModule,
    CarouselModule,
    MatAutocompleteModule,
    MatDialogModule,
    MatDatepickerModule,
    MatExpansionModule,
    MatSidenavModule,
    MatRadioModule,
    FlexLayoutModule,
    MatListModule
  ],
  providers: [
    CategoriasService,
    ProductosService,
    CatalogueComponent,
    DataSharingService,
    ProvinciasService,
    LocalidadesService,
    SucursalesService,
    CadenasService,
    MenuService,
    CartComponent,
    ErrorManager
  ],
  entryComponents: [
    DialogInfoSucursalComponent,
    DialogLocationComponent,
    MapComponent,
    DialogPlatoComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(@Inject(LOCALE_ID) locale: string) {
    registerLocaleData(locale === 'es' ? localeEs : localeEn);
   }
}
