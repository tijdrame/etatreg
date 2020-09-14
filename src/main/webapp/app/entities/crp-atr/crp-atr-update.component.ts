import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICrpAtr, CrpAtr } from 'app/shared/model/crp-atr.model';
import { CrpAtrService } from './crp-atr.service';

@Component({
  selector: 'jhi-crp-atr-update',
  templateUrl: './crp-atr-update.component.html',
})
export class CrpAtrUpdateComponent implements OnInit {
  isSaving = false;
  demAtrDp: any;
  datopeDp: any;
  dateArreteDp: any;

  editForm = this.fb.group({
    id: [],
    cenr: [],
    refint: [],
    num: [],
    nobor: [],
    typenr: [],
    idAtr: [],
    demAtr: [],
    numAtr: [],
    nomRes: [],
    cpayIso: [],
    datope: [],
    regt: [],
    nomEtr: [],
    nocli: [],
    catRes: [],
    ceco: [],
    cpayEtg: [],
    natcpt: [],
    sens: [],
    devn: [],
    mdev: [],
    taux: [],
    mnat: [],
    etab: [],
    nomFic: [],
    dateArrete: [],
  });

  constructor(protected crpAtrService: CrpAtrService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ crpAtr }) => {
      this.updateForm(crpAtr);
    });
  }

  updateForm(crpAtr: ICrpAtr): void {
    this.editForm.patchValue({
      id: crpAtr.id,
      cenr: crpAtr.cenr,
      refint: crpAtr.refint,
      num: crpAtr.num,
      nobor: crpAtr.nobor,
      typenr: crpAtr.typenr,
      idAtr: crpAtr.idAtr,
      demAtr: crpAtr.demAtr,
      numAtr: crpAtr.numAtr,
      nomRes: crpAtr.nomRes,
      cpayIso: crpAtr.cpayIso,
      datope: crpAtr.datope,
      regt: crpAtr.regt,
      nomEtr: crpAtr.nomEtr,
      nocli: crpAtr.nocli,
      catRes: crpAtr.catRes,
      ceco: crpAtr.ceco,
      cpayEtg: crpAtr.cpayEtg,
      natcpt: crpAtr.natcpt,
      sens: crpAtr.sens,
      devn: crpAtr.devn,
      mdev: crpAtr.mdev,
      taux: crpAtr.taux,
      mnat: crpAtr.mnat,
      etab: crpAtr.etab,
      nomFic: crpAtr.nomFic,
      dateArrete: crpAtr.dateArrete,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const crpAtr = this.createFromForm();
    if (crpAtr.id !== undefined) {
      this.subscribeToSaveResponse(this.crpAtrService.update(crpAtr));
    } else {
      this.subscribeToSaveResponse(this.crpAtrService.create(crpAtr));
    }
  }

  private createFromForm(): ICrpAtr {
    return {
      ...new CrpAtr(),
      id: this.editForm.get(['id'])!.value,
      cenr: this.editForm.get(['cenr'])!.value,
      refint: this.editForm.get(['refint'])!.value,
      num: this.editForm.get(['num'])!.value,
      nobor: this.editForm.get(['nobor'])!.value,
      typenr: this.editForm.get(['typenr'])!.value,
      idAtr: this.editForm.get(['idAtr'])!.value,
      demAtr: this.editForm.get(['demAtr'])!.value,
      numAtr: this.editForm.get(['numAtr'])!.value,
      nomRes: this.editForm.get(['nomRes'])!.value,
      cpayIso: this.editForm.get(['cpayIso'])!.value,
      datope: this.editForm.get(['datope'])!.value,
      regt: this.editForm.get(['regt'])!.value,
      nomEtr: this.editForm.get(['nomEtr'])!.value,
      nocli: this.editForm.get(['nocli'])!.value,
      catRes: this.editForm.get(['catRes'])!.value,
      ceco: this.editForm.get(['ceco'])!.value,
      cpayEtg: this.editForm.get(['cpayEtg'])!.value,
      natcpt: this.editForm.get(['natcpt'])!.value,
      sens: this.editForm.get(['sens'])!.value,
      devn: this.editForm.get(['devn'])!.value,
      mdev: this.editForm.get(['mdev'])!.value,
      taux: this.editForm.get(['taux'])!.value,
      mnat: this.editForm.get(['mnat'])!.value,
      etab: this.editForm.get(['etab'])!.value,
      nomFic: this.editForm.get(['nomFic'])!.value,
      dateArrete: this.editForm.get(['dateArrete'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICrpAtr>>): void {
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
