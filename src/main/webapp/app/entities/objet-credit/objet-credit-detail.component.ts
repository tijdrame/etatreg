import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IObjetCredit } from 'app/shared/model/objet-credit.model';

@Component({
  selector: 'jhi-objet-credit-detail',
  templateUrl: './objet-credit-detail.component.html',
})
export class ObjetCreditDetailComponent implements OnInit {
  objetCredit: IObjetCredit | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ objetCredit }) => (this.objetCredit = objetCredit));
  }

  previousState(): void {
    window.history.back();
  }
}
