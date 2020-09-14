import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBp1His, Bp1His } from 'app/shared/model/bp-1-his.model';
import { Bp1HisService } from './bp-1-his.service';

@Component({
  selector: 'jhi-bp-1-his-update',
  templateUrl: './bp-1-his-update.component.html',
})
export class Bp1HisUpdateComponent implements OnInit {
  isSaving = false;
  dcoDp: any;
  dateArreteDp: any;

  editForm = this.fb.group({
    id: [],
    dco: [],
    age: [],
    dev: [],
    ncp: [],
    ope: [],
    lib: [],
    mon: [],
    sen: [],
    pie: [],
    ncc: [],
    uti: [],
    utf: [],
    nat: [],
    nomFic: [],
    dateArrete: [],
  });

  constructor(protected bp1HisService: Bp1HisService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bp1His }) => {
      this.updateForm(bp1His);
    });
  }

  updateForm(bp1His: IBp1His): void {
    this.editForm.patchValue({
      id: bp1His.id,
      dco: bp1His.dco,
      age: bp1His.age,
      dev: bp1His.dev,
      ncp: bp1His.ncp,
      ope: bp1His.ope,
      lib: bp1His.lib,
      mon: bp1His.mon,
      sen: bp1His.sen,
      pie: bp1His.pie,
      ncc: bp1His.ncc,
      uti: bp1His.uti,
      utf: bp1His.utf,
      nat: bp1His.nat,
      nomFic: bp1His.nomFic,
      dateArrete: bp1His.dateArrete,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bp1His = this.createFromForm();
    if (bp1His.id !== undefined) {
      this.subscribeToSaveResponse(this.bp1HisService.update(bp1His));
    } else {
      this.subscribeToSaveResponse(this.bp1HisService.create(bp1His));
    }
  }

  private createFromForm(): IBp1His {
    return {
      ...new Bp1His(),
      id: this.editForm.get(['id'])!.value,
      dco: this.editForm.get(['dco'])!.value,
      age: this.editForm.get(['age'])!.value,
      dev: this.editForm.get(['dev'])!.value,
      ncp: this.editForm.get(['ncp'])!.value,
      ope: this.editForm.get(['ope'])!.value,
      lib: this.editForm.get(['lib'])!.value,
      mon: this.editForm.get(['mon'])!.value,
      sen: this.editForm.get(['sen'])!.value,
      pie: this.editForm.get(['pie'])!.value,
      ncc: this.editForm.get(['ncc'])!.value,
      uti: this.editForm.get(['uti'])!.value,
      utf: this.editForm.get(['utf'])!.value,
      nat: this.editForm.get(['nat'])!.value,
      nomFic: this.editForm.get(['nomFic'])!.value,
      dateArrete: this.editForm.get(['dateArrete'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBp1His>>): void {
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
