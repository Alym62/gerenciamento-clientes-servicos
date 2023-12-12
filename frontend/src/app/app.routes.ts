import { Routes } from "@angular/router";
import { HomeComponent } from "./home/home.component";
import { NavbarComponent } from "./template/navbar/navbar.component";

export const APP_ROUTES: Routes = [
    {
        path: 'home',
        component: HomeComponent
    }
]