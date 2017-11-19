import {Component, Input, ViewChild} from '@angular/core';
import { ModalDirective } from 'ngx-bootstrap/modal';

@Component({
    selector: 'common-modal',
    styleUrls: ['modal.css'],
    templateUrl: 'modalhtml.html',
})
export class MyModal {
    @ViewChild('childModal') public childModal: ModalDirective;
    @ViewChild('childModal2') public childModal2: ModalDirective;
    @ViewChild('childModal3') public childModal3: ModalDirective;
    @Input() title: string;
   constructor() {
   }

   show(): void {
     this.childModal.show();
   }

   show2(): void {
    this.childModal2.show();
   }

   show3(): void {
    this.childModal3.show();
  }

   hide() {
     this.childModal.hide();
   }
}
