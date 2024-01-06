import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { SERVICO_PRESTADO_ROUTE } from './servico-prestado.routes';

@NgModule({
  imports: [RouterModule.forChild(SERVICO_PRESTADO_ROUTE)],
  exports: [RouterModule]
})
export class ServicoPrestadoRoutingModule { }
