import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IBp2Infos, Bp2Infos } from 'app/shared/model/bp-2-infos.model';
import { Bp2InfosService } from './bp-2-infos.service';

@Component({
  selector: 'jhi-bp-2-infos-update',
  templateUrl: './bp-2-infos-update.component.html',
})
export class Bp2InfosUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeIsoDevise: [],
    actifBilletEtRcai: [],
    actifMaisonMere: [],
    actifAuTresor: [],
    actifClientDeb: [],
    actifEffesCpte: [],
    actifEffetEnc: [],
    passifMaisonMere: [],
    passifAuTresor: [],
    passifCliCpteCh: [],
    passifCptApresEnc: [],
    dateChargement: [],
    dateDechargement: [],
    passifCliCptVue: [],
  });

  constructor(protected bp2InfosService: Bp2InfosService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bp2Infos }) => {
      if (!bp2Infos.id) {
        const today = moment().startOf('day');
        bp2Infos.dateChargement = today;
        bp2Infos.dateDechargement = today;
        bp2Infos.passifCliCptVue = today;
      }

      this.updateForm(bp2Infos);
    });
  }

  updateForm(bp2Infos: IBp2Infos): void {
    this.editForm.patchValue({
      id: bp2Infos.id,
      codeIsoDevise: bp2Infos.codeIsoDevise,
      actifBilletEtRcai: bp2Infos.actifBilletEtRcai,
      actifMaisonMere: bp2Infos.actifMaisonMere,
      actifAuTresor: bp2Infos.actifAuTresor,
      actifClientDeb: bp2Infos.actifClientDeb,
      actifEffesCpte: bp2Infos.actifEffesCpte,
      actifEffetEnc: bp2Infos.actifEffetEnc,
      passifMaisonMere: bp2Infos.passifMaisonMere,
      passifAuTresor: bp2Infos.passifAuTresor,
      passifCliCpteCh: bp2Infos.passifCliCpteCh,
      passifCptApresEnc: bp2Infos.passifCptApresEnc,
      dateChargement: bp2Infos.dateChargement ? bp2Infos.dateChargement.format(DATE_TIME_FORMAT) : null,
      dateDechargement: bp2Infos.dateDechargement ? bp2Infos.dateDechargement.format(DATE_TIME_FORMAT) : null,
      passifCliCptVue: bp2Infos.passifCliCptVue ? bp2Infos.passifCliCptVue.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bp2Infos = this.createFromForm();
    if (bp2Infos.id !== undefined) {
      this.subscribeToSaveResponse(this.bp2InfosService.update(bp2Infos));
    } else {
      this.subscribeToSaveResponse(this.bp2InfosService.create(bp2Infos));
    }
  }

  private createFromForm(): IBp2Infos {
    return {
      ...new Bp2Infos(),
      id: this.editForm.get(['id'])!.value,
      codeIsoDevise: this.editForm.get(['codeIsoDevise'])!.value,
      actifBilletEtRcai: this.editForm.get(['actifBilletEtRcai'])!.value,
      actifMaisonMere: this.editForm.get(['actifMaisonMere'])!.value,
      actifAuTresor: this.editForm.get(['actifAuTresor'])!.value,
      actifClientDeb: this.editForm.get(['actifClientDeb'])!.value,
      actifEffesCpte: this.editForm.get(['actifEffesCpte'])!.value,
      actifEffetEnc: this.editForm.get(['actifEffetEnc'])!.value,
      passifMaisonMere: this.editForm.get(['passifMaisonMere'])!.value,
      passifAuTresor: this.editForm.get(['passifAuTresor'])!.value,
      passifCliCpteCh: this.editForm.get(['passifCliCpteCh'])!.value,
      passifCptApresEnc: this.editForm.get(['passifCptApresEnc'])!.value,
      dateChargement: this.editForm.get(['dateChargement'])!.value
        ? moment(this.editForm.get(['dateChargement'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dateDechargement: this.editForm.get(['dateDechargement'])!.value
        ? moment(this.editForm.get(['dateDechargement'])!.value, DATE_TIME_FORMAT)
        : undefined,
      passifCliCptVue: this.editForm.get(['passifCliCptVue'])!.value
        ? moment(this.editForm.get(['passifCliCptVue'])!.value, DATE_TIME_FORMAT)
        : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBp2Infos>>): void {
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
