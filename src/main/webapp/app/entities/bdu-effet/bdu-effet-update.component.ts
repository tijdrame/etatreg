import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBduEffet, BduEffet } from 'app/shared/model/bdu-effet.model';
import { BduEffetService } from './bdu-effet.service';

@Component({
  selector: 'jhi-bdu-effet-update',
  templateUrl: './bdu-effet-update.component.html',
})
export class BduEffetUpdateComponent implements OnInit {
  isSaving = false;
  dateEscompteDp: any;
  dateEcheanceDp: any;

  editForm = this.fb.group({
    id: [],
    code: [],
    numEnreg: [],
    natureDeposant: [],
    paysResidence: [],
    ville: [],
    montantEffet: [],
    dateEscompte: [],
    dateEcheance: [],
    nbreJours: [],
    tauxInteret: [],
    montantCharges: [],
  });

  constructor(protected bduEffetService: BduEffetService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bduEffet }) => {
      this.updateForm(bduEffet);
    });
  }

  updateForm(bduEffet: IBduEffet): void {
    this.editForm.patchValue({
      id: bduEffet.id,
      code: bduEffet.code,
      numEnreg: bduEffet.numEnreg,
      natureDeposant: bduEffet.natureDeposant,
      paysResidence: bduEffet.paysResidence,
      ville: bduEffet.ville,
      montantEffet: bduEffet.montantEffet,
      dateEscompte: bduEffet.dateEscompte,
      dateEcheance: bduEffet.dateEcheance,
      nbreJours: bduEffet.nbreJours,
      tauxInteret: bduEffet.tauxInteret,
      montantCharges: bduEffet.montantCharges,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bduEffet = this.createFromForm();
    if (bduEffet.id !== undefined) {
      this.subscribeToSaveResponse(this.bduEffetService.update(bduEffet));
    } else {
      this.subscribeToSaveResponse(this.bduEffetService.create(bduEffet));
    }
  }

  private createFromForm(): IBduEffet {
    return {
      ...new BduEffet(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      numEnreg: this.editForm.get(['numEnreg'])!.value,
      natureDeposant: this.editForm.get(['natureDeposant'])!.value,
      paysResidence: this.editForm.get(['paysResidence'])!.value,
      ville: this.editForm.get(['ville'])!.value,
      montantEffet: this.editForm.get(['montantEffet'])!.value,
      dateEscompte: this.editForm.get(['dateEscompte'])!.value,
      dateEcheance: this.editForm.get(['dateEcheance'])!.value,
      nbreJours: this.editForm.get(['nbreJours'])!.value,
      tauxInteret: this.editForm.get(['tauxInteret'])!.value,
      montantCharges: this.editForm.get(['montantCharges'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBduEffet>>): void {
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
