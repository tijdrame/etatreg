import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBpnaema } from 'app/shared/model/bpnaema.model';

@Component({
  selector: 'jhi-bpnaema-detail',
  templateUrl: './bpnaema-detail.component.html',
})
export class BpnaemaDetailComponent implements OnInit {
  bpnaema: IBpnaema | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bpnaema }) => (this.bpnaema = bpnaema));
  }

  previousState(): void {
    window.history.back();
  }
}
