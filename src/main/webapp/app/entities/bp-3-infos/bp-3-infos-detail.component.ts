import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBp3Infos } from 'app/shared/model/bp-3-infos.model';

@Component({
  selector: 'jhi-bp-3-infos-detail',
  templateUrl: './bp-3-infos-detail.component.html',
})
export class Bp3InfosDetailComponent implements OnInit {
  bp3Infos: IBp3Infos | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bp3Infos }) => (this.bp3Infos = bp3Infos));
  }

  previousState(): void {
    window.history.back();
  }
}
