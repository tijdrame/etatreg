import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBpbqe, Bpbqe } from 'app/shared/model/bpbqe.model';
import { BpbqeService } from './bpbqe.service';

@Component({
  selector: 'jhi-bpbqe-update',
  templateUrl: './bpbqe-update.component.html',
})
export class BpbqeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeBqe: [],
    codeCb: [],
    description: [],
  });

  constructor(protected bpbqeService: BpbqeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bpbqe }) => {
      this.updateForm(bpbqe);
    });
  }

  updateForm(bpbqe: IBpbqe): void {
    this.editForm.patchValue({
      id: bpbqe.id,
      codeBqe: bpbqe.codeBqe,
      codeCb: bpbqe.codeCb,
      description: bpbqe.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bpbqe = this.createFromForm();
    if (bpbqe.id !== undefined) {
      this.subscribeToSaveResponse(this.bpbqeService.update(bpbqe));
    } else {
      this.subscribeToSaveResponse(this.bpbqeService.create(bpbqe));
    }
  }

  private createFromForm(): IBpbqe {
    return {
      ...new Bpbqe(),
      id: this.editForm.get(['id'])!.value,
      codeBqe: this.editForm.get(['codeBqe'])!.value,
      codeCb: this.editForm.get(['codeCb'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBpbqe>>): void {
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
