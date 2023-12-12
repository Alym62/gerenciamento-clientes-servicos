import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {APP_ROUTES} from './app.routes'

@NgModule({
  imports: [RouterModule.forRoot(APP_ROUTES)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
