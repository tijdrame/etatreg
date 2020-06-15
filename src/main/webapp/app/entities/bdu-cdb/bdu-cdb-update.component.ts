import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBduCdb, BduCdb } from 'app/shared/model/bdu-cdb.model';
import { BduCdbService } from './bdu-cdb.service';

@Component({
  selector: 'jhi-bdu-cdb-update',
  templateUrl: './bdu-cdb-update.component.html',
})
export class BduCdbUpdateComponent implements OnInit {
  isSaving = false;
  dateCreditDp: any;

  editForm = this.fb.group({
    id: [],
    code: [],
    numEnreg: [],
    dateTraitement: [],
    typeCredit: [],
    objetCredit: [],
    montantCreditDemande: [],
    dureeCreditDemande: [],
    tauxInteretSouhaite: [],
    natureDebiteur: [],
    paysResidence: [],
    ville: [],
    statutJuridique: [],
    sexeDebiteur: [],
    secteurActivite: [],
    tailleEntreprise: [],
    decision: [],
    motifRejet: [],
    dateCredit: [],
    montantCreditAccorde: [],
    dureeCreditAccorde: [],
    periodiciteRemboursement: [],
    tauxInteretApplique: [],
    montantInteret: [],
    montantCharges: [],
    montantCommission: [],
    autresCommissions: [],
  });

  constructor(protected bduCdbService: BduCdbService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bduCdb }) => {
      if (!bduCdb.id) {
        const today = moment().startOf('day');
        bduCdb.dateTraitement = today;
      }

      this.updateForm(bduCdb);
    });
  }

  updateForm(bduCdb: IBduCdb): void {
    this.editForm.patchValue({
      id: bduCdb.id,
      code: bduCdb.code,
      numEnreg: bduCdb.numEnreg,
      dateTraitement: bduCdb.dateTraitement ? bduCdb.dateTraitement.format(DATE_TIME_FORMAT) : null,
      typeCredit: bduCdb.typeCredit,
      objetCredit: bduCdb.objetCredit,
      montantCreditDemande: bduCdb.montantCreditDemande,
      dureeCreditDemande: bduCdb.dureeCreditDemande,
      tauxInteretSouhaite: bduCdb.tauxInteretSouhaite,
      natureDebiteur: bduCdb.natureDebiteur,
      paysResidence: bduCdb.paysResidence,
      ville: bduCdb.ville,
      statutJuridique: bduCdb.statutJuridique,
      sexeDebiteur: bduCdb.sexeDebiteur,
      secteurActivite: bduCdb.secteurActivite,
      tailleEntreprise: bduCdb.tailleEntreprise,
      decision: bduCdb.decision,
      motifRejet: bduCdb.motifRejet,
      dateCredit: bduCdb.dateCredit,
      montantCreditAccorde: bduCdb.montantCreditAccorde,
      dureeCreditAccorde: bduCdb.dureeCreditAccorde,
      periodiciteRemboursement: bduCdb.periodiciteRemboursement,
      tauxInteretApplique: bduCdb.tauxInteretApplique,
      montantInteret: bduCdb.montantInteret,
      montantCharges: bduCdb.montantCharges,
      montantCommission: bduCdb.montantCommission,
      autresCommissions: bduCdb.autresCommissions,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bduCdb = this.createFromForm();
    if (bduCdb.id !== undefined) {
      this.subscribeToSaveResponse(this.bduCdbService.update(bduCdb));
    } else {
      this.subscribeToSaveResponse(this.bduCdbService.create(bduCdb));
    }
  }

  private createFromForm(): IBduCdb {
    return {
      ...new BduCdb(),
      id: this.editForm.get(['id'])!.value,
      code: this.editForm.get(['code'])!.value,
      numEnreg: this.editForm.get(['numEnreg'])!.value,
      dateTraitement: this.editForm.get(['dateTraitement'])!.value
        ? moment(this.editForm.get(['dateTraitement'])!.value, DATE_TIME_FORMAT)
        : undefined,
      typeCredit: this.editForm.get(['typeCredit'])!.value,
      objetCredit: this.editForm.get(['objetCredit'])!.value,
      montantCreditDemande: this.editForm.get(['montantCreditDemande'])!.value,
      dureeCreditDemande: this.editForm.get(['dureeCreditDemande'])!.value,
      tauxInteretSouhaite: this.editForm.get(['tauxInteretSouhaite'])!.value,
      natureDebiteur: this.editForm.get(['natureDebiteur'])!.value,
      paysResidence: this.editForm.get(['paysResidence'])!.value,
      ville: this.editForm.get(['ville'])!.value,
      statutJuridique: this.editForm.get(['statutJuridique'])!.value,
      sexeDebiteur: this.editForm.get(['sexeDebiteur'])!.value,
      secteurActivite: this.editForm.get(['secteurActivite'])!.value,
      tailleEntreprise: this.editForm.get(['tailleEntreprise'])!.value,
      decision: this.editForm.get(['decision'])!.value,
      motifRejet: this.editForm.get(['motifRejet'])!.value,
      dateCredit: this.editForm.get(['dateCredit'])!.value,
      montantCreditAccorde: this.editForm.get(['montantCreditAccorde'])!.value,
      dureeCreditAccorde: this.editForm.get(['dureeCreditAccorde'])!.value,
      periodiciteRemboursement: this.editForm.get(['periodiciteRemboursement'])!.value,
      tauxInteretApplique: this.editForm.get(['tauxInteretApplique'])!.value,
      montantInteret: this.editForm.get(['montantInteret'])!.value,
      montantCharges: this.editForm.get(['montantCharges'])!.value,
      montantCommission: this.editForm.get(['montantCommission'])!.value,
      autresCommissions: this.editForm.get(['autresCommissions'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBduCdb>>): void {
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
