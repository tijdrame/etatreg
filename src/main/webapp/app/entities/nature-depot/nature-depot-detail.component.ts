import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INatureDepot } from 'app/shared/model/nature-depot.model';

@Component({
  selector: 'jhi-nature-depot-detail',
  templateUrl: './nature-depot-detail.component.html',
})
export class NatureDepotDetailComponent implements OnInit {
  natureDepot: INatureDepot | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ natureDepot }) => (this.natureDepot = natureDepot));
  }

  previousState(): void {
    window.history.back();
  }
}
