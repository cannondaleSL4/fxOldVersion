import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';

import {NgbModule, NgbCarouselConfig} from '@ng-bootstrap/ng-bootstrap';
import {Carousel} from './carousel/carousel.components';

import {MatCardModule} from '@angular/material';
import {CardFancyExample} from './card/mycard.components';

import { ModalModule, ModalDirective } from 'ngx-bootstrap/modal';
import {MyModal} from './modal/modal.component';

import {TablesQ} from './tablesq/tablesq.component';
import {DataSource} from '@angular/cdk/collections';
import {Observable} from 'rxjs/Observable';

import {MatTableModule} from '@angular/material';
import {CdkTableModule} from '@angular/cdk/table';

@NgModule({
  declarations: [
    AppComponent,
    Carousel,
    CardFancyExample,
    MyModal,
    TablesQ,
  ],
  imports: [
     BrowserModule,
     NgbModule.forRoot(),
     MatCardModule,
     ModalModule.forRoot(),
     MatTableModule,
     CdkTableModule,
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }