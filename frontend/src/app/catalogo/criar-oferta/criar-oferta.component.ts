import { Component, OnInit } from '@angular/core';
import { PageNotificationService } from '@nuvem/primeng-components';

import { finalize } from 'rxjs/operators';
import { BlockUI, NgBlockUI } from 'ng-block-ui';

import { OfertaService } from '../../services/oferta.service';
import { ItemService } from 'src/app/services/item.service';
import { Oferta } from '../../shared/models/oferta.model';
import { Usuario } from '../../shared/models/usuario.model';
import { Item } from '../../shared/models/item.model';

@Component({
  selector: 'app-criar-oferta',
  templateUrl: './criar-oferta.component.html',
  styleUrls: ['./criar-oferta.component.css']
})
export class CriarOfertaComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  private _mensagemBlockUi: String = 'Carregando...';

  displayOferta: boolean = false;
  itemSource: Item[];
  itemTarget: Item[];
  novaOferta: Oferta = new Oferta;
  usuarioLogado: Usuario;

  constructor(
    private itemService: ItemService,
    private ofertaService: OfertaService,
    private notification: PageNotificationService
    
  ) { }

  ngOnInit(): void {
  }

  montarImagens(itens: Item[]){
    itens.forEach(element => {
      let formatoImagem = "data:image/jpg;base64,";
      let imagem = formatoImagem.concat(element.imagem);
      element.imagem = imagem;
    });
    return itens;
  }

  iniciarOferta(itemDesejadoId){
    this.usuarioLogado = JSON.parse(localStorage.getItem("usuario"));
    this.novaOferta.itemId = itemDesejadoId;
    this.novaOferta.usuarioOfertanteId = this.usuarioLogado.id
  }

  iniciarListasItensOfertados(){
    this.blockUI.start(this._mensagemBlockUi);
    this.itemService.listarPorDono(this.usuarioLogado.id).pipe(
      finalize(() => {
        this.displayOferta = true;
        this.blockUI.stop();
      })
    ).subscribe(
      (itens) => {
        this.itemSource = itens;
        this.itemSource = this.montarImagens(this.itemSource);
        this.itemTarget = [];
      }
    );
  }

  showOfertaDialog(id) {
    this.iniciarOferta(id);
    this.iniciarListasItensOfertados();
  }

  salvarOferta(){
    this.blockUI.start(this._mensagemBlockUi);

    let itensOfertadosId: number[] = [];

    this.itemTarget.map(element => {
      itensOfertadosId.push(element.id);
    });

    this.novaOferta.itensOfertados = itensOfertadosId;
    
    this.ofertaService.salvar(this.novaOferta).pipe(
      finalize(() => {
        this.displayOferta = false;
        this.blockUI.stop();
      })
    ).subscribe(
      () => { this.notification.addSuccessMessage("Oferta realizada com sucesso"); },
      () => { this.notification.addErrorMessage("Erro ao realizar oferta"); }
    );
  }
}
