import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBpsectinst, Bpsectinst } from 'app/shared/model/bpsectinst.model';
import { BpsectinstService } from './bpsectinst.service';

@Component({
  selector: 'jhi-bpsectinst-update',
  templateUrl: './bpsectinst-update.component.html',
})
export class BpsectinstUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeBqe: [],
    codeCb: [],
    description: [],
  });

  constructor(protected bpsectinstService: BpsectinstService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bpsectinst }) => {
      this.updateForm(bpsectinst);
    });
  }

  updateForm(bpsectinst: IBpsectinst): void {
    this.editForm.patchValue({
      id: bpsectinst.id,
      codeBqe: bpsectinst.codeBqe,
      codeCb: bpsectinst.codeCb,
      description: bpsectinst.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bpsectinst = this.createFromForm();
    if (bpsectinst.id !== undefined) {
      this.subscribeToSaveResponse(this.bpsectinstService.update(bpsectinst));
    } else {
      this.subscribeToSaveResponse(this.bpsectinstService.create(bpsectinst));
    }
  }

  private createFromForm(): IBpsectinst {
    return {
      ...new Bpsectinst(),
      id: this.editForm.get(['id'])!.value,
      codeBqe: this.editForm.get(['codeBqe'])!.value,
      codeCb: this.editForm.get(['codeCb'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBpsectinst>>): void {
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
