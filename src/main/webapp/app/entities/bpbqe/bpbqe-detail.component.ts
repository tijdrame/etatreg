import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBpbqe } from 'app/shared/model/bpbqe.model';

@Component({
  selector: 'jhi-bpbqe-detail',
  templateUrl: './bpbqe-detail.component.html',
})
export class BpbqeDetailComponent implements OnInit {
  bpbqe: IBpbqe | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bpbqe }) => (this.bpbqe = bpbqe));
  }

  previousState(): void {
    window.history.back();
  }
}
