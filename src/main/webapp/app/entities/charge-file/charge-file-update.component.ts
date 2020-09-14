import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IChargeFile, ChargeFile } from 'app/shared/model/charge-file.model';
import { ChargeFileService } from './charge-file.service';

@Component({
  selector: 'jhi-charge-file-update',
  templateUrl: './charge-file-update.component.html',
})
export class ChargeFileUpdateComponent implements OnInit {
  isSaving = false;
  dateChargeDp: any;

  editForm = this.fb.group({
    id: [],
    nomFic: [],
    dateCharge: [],
  });

  constructor(protected chargeFileService: ChargeFileService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ chargeFile }) => {
      this.updateForm(chargeFile);
    });
  }

  updateForm(chargeFile: IChargeFile): void {
    this.editForm.patchValue({
      id: chargeFile.id,
      nomFic: chargeFile.nomFic,
      dateCharge: chargeFile.dateCharge,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const chargeFile = this.createFromForm();
    if (chargeFile.id !== undefined) {
      this.subscribeToSaveResponse(this.chargeFileService.update(chargeFile));
    } else {
      this.subscribeToSaveResponse(this.chargeFileService.create(chargeFile));
    }
  }

  private createFromForm(): IChargeFile {
    return {
      ...new ChargeFile(),
      id: this.editForm.get(['id'])!.value,
      nomFic: this.editForm.get(['nomFic'])!.value,
      dateCharge: this.editForm.get(['dateCharge'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IChargeFile>>): void {
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
