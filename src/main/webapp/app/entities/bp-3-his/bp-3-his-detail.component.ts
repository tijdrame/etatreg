import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBp3His } from 'app/shared/model/bp-3-his.model';

@Component({
  selector: 'jhi-bp-3-his-detail',
  templateUrl: './bp-3-his-detail.component.html',
})
export class Bp3HisDetailComponent implements OnInit {
  bp3His: IBp3His | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bp3His }) => (this.bp3His = bp3His));
  }

  previousState(): void {
    window.history.back();
  }
}
