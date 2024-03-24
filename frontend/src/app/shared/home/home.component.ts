import { Component, OnInit } from '@angular/core';
import { ClientesService } from 'src/app/core/services/clientes.service';
import { FileService } from 'src/app/core/services/file.service';
import { ServicoPrestadoService } from 'src/app/core/services/servico-prestado.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  totalClientes: number = 0;
  mediaMensal: number = 0;
  valorUltimoServicoPrestado: number = 0;
  imagem: any;

  constructor(
    private clienteService: ClientesService, 
    private servicoService: ServicoPrestadoService,
    private fileService: FileService
    ) { }

  carregarTotalClientes(): void {
    this.clienteService.listarTotalDeClientesNoSistema().subscribe((total) => this.totalClientes = total);
  }

  carregarMediaMensal(): void {
    this.clienteService.listarMediaMensalDeClientesNoSistema().subscribe((media) => this.mediaMensal = media);
  }

  carregarValorUltimoServico(): void {
    this.servicoService.valorUltimoServico().subscribe((valor) => this.valorUltimoServicoPrestado = valor);
  }

  carregarImagem(): void {
    this.fileService.getImage('f5c75bd5-9aa3-4baa-ad28-a37f2a5669a7').subscribe(
      (response) => {
        const reader = new FileReader();
        reader.readAsDataURL(response);
        reader.onloadend = () => {
          this.imagem = reader.result;
        };
      }, error => {
        console.log(error);
      }
    );
  }

  ngOnInit(): void {
    this.carregarTotalClientes();
    this.carregarMediaMensal();
    this.carregarValorUltimoServico();
    this.carregarImagem();
  }
}
