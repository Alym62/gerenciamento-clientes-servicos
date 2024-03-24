import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxMaskDirective } from 'ngx-mask';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientesService } from 'src/app/core/services/clientes.service';
import { ClientesModule } from 'src/app/features/clientes/clientes.module';
import { ServicoPrestadoModule } from 'src/app/features/servico-prestado/servico-prestado.module';
import { TemplateModule } from 'src/app/core/components/template/template.module';
import { ServicoPrestadoService } from 'src/app/core/services/servico-prestado.service';
import { LoginComponent } from 'src/app/shared/login/login.component';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { LayoutComponent } from 'src/app/shared/layout/layout.component';
import { AuthService } from 'src/app/core/services/auth-service.service';
import { HttpInterceptorInterceptor } from 'src/app/core/interceptors/http-interceptor.interceptor';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatRadioModule } from '@angular/material/radio';
import { FileService } from './core/services/file.service';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LayoutComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    TemplateModule,
    ClientesModule,
    HttpClientModule,
    NgxMaskDirective,
    ServicoPrestadoModule,
    FormsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
    MatIconModule,
    MatRadioModule
  ],
  providers: [ClientesService, ServicoPrestadoService, AuthService, FileService, {
    provide: HTTP_INTERCEPTORS,
    useClass: HttpInterceptorInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
