import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBp2Infos } from 'app/shared/model/bp-2-infos.model';

@Component({
  selector: 'jhi-bp-2-infos-detail',
  templateUrl: './bp-2-infos-detail.component.html',
})
export class Bp2InfosDetailComponent implements OnInit {
  bp2Infos: IBp2Infos | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bp2Infos }) => (this.bp2Infos = bp2Infos));
  }

  previousState(): void {
    window.history.back();
  }
}
