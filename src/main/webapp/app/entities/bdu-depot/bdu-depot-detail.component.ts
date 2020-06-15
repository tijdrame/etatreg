import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBduDepot } from 'app/shared/model/bdu-depot.model';

@Component({
  selector: 'jhi-bdu-depot-detail',
  templateUrl: './bdu-depot-detail.component.html',
})
export class BduDepotDetailComponent implements OnInit {
  bduDepot: IBduDepot | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bduDepot }) => (this.bduDepot = bduDepot));
  }

  previousState(): void {
    window.history.back();
  }
}
