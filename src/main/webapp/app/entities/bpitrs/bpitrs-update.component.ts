import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBpitrs, Bpitrs } from 'app/shared/model/bpitrs.model';
import { BpitrsService } from './bpitrs.service';

@Component({
  selector: 'jhi-bpitrs-update',
  templateUrl: './bpitrs-update.component.html',
})
export class BpitrsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    description: [],
    codeCb: [],
    codeBqe: [],
  });

  constructor(protected bpitrsService: BpitrsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bpitrs }) => {
      this.updateForm(bpitrs);
    });
  }

  updateForm(bpitrs: IBpitrs): void {
    this.editForm.patchValue({
      id: bpitrs.id,
      description: bpitrs.description,
      codeCb: bpitrs.codeCb,
      codeBqe: bpitrs.codeBqe,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bpitrs = this.createFromForm();
    if (bpitrs.id !== undefined) {
      this.subscribeToSaveResponse(this.bpitrsService.update(bpitrs));
    } else {
      this.subscribeToSaveResponse(this.bpitrsService.create(bpitrs));
    }
  }

  private createFromForm(): IBpitrs {
    return {
      ...new Bpitrs(),
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      codeCb: this.editForm.get(['codeCb'])!.value,
      codeBqe: this.editForm.get(['codeBqe'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBpitrs>>): void {
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
