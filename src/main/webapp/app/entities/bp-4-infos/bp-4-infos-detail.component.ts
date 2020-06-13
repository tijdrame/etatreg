import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBp4Infos } from 'app/shared/model/bp-4-infos.model';

@Component({
  selector: 'jhi-bp-4-infos-detail',
  templateUrl: './bp-4-infos-detail.component.html',
})
export class Bp4InfosDetailComponent implements OnInit {
  bp4Infos: IBp4Infos | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bp4Infos }) => (this.bp4Infos = bp4Infos));
  }

  previousState(): void {
    window.history.back();
  }
}
