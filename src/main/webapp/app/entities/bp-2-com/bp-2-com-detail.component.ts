import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBp2Com } from 'app/shared/model/bp-2-com.model';

@Component({
  selector: 'jhi-bp-2-com-detail',
  templateUrl: './bp-2-com-detail.component.html',
})
export class Bp2ComDetailComponent implements OnInit {
  bp2Com: IBp2Com | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bp2Com }) => (this.bp2Com = bp2Com));
  }

  previousState(): void {
    window.history.back();
  }
}
