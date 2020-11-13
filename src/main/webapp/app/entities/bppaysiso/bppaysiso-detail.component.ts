import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBppaysiso } from 'app/shared/model/bppaysiso.model';

@Component({
  selector: 'jhi-bppaysiso-detail',
  templateUrl: './bppaysiso-detail.component.html',
})
export class BppaysisoDetailComponent implements OnInit {
  bppaysiso: IBppaysiso | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bppaysiso }) => (this.bppaysiso = bppaysiso));
  }

  previousState(): void {
    window.history.back();
  }
}
