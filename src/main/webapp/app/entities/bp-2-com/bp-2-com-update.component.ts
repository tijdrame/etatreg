import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBp2Com, Bp2Com } from 'app/shared/model/bp-2-com.model';
import { Bp2ComService } from './bp-2-com.service';

@Component({
  selector: 'jhi-bp-2-com-update',
  templateUrl: './bp-2-com-update.component.html',
})
export class Bp2ComUpdateComponent implements OnInit {
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

  constructor(protected bp2ComService: Bp2ComService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bp2Com }) => {
      this.updateForm(bp2Com);
    });
  }

  updateForm(bp2Com: IBp2Com): void {
    this.editForm.patchValue({
      id: bp2Com.id,
      age: bp2Com.age,
      dev: bp2Com.dev,
      ncp: bp2Com.ncp,
      inti: bp2Com.inti,
      sde: bp2Com.sde,
      cha: bp2Com.cha,
      dou: bp2Com.dou,
      ddd: bp2Com.ddd,
      ddc: bp2Com.ddc,
      nomFic: bp2Com.nomFic,
      dateArrete: bp2Com.dateArrete,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bp2Com = this.createFromForm();
    if (bp2Com.id !== undefined) {
      this.subscribeToSaveResponse(this.bp2ComService.update(bp2Com));
    } else {
      this.subscribeToSaveResponse(this.bp2ComService.create(bp2Com));
    }
  }

  private createFromForm(): IBp2Com {
    return {
      ...new Bp2Com(),
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBp2Com>>): void {
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
