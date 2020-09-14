import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICrpAtr } from 'app/shared/model/crp-atr.model';

@Component({
  selector: 'jhi-crp-atr-detail',
  templateUrl: './crp-atr-detail.component.html',
})
export class CrpAtrDetailComponent implements OnInit {
  crpAtr: ICrpAtr | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ crpAtr }) => (this.crpAtr = crpAtr));
  }

  previousState(): void {
    window.history.back();
  }
}
