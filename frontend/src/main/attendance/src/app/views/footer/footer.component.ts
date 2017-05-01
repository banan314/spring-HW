/**
 * Created by kamil on 01.05.17.
 */
import {Component, OnInit} from '@angular/core';

@Component({
  moduleId: module.id,
  selector: 'footer',
  templateUrl: 'footer.component.html'
})

export class FooterComponent implements OnInit {
  companyName = "PGS Software";

  constructor() {
  }

  ngOnInit() {
  }
}
