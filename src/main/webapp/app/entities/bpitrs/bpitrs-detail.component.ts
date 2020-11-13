import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBpitrs } from 'app/shared/model/bpitrs.model';

@Component({
  selector: 'jhi-bpitrs-detail',
  templateUrl: './bpitrs-detail.component.html',
})
export class BpitrsDetailComponent implements OnInit {
  bpitrs: IBpitrs | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bpitrs }) => (this.bpitrs = bpitrs));
  }

  previousState(): void {
    window.history.back();
  }
}
