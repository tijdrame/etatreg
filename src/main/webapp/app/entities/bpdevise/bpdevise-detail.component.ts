import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBpdevise } from 'app/shared/model/bpdevise.model';

@Component({
  selector: 'jhi-bpdevise-detail',
  templateUrl: './bpdevise-detail.component.html',
})
export class BpdeviseDetailComponent implements OnInit {
  bpdevise: IBpdevise | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bpdevise }) => (this.bpdevise = bpdevise));
  }

  previousState(): void {
    window.history.back();
  }
}
