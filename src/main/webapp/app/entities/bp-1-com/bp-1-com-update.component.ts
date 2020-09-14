import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBp1Com, Bp1Com } from 'app/shared/model/bp-1-com.model';
import { Bp1ComService } from './bp-1-com.service';

@Component({
  selector: 'jhi-bp-1-com-update',
  templateUrl: './bp-1-com-update.component.html',
})
export class Bp1ComUpdateComponent implements OnInit {
  isSaving = false;
  douDp: any;
  dddDp: any;
  ddcDp: any;
  dateArreteDp: any;

  editForm = this.fb.group({
    id: [],
    age: [],
    dev: [],
    ncp: [],
    inti: [],
    sde: [],
    cha: [],
    dou: [],
    ddd: [],
    ddc: [],
    nomFic: [],
    dateArrete: [],
  });

  constructor(protected bp1ComService: Bp1ComService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bp1Com }) => {
      this.updateForm(bp1Com);
    });
  }

  updateForm(bp1Com: IBp1Com): void {
    this.editForm.patchValue({
      id: bp1Com.id,
      age: bp1Com.age,
      dev: bp1Com.dev,
      ncp: bp1Com.ncp,
      inti: bp1Com.inti,
      sde: bp1Com.sde,
      cha: bp1Com.cha,
      dou: bp1Com.dou,
      ddd: bp1Com.ddd,
      ddc: bp1Com.ddc,
      nomFic: bp1Com.nomFic,
      dateArrete: bp1Com.dateArrete,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bp1Com = this.createFromForm();
    if (bp1Com.id !== undefined) {
      this.subscribeToSaveResponse(this.bp1ComService.update(bp1Com));
    } else {
      this.subscribeToSaveResponse(this.bp1ComService.create(bp1Com));
    }
  }

  private createFromForm(): IBp1Com {
    return {
      ...new Bp1Com(),
      id: this.editForm.get(['id'])!.value,
      age: this.editForm.get(['age'])!.value,
      dev: this.editForm.get(['dev'])!.value,
      ncp: this.editForm.get(['ncp'])!.value,
      inti: this.editForm.get(['inti'])!.value,
      sde: this.editForm.get(['sde'])!.value,
      cha: this.editForm.get(['cha'])!.value,
      dou: this.editForm.get(['dou'])!.value,
      ddd: this.editForm.get(['ddd'])!.value,
      ddc: this.editForm.get(['ddc'])!.value,
      nomFic: this.editForm.get(['nomFic'])!.value,
      dateArrete: this.editForm.get(['dateArrete'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBp1Com>>): void {
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
