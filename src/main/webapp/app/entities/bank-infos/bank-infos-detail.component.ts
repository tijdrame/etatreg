import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBankInfos } from 'app/shared/model/bank-infos.model';

@Component({
  selector: 'jhi-bank-infos-detail',
  templateUrl: './bank-infos-detail.component.html',
})
export class BankInfosDetailComponent implements OnInit {
  bankInfos: IBankInfos | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bankInfos }) => (this.bankInfos = bankInfos));
  }

  previousState(): void {
    window.history.back();
  }
}
