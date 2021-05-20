import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AppFooterComponent } from './../components/footer/app.footer.component';
import { AppTopbarComponent } from './../components/topbar/app.topbar.component';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';
import { BreadcrumbModule, MenuModule } from '@nuvem/primeng-components';
import { SharedModule } from './../shared/shared.module';
import { BlockUIModule } from 'ng-block-ui';
import { VersionTagModule } from '@nuvem/angular-base';

@NgModule({
  declarations: [
    AdminComponent,
    AppTopbarComponent,
    AppFooterComponent,
  ],
  imports: [
    BlockUIModule.forRoot({
      message: "Carregando..."
    }),
    CommonModule,
    AdminRoutingModule,
    SharedModule,
    BreadcrumbModule,
    MenuModule,
    VersionTagModule,
  ]
})
export class AdminModule { }
