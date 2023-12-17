import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientesRoutingModule } from './clientes-routing.module';
import { ClienteFormComponent } from './cliente-form/cliente-form.component';
import { RouterModule } from '@angular/router';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatCardModule} from '@angular/material/card';
import {MatNativeDateModule} from '@angular/material/core';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { FormsModule } from '@angular/forms'
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {NgxMaskDirective, provideNgxMask} from 'ngx-mask'
import {MatSnackBarModule} from '@angular/material/snack-bar';
import { ClientesListaComponent } from './clientes-lista/clientes-lista.component';
import { MatTableModule } from '@angular/material/table';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatPaginatorModule} from '@angular/material/paginator';

@NgModule({
  declarations: [
    ClienteFormComponent,
    ClientesListaComponent,
  ],
  imports: [
    CommonModule,
    ClientesRoutingModule,
    RouterModule,
    MatInputModule,
    MatFormFieldModule,
    MatCardModule,
    MatNativeDateModule,
    MatDatepickerModule,
    FormsModule,
    MatButtonModule,
    MatIconModule,
    NgxMaskDirective,
    MatSnackBarModule,
    MatTableModule,
    MatTooltipModule,
    MatPaginatorModule
  ],
  providers: [provideNgxMask({})],
  exports: [ClienteFormComponent, ClientesListaComponent]
})
export class ClientesModule { }
