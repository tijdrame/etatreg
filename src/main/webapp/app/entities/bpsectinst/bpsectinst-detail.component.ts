import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBpsectinst } from 'app/shared/model/bpsectinst.model';

@Component({
  selector: 'jhi-bpsectinst-detail',
  templateUrl: './bpsectinst-detail.component.html',
})
export class BpsectinstDetailComponent implements OnInit {
  bpsectinst: IBpsectinst | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bpsectinst }) => (this.bpsectinst = bpsectinst));
  }

  previousState(): void {
    window.history.back();
  }
}
