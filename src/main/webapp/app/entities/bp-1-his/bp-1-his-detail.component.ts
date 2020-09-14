import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBp1His } from 'app/shared/model/bp-1-his.model';

@Component({
  selector: 'jhi-bp-1-his-detail',
  templateUrl: './bp-1-his-detail.component.html',
})
export class Bp1HisDetailComponent implements OnInit {
  bp1His: IBp1His | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bp1His }) => (this.bp1His = bp1His));
  }

  previousState(): void {
    window.history.back();
  }
}
