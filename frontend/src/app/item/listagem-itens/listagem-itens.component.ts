import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-listagem-itens',
  templateUrl: './listagem-itens.component.html',
  styleUrls: ['./listagem-itens.component.css']
})
export class ListagemItensComponent implements OnInit {

  private displayBasic: boolean = false;
  
  constructor() { }

  ngOnInit(): void {

  }

  alterar(){
    this.displayBasic = true;
  }

  fechar(displayBasicClose: boolean){
    this.displayBasic = displayBasicClose;    
  }
}
