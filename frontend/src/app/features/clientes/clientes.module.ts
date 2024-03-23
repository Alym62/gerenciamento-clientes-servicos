import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientesRoutingModule } from './clientes-routing.module';
import { ClienteFormComponent } from './cliente-form/cliente-form.component';
import { RouterModule } from '@angular/router';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { MAT_DATE_FORMATS, MAT_DATE_LOCALE, MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { FormsModule } from '@angular/forms'
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { NgxMaskDirective, provideNgxMask } from 'ngx-mask'
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { ClientesListaComponent } from 'src/app/features/clientes/clientes-lista/clientes-lista.component';
import { MatTableModule } from '@angular/material/table';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatPaginatorModule } from '@angular/material/paginator';
import { CpfPipe } from 'src/app/core/pipes/cpf/cpf.pipe';

const FORMATO_DATA = {
  parse: {
    dateInput: 'DD/MM/YYYY',
  },
  display: {
    dateInput: 'DD/MM/YYYY',
    monthYearLabel: 'MMM YYYY',
    dateA11yLabel: 'LL',
    monthYearA11yLabel: 'MMMM YYYY',
  },
};

@NgModule({
  declarations: [
    ClienteFormComponent,
    ClientesListaComponent,
    CpfPipe
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
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'pt-BR' },
    { provide: MAT_DATE_FORMATS, useValue: FORMATO_DATA },
    provideNgxMask()
  ],
  exports: [ClienteFormComponent, ClientesListaComponent]
})
export class ClientesModule { }
