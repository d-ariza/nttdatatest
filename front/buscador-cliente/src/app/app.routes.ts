import { Routes } from '@angular/router';
import { BusquedaClienteComponent } from './pages/busqueda-cliente/busqueda-cliente';
import { ResumenClienteComponent } from './pages/resumen-cliente/resumen-cliente';

export const routes: Routes = [
  { path: '', component: BusquedaClienteComponent },
  { path: 'resumen', component: ResumenClienteComponent }
];
