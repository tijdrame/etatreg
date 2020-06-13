import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IObjetCredit, ObjetCredit } from 'app/shared/model/objet-credit.model';
import { ObjetCreditService } from './objet-credit.service';

@Component({
  selector: 'jhi-objet-credit-update',
  templateUrl: './objet-credit-update.component.html',
})
export class ObjetCreditUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeBdu: [null, [Validators.required]],
    codeCb: [null, [Validators.required]],
    description: [],
  });

  constructor(protected objetCreditService: ObjetCreditService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ objetCredit }) => {
      this.updateForm(objetCredit);
    });
  }

  updateForm(objetCredit: IObjetCredit): void {
    this.editForm.patchValue({
      id: objetCredit.id,
      codeBdu: objetCredit.codeBdu,
      codeCb: objetCredit.codeCb,
      description: objetCredit.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const objetCredit = this.createFromForm();
    if (objetCredit.id !== undefined) {
      this.subscribeToSaveResponse(this.objetCreditService.update(objetCredit));
    } else {
      this.subscribeToSaveResponse(this.objetCreditService.create(objetCredit));
    }
  }

  private createFromForm(): IObjetCredit {
    return {
      ...new ObjetCredit(),
      id: this.editForm.get(['id'])!.value,
      codeBdu: this.editForm.get(['codeBdu'])!.value,
      codeCb: this.editForm.get(['codeCb'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IObjetCredit>>): void {
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
