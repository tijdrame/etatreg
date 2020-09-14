import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IChargeFile } from 'app/shared/model/charge-file.model';

@Component({
  selector: 'jhi-charge-file-detail',
  templateUrl: './charge-file-detail.component.html',
})
export class ChargeFileDetailComponent implements OnInit {
  chargeFile: IChargeFile | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ chargeFile }) => (this.chargeFile = chargeFile));
  }

  previousState(): void {
    window.history.back();
  }
}
