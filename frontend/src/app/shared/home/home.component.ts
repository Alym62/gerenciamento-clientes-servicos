import { Component, OnInit } from '@angular/core';
import { ClientesService } from 'src/app/core/services/clientes.service';
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

  constructor(private clienteService: ClientesService, private servicoService: ServicoPrestadoService) { }

  carregarTotalClientes(): void {
    this.clienteService.listarTotalDeClientesNoSistema().subscribe((total) => this.totalClientes = total);
  }

  carregarMediaMensal(): void {
    this.clienteService.listarMediaMensalDeClientesNoSistema().subscribe((media) => this.mediaMensal = media);
  }

  carregarValorUltimoServico(): void {
    this.servicoService.valorUltimoServico().subscribe((valor) => this.valorUltimoServicoPrestado = valor);
  }

  ngOnInit(): void {
    this.carregarTotalClientes();
    this.carregarMediaMensal();
    this.carregarValorUltimoServico();
  }
}
