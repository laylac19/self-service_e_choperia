import {Component, Input, OnInit} from '@angular/core';


@Component({
  selector: 'app-cliente-caixa',
  templateUrl: './cliente-caixa-component.html',
  styleUrls: ['./cliente-caixa-component.scss']
})
export class ClienteCaixaComponent implements OnInit{
  @Input('nome') nome: string;

  ngOnInit(): void {
  }

  constructor() {
  }
}
