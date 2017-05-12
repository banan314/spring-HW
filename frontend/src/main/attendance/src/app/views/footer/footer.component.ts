/**
 * Created by kamil on 01.05.17.
 */
import {Component, OnInit} from '@angular/core';

@Component({
  moduleId: module.id,
  selector: 'end-matter',
  templateUrl: 'footer.component.html',
  styleUrls: ['footer.component.css']
})

export class FooterComponent implements OnInit {
  companyName = "PGS Software";

  constructor() {
  }

  ngOnInit() {
  }
}
