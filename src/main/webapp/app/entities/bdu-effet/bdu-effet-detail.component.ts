import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBduEffet } from 'app/shared/model/bdu-effet.model';

@Component({
  selector: 'jhi-bdu-effet-detail',
  templateUrl: './bdu-effet-detail.component.html',
})
export class BduEffetDetailComponent implements OnInit {
  bduEffet: IBduEffet | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bduEffet }) => (this.bduEffet = bduEffet));
  }

  previousState(): void {
    window.history.back();
  }
}
