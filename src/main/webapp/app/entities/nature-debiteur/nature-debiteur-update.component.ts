import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INatureDebiteur, NatureDebiteur } from 'app/shared/model/nature-debiteur.model';
import { NatureDebiteurService } from './nature-debiteur.service';

@Component({
  selector: 'jhi-nature-debiteur-update',
  templateUrl: './nature-debiteur-update.component.html',
})
export class NatureDebiteurUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeBdu: [null, [Validators.required]],
    codeCb: [null, [Validators.required]],
    description: [],
  });

  constructor(protected natureDebiteurService: NatureDebiteurService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ natureDebiteur }) => {
      this.updateForm(natureDebiteur);
    });
  }

  updateForm(natureDebiteur: INatureDebiteur): void {
    this.editForm.patchValue({
      id: natureDebiteur.id,
      codeBdu: natureDebiteur.codeBdu,
      codeCb: natureDebiteur.codeCb,
      description: natureDebiteur.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const natureDebiteur = this.createFromForm();
    if (natureDebiteur.id !== undefined) {
      this.subscribeToSaveResponse(this.natureDebiteurService.update(natureDebiteur));
    } else {
      this.subscribeToSaveResponse(this.natureDebiteurService.create(natureDebiteur));
    }
  }

  private createFromForm(): INatureDebiteur {
    return {
      ...new NatureDebiteur(),
      id: this.editForm.get(['id'])!.value,
      codeBdu: this.editForm.get(['codeBdu'])!.value,
      codeCb: this.editForm.get(['codeCb'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INatureDebiteur>>): void {
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
