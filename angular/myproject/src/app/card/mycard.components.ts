import {Component, ViewChild, NgModule, ViewContainerRef} from '@angular/core'
import { BrowserModule } from '@angular/platform-browser';
import {ModalDirective, ModalModule} from 'ngx-bootstrap/modal';
import {MyModal} from '../modal/modal.component';


@Component({
  selector: 'mycard',
  styleUrls: ['mycard.css'],
  templateUrl: 'mycard.html',
})
export class CardFancyExample {
  @ViewChild('childModal') childModal: MyModal;
  constructor(private viewContainerRef: ViewContainerRef) {
  }
}