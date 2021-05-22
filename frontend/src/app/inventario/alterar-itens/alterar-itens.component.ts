import { ItemService } from 'src/app/services/item.service';
import { Item } from '../../shared/models/item.model';
import { finalize } from 'rxjs/operators';
import { NgBlockUI, BlockUI } from 'ng-block-ui';

import { FormsModule } from '@angular/forms';
import { PageNotificationService } from '@nuvem/primeng-components';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-alterar-itens',
  templateUrl: './alterar-itens.component.html',
  styleUrls: ['./alterar-itens.component.css']
})

export class AlterarItensComponent implements OnInit {

  displayBasic: boolean = false ; 
  item: Item; 
  itemOriginal: Item;
  @Output() atualiozou = new EventEmitter();
  @BlockUI() blockUI: NgBlockUI;
  private _mensagemBlockUi: String = 'Carregando...';

  private form: FormGroup;
  private imagem: any;
  private usuarioLogado: any;

  constructor(
    private itensServices: ItemService,
    private formBuilder: FormBuilder,
    private notification: PageNotificationService,
  ) { }

  ngOnInit(): void {
    this.iniciarForm();
  }

  iniciarForm(){
    this.form = this.formBuilder.group({
      id: [null],
	    descricao: [null, [Validators.required]],
	    disponibilidade: [null, [Validators.required]],
	    nome: [null, [Validators.required]],
      imagem: [null, [Validators.required]],
      usuarioId: [null, [Validators.required]],
      categoriaId: [null, [Validators.required]]
    });
  }

  uploadImagem(event){
    let fileReader = new FileReader();
    let file = event.currentFiles[0];
    
    fileReader.onloadend = () => {
      this.imagem = fileReader.result;
      let blob = this.imagem.split(",");
      this.form.patchValue({ imagem: blob[1] });
    }
    fileReader.readAsDataURL(file);
  }

  montarImagem(item: Item){
    this.itemOriginal = item;
    let formato = "data:image/jpg;base64,";
    let img = formato.concat(item.imagem);
    item.imagem = img;
    return item;
  }

  desmontarImagem(item: Item){
    let blob = item.imagem.split(",");
    this.form.patchValue({ imagem: blob[1] })
  }

  alterar(){  
    console.log(this.form.value);
    this.usuarioLogado = JSON.parse(localStorage.getItem("usuario"));
    this.form.value.usuarioId = this.usuarioLogado.id;
    this.blockUI.start(this._mensagemBlockUi);
    this.itensServices.alterar(this.form.value).pipe(
      finalize(()=>{
        this.blockUI.stop();
        this.fecharModal();
      })
    ).subscribe(
      () => {
        this.notification.addSuccessMessage("Item alterado com sucesso");
      },
      ()=>{
        this.notification.addErrorMessage("Erro ao alterar usuario");
      }
    );
    this.atualiozou.emit("");
  }

  fecharModal(){
    this.displayBasic = false;
  }

  abrir(item: Item){
    this.desmontarImagem(item);
    this.displayBasic = true;
    this.form.patchValue(item);
  }


  load() {
    //Session storage salva os dados como string
    (sessionStorage.refresh == 'true' || !sessionStorage.refresh) && location.reload();
    sessionStorage.refresh = false;
  }
}
