import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBankInfos, BankInfos } from 'app/shared/model/bank-infos.model';
import { BankInfosService } from './bank-infos.service';

@Component({
  selector: 'jhi-bank-infos-update',
  templateUrl: './bank-infos-update.component.html',
})
export class BankInfosUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeId: [null, [Validators.required]],
    sigle: [null, []],
    raisonSociale: [null, [Validators.required]],
  });

  constructor(protected bankInfosService: BankInfosService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bankInfos }) => {
      this.updateForm(bankInfos);
    });
  }

  updateForm(bankInfos: IBankInfos): void {
    this.editForm.patchValue({
      id: bankInfos.id,
      codeId: bankInfos.codeId,
      sigle: bankInfos.sigle,
      raisonSociale: bankInfos.raisonSociale,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bankInfos = this.createFromForm();
    if (bankInfos.id !== undefined) {
      this.subscribeToSaveResponse(this.bankInfosService.update(bankInfos));
    } else {
      this.subscribeToSaveResponse(this.bankInfosService.create(bankInfos));
    }
  }

  private createFromForm(): IBankInfos {
    return {
      ...new BankInfos(),
      id: this.editForm.get(['id'])!.value,
      codeId: this.editForm.get(['codeId'])!.value,
      sigle: this.editForm.get(['sigle'])!.value,
      raisonSociale: this.editForm.get(['raisonSociale'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBankInfos>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
