import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDechargement } from 'app/shared/model/dechargement.model';

@Component({
  selector: 'jhi-dechargement-detail',
  templateUrl: './dechargement-detail.component.html',
})
export class DechargementDetailComponent implements OnInit {
  dechargement: IDechargement | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ dechargement }) => (this.dechargement = dechargement));
  }

  previousState(): void {
    window.history.back();
  }
}
