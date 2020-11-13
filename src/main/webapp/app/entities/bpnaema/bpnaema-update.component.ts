import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBpnaema, Bpnaema } from 'app/shared/model/bpnaema.model';
import { BpnaemaService } from './bpnaema.service';

@Component({
  selector: 'jhi-bpnaema-update',
  templateUrl: './bpnaema-update.component.html',
})
export class BpnaemaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeBqe: [],
    codeCb: [],
    description: [],
  });

  constructor(protected bpnaemaService: BpnaemaService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bpnaema }) => {
      this.updateForm(bpnaema);
    });
  }

  updateForm(bpnaema: IBpnaema): void {
    this.editForm.patchValue({
      id: bpnaema.id,
      codeBqe: bpnaema.codeBqe,
      codeCb: bpnaema.codeCb,
      description: bpnaema.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bpnaema = this.createFromForm();
    if (bpnaema.id !== undefined) {
      this.subscribeToSaveResponse(this.bpnaemaService.update(bpnaema));
    } else {
      this.subscribeToSaveResponse(this.bpnaemaService.create(bpnaema));
    }
  }

  private createFromForm(): IBpnaema {
    return {
      ...new Bpnaema(),
      id: this.editForm.get(['id'])!.value,
      codeBqe: this.editForm.get(['codeBqe'])!.value,
      codeCb: this.editForm.get(['codeCb'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBpnaema>>): void {
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
