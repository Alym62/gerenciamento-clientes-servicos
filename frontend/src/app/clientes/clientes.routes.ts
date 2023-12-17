import { Route, Routes } from "@angular/router";
import { ClienteFormComponent } from "./cliente-form/cliente-form.component";
import { ClientesListaComponent } from "./clientes-lista/clientes-lista.component";

export const CLIENTES_ROUTE: Routes = [
    {
        path: 'cliente-form',
        component: ClienteFormComponent
    },
    {
        path: 'cliente-lista',
        component: ClientesListaComponent
    }
]