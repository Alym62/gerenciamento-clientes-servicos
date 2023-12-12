import { Route, Routes } from "@angular/router";
import { ClienteFormComponent } from "./cliente-form/cliente-form.component";

export const CLIENTES_ROUTE: Routes = [
    {
        path: 'cliente-form',
        component: ClienteFormComponent
    }
]