import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INatureDepot, NatureDepot } from 'app/shared/model/nature-depot.model';
import { NatureDepotService } from './nature-depot.service';

@Component({
  selector: 'jhi-nature-depot-update',
  templateUrl: './nature-depot-update.component.html',
})
export class NatureDepotUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeBdu: [null, [Validators.required]],
    codeCb: [null, [Validators.required]],
    description: [],
  });

  constructor(protected natureDepotService: NatureDepotService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ natureDepot }) => {
      this.updateForm(natureDepot);
    });
  }

  updateForm(natureDepot: INatureDepot): void {
    this.editForm.patchValue({
      id: natureDepot.id,
      codeBdu: natureDepot.codeBdu,
      codeCb: natureDepot.codeCb,
      description: natureDepot.description,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const natureDepot = this.createFromForm();
    if (natureDepot.id !== undefined) {
      this.subscribeToSaveResponse(this.natureDepotService.update(natureDepot));
    } else {
      this.subscribeToSaveResponse(this.natureDepotService.create(natureDepot));
    }
  }

  private createFromForm(): INatureDepot {
    return {
      ...new NatureDepot(),
      id: this.editForm.get(['id'])!.value,
      codeBdu: this.editForm.get(['codeBdu'])!.value,
      codeCb: this.editForm.get(['codeCb'])!.value,
      description: this.editForm.get(['description'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INatureDepot>>): void {
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
