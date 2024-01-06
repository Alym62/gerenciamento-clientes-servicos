import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CLIENTES_ROUTE } from './clientes.routes';

@NgModule({
  imports: [RouterModule.forChild(CLIENTES_ROUTE)],
  exports: [RouterModule]
})
export class ClientesRoutingModule { }
