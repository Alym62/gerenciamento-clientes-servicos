import { Routes } from "@angular/router";
import { ServicoPrestadoFormComponent } from "./servico-prestado-form/servico-prestado-form.component";
import { ServicoPrestadoListaComponent } from "./servico-prestado-lista/servico-prestado-lista.component";

export const SERVICO_PRESTADO_ROUTE: Routes = [
    {
        path: 'servico-prestado-form',
        component: ServicoPrestadoFormComponent
    },
    {
        path: 'servico-prestado-lista',
        component: ServicoPrestadoListaComponent
    }
]