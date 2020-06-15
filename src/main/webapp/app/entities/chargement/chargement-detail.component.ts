import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IChargement } from 'app/shared/model/chargement.model';

@Component({
  selector: 'jhi-chargement-detail',
  templateUrl: './chargement-detail.component.html',
})
export class ChargementDetailComponent implements OnInit {
  chargement: IChargement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ chargement }) => (this.chargement = chargement));
  }

  previousState(): void {
    window.history.back();
  }
}
