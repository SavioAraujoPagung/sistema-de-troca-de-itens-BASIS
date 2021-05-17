import { finalize } from 'rxjs/operators';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BlockUI, NgBlockUI } from 'ng-block-ui';

import { LoginService } from './../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  @BlockUI() blockUI: NgBlockUI;
  private _mensagemBlockUi: String = 'Carregando...';

  form: FormGroup;
  displayModal: boolean = false;

  constructor(
    private fb: FormBuilder,
    private loginService: LoginService,
    private router: Router
    ) { }

  iniciarForm(){
    this.form = this.fb.group({
      email: [null, [Validators.required, Validators.email]],
      token: [null, [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.iniciarForm();
  }

  login(){
    this.blockUI.start(this._mensagemBlockUi);
    this.loginService.login(this.form.value).pipe(
      finalize(() => {
        this.blockUI.stop();
      })
    ).subscribe(
      (data) => {
        localStorage.setItem('token', this.form.get('token').value);
        localStorage.setItem('usuario', JSON.stringify(data));
        this.router.navigate(['admin']);
      }
    )
  }

}
