import { Routes } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from "./login/login.component";
import { LayoutComponent } from "./layout/layout.component";
import { AuthGuard } from "./auth/auth.guard";
import { ServicoPrestadoFormComponent } from "./servico-prestado/servico-prestado-form/servico-prestado-form.component";
import { ServicoPrestadoListaComponent } from "./servico-prestado/servico-prestado-lista/servico-prestado-lista.component";
import { ClienteFormComponent } from "./clientes/cliente-form/cliente-form.component";
import { ClientesListaComponent } from "./clientes/clientes-lista/clientes-lista.component";

export const APP_ROUTES: Routes = [
    {
        path: 'login',
        component: LoginComponent
    },
    {
        canActivate: [AuthGuard],
        path: '',
        component: LayoutComponent,
        children: [
            {
                canActivate: [AuthGuard],
                path: 'home',
                component: HomeComponent,
            },
            {
                path: 'servico-prestado',
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
            {
                path: 'clientes',
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
        ],
    }
]