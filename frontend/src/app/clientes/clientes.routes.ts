import { Routes } from '@angular/router';
import { ClienteFormComponent } from './cliente-form/cliente-form.component';
import { ClientesListaComponent } from './clientes-lista/clientes-lista.component';
import { LayoutComponent } from '../layout/layout.component';
import { AuthGuard } from '../auth/auth.guard';

export const CLIENTES_ROUTE: Routes = [
  {
    path: 'clientes',
    component: LayoutComponent,
    children: [
      {
        path: 'form',
        component: ClienteFormComponent,
      },
      {
        path: 'lista',
        component: ClientesListaComponent,
      },
      {
        path: 'form/:id',
        component: ClienteFormComponent,
      },
      {
        path: '',
        redirectTo: '/clientes/lista',
        pathMatch: 'full'
      }
    ],
  }
];
