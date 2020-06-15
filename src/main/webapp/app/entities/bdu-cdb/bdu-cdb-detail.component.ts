import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBduCdb } from 'app/shared/model/bdu-cdb.model';

@Component({
  selector: 'jhi-bdu-cdb-detail',
  templateUrl: './bdu-cdb-detail.component.html',
})
export class BduCdbDetailComponent implements OnInit {
  bduCdb: IBduCdb | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bduCdb }) => (this.bduCdb = bduCdb));
  }

  previousState(): void {
    window.history.back();
  }
}
