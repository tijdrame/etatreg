import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBpdevise, Bpdevise } from 'app/shared/model/bpdevise.model';
import { BpdeviseService } from './bpdevise.service';

@Component({
  selector: 'jhi-bpdevise-update',
  templateUrl: './bpdevise-update.component.html',
})
export class BpdeviseUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeBqe: [],
    codeCb: [],
    description: [],
  });

  constructor(protected bpdeviseService: BpdeviseService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bpdevise }) => {
      this.updateForm(bpdevise);
    });
  }

  updateForm(bpdevise: IBpdevise): void {
    this.editForm.patchValue({
      id: bpdevise.id,
      codeBqe: bpdevise.codeBqe,
      codeCb: bpdevise.codeCb,
      description: bpdevise.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bpdevise = this.createFromForm();
    if (bpdevise.id !== undefined) {
      this.subscribeToSaveResponse(this.bpdeviseService.update(bpdevise));
    } else {
      this.subscribeToSaveResponse(this.bpdeviseService.create(bpdevise));
    }
  }

  private createFromForm(): IBpdevise {
    return {
      ...new Bpdevise(),
      id: this.editForm.get(['id'])!.value,
      codeBqe: this.editForm.get(['codeBqe'])!.value,
      codeCb: this.editForm.get(['codeCb'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBpdevise>>): void {
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
