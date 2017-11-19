import {Component} from '@angular/core';
import {NgbCarouselConfig} from '@ng-bootstrap/ng-bootstrap';

/**
 * @title Basic grid-list
 */
@Component({
  selector: 'app-carousel',
  styleUrls: ['carousel.css'],
  templateUrl: 'carousel.html',
  providers: [NgbCarouselConfig] // add NgbCarouselConfig to the component providers
})
export class Carousel {
  constructor(config: NgbCarouselConfig) {
    // customize default values of carousels used by this component tree
    config.interval = 5000;
    config.wrap = false;
    config.keyboard = false;
  }
 }