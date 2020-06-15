import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBduAutor, BduAutor } from 'app/shared/model/bdu-autor.model';
import { BduAutorService } from './bdu-autor.service';

@Component({
  selector: 'jhi-bdu-autor-update',
  templateUrl: './bdu-autor-update.component.html',
})
export class BduAutorUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    code: [],
    numEnreg: [],
    natureBeneficiaire: [],
    paysResidence: [],
    ville: [],
    statutJuridique: [],
    sexeBeneficiaire: [],
    secteurActivite: [],
    tailleEntreprise: [],
    montantMaxAutorise: [],
    montantMaxUtilise: [],
    soldeCompte: [],
    tauxInteret: [],
  });

  constructor(protected bduAutorService: BduAutorService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bduAutor }) => {
      this.updateForm(bduAutor);
    });
  }

  updateForm(bduAutor: IBduAutor): void {
    this.editForm.patchValue({
      id: bduAutor.id,
      code: bduAutor.code,
      numEnreg: bduAutor.numEnreg,
      natureBeneficiaire: bduAutor.natureBeneficiaire,
      paysResidence: bduAutor.paysResidence,
      ville: bduAutor.ville,
      statutJuridique: bduAutor.statutJuridique,
      sexeBeneficiaire: bduAutor.sexeBeneficiaire,
      secteurActivite: bduAutor.secteurActivite,
      tailleEntreprise: bduAutor.tailleEntreprise,
      montantMaxAutorise: bduAutor.montantMaxAutorise,
      montantMaxUtilise: bduAutor.montantMaxUtilise,
      soldeCompte: bduAutor.soldeCompte,
      tauxInteret: bduAutor.tauxInteret,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bduAutor = this.createFromForm();
    if (bduAutor.id !== undefined) {
      this.subscribeToSaveResponse(this.bduAutorService.update(bduAutor));
    } else {
      this.subscribeToSaveResponse(this.bduAutorService.create(bduAutor));
    }
  }

  private createFromForm(): IBduAutor {
    return {
      ...new BduAutor(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      numEnreg: this.editForm.get(['numEnreg'])!.value,
      natureBeneficiaire: this.editForm.get(['natureBeneficiaire'])!.value,
      paysResidence: this.editForm.get(['paysResidence'])!.value,
      ville: this.editForm.get(['ville'])!.value,
      statutJuridique: this.editForm.get(['statutJuridique'])!.value,
      sexeBeneficiaire: this.editForm.get(['sexeBeneficiaire'])!.value,
      secteurActivite: this.editForm.get(['secteurActivite'])!.value,
      tailleEntreprise: this.editForm.get(['tailleEntreprise'])!.value,
      montantMaxAutorise: this.editForm.get(['montantMaxAutorise'])!.value,
      montantMaxUtilise: this.editForm.get(['montantMaxUtilise'])!.value,
      soldeCompte: this.editForm.get(['soldeCompte'])!.value,
      tauxInteret: this.editForm.get(['tauxInteret'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBduAutor>>): void {
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
