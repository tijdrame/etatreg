import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBp3Infos, Bp3Infos } from 'app/shared/model/bp-3-infos.model';
import { Bp3InfosService } from './bp-3-infos.service';

@Component({
  selector: 'jhi-bp-3-infos-update',
  templateUrl: './bp-3-infos-update.component.html',
})
export class Bp3InfosUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeIsoDevise: [],
    libelleDevise: [],
    acheteurVendeur: [],
    achatsBilletETr: [],
    ventesBilletEtr: [],
    achatsChqVoy: [],
    ventesChqVoy: [],
  });

  constructor(protected bp3InfosService: Bp3InfosService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bp3Infos }) => {
      this.updateForm(bp3Infos);
    });
  }

  updateForm(bp3Infos: IBp3Infos): void {
    this.editForm.patchValue({
      id: bp3Infos.id,
      codeIsoDevise: bp3Infos.codeIsoDevise,
      libelleDevise: bp3Infos.libelleDevise,
      acheteurVendeur: bp3Infos.acheteurVendeur,
      achatsBilletETr: bp3Infos.achatsBilletETr,
      ventesBilletEtr: bp3Infos.ventesBilletEtr,
      achatsChqVoy: bp3Infos.achatsChqVoy,
      ventesChqVoy: bp3Infos.ventesChqVoy,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bp3Infos = this.createFromForm();
    if (bp3Infos.id !== undefined) {
      this.subscribeToSaveResponse(this.bp3InfosService.update(bp3Infos));
    } else {
      this.subscribeToSaveResponse(this.bp3InfosService.create(bp3Infos));
    }
  }

  private createFromForm(): IBp3Infos {
    return {
      ...new Bp3Infos(),
      id: this.editForm.get(['id'])!.value,
      codeIsoDevise: this.editForm.get(['codeIsoDevise'])!.value,
      libelleDevise: this.editForm.get(['libelleDevise'])!.value,
      acheteurVendeur: this.editForm.get(['acheteurVendeur'])!.value,
      achatsBilletETr: this.editForm.get(['achatsBilletETr'])!.value,
      ventesBilletEtr: this.editForm.get(['ventesBilletEtr'])!.value,
      achatsChqVoy: this.editForm.get(['achatsChqVoy'])!.value,
      ventesChqVoy: this.editForm.get(['ventesChqVoy'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBp3Infos>>): void {
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
