import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBp1Com } from 'app/shared/model/bp-1-com.model';

@Component({
  selector: 'jhi-bp-1-com-detail',
  templateUrl: './bp-1-com-detail.component.html',
})
export class Bp1ComDetailComponent implements OnInit {
  bp1Com: IBp1Com | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bp1Com }) => (this.bp1Com = bp1Com));
  }

  previousState(): void {
    window.history.back();
  }
}
