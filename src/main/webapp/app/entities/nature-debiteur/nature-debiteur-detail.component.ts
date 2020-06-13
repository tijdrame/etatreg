import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INatureDebiteur } from 'app/shared/model/nature-debiteur.model';

@Component({
  selector: 'jhi-nature-debiteur-detail',
  templateUrl: './nature-debiteur-detail.component.html',
})
export class NatureDebiteurDetailComponent implements OnInit {
  natureDebiteur: INatureDebiteur | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ natureDebiteur }) => (this.natureDebiteur = natureDebiteur));
  }

  previousState(): void {
    window.history.back();
  }
}
