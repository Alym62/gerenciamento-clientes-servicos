import { Routes } from "@angular/router";
import { ServicoPrestadoFormComponent } from "./servico-prestado-form/servico-prestado-form.component";
import { ServicoPrestadoListaComponent } from "./servico-prestado-lista/servico-prestado-lista.component";
import { LayoutComponent } from "../layout/layout.component";

export const SERVICO_PRESTADO_ROUTE: Routes = [
    {
        path: 'servico-prestado',
        component: LayoutComponent,
        children: [
            {
                path: 'form',
                component: ServicoPrestadoFormComponent
            },
            {
                path: 'lista',
                component: ServicoPrestadoListaComponent
            },
            {
                path: '',
                redirectTo: '/servico-prestado/lista',
                pathMatch: 'full'
            }
        ]
    },
]