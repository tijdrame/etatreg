import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBduDepot, BduDepot } from 'app/shared/model/bdu-depot.model';
import { BduDepotService } from './bdu-depot.service';

@Component({
  selector: 'jhi-bdu-depot-update',
  templateUrl: './bdu-depot-update.component.html',
})
export class BduDepotUpdateComponent implements OnInit {
  isSaving = false;
  dateDepotDp: any;

  editForm = this.fb.group({
    id: [],
    code: [],
    numEnreg: [],
    natureDepot: [],
    paysResidence: [],
    ville: [],
    natureDeposant: [],
    statutJuridique: [],
    sexeDeposant: [],
    secteurActivite: [],
    tailleEntreprise: [],
    dateDepot: [],
    termeDepot: [],
    montantDepot: [],
    tauxRenumeration: [],
  });

  constructor(protected bduDepotService: BduDepotService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bduDepot }) => {
      this.updateForm(bduDepot);
    });
  }

  updateForm(bduDepot: IBduDepot): void {
    this.editForm.patchValue({
      id: bduDepot.id,
      code: bduDepot.code,
      numEnreg: bduDepot.numEnreg,
      natureDepot: bduDepot.natureDepot,
      paysResidence: bduDepot.paysResidence,
      ville: bduDepot.ville,
      natureDeposant: bduDepot.natureDeposant,
      statutJuridique: bduDepot.statutJuridique,
      sexeDeposant: bduDepot.sexeDeposant,
      secteurActivite: bduDepot.secteurActivite,
      tailleEntreprise: bduDepot.tailleEntreprise,
      dateDepot: bduDepot.dateDepot,
      termeDepot: bduDepot.termeDepot,
      montantDepot: bduDepot.montantDepot,
      tauxRenumeration: bduDepot.tauxRenumeration,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bduDepot = this.createFromForm();
    if (bduDepot.id !== undefined) {
      this.subscribeToSaveResponse(this.bduDepotService.update(bduDepot));
    } else {
      this.subscribeToSaveResponse(this.bduDepotService.create(bduDepot));
    }
  }

  private createFromForm(): IBduDepot {
    return {
      ...new BduDepot(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      numEnreg: this.editForm.get(['numEnreg'])!.value,
      natureDepot: this.editForm.get(['natureDepot'])!.value,
      paysResidence: this.editForm.get(['paysResidence'])!.value,
      ville: this.editForm.get(['ville'])!.value,
      natureDeposant: this.editForm.get(['natureDeposant'])!.value,
      statutJuridique: this.editForm.get(['statutJuridique'])!.value,
      sexeDeposant: this.editForm.get(['sexeDeposant'])!.value,
      secteurActivite: this.editForm.get(['secteurActivite'])!.value,
      tailleEntreprise: this.editForm.get(['tailleEntreprise'])!.value,
      dateDepot: this.editForm.get(['dateDepot'])!.value,
      termeDepot: this.editForm.get(['termeDepot'])!.value,
      montantDepot: this.editForm.get(['montantDepot'])!.value,
      tauxRenumeration: this.editForm.get(['tauxRenumeration'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBduDepot>>): void {
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
