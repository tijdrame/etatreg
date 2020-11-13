import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBppaysiso, Bppaysiso } from 'app/shared/model/bppaysiso.model';
import { BppaysisoService } from './bppaysiso.service';

@Component({
  selector: 'jhi-bppaysiso-update',
  templateUrl: './bppaysiso-update.component.html',
})
export class BppaysisoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeBqe: [],
    codeCb: [],
    description: [],
  });

  constructor(protected bppaysisoService: BppaysisoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bppaysiso }) => {
      this.updateForm(bppaysiso);
    });
  }

  updateForm(bppaysiso: IBppaysiso): void {
    this.editForm.patchValue({
      id: bppaysiso.id,
      codeBqe: bppaysiso.codeBqe,
      codeCb: bppaysiso.codeCb,
      description: bppaysiso.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bppaysiso = this.createFromForm();
    if (bppaysiso.id !== undefined) {
      this.subscribeToSaveResponse(this.bppaysisoService.update(bppaysiso));
    } else {
      this.subscribeToSaveResponse(this.bppaysisoService.create(bppaysiso));
    }
  }

  private createFromForm(): IBppaysiso {
    return {
      ...new Bppaysiso(),
      id: this.editForm.get(['id'])!.value,
      codeBqe: this.editForm.get(['codeBqe'])!.value,
      codeCb: this.editForm.get(['codeCb'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBppaysiso>>): void {
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
